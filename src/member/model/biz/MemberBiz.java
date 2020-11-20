package member.model.biz;

import java.sql.Connection;

import member.model.vo.Member;

public interface MemberBiz {
	
	public int Check_LoginId(String uId);
	public int Check_LoginPw(String uId, String uPw);
	public Member login(String uId, String uPw);
	public int Check_Admin(String uId);

}
