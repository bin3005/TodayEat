package member.model.dao;

import java.sql.Connection;

import member.model.vo.Member;

public interface MemberDao {
	public String selectMemberLogin = "SELECT * FROM MEMBER WHERE ID = ? AND PW = ?";
	public String selectLoginId = "SELECT ID FROM MEMBER WHERE ID = ?";
	public String selectLoginPw = "SELECT PW FROM MEMBER WHERE ID = ? AND PW = ?";
	public String selectMemberGrade = "SELECT GRADE_CODE FROM MEMBER JOIN MEMBER_GRADE USING(GRADE_CODE) WHERE GRADE_NAME ='MANAGER' AND ID = ? ";
	
	
	public int Check_LoginId(Connection con, String uId);
	public int Check_LoginPw(Connection con, String uId, String uPw);
	public int Check_Admin(Connection con, String uId);
	public Member login(Connection con, String uId, String uPw);
}
