package member.controller;

import java.util.Scanner;

import member.model.biz.MemberBiz;
import member.model.biz.MemberBizImpl;
import member.model.vo.Member;


public class MemberController {
	MemberBiz biz = new MemberBizImpl();
	Scanner in = new Scanner(System.in);
	
	public int Check_LoginId(String uId) {
		int res = biz.Check_LoginId(uId);
		return res;
	}
	public int Check_LoginPw(String uId, String uPw) {
		int res = biz.Check_LoginPw(uId, uPw);
		return res;
	}
	public Member login(String uId, String uPw) {
		Member login = biz.login(uId, uPw);
		
		return login;
	}
	public int Check_Admin(String uId) {
		int res = biz.Check_Admin(uId);
		return res;
	}
}
