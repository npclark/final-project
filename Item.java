/**
 * Items are used to solve puzzles
 * @author Noah Clark <npclark@dmacc.edu>
 * @version 1.0
 * @since 1.0
 */
public class Item {
	/**
	 * The item's index for puzzle solving and sorting
	 */
	private int itemID;
	/**
	 * Name of the item
	 */
	private String itemName;
	/**
	 * Description of item shown when picked up
	 */
	public String itemDesc;
	
	/**
	 * Constructor for the item
	 * @param name Display name for item
	 * @param ID Unique identifier for the item
	 * @param desc Description of the item when picked up
	 */
	public Item(String name, int ID, String desc) {
		this.itemName = name;
		this.itemID = ID;
		this.itemDesc = desc;
	}

	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
}
