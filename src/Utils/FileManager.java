package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import Controller.ShopController;
import dao.CartDAO;
import dao.ItemDAO;
import dao.UserDAO;
import vo.Cart;
import vo.Item;
import vo.User;

public class FileManager {
  // cart.txt
	// user.txt
	// item.txt
	final String PATH;
	public FileManager(){
		PATH = System.getProperty("user.dir")+"\\src\\";
		System.out.println(PATH);
	}
	
	public void fileSave(UserDAO userDAO, ItemDAO itemDAO, CartDAO cartDAO) {
		userFileSave(userDAO);
		itemFileSave(itemDAO);
		cartFileSave(cartDAO);
	}
	
	//장바구니 리스트 저장
	private void cartFileSave(CartDAO cartDAO) {
		String fileName = "cartList01.txt";
		
		File file = new File(PATH+fileName);
		if(cartDAO.getCartList().size() == 0) {
			System.out.println("장바구니 정보가 없습니다.");
			return;
		}
		
		try (FileWriter fw = new FileWriter(file)){
			String data = "";
			for(Cart c : cartDAO.getCartList()) {
				data += c +"\n";
			}
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("장바구니 정보가 저장되었습니다.");
	}
	
	//아이템 리스트 저장
	private void itemFileSave(ItemDAO itemDAO) {
		String fileName = "itemList01.txt";
		
		File file = new File(PATH+fileName);
		
		if(itemDAO.getItemListSize() == 0) {
			System.out.println("아이템 정보가 없습니다.");
			return;
		}
		
		try (FileWriter fw = new FileWriter(file)){
			String data = "";
			for(Item i : itemDAO.getItemList()) {
				data += i +"\n";
			}
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("아이템 정보가 저장되었습니다.");
	}
	
	//유저 정보 저장
	private void userFileSave(UserDAO userDAO) {
		String fileName = "userList01.txt";
		
		File file = new File(PATH+fileName);
		if(userDAO.getUserList().size() == 1) {
			System.out.println("유저 정보가 없습니다.");
			return;
		}
		
		try (FileWriter fw = new FileWriter(file)){
			String data = "";
			for(User u : userDAO.getUserList()) {
				data += u +"\n";
			}
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("유저 정보가 저장되었습니다.");
	}
	
	public void fileLoad(UserDAO userDAO, ItemDAO itemDAO, CartDAO cartDAO) {
		userFileLoad(userDAO);
		itemFileLoad(itemDAO);
		cartFileLoad(cartDAO);
	}
	//아이템 리스트 로드
	private void userFileLoad(UserDAO userDAO) {
		String fileName = "userList01.txt";
		
		File file = new File(PATH+fileName);
		int cnt =0;
		try (FileReader fr = new FileReader(file)){
			BufferedReader br = new BufferedReader(fr);
			userDAO.reset();
			String nextLine = "";
			while((nextLine = br.readLine()) != null) {
				cnt++;
				userDAO.parseData(nextLine.split("/"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(cnt +"건의 유저 정보가 로드되었습니다.");
	}
	
	//아이템 리스트 로드
	private void itemFileLoad(ItemDAO itemDAO) {
		String fileName = "itemList01.txt";
		
		File file = new File(PATH+fileName);
		int cnt =0;
		try (FileReader fr = new FileReader(file)){
			BufferedReader br = new BufferedReader(fr);
			itemDAO.reset();
			String nextLine = "";
			while((nextLine = br.readLine()) != null) {
				cnt++;
				itemDAO.parseData(nextLine.split("/"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(cnt +"건의 아이템 정보가 로드되었습니다.");
	}
	//장바구니 리스트 로드
	private void cartFileLoad(CartDAO cartDAO) {
		String fileName = "cartList01.txt";
		
		File file = new File(PATH+fileName);
		int cnt =0;
		try (FileReader fr = new FileReader(file)){
			BufferedReader br = new BufferedReader(fr);
			cartDAO.reset();
			String nextLine = "";
			while((nextLine = br.readLine()) != null) {
				cnt++;
				cartDAO.parseData(nextLine.split("/"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(cnt +"건의 장바구니 정보가 로드되었습니다.");
	}
	
	
}
