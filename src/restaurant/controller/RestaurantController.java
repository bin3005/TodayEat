package restaurant.controller;

import java.util.ArrayList;
import java.util.Scanner;

import restaurant.model.biz.RestaurantBizImpl;
import restaurant.model.vo.Menu;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.Review;


public class RestaurantController {
	RestaurantBizImpl biz = new RestaurantBizImpl();
	Scanner in = new Scanner(System.in);
	
	public ArrayList<Restaurant> selectAllRestaurant() {// dao의 저장된 레스토랑 arraylist 반환
		ArrayList<Restaurant> list = biz.selectAllRestaurant();
		
		return list;
	}
	public int insertRestaurant(Restaurant rest) {
		ArrayList<Restaurant> list = biz.selectAllRestaurant();
		for (int i = 0; i < list.size(); i++) {
			if (rest.getrName().equals(list.get(i).getrName())) {//동일 식당 존재
				return -1;
			}
		}
		
		
		
		int res =biz.insertRestaurant(rest);
		return res;
	}
	public int deleteRestaurant(String rName) {
		int res = biz.deleteRestaurant(rName);
		return res;
	}
	
	
	public int insertMenu(Menu m) {
		int res = biz.insertMenu(m);
		return res;
	}
	public int deleteMenu(Menu m) {
		int res = biz.deleteMenu(m);
		return res;
	}
	public int updateMenu(Menu m) {
		int res = biz.updateMenu(m);
		return res;
	}
	public ArrayList<Restaurant> findMenu(String mName) { // 이름으로검색 3
		mName = "%"+mName+"%";
		ArrayList<Restaurant> findList = biz.findMenu(mName);
		return findList;
	}
	public Restaurant randomMenu() {// 랜덤메뉴를 dao에게 리스트로전달 받고 view에게 넘김
		Restaurant r = biz.randomMenu();
		return r;

	}
	
	
	public int insertReview(Review r) {
		int res = biz.insertReview(r);
		return res;
	}
	/*
	
	
	//사용자에게 리뷰정보를 입력받아 dao에게 넘겨줌
	
	
	public String bestRestaurant() { //베스트 식당을 dao에게 전달받아 view에게 넘겨줌
		return rd.bestRestaurant();
	}
	

	
	
	
	
	*/

}
