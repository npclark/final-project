//import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Driver class for the program
 * @author Noah Clark <npclark@dmacc.edu>
 * @version 1.7
 * @since 1.1
 */
public class DungeonOfMildDiscomfort {
	// Holds total number of items in inventory
	public static int numberOfItems = 0;
	
	// Displays the player's inventory
	public static JLabel inventoryList;
	
	// Destination room
	public static Room destination = null;
	
	// Displays currently accessible rooms
	public static JComboBox<Room> selectableRooms;
	
	// Gives the name of the current room
	public static JLabel roomTitle;
	
	// Gives a description of what's going on
	public static JLabel roomDesc;
	
	public static void main(String[] args) {
		// Holds the player's current items
		Item [] inventory;
		final int MAX_ITEMS = 5;
		inventory = new Item[MAX_ITEMS];
		
		// Holds names of player's items for display
		String[] inventoryName;
		inventoryName = new String[MAX_ITEMS];
		
		// Graph object that holds Edges
		final int NUMBER_OF_ROOMS = 9;
		Graph map = new Graph(NUMBER_OF_ROOMS);
		
		// Set up connections for easy referral
		Edge connections [][] = map.getConnections();
		
		// Current room the player is in
		Room current = new Room("Void", -1, "This room does not exist");
		
		// Create puzzles
		Puzzle lock1 = new Puzzle(1, "<html>The stone door rolls away as you slide the key into its socket</html>");
		Puzzle lock2 = new Puzzle(2, "<html>As the door swings inward, a voice says 'turn... back...'</html>");
		Puzzle lock3 = new Puzzle(3, "<html>As you bring the key up to the lock, both key and door vanish into dust</html>"); 
		Puzzle lock4 = new Puzzle(4, "<html>The Jade Key dispells the green sheen on the door, allowing you to walk through</html>");
		Puzzle lock5 = new Puzzle(5, "<html>The wall bursts into flames, and beyond you see a glimmer of gold</html>");
				
		// Create items
		Item key1 = new Item("Stone Key", 1, "<html>A round stone object lies on the ground, you pick it up</html>");
		Item key2 = new Item("Skull Key", 2, "<html>A key is tucked behind one of the barrels. It's carved with a smiling skull</html>");
		Item key3 = new Item("Sapphire Key", 3, "<html>Lying in the center of the room is a key sculpted from a blue gem</html>");
		Item key4 = new Item("Jade Key", 4, "<html>At the bottom of the pool is a green key, it seems to be glowing</html>");
		Item key5 = new Item("Ruby Key", 5, "<html>The key's handle is shaped like a flame, and it glows softly</html>");
				
		// Create rooms
		Room room1 = new Room ("Entrance", 0, "<html>A large stone door stands in front of you, naming this place Onhax's" + 
			" Tomb of Mild Discomfort</html>", lock1, key1);
		Room room2 = new Room ("Foyer", 1, "<html>A door carved in the shape of a skull stands before you, and another do" +
			"orway is off to you right</html>", lock2);
		Room room3 = new Room ("Storage Room", 2, "<html>There are a lot of musty old boxes, covered in webs</html>", key2);
		Room room4 = new Room ("Central Room", 3, "<html>Three doors are placed around a circular door. The far door has " + 
			"a green sheen</html>", lock4, key3);
		Room room5 = new Room ("Ruby Altar", 4, "<html>The room is cast in a warm red light, there's a key on a podium in" +
			" the center</html>", key5);
		Room room6 = new Room ("Armory", 5, "<html>Myraid weapons and armor lie strewn on the ground. A blue door stands " +
			"on the opposite end.</html>", lock3);
		Room room7 = new Room ("Ruined Chamber", 6, "<html>This chamber is falling apart with a pool of water at the end." +
			" It has a sickly green glow</html>", key4);
		Room room8 = new Room ("Antechamber", 7, "<html>You see a bas relief of the Great Onhax, there is a flame with a " +
			"hollow recess</html>", lock5);
		Room roomWin = new Room("Onhax's Hoard", 8, "<html>Sitting before you is a sea of gold and jewels. Onhax the Anno" +
			"ying's treasures are yours! You Win!<html>");
				
		// Create Edges
		map.addEdge(room1, room2, false);
		map.addEdge(room2, room3);
		map.addEdge(room2, room4, false);
		map.addEdge(room4, room5);
		map.addEdge(room4, room6);
		map.addEdge(room6, room7, false);
		map.addEdge(room4, room8, false);
		map.addEdge(room8, roomWin, false);
		
		// Create JFrame for game
		JFrame frame = new JFrame("Tomb of Mild Discomfort");
		frame.setSize(600, 300);
		
		//Create JPanel for layout
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		frame.add(panel);
		
		// Create JLabels
		final JLabel NEARBY_ROOMS = new JLabel("Nearby Rooms", SwingConstants.CENTER);
		
		final JLabel INVENTORY_LABEL = new JLabel("Inventory", SwingConstants.CENTER);
		
		roomTitle = new JLabel();
		
		roomDesc = new JLabel();
		
		inventoryList = new JLabel();
				
		// Create JComboBox
		selectableRooms = new JComboBox<Room>();
		
		// Add action listener to JComboBox
		selectableRooms.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				Room selected = (Room) selectableRooms.getSelectedItem();
				destination = selected;
			}
		});
		
		// Create JButtons
		JButton searchButton = new JButton("Search Room");
		
		JButton examineLockButton = new JButton("Examine Object");
		
		JButton gotoRoomButton = new JButton("Go to room");
		
		// Add action listeners to JButtons
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomDesc.setText(current.getRoomSearchDesc());
				if(current.getRoomItem() != null) {
					inventory[numberOfItems] = current.getRoomItem();
					current.setRoomItem(null);
					numberOfItems++;
					BubbleSort(inventory, inventoryName);
					String input = "<html>";
					for(int i = 0; i < numberOfItems; i++) {
						input += inventoryName[i] + "<br/>";
					}
					input += "</html>";
					inventoryList.setText(input);
				}
			}
		});
		examineLockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current.getRoomPuzzle() == null) {
					roomDesc.setText(current.getRoomPuzzleDesc());
				} else {
					roomDesc.setText(IsSolved(current, inventory, map));
				}
			}
		});
		gotoRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destination = (Room) selectableRooms.getSelectedItem();
				if(connections[current.getRoomID()][destination.getRoomID()].isOpen()) {
					Room temp = new Room();
					gotoRoom(temp, current);
					gotoRoom(current, destination);
					roomDesc.setText(current.getRoomDesc());
					roomTitle.setText(current.getRoomName());
					selectableRooms.removeAllItems();
					Room[] accessibleRooms = accessible(current, map);
					for(int i = 0; i < accessibleRooms.length; i++)
						selectableRooms.addItem(accessibleRooms[i]);
					gotoRoom(destination, temp);
					selectableRooms.addItem(destination);
				} else {
					roomDesc.setText("The way is locked");
				}
			}
		});
		
		
		
		// Set up current room
		gotoRoom(current, room1);
		roomTitle = new JLabel(current.getRoomName(), SwingConstants.CENTER);
		roomDesc = new JLabel(current.getRoomDesc());
		selectableRooms = new JComboBox<Room>(accessible(current, map));
		destination = (Room) selectableRooms.getSelectedItem();
		
		// Add elements to panel
		panel.add(NEARBY_ROOMS);
		panel.add(roomTitle);
		panel.add(INVENTORY_LABEL);
		panel.add(selectableRooms);
		panel.add(roomDesc);
		panel.add(inventoryList);
		panel.add(gotoRoomButton);
		panel.add(examineLockButton);
		panel.add(searchButton);
		
		inventoryList.setVisible(true);
		// Launch JFrame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Bubble sort for inventory
	 * @param arrayData The array that is being sorted
	 * @param arrayString the array being sorted for display purposes
	 */
	public static void BubbleSort(Item arrayData[], String arrayString[]) {
		int n = numberOfItems;
			for(int i = 0; i < n-1; i++) 
				for(int j = 0; j < n-i-1; j++) 
						if(arrayData[j].getItemID() > arrayData[j+1].getItemID()) {
							Item temp = arrayData[j];
							arrayData[j] = arrayData[j+1];
							arrayData[j+1] = temp;
						}
		for(int i = 0; i < n; i++) 
			if(arrayData[i] != null)
				arrayString[i] = arrayData[i].getItemName();
	}
	
	/**
	 * Determines if puzzle can be unlocked
	 * @param current Room player is currently in
	 * @param dest Room player wants to go to
	 * @param array Player inventory
	 * @param map the map of the dungeon
	 * @return Room's roomPuzzleDesc property if it's solved, otherwise give default answer
	 */
	public static String IsSolved(Room current, Item array[], Graph map) {
		// Default answer
		String output = "You don't have the right item...";
		int n = array.length;
		for(int i = 0; i < n; i++) {
			if(array[i] != null) 
				if(current.getRoomPuzzle().getPuzzleID() == array[i].getItemID()) {
					output = current.getRoomPuzzleDesc();
					// Change Edges' open property
					Edge connections[][] = map.getConnections();
					int numRooms = map.getNumRooms();
					for(int j = 0; j < numRooms; j++)
						if(connections[current.getRoomID()][j] != null || connections[j][current.getRoomID()] != null) {
							if(connections[current.getRoomID()][j].isOpen() == false || connections[j][current.getRoomID()].isOpen() == false) {
								connections[current.getRoomID()][j].setOpen(true);
								connections[j][current.getRoomID()].setOpen(true);
							}
						}
				}
		}
		return output;
	}
	
	/**
	 * Moves player to the desired room
	 * @param current room the player is currently in
	 * @param dest room the player wants to go to
	 */
	public static void gotoRoom(Room current, Room dest) {
		current.setRoomName(dest.getRoomName());
		current.setRoomDesc(dest.getRoomDesc());
		current.setRoomSearchDesc(dest.getRoomSearchDesc());
		current.setRoomPuzzleDesc(dest.getRoomPuzzleDesc());
		current.setRoomID(dest.getRoomID());
		current.setRoomPuzzle(dest.getRoomPuzzle());
		current.setRoomItem(dest.getRoomItem());
		current.setSolved(dest.getSolved());
	}
	
	/**
	 * Updates JComboBox with currently adjacent rooms
	 * @param current Room the player is currently in
	 * @param map Graph object that holds all the connections
	 * @return New array to be put into JComboBox
	 */
	public static Room[] accessible(Room current, Graph map){
		Room[] output;
		int numAdjRooms = 0;
		int numTotalRooms = map.getNumRooms();
		output = new Room[numTotalRooms];
		Edge connections[][] = map.getConnections();
		for(int i = 0; i < numTotalRooms; i++) {
			if(connections[current.getRoomID()][i] != null) {
				output[numAdjRooms] = connections[current.getRoomID()][i].getDest();
				numAdjRooms++;
			}
		}
		return output;
	}
}