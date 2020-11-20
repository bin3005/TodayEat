package restaurant.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import restaurant.model.vo.Menu;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.Review;

public class RestaurantDaoImpl implements RestaurantDao {
	Scanner in = new Scanner(System.in);

	@Override
	public ArrayList<Restaurant> selectAllRestaurant(Connection con) {
		Statement stmt = null;
		ArrayList<Restaurant> restaurant = new ArrayList<Restaurant>();
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectAllRestaurant);

			while (rs.next()) {
				restaurant.add(new Restaurant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getNString(5), new ArrayList<Menu>(), new ArrayList<Review>()));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		ArrayList<Menu> menu = selectAllMenu(con);
		ArrayList<Review> review = selectAllReview(con);

		for (int i = 0; i < restaurant.size(); i++) {

			ArrayList<Menu> addMenu = new ArrayList<Menu>();
			ArrayList<Review> addReview = new ArrayList<Review>();

			for (int j = 0; j < menu.size(); j++) {
				if (restaurant.get(i).getResNo() == menu.get(j).getResNo()) {
					addMenu.add(menu.get(j));
				}
			}
			restaurant.get(i).setMenuList(addMenu);

			for (int j = 0; j < review.size(); j++) {
				if (restaurant.get(i).getResNo() == review.get(j).getResNo()) {
					addReview.add(review.get(j));
				}
			}
			restaurant.get(i).setReviewList(addReview);
		}
		
