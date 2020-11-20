package restaurant.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import restaurant.model.vo.Menu;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.Review;

public interface RestaurantDao {
	public String selectAllRestaurant = "SELECT * FROM RESTAURANT ORDER BY RES_NO";
	public String selectAllMenu = "SELECT * FROM Menu ORDER BY RES_NO,MENU_NO";
	public String selectAllReview = "SELECT * FROM Review ORDER BY RES_NO,RV_NO";
	public String insertRestaurnat ="INSERT INTO RESTAURANT VALUES(?,?,?,?,?)";
	public String deleteRestaurant ="DELETE RESTAURANT WHERE RES_NAME = ?";
	public String insertMenu = "INSERT INTO MENU VALUES(?,?,?,?)";
	public String deleteMenu = "DELETE MENU WHERE MENU_NO = ?";
	public String updateMenu = "UPDATE MENU SET MENU_NAME = ?, MENU_PRICE = ? WHERE MENU_NO = ? AND RES_NO = ?";
	public String findMenu = "SELECT * FROM RESTAURANT JOIN MENU USING(RES_NO)"+
			" WHERE MENU_NAME IN( SELECT MENU_NAME FROM MENU WHERE MENU_NAME LIKE ? )";
	public String randomMenu = "SELECT * FROM RESTAURANT JOIN MENU USING(RES_NO) WHERE MENU_NO = ?  AND RES_NO = ?";
	public String insertReview = "INSERT INTO REVIEW VALUES(?,?,?,?,?,?,?)";
	public String updateRestTime = "UPDATE RESTAURANT SET RES_TIME = ? WHERE RES_NO =?";
	public String selectRestTime =	"SELECT RES_TIME FROM RESTAURANT WHERE RES_NO = ?";
	public String updateRestGrade = "UPDATE RESTAURANT SET RES_GRADE = ? WHERE RES_NO = ?";
	
	public String selectMenuList = "SELECT * FROM MENU WHERE RES_NO = ? ORDER BY MENU_NO";
	public String selectReviewList = "SELECT * FROM REVIEW WHERE RES_NO = ? ORDER BY RV_NO";
	
	public ArrayList<Restaurant> selectAllRestaurant(Connection con);
	public Restaurant getLastRestaurant(Connection con);
	public int insertRestaurant(Connection con, Restaurant rest);
	public int deleteRestaurant(Connection con, String rName);
	
	public int insertMenu(Connection con, Menu m);
	public int getLastMenuNo(Connection con,int resNo);
	public int deleteMenu(Connection con, Menu m);
	public int updateMenu(Connection con, Menu m);
	public ArrayList<Restaurant> findMenu(Connection con, String mName); 
	public Restaurant randomMenu(Connection con);
	public ArrayList<Menu> selectMenuList(Connection con, int resNo);
	
	public int insertReview(Connection con , Review r);
	public int updateRestaurantTime(Connection con,int resNo);
	public int updateRestaurantGrade(Connection con,int resNo);
	public int selectRestTime(Connection con, int resNo);
	public int getLastReviewNo(Connection con,int resNo);
	public ArrayList<Review> selectReviewList(Connection con, int resNo);
	
	public ArrayList<Menu> selectAllMenu(Connection con);
	public ArrayList<Review> selectAllReview(Connection con);
	
	
	
}
