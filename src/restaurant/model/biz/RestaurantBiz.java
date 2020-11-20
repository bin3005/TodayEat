package restaurant.model.biz;

import java.util.ArrayList;

import restaurant.model.vo.Menu;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.Review;

public interface RestaurantBiz {
	public ArrayList<Restaurant> selectAllRestaurant();
	public int insertRestaurant(Restaurant rest);
	public int deleteRestaurant(String rName);
	
	public int insertMenu(Menu m);
	public int deleteMenu(Menu m);
	public int updateMenu(Menu m);
	public ArrayList<Restaurant> findMenu(String mName);
	public Restaurant randomMenu();
	
	public int insertReview(Review r);
}
