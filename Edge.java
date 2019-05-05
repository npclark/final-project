/**
 * Connections between the different Rooms
 * @author Noah Clark <npclark@dmacc.edu>
 * @version 1.0
 * @since 1.1
 */
public class Edge {
	/**
	 * Determines if Edge is open
	 */
	private boolean open;
	/**
	 * Room the Edge starts from
	 */
	private Room start;
	/**
	 * Room the Edge goes to
	 */
	private Room dest;
	
	/**
	 * Constructor for Edge that is open
	 * @param s Room the Edge starts from
	 * @param d Room the Edge goes to
	 */
	public Edge(Room s, Room d) {
		start = s;
		dest = d;
		open = true;
	}
	/**
	 * Constructor for Edge that is not open
	 * @param s Room the Edge starts from
	 * @param d Room the Edge goes to
	 * @param open This will always be false
	 */
	public Edge(Room s, Room d, boolean open) {
		start = s;
		dest = d;
		this.open = open;
	}

	
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Room getStart() {
		return start;
	}

	public void setStart(Room start) {
		this.start = start;
	}

	public Room getDest() {
		return dest;
	}

	public void setDest(Room dest) {
		this.dest = dest;
	}
	
}
