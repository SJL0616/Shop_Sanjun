package vo;

public class Cart {
	private String userId; // 구입한 유저 id
	private String itemName; // 구입한 아이템
	
	
	public Cart(String userId, String itemName) {
		super();
		this.userId = userId;
		this.itemName = itemName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userId +"/"+itemName;
	}
	
}