		return restaurant;
	}

	@Override
	public ArrayList<Menu> selectAllMenu(Connection con) {
		Statement stmt = null;
		ArrayList<Menu> menu = new ArrayList<Menu>();
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectAllMenu);
			while (rs.next()) {
				menu.add(new Menu(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return menu;
	}

	@Override
	public ArrayList<Review> selectAllReview(Connection con) {
		Statement stmt = null;
		ArrayList<Review> review = new ArrayList<Review>();
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectAllReview);
			while (rs.next()) {
				review.add(new Review(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5),
						(Date)(rs.getTimestamp(6)), rs.getInt(7)));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}
		return review;
	}

	@Override
	public Restaurant getLastRestaurant(Connection con) {
		ArrayList<Restaurant> restList = selectAllRestaurant(con);
		Restaurant rest = null;

		if (restList.size() != 0) {
			rest = restList.get(restList.size() - 1);
		}
		return rest;
	}

	@Override
	public int insertRestaurant(Connection con, Restaurant rest) {
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(insertRestaurnat);
			pstm.setInt(1, rest.getResNo());
			pstm.setString(2, rest.getrName());
			pstm.setInt(3, rest.getReTime());
			pstm.setDouble(4, rest.getReGrade());
			pstm.setString(5, rest.getpNum());

			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteRestaurant(Connection con, String rName) {
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(deleteRestaurant);
			pstm.setString(1, rName);

			res = pstm.executeUpdate();

			if (res > 0) {
				commit(con);
			} else {
				rollback(con);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstm);
		}

		return res;
	}

	@Override
	public int insertMenu(Connection con, Menu m) {
		PreparedStatement pstm = null;
		int res = 0;

		// 같은이름검사
		ArrayList<Restaurant> restList = selectAllRestaurant(con);
		ArrayList<Menu> menuList = null;
		for (int i = 0; i < restList.size(); i++) {
			if (restList.get(i).getResNo() == m.getResNo()) {
				menuList = restList.get(i).getMenuList();

			}
		}
		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getmName().equals(m.getmName())) {
				rollback(con);
				return -3;
			}
		}

		try {
			pstm = con.prepareStatement(insertMenu);
			pstm.setInt(1, m.getMenuNo());
			pstm.setString(2, m.getmName());
			pstm.setInt(3, m.getmPrice());
			pstm.setInt(4, m.getResNo());

			res = pstm.executeUpdate();

			if (res > 0) {
				commit(con);
			} else {
				rollback(con);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstm);
		}
		return res;
	}

	@Override
	public int getLastMenuNo(Connection con, int resNo) {
		ArrayList<Menu> menuList = selectMenuList(con, resNo);
		if( menuList != null) {
			int lastNo = menuList.get(menuList.size() - 1).getMenuNo();
			return lastNo;
		}else {
			return 0;
		}
	}

	@Override
	public int deleteMenu(Connection con, Menu m) {
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(deleteMenu);
			pstm.setInt(1, m.getMenuNo());

			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstm);
		}

		return res;
	}

	@Override
	public int updateMenu(Connection con, Menu m) {
		PreparedStatement pstm = null;
		int res = 0;

		////////////////////
		// 중복이름 에러코드
		ArrayList<Restaurant> restList = selectAllRestaurant(con);
		ArrayList<Menu> menuList = null;
		for (int i = 0; i < restList.size(); i++) {
			if (restList.get(i).getResNo() == m.getResNo()) {
				menuList = restList.get(i).getMenuList();

			}
		}

		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getmName().equals(m.getmName())) {
				if(menuList.get(i).getMenuNo() != m.getMenuNo()) {
					rollback(con);
					return -3;
				}
			}
		}
		///////////////////////
		try {
			pstm = con.prepareStatement(updateMenu);
			pstm.setString(1, m.getmName());
			pstm.setInt(2, m.getmPrice());
			pstm.setInt(3, m.getMenuNo());
			pstm.setInt(4, m.getResNo());

			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstm);
		}
		return res;
	}

	@Override
	public ArrayList<Restaurant> findMenu(Connection con, String mName) {
		ArrayList<Restaurant> restList = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			pstm = con.prepareStatement(findMenu);
			pstm.setString(1, mName);
			rs = pstm.executeQuery();
			// res_no res_name res_time, res_grade, res_phone menu_no menu_name, menu_price
			if (rs != null) {
				restList = new ArrayList<Restaurant>();
				while (rs.next()) {
					ArrayList<Menu> menuList = new ArrayList<Menu>();
					Menu m = new Menu(rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(1));
					menuList.add(m);
					Restaurant r = new Restaurant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
							rs.getString(5), menuList, new ArrayList<Review>());
					restList.add(r);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstm);
			close(rs);
		}

		return restList;
	}

	@Override
	public Restaurant randomMenu(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Restaurant r = null;

		ArrayList<Restaurant> restList = selectAllRestaurant(con);

		int randRestSize = (int) (Math.random() * restList.size());
		int randMenuSize = (int) (Math.random() * restList.get(randRestSize).getMenuList().size());
		int randMenuNum = restList.get(randRestSize).getMenuList().get(randMenuSize).getMenuNo();
		int randRestNum = restList.get(randRestSize).getResNo();

		try {
			pstm = con.prepareStatement(randomMenu);
			pstm.setInt(1, randMenuNum);
			pstm.setInt(2, randRestNum);
			rs = pstm.executeQuery();

			if (rs.next()) {
				ArrayList<Menu> menuList = new ArrayList<Menu>();
				Menu m = new Menu(rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(1));
				menuList.add(m);
				r = new Restaurant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5),
						menuList, null);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstm);
			close(rs);
		}

		return r;
	}

	@Override
	public int insertReview(Connection con, Review r) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(insertReview);
			pstm.setInt(1, r.getReviewNo());
			pstm.setString(2, r.getViewName());
			pstm.setDouble(3, r.getViewGrade());
			pstm.setInt(4, r.getViewTime());
			pstm.setString(5, r.getComment());
			pstm.setTimestamp(6, new java.sql.Timestamp(r.getToday().getTime()));
			pstm.setInt(7, r.getResNo());
			
			res = pstm.executeUpdate();
			if( res > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return res;
	}

	@Override
	public int getLastReviewNo(Connection con, int resNo) {
		ArrayList<Review> reviewList = selectReviewList(con, resNo);
		if(reviewList != null) {
			int lastNo = reviewList.get(reviewList.size() - 1).getReviewNo();
			return lastNo;
		}else {
			return 0;
		}

	}

	@Override
	public ArrayList<Menu> selectMenuList(Connection con, int resNo) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Menu> menuList = null;
		try {
			pstm = con.prepareStatement(selectMenuList);
			pstm.setInt(1, resNo);
			rs = pstm.executeQuery();
						
			if(rs.next()) {
				menuList = new ArrayList<Menu>();
				Menu m2 = new Menu(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				menuList.add(m2);
				while(rs.next()) {
					Menu m = new Menu(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
					menuList.add(m);
				}				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
		}
		return menuList;
	}

	@Override
	public ArrayList<Review> selectReviewList(Connection con, int resNo) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Review> reviewList = null;
		try {
			pstm = con.prepareStatement(selectReviewList);
			pstm.setInt(1, resNo);
			rs = pstm.executeQuery();
						
			if(rs.next()) {
				reviewList = new ArrayList<Review>();
				Review r2 = new Review(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getDate(6)
						,rs.getInt(7));
				reviewList.add(r2);
				while(rs.next()) {
					Review r = new Review(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getDate(6)
							,rs.getInt(7));
					reviewList.add(r);
				}				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
		}
		return reviewList;
	}

	@Override
	public int updateRestaurantTime(Connection con, int resNo) {
		ArrayList<Review> reviewList = selectReviewList(con, resNo);
		int avgTime = 0;
		int cnt = 0;
		int timeSum = selectRestTime(con, resNo);
		
		if( timeSum != 0 && timeSum != -1) {
			cnt = 1;
		}
		
		for(int i = 0 ; i < reviewList.size() ; i++) {
			if( reviewList.get(i).getViewTime() <= 40 && reviewList.get(i).getViewTime() >= 10) {
				timeSum += reviewList.get(i).getViewTime();
				cnt++;
			}
		}
		
		if( cnt != 0) {
			avgTime = timeSum / cnt;
		}else {
			return -2; //10분이상 40분이하 예상시간이 1개도 존재하지않음
		}
		
		
		
		
		//"UPDATE RESTAURANT SET RES_TIME = ? WHERE RES_NO =?";
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(updateRestTime);
			pstm.setInt(1, avgTime);
			pstm.setInt(2, resNo);
			
			res = pstm.executeUpdate();
			
			if( !(res > 0) ) {
				rollback(con);
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return res;
	}

	@Override
	public int selectRestTime(Connection con, int resNo) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		int restTime = 0;
		
		try {
			pstm = con.prepareStatement(selectRestTime);
			pstm.setInt(1, resNo);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				restTime = rs.getInt(1);
			}else {
				System.out.println("[ERROR]: 존재하지 않는 식당!!");
				restTime = -1;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
		}
		return restTime;
	}

	@Override
	public int updateRestaurantGrade(Connection con, int resNo) {
		ArrayList<Review> reviewList = selectReviewList(con, resNo);
		double gradeSum = 0;
		int cnt = 0;
		double gradeAvg = 0;
		for(int i = 0 ; i < reviewList.size(); i++) {
			gradeSum += reviewList.get(i).getViewGrade();
			cnt++;
		}
		
		gradeAvg = gradeSum / cnt;
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(updateRestGrade);
			pstm.setDouble(1, gradeAvg);
			pstm.setInt(2, resNo);
			
			res = pstm.executeUpdate();
			
			if( !(res > 0) ) {
				rollback(con);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		
			
		return res;
	}

}
