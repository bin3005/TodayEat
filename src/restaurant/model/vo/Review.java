package restaurant.model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private int reviewNo;	//리뷰번호
	private String viewName;	//작성자 이름
	private double viewGrade;			//평점
	private int viewTime;		//리뷰
	private String comment;		//리뷰 내용
	private Date today;			//리뷰 작성 시간
	private int resNo;
	
	

	

	

	public Review(int reviewNo, String viewName, double viewGrade, int viewTime, String comment, Date today,
			int resNo) {
		super();
		this.reviewNo = reviewNo;
		this.viewName = viewName;
		this.viewGrade = viewGrade;
		this.viewTime = viewTime;
		this.comment = comment;
		this.today = today;
		this.resNo = resNo;
	}
	


	public Review(int reviewNo, String viewName, double viewGrade, int viewTime, String comment, Date today) {
		super();
		this.reviewNo = reviewNo;
		this.viewName = viewName;
		this.viewGrade = viewGrade;
		this.viewTime = viewTime;
		this.comment = comment;
		this.today = today;
	}



	public Review() { 	//기본 생성자
		super();
	}
	
	
	
	public int getResNo() {
		return resNo;
	}



	public void setResNo(int resNo) {
		this.resNo = resNo;
	}



	public String getViewName() { //getter/setter
		return viewName;
	}
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	
	public double getViewGrade() {
		return viewGrade;
	}

	public void setViewGrade(double viewGrade) {
		this.viewGrade = viewGrade;
	}

	public void setViewGrade(int viewGrade) {
		this.viewGrade = viewGrade;
	}
	
	public int getViewTime() {
		return viewTime;
	}
	
	public void setViewTime(int viewTime) {
		this.viewTime = viewTime;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getToday() {
		return today;
	}
	
	public void setToday(Date today) {
		this.today = today;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	/*
	@Override
	public String toString() {
		SimpleDateFormat sd = new SimpleDateFormat("yy-MM-dd-h:mm");
		String date = sd.format(today);
		
		String vg ="";
		switch(viewGrade){
			case 1: vg = "*"; break;
			case 2: vg = "**"; break;
			case 3: vg = "***"; break;
			case 4: vg = "****"; break;
			case 5: vg = "*****"; break;
			
		}
		
		return reviewNo+"."+"  "+viewName+" "+vg+" "+viewTime+"분 "+comment+" "+date;
	}
	*/



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + resNo;
		result = prime * result + reviewNo;
		result = prime * result + ((today == null) ? 0 : today.hashCode());
		long temp;
		temp = Double.doubleToLongBits(viewGrade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((viewName == null) ? 0 : viewName.hashCode());
		result = prime * result + viewTime;
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
		Review other = (Review) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (resNo != other.resNo)
			return false;
		if (reviewNo != other.reviewNo)
			return false;
		if (today == null) {
			if (other.today != null)
				return false;
		} else if (!today.equals(other.today))
			return false;
		if (Double.doubleToLongBits(viewGrade) != Double.doubleToLongBits(other.viewGrade))
			return false;
		if (viewName == null) {
			if (other.viewName != null)
				return false;
		} else if (!viewName.equals(other.viewName))
			return false;
		if (viewTime != other.viewTime)
			return false;
		return true;
	}
}
