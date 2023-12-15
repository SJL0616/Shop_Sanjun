package dao;

import java.util.ArrayList;

import Utils.InputManger;
import vo.Item;
import vo.User;

public class ItemDAO {
    private ArrayList<Item> itemList;
    private ArrayList<String> catList;
    
    public ArrayList<Item> getItemList() {
		return itemList;
	}
	public int getCatListSize() {
    	return catList.size();
    }
    public int getItemListSize() {
    	return itemList.size();
    }
	public ItemDAO(){
		reset();
		//itemList.add(new Item("사과", 10000, "과일"));
		//itemList.add(new Item("귤", 6500, "과일"));
		//itemList.add(new Item("칸초", 2000, "과자"));
		//itemList.add(new Item("소고기", 2000, "고기"));
	}
	public void reset() {
		itemList = new ArrayList<>();
		catList = new ArrayList<String>();
	}
	public void parseData(String[] data) {
		if(data != null && data.length > 0) {
			itemList.add(new Item(data[0],Integer.parseInt(data[1]),data[2]));
			for (Item item : itemList) {
				if(!catList.contains(item.getCategory())) {
					catList.add(item.getCategory());
				}
			}
		}
		
	}
	
	//카테고리 리스트 길이가 0 이면 false 반환 메서드
	private boolean isCatListEmpty() {
		if(catList.size() == 0) {
			System.out.println("카테고리 정보가 없습니다.");
			return true;
		}
		return false;
	}
	
	
	//아이템 리스트 길이가 0 이면 false 반환 메서드
	public boolean isListEmpty() {
		if(itemList.size() == 0) {
			System.out.println("아이템 정보가 없습니다.");
			return true;
		}
		return false;
	}
	
	//리스트 길이가 0 이면 false 반환 메서드
	private boolean isListEmptyByCat(String cat) {
		for (Item item : itemList) {
			if(item.getCategory().equals(cat)) {
				return false;
			}
		}
		return true;
	}
	
	//카테고리 리스트 출력 
	public void printCatList() {
		int idx = 1;
		for (String cat : catList) {
		    System.out.println("(" + idx + ") " + cat );
			idx++;
		}
	}
	
	//카테고리 이름 받아서 전체 리스트 출력 메서드
	public ArrayList<Item> printItemListByCat(String cat) {
		ArrayList<Item> temp = new ArrayList<Item>();
		int idx = 0;
		for (Item item : itemList) {
			if(item.getCategory().equals(cat)) {
				temp.add(item);
				idx++;
				System.out.println("(" + idx + ") " + item.getName() + " " + item.getPrice() + "원");
			}
			
		}
		if(temp.size()== 0) {
			System.out.println("아이템이 없습니다..");
		}
		return temp;
	}
	
	public String getCatByIdx(int idx) {
		return catList.get(idx);
	}
	
	//아이템 이름을 받아서 아이템을 반환하는 메서드
	//로그인에 사용하는 메서드
	private Item getItemByName(String name) {
		for (Item i : itemList) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	//아이템 추가 메뉴 메서드
	private void addItemMenu(String catName) {
		while(true) {
			String inputStr =  InputManger.getStrVal("추가 아이템 이름 0(뒤로가기)");
			if(inputStr.equals("0")) {
				break;
			}
			Item i = null;
			if((i = getItemByName(inputStr))!= null) {
				System.out.println("중복 아이템 이름입니다.");
				continue;
			}
			System.out.print("추가 아이템 가격 >>");
			int inputPrice = InputManger.getIntVal(1, 99999);
			addAtItemList(new Item(inputStr, inputPrice, catName));
			
			System.out.println(catName+"카테고리에 "+inputStr+"아이템이 추가되었습니다.");
			break;
		}
	}
	
	//아이템 추가
	private void addAtItemList(Item i) {
		itemList.add(i);
	}
	//아이템 추가
	private void removeAtItemList(Item i) {
		itemList.remove(i);
	}
	//아이템 추가 메뉴 메서드
	private void removeItemMenu(String catName) {
		while(true) {
			if(isListEmptyByCat(catName)) {
				System.out.println("카테고리에 등록된 아이템이 없습니다.");
				break;
			}
			String inputStr =  InputManger.getStrVal("삭제 아이템 이름 0(뒤로가기)");
			if(inputStr.equals("0")) {
				break;
			}
			Item i = null;
			if((i = getItemByName(inputStr))== null) {
				System.out.println("없는 아이템 이름입니다.");
				continue;
			}
			removeAtItemList(i);
			System.out.println(catName+"카테고리에 "+inputStr+"아이템이 삭제되었습니다.");
			break;
		}
	}
	
	//아이템 관리 관리자메뉴
	public void itemManage() {
		
		while (true) {
			if (isListEmpty()) {
				break;
			}
			printCatList();
			System.out.println("카테고리 번호 (0 뒤로가기)>> ");
			int sel = InputManger.getIntVal(0, catList.size());

			if (sel == 0) {
				break;
			} else {
				String catName = catList.get(sel - 1);
				printItemListByCat(catName);
				System.out.println("[1] 아이템 추가 [2] 아이템 삭제 [0]뒤로가기");
				sel = InputManger.getIntVal(0, catList.size());
				if (sel == 1) {
					addItemMenu(catName);
					continue;
				} else if (sel == 2) {
					removeItemMenu(catName);
					continue;
				} else {
					continue;
				}
			}
		}
	}
	
	//카테고리 관리 관리자메뉴
	public void categoryManage() {
		while (true) {
			if (isCatListEmpty()) {
				break;
			}
			printCatList();
			System.out.println("[1] 카테고리 추가 [2] 카테고리 삭제 [0]뒤로가기");
			int sel = InputManger.getIntVal(0, catList.size());

			if (sel == 0) {
				break;
			} else if (sel == 1){
				addCategoryMenu();
				continue;
			}else if(sel == 2) {
				removeCategoryMenu();
				continue;
			}
			break;
		}
	}
	//카테고리 추가 관리자 메뉴
	private void addCategoryMenu() {
		while(true) {
			String inputStr =  InputManger.getStrVal("추가 카테고리 이름 0(뒤로가기)");
			if(inputStr.equals("0")) {
				break;
			}
			if(catList.contains(inputStr)) {
				System.out.println("중복 카테고리 이름입니다.");
				continue;
			}
			catList.add(inputStr);
			
			System.out.println(inputStr +"카테고리가 추가되었습니다.");
			break;
		}
	}
	//카테고리 삭제 관리자 메뉴
	private void removeCategoryMenu() {
		while(true) {
			String inputStr =  InputManger.getStrVal("삭제 카테고리 이름 0(뒤로가기)");
			if(inputStr.equals("0")) {
				break;
			}
			if(!catList.contains(inputStr)) {
				System.out.println("없는 카테고리 이름입니다.");
				continue;
			}
			catList.remove(inputStr);
			
			System.out.println(inputStr +"카테고리가 삭제되었습니다.");
			break;
		}
	}
	
	
	
	
}
