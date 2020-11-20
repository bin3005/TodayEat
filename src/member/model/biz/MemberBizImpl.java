package member.model.biz;

import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.dao.MemberDaoImpl;
import member.model.vo.Member;
import static common.JDBCTemplate.*;

public class MemberBizImpl implements MemberBiz{
	MemberDao dao = new MemberDaoImpl();

	@Override
	public Member login(String uId, String uPw) {
		Connection con = getConnection();
		Member login = dao.login(con, uId, uPw);
		
		return login;
	}

	@Override
	public int Check_LoginId(String uId) {
		Connection con = getConnection();
		int res = dao.Check_LoginId(con, uId);
		return res;
	}

	@Override
	public int Check_LoginPw(String uId, String uPw) {
		Connection con = getConnection();
		int res = dao.Check_LoginPw(con, uId, uPw);
		return res;
	}

	@Override
	public int Check_Admin(String uId) {
		Connection con = getConnection();
		int res = dao.Check_Admin(con, uId);
		return res;
	}

}
