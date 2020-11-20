package restaurant.model.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class  Restaurant  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int resNo;
	private String rName;					//식당 이름
	private int reTime;						//식당까지 걸리는 시간
	private double reGrade; 				//식당 평점
	private String pNum;					//식당 전화번호
	private ArrayList<Menu> menuList = new ArrayList<Menu>();	//식당 메뉴 저장 리스트
	private ArrayList<Review> reviewList = new ArrayList<Review>();	//식당 리뷰 저장 리스트

	public  Restaurant() { //기본 생성자
		super();
	}

	

	public Restaurant(int resNo, String rName, int reTime, double reGrade, String pNum, ArrayList<Menu> menuList,
			ArrayList<Review> reviewList) {
		super();
		this.resNo = resNo;
		this.rName = rName;
		this.reTime = reTime;
		this.reGrade = reGrade;
		this.pNum = pNum;
		this.menuList = menuList;
		this.reviewList = reviewList;
	}
	



	public Restaurant(int resNo, String rName, int reTime, double reGrade, String pNum) {
		super();
		this.resNo = resNo;
		this.rName = rName;
		this.reTime = reTime;
		this.reGrade = reGrade;
		this.pNum = pNum;
	}



	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public int getRetime() {
		return reTime;
	}

	public void setRetime(int retime) {
		this.reTime = retime;
	}

	public double getReGrade() {
		return reGrade;
	}

	public void setReGrade(double reGrade) {
		this.reGrade = reGrade;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public int getResNo() {
		return resNo;
	}

	public void setResNo(int resNo) {
		this.resNo = resNo;
	}

	public int getReTime() {
		return reTime;
	}

	public void setReTime(int reTime) {
		this.reTime = reTime;
	}

	public ArrayList<Menu> getMenuList() {
		return menuList;
	}

	public ArrayList<Review> getReviewList() {
		return reviewList;
	}

	public void setMenuList(ArrayList<Menu> menuList) {
		this.menuList = menuList;
	}

	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviewList = reviewList;
	}

	@Override
	public String toString() {
		return resNo+" : "+rName+" : "+menuList.get(0).getMenuNo() + " : "+ menuList.get(0).getmName();
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuList == null) ? 0 : menuList.hashCode());
		result = prime * result + ((pNum == null) ? 0 : pNum.hashCode());
		result = prime * result + ((rName == null) ? 0 : rName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reGrade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reTime;
		result = prime * result + resNo;
		result = prime * result + ((reviewList == null) ? 0 : reviewList.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (menuList == null) {
			if (other.menuList != null)
				return false;
		} else if (!menuList.equals(other.menuList))
			return false;
		if (pNum == null) {
			if (other.pNum != null)
				return false;
		} else if (!pNum.equals(other.pNum))
			return false;
		if (rName == null) {
			if (other.rName != null)
				return false;
		} else if (!rName.equals(other.rName))
			return false;
		if (Double.doubleToLongBits(reGrade) != Double.doubleToLongBits(other.reGrade))
			return false;
		if (reTime != other.reTime)
			return false;
		if (resNo != other.resNo)
			return false;
		if (reviewList == null) {
			if (other.reviewList != null)
				return false;
		} else if (!reviewList.equals(other.reviewList))
			return false;
		return true;
	}
	
}
