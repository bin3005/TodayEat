package restaurant.model.vo;

import java.io.Serializable;

public class Menu  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int menuNo;
	private String mName;		//메뉴 이름
	private int mPrice;			//메뉴 가격
	private int resNo;
	
	
	public Menu(int menuNo, String mName, int mPrice, int resNo) {
		super();
		this.menuNo = menuNo;
		this.mName = mName;
		this.mPrice = mPrice;
		this.resNo = resNo;
	}
	public int getResNo() {
		return resNo;
	}
	public void setResNo(int resNo) {
		this.resNo = resNo;
	}
	public String getmName() { //getter / setter
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getmPrice() {
		return mPrice;
	}
	public void setmPrice(int mPrice) {
		this.mPrice = mPrice;
	}
	public Menu() { //기본 생성자
		super();
	}
	public Menu(String mName, int mPrice) { // 매개변수 생성자
		super();
		this.mName = mName;
		this.mPrice = mPrice;
	}
	
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	@Override
	public String toString() {	//toString 34
		return mName + "\t\t\t" + mPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + mPrice;
		result = prime * result + menuNo;
		result = prime * result + resNo;
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
		Menu other = (Menu) obj;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mPrice != other.mPrice)
			return false;
		if (menuNo != other.menuNo)
			return false;
		if (resNo != other.resNo)
			return false;
		return true;
	}
	
}
