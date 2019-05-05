/**
 * Puzzle superclass that provides 
 * @author Noah Clark <npclark@dmacc.edu>
 * @version 1.0
 * @since 1.0
 */
public class Puzzle {
	/**
	 * Unique identifier for the puzzle, compared to itemID to solve
	 */
	private int puzzleID;
	/**
	 * Description of puzzle for display
	 */
	private String puzzleDesc;
	
	/**
	 * Constructor for puzzle
	 * @param ID Unique identifier for puzzle
	 * @param desc Description of puzzle for display
	 */
	public Puzzle(int ID, String desc) {
		puzzleID = ID;
		puzzleDesc = desc;
	}
	
	public int getPuzzleID() {
		return puzzleID;
	}
	public void setPuzzleID(int puzzleID) {
		this.puzzleID = puzzleID;
	}
	public String getPuzzleDesc() {
		return puzzleDesc;
	}
	public void setPuzzleDesc(String puzzleDesc) {
		this.puzzleDesc = puzzleDesc;
	}

	/**
	 * Determines if puzzle is solved or not
	 * @param current Room that the player is currently in
	 * @param puzzle used to get ID to compare to item
	 * @param item used to get ID to compare to puzzle
	 * @return determines if puzzle is solved
	 */
	public boolean Solve(Room current, Puzzle puzzle, Item item) {
		current.setSolved(item.getItemID() == puzzle.getPuzzleID());
		return current.getSolved();
	}
}
