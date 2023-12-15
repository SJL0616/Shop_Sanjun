package Controller;

import Utils.FileManager;
import Utils.InputManger;
import dao.CartDAO;
import dao.ItemDAO;
import dao.UserDAO;
import vo.Cart;
import vo.Item;
import vo.User;

public class ShopController {

	private UserDAO userDAO;
	private ItemDAO itemDAO;
	private CartDAO cartDAO;
	private FileManager fileManager;

	private String log;

	public ShopController() {

	}

	private void init() {
		log = null;
		userDAO = new UserDAO();
		itemDAO = new ItemDAO();
		cartDAO = new CartDAO();
		fileManager = new FileManager();
	}

	public void run() {
		init();
		while (true) {
			int maxMenu = 0;
			if (log == null) {
				System.out.println("[1] 로그인 [2] 회원가입 [0]종료");
				maxMenu = 2;
			} else {
				if (log.equals("admin")) {
					System.out.println("[관리자 메뉴]");
					System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [5.파일 저장] [6.파일 로드] [0.로그아웃] ");

					maxMenu = 6;
				} else {
					System.out.println("[사용자 메뉴]");
					System.out.println("[1] 쇼핑 [2] 주문확인 [3] 탈퇴(주문정보) [0] 로그아웃");
					maxMenu = 3;
				}

			}

			int sel = InputManger.getIntVal(0, maxMenu);
			if (sel == 0 && log == null) {
				System.out.println("종료합니다");
				break;
			}

			if (log == null) {
				if (sel == 1) {
					log = userDAO.login();
				} else if (sel == 2) {
					log = userDAO.join();
				}
			} else {
				if (log.equals("admin")) {
					if (sel == 1) {
						itemDAO.itemManage();
					} else if (sel == 2) {
						itemDAO.categoryManage();
					} else if (sel == 3) {
						cartDAO.cartManage();
					} else if (sel == 4) {
						userDAO.userManage(cartDAO);
					} else if (sel == 5) {
						fileManager.fileSave(userDAO, itemDAO, cartDAO);
					} else if (sel == 6) {
						fileManager.fileLoad(userDAO, itemDAO, cartDAO);
					} else if (sel == 0) {
						log = null;
					}
				} else {
					if (sel == 1) {
						cartDAO.shopping(log, itemDAO);
					} else if (sel == 2) {
						cartDAO.myCart(log);
					} else if (sel == 3) {
						userDAO.resign(log, cartDAO);
						log = null;
					} else if (sel == 0) {
						log = null;
					}
				}

			}
		}
	}

}
