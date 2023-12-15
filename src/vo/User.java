package vo;

public class User {
	private String userId;
	private String userPw;
	private String userName;
	
	public User(String userId, String userPw, String userName) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userId +"/"+userPw +"/"+userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
