package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static common.JDBCTemplate.*;

import member.model.vo.Member;

public class MemberDaoImpl implements MemberDao {

	@Override
	public Member login(Connection con, String uId, String uPw) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Member login = null;
		try {
			pstm = con.prepareStatement(selectMemberLogin);
			pstm.setString(1, uId);
			pstm.setString(2, uPw);
			rs = pstm.executeQuery();
			
			if ( rs.next()) {
				login = new Member(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
		}
		
		return login;
	}

	@Override
	public int Check_LoginId(Connection con, String uId) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(selectLoginId);
			pstm.setString(1, uId);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				res = 1;
			}else {
				res = -1;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
		}
		
		return res;
	}

	@Override
	public int Check_LoginPw(Connection con, String uId, String uPw) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(selectLoginPw);
			pstm.setString(1, uId);
			pstm.setString(2, uPw);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				res = 1;
			}else {
				res = -1;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
		}
		
		return res;
	}

	@Override
	public int Check_Admin(Connection con, String uId) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(selectMemberGrade);
			pstm.setString(1, uId);
			
			rs = pstm.executeQuery();
			if(rs.next()) {
				return 1;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
		}
		return res;
	}

}
