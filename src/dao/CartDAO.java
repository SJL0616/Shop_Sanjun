package dao;

import java.util.ArrayList;

import Utils.InputManger;
import vo.Cart;
import vo.Item;

public class CartDAO {
	private ArrayList<Cart> cartList;
	
	public CartDAO() {
		reset();
		//cartList.add(new Cart("aaa", "칸초"));
		//cartList.add(new Cart("bbb", "귤"));
		//cartList.add(new Cart("ccc", "사과"));
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Cart> getCartList() {
		return cartList;
	}
	public void reset() {
		cartList = new ArrayList<>();
		
	}
	public void parseData(String[] data) {
		if(data != null && data.length > 0) {
			cartList.add(new Cart(data[0], data[1]));
		}
	}
	
	//카트리스트 전체 출력
	private void printCartList() {
		int idx = 0;
		for (Cart c : cartList) {
		    System.out.println("(" + idx + ") " + c.getUserId() +" | "+c.getItemName() );
			idx++;
		}
		
	}
	
	//유저 아이디를 받아서 내 장바구니 출력
	private void printCartListById(String id) {
		int idx = 0;
		for (Cart c : cartList) {
			if(c.getUserId().equals(id)) {
				idx++;
				System.out.println("(" + idx + ") " + c.getUserId() +" | "+c.getItemName() );
			}
		}
		if( idx == 0){
			System.out.println(id+"회원님의 장바구니 정보가 없습니다.");
		}
	}
	
	public void cartManage() {
		while (true) {
			if (cartList.size() == 0) {
				System.out.println("장바구니 정보가 없습니다.");
				break;
			}
			printCartList();
			System.out.println("[1] 장바구니 삭제 [0]뒤로가기");
			int sel = InputManger.getIntVal(0, 1);
			if (sel == 0) {
				break;
			} else if (sel == 1) {
				System.out.print("삭제할 장바구니 번호 >>");
				sel = InputManger.getIntVal(1, cartList.size())-1;
				Cart c = null;
				if ((c = cartList.get(sel)) == null) {
					System.out.println("찾을 수 없는 장바구니입니다.");
					continue;
				}
				System.out.println((sel+1)+"번 장바구니 : ["+ c.getUserId()+"|"+ c.getItemName() + "]정보가 삭제되었습니다.");
				cartList.remove(c);
				continue;
			}
			break;
		}
	}
	
	//유저 아이디를 받아서 모든 장바구니를 삭제하는 메서드
	public void removeCartByUserId(String id) {
		if (cartList.size() == 0)
			return;
		ArrayList<Cart> temp = new ArrayList<Cart>();

		for (Cart c : cartList) {
			if (c.getUserId().equals(id)) {
				temp.add(c);
			}
		}
		if (temp.size() > 0) {
			for (Cart c : temp) {
				System.out.println("장바구니 : [" + c.getUserId() + "|" + c.getItemName() + "]정보가 삭제되었습니다.");
				cartList.remove(c);
			}
		} else {
			System.out.println("삭제할 장바구니 정보가 없습니다ㅣ.");
		}
	}
	
	private void addToCartList(Cart c) {
		cartList.add(c);
	}
	
	//쇼핑 사용자 메뉴
	public void shopping(String log, ItemDAO itemDAO) {
		while (true) {
			if (itemDAO.isListEmpty()) {
				break;
			}
			itemDAO.printCatList();
			System.out.println("카테고리 번호 (0 뒤로가기)>> ");
			int sel = InputManger.getIntVal(0, itemDAO.getCatListSize());

			if (sel == 0) {
				break;
			} else {
				String catName = itemDAO.getCatByIdx(sel - 1);
				ArrayList<Item> temp = itemDAO.printItemListByCat(catName);
				if (temp.size() == 0) {
					continue;
				}
				System.out.println("주문 아이템 번호 0(뒤로가기)");
				sel = InputManger.getIntVal(0, temp.size());
				if (sel == 0) {
					continue;
				}
				addToCartList(new Cart(log, temp.get(sel - 1).getName()));
				System.out.println("[ " + temp.get(sel - 1).getName() + " ] 주문이 완료되었습니다.");

			}
		}
	}
	
	public void myCart(String log) {
		printCartListById(log);
	}
	
}
