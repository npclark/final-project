/**
 * Holds Edges
 * @author Noah Clark <npclark@dmacc.edu>
 * @version 1.3
 * @since 1.1
 */
public class Graph {
	/**
	 * Matrix that holds all Edges in Graph
	 */
	private Edge connections [][];
	/**
	 * Number of rooms in Map
	 */
	private int numRooms;
	/**
	 * Constructor for Graphs
	 * @param n The total number of rooms
	 */
	public Graph(int n) {
		this.numRooms = n;
		connections = new Edge[numRooms][numRooms];
	}
	
	public Edge[][] getConnections() {
		return connections;
	}
	public void setConnections(Edge[][] connections) {
		this.connections = connections;
	}
	public int getNumRooms(){
		return numRooms;
	}
	public void setNumRooms(int numberOfConnections) {
		this.numRooms= numberOfConnections;
	}

	/**
	 * Adds a new Edge to the Graph without a puzzle in the way
	 * @param start starting room
	 * @param dest destination room
	 */
	public void addEdge(Room start, Room dest) {
		Edge newEdge = new Edge(start, dest);
		connections[start.getRoomID()][dest.getRoomID()] = newEdge;
		connections[dest.getRoomID()][start.getRoomID()] = newEdge;
	}
	/**
	 * Adds a new Edge to the Graph with a puzzle in the way
	 * @param start starting room
	 * @param dest destination room
	 * @param open this will always be false
	 */
	public void addEdge(Room start, Room dest, boolean open) {
		Edge newEdge = new Edge(start, dest, open);
		connections[start.getRoomID()][dest.getRoomID()] = newEdge;
		connections[dest.getRoomID()][start.getRoomID()] = newEdge;
	}
	/**
	 * Determines if pathway is unlocked
	 * @param start starting room
	 * @param dest destination room
	 * @return if unlocked returns true, otherwise returns false
	 */
	public boolean unlocked(Room start, Room dest) {
		return connections[start.getRoomID()][dest.getRoomID()].isOpen();
	}
}
