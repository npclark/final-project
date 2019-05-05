/**
 * This is the basic template for Rooms in the Dungeon Crawler
 * @author Noah Clark <npclark@dmacc.edu>
 * @version 1.3
 * @since 1.1
 */
public class Room {
	/**
	 * Name of the Room ex. Entrance, Soul Alter, etc.
	 */
	private String roomName;
	/**
	 * Default description of room
	 */
	private String roomDesc;
	/**
	 * Description of Room when searched
	 */
	private String roomSearchDesc;
	/**
	 * Description of Room when examined
	 */
	private String roomPuzzleDesc;
	/**
	 * Unique identifier for the room
	 */
	private int roomID;
	/**
	 * The room's puzzle, if it has any
	 */
	private Puzzle roomPuzzle = null;
	/**
	 * Item in room, if any
	 */
	private Item roomItem = null;
	/**
	 * Determines if player can move through room
	 */
	private boolean isSolved;
	
	/**
	 * Default constructor for Room
	 */
	public Room() {
		
	}
	/**
	 * Constructor for Room without puzzle or item
	 * @param name Name of the Room
	 * @param ID Unique identifier for the Room
	 * @param desc Description of the Room
	 */
	public Room(String name, int ID, String desc) {
		roomName = name;
		roomID = ID;
		roomItem = null;
		
		// Since there isn't a puzzle, there is only 1 description needed
		roomDesc = desc;
		roomPuzzleDesc = "The path forward is open";
		roomSearchDesc = "There's nothing here...";
	}
	
	/**
	 * Constructor for Room without puzzle and with item
	 * @param name Name of the Room
	 * @param ID Unique identifier for the Room
	 * @param desc Description of the Room
	 * @param item Item object in the Room
	 */
	public Room(String name, int ID, String desc, Item item) {
		roomName = name;
		roomID = ID;
		roomItem = item;
		
		// Since there isn't a puzzle, there is only 1 description needed
		roomDesc = desc;
		roomPuzzleDesc = "The path forward is open";
		roomSearchDesc = item.getItemDesc();
	}
	
	/**
	 * Constructor for Room with puzzle and without item
	 * @param name Name of the Room
	 * @param ID Unique identifier for the Room
	 * @param startDesc Description of Room before puzzle is solved
	 * @param finishDesc Description of Room after puzzle is solved
	 * @param puzzle The Room's puzzle
	 */
	public Room(String name, int ID, String desc, Puzzle puzzle) {
		roomName = name;
		roomID = ID;
		roomPuzzle = puzzle;
		
		// Since there's a puzzle, there are two descriptions
		roomDesc = desc;
		roomPuzzleDesc = puzzle.getPuzzleDesc();
		roomSearchDesc = "There's nothing here...";
	}
	
	/**
	 * Constructor for room with puzzle and item
	 * @param name Name of the Room
	 * @param ID Unique identifier for Room
	 * @param startDesc Description of Room before puzzle is solved
	 * @param finishDesc Description of Room after puzzle is solved
	 * @param puzzle The Room's puzzle
	 * @param item Item object in room
	 */
	public Room(String name, int ID, String desc, Puzzle puzzle, Item item) {
		roomName = name;
		roomID = ID;
		roomPuzzle = puzzle;
		roomItem = item;
		
		// Since there's a puzzle, there are two descriptions
		roomDesc = desc;
		roomPuzzleDesc = puzzle.getPuzzleDesc();
		roomSearchDesc = item.getItemDesc();
	}

	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}
	public String getRoomSearchDesc() {
		return roomSearchDesc;
	}
	public void setRoomSearchDesc(String roomSearchDesc) {
		this.roomSearchDesc = roomSearchDesc;
	}
	public String getRoomPuzzleDesc() {
		return roomPuzzleDesc;
	}
	public void setRoomPuzzleDesc(String roomPuzzleDesc) {
		this.roomPuzzleDesc = roomPuzzleDesc;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public Puzzle getRoomPuzzle() {
		return roomPuzzle;
	}

	public void setRoomPuzzle(Puzzle roomPuzzle) {
		this.roomPuzzle = roomPuzzle;
	}

	public Item getRoomItem() {
		return roomItem;
	}

	public void setRoomItem(Item roomItem) {
		this.roomItem = roomItem;
	}

	public boolean getSolved() {
		return isSolved;
	}

	public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
	}

	
	@Override
	public String toString() {
		return roomName; 
	}

	
}