package dao;

import java.util.ArrayList;

import Utils.InputManger;
import vo.Cart;
import vo.User;

public class UserDAO {
	private ArrayList<User> userList;
	
	public ArrayList<User> getUserList() {
		return userList;
	}


	public UserDAO(){
		reset();
		if(userList.size() == 0) addUser(new User("admin", "1111", "관리자"));
		//addUser(new User("admin", "1111", "관리자"));
		//addUser(new User("aaa", "2222", "이춘식"));
		//addUser(new User("bbb", "3333", "광훈이"));
		//addUser(new User("ccc", "4444", "강민수"));
	}
	
	public void reset() {
		userList = new ArrayList<>();
	}
	
	public void parseData(String[] data) {
		if(data != null && data.length > 0) {
			userList.add(new User(data[0], data[1], data[2]));
		}
	}
	
	//로그인 
	public String login() {
		if(isListEmpty()) return null;
		System.out.println("[  로그인  ]");
		while(true) {
			String id = InputManger.getStrVal("[로그인] 아이디 입력");
			String pw = InputManger.getStrVal("[로그인] 비밀번호 입력");
			User u = null;
			if ((u = getUserByIdAndPw(id, pw)) == null) {
				System.out.println("아이디를 찾을 수 없습니다.");
				continue;
			}
			System.out.println(u.getUserId() + "님 로그인이 완료되었습니다.");
			return u.getUserId();
		}
	}
	//리스트 길이가 0 이면 false 반환 메서드
	private boolean isListEmpty() {
		if(userList.size() == 0) {
			System.out.println("유저 정보가 없습니다.");
			return true;
		}
		return false;
	}
	//로그인에 사용하는 메서드
	private User getUserByIdAndPw(String id, String pw) {
		for (User u : userList) {
			if (u.getUserId().equals(id)) {
				if(!pw.isEmpty() && !u.getUserPw().equals(pw) ) {
					continue;
				}
				return u;
			}
		}
		return null;
	}
	private User getUserByIdAndPw(String id) {
		return getUserByIdAndPw(id,"");
	}
	
	
	//회원가입 
	public String join() {
		System.out.println("[  회원 가입  ]");
		while (true) {
			String id = InputManger.getStrVal("아이디 입력");
			User u = null;
			if ((u = getUserByIdAndPw(id)) != null) {
				System.out.println("아이디 중복");
				continue;
			}
			String pw = InputManger.getStrVal("비밀번호 입력");
			String name = InputManger.getStrVal("이름 입력");
			addUser(new User(id, pw, name));
			System.out.println(id + "님 회원가입이 완료되었습니다.");
			return id;
		}
	}
	//리스트에 추가 메서드
	private void addUser(User u) {
		userList.add(u);
	}
	
	private void printUserList() {
		int idx = 1;
		for (User u : userList) {
		    System.out.println("(" + idx + ") " + u.getUserId() +" "+ u.getUserName()+" "+ u.getUserPw() );
			idx++;
		}
	}
	
	//유저 관리 관리자메뉴
	public void userManage(CartDAO cartDAO) {
		while (true) {
			if (userList.size() == 0) {
				System.out.println("유저 정보가 없습니다.");
				break;
			}
			printUserList();
			System.out.println("[1] 유저 삭제 [0]뒤로가기");
			int sel = InputManger.getIntVal(0, 1);
			if (sel == 0) {
				break;
			} else if (sel == 1) {
				System.out.print("삭제할 유저 번호(UI에 표시된 번호) >>");
				sel = InputManger.getIntVal(1, userList.size())-1;
				User u = null;
				if ((u = userList.get(sel)) == null) {
					System.out.println("유저를 찾을 수 없습니다.");
					continue;
				}
				if(u.getUserId().equals("admin")) {
					System.out.println("관리자 정보를 삭제할 수 없습니다.");
					continue;
				}
				cartDAO.removeCartByUserId(u.getUserId());
				System.out.println("유저 : ["+ u.getUserId()+"|"+ u.getUserName() + "]정보가 삭제되었습니다.");
				userList.remove(u);
				continue;
			}
			break;
		}
	}
	
	//사용자 메뉴 || 유저 id를 받아서 유저 객체 반환 
	
	public void resign(String log, CartDAO cartDAO) {
		System.out.println("정말 탈퇴하시겠습니까?");
		System.out.println("[1] 예 [0]뒤로가기");
		int sel = InputManger.getIntVal(0, 1);
		if (sel == 0) {
			return;
		} 
		User u  = getUserByIdAndPw(log);
		cartDAO.removeCartByUserId(u.getUserId());
		System.out.println("유저 : ["+ u.getUserId()+"|"+ u.getUserName() + "]정보가 삭제되었습니다.");
		userList.remove(u);
	}
	
}
