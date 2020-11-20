package member.model.vo;

import java.io.Serializable;

public class Member {
	private int memNo;
	private String memID;
	private String memPW;
	private String nickName;
	private String memName;
	private String rrn;
	private String phone;
	private int grade_Code;
	private int cnt_Review;

	public Member() {
		super();
	}

	public Member(int memNo, String memID, String memPW, String nickName, String memName, String rrn, String phone,
			int grade_Code, int cnt_Review) {
		super();
		this.memNo = memNo;
		this.memID = memID;
		this.memPW = memPW;
		this.nickName = nickName;
		this.memName = memName;
		this.rrn = rrn;
		this.phone = phone;
		this.grade_Code = grade_Code;
		this.cnt_Review = cnt_Review;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getMemPW() {
		return memPW;
	}

	public void setMemPW(String memPW) {
		this.memPW = memPW;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getGrade_Code() {
		return grade_Code;
	}

	public void setGrade_Code(int grade_Code) {
		this.grade_Code = grade_Code;
	}

	public int getCnt_Review() {
		return cnt_Review;
	}

	public void setCnt_Review(int cnt_Review) {
		this.cnt_Review = cnt_Review;
	}

	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memID=" + memID + ", memPW=" + memPW + ", nickName=" + nickName
				+ ", memName=" + memName + ", rrn=" + rrn + ", phone=" + phone + ", grade_Code=" + grade_Code
				+ ", cnt_Review=" + cnt_Review + "]";
	}
}

