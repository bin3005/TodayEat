package restaurant.model.biz;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import restaurant.model.dao.RestaurantDao;
import restaurant.model.dao.RestaurantDaoImpl;
import restaurant.model.vo.Menu;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.Review;
public class RestaurantBizImpl implements RestaurantBiz{

	RestaurantDao dao = new RestaurantDaoImpl();
	
	@Override
	public ArrayList<Restaurant> selectAllRestaurant() {
		Connection con = getConnection();
		
		ArrayList<Restaurant> restaurant = dao.selectAllRestaurant(con);
		close(con);
		return restaurant;
	}
	@Override
	public int insertRestaurant(Restaurant rest) {
		Connection con = getConnection();
		
		Restaurant lastRest = dao.getLastRestaurant(con);
		if( lastRest == null) {
			rest.setResNo(1);
		}else {
			rest.setResNo(lastRest.getResNo()+1);
		}
		
		int res = dao.insertRestaurant(con, rest);
		close(con);
		return res;
	}
	@Override
	public int deleteRestaurant(String rName) {
		Connection con = getConnection();
		int res = dao.deleteRestaurant(con, rName);
		close(con);
		return res;
	}

	
	
	
	@Override
	public int insertMenu(Menu m) {
		Connection con = getConnection();
		m.setMenuNo(dao.getLastMenuNo(con, m.getResNo()) + 1);	
		int res = dao.insertMenu(con, m);
		close(con);
		return res;
	}
	@Override
	public int deleteMenu(Menu m) {
		Connection con = getConnection();
		int res = dao.deleteMenu(con, m);
		close(con);
		return res;
	}
	@Override
	public int updateMenu(Menu m) {
		Connection con = getConnection();
		int res = dao.updateMenu(con, m);
		close(con);
		return res;
	}
	@Override
	public ArrayList<Restaurant> findMenu(String mName) {
		Connection con = getConnection();
		ArrayList<Restaurant> findList = dao.findMenu(con,mName);
		close(con);
		
		return findList;
	}
	@Override
	public Restaurant randomMenu() {
		Connection con = getConnection();
		Restaurant randomRest = dao.randomMenu(con);
		close(con);
		
		return randomRest;
	}
	
	
	@Override
	public int insertReview(Review r) {
		Connection con = getConnection();
		r.setReviewNo( dao.getLastReviewNo(con, r.getResNo() )+1);
		int res = dao.insertReview(con, r);
		
		if(res > 0) {
				int updateGrade = dao.updateRestaurantGrade(con, r.getResNo());
				int updateTime = dao.updateRestaurantTime(con, r.getResNo());
				if( updateTime >  0 && updateGrade > 0) {
					commit(con);
					System.out.println("레스토랑 예상시간 Update 성공");
				}else {
					System.out.println("레스토랑 예상시간 Update 실패");
				}
		}
		close(con);

		return res;
	}
	
	

}
