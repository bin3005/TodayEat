package view.commonview.leftpanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import member.model.vo.Member;
import view.adminview.leftpanel.LeftPanel1;
import view.adminview.rightpanel.RightPanel1;
import view.clientview.leftpanel.MenuPanel;
import view.commonview.RestaurantFrame;
import view.commonview.rightpanel.rightpanel;
import view.commonview.toppanel.TopPanel1;
import view.commonview.toppanel.TopPanel3;
import view.commonview.toppanel.toppanel;

public class LoginPanel extends JPanel implements leftpanel{
	private RestaurantFrame mf;

	public LoginPanel(RestaurantFrame mf) {
		this.mf = mf;

		this.setBounds(12, 186, 510, 549);
		this.setLayout(null);
		this.setBackground(new Color(255, 0, 0, 0));
		this.setBorder(new LineBorder(Color.black, 2));

		JLabel Logo = new JLabel("회원 로그인");
		Logo.setBounds(50, 130, 100, 32);
		Logo.setFont(new Font("굴림", Font.BOLD, 16));
		this.add(Logo);

		JLabel comment = new JLabel("백스페이스바는 한번씩 눌러주세요.");
		comment.setBounds(200, 255, 300, 30);
		this.add(comment);

		JLabel id_label = new JLabel("아이디");
		id_label.setBounds(50, 180, 59, 21);
		this.add(id_label);

		JLabel pw_label = new JLabel("비밀번호");
		pw_label.setBounds(50, 235, 59, 21);
		this.add(pw_label);

		JTextField id = new JTextField();
		id.setBounds(120, 175, 255, 30);
		this.add(id);

		JPasswordField pw = new JPasswordField();
		pw.setBounds(120, 230, 255, 30);
		this.add(pw);

		JButton Login = new JButton("LOGIN");
		Login.setBounds(390, 175, 85, 85);
		Login.setFont(new Font("굴림", Font.BOLD, 14));
		Login.setBackground(Color.black);
		Login.setForeground(Color.white);
		this.add(Login);

		//////////////////////////////////////
		//////// 로그인 버튼
		Login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] pwdArr = pw.getPassword();
				String uId = id.getText();
				String password = "";
				for (int i = 0; i < pwdArr.length; i++) {
					password += pwdArr[i] + "";
				}
				if (!uId.isEmpty() && !password.isEmpty()) {
					int idCheck = mf.getMc().Check_LoginId(uId);
					if (idCheck == 1) {// 아이디 일치
						int pwCheck = mf.getMc().Check_LoginPw(uId, password);

						if (pwCheck == 1) {// 비밀번호 일치
							Member login = mf.getMc().login(uId, password);
							loginedMember.put("loginedMember", login);
							if(mf.getMc().Check_Admin(uId) == 1) {
							
								LeftPanel1 leftPanel = new LeftPanel1(mf, 0);
								changePanel(leftPanel);
								RightPanel1 rightPanel = new RightPanel1(mf);
								changePanel(rightPanel);
							}else {
								MenuPanel leftPanel = new MenuPanel(mf, 0);
								changePanel(leftPanel);

							}
							if(component.get("topPanel") instanceof TopPanel1) {
								TopPanel1 topPanel = new TopPanel1(mf);
								changePanel(topPanel);
								
							}
							else if(component.get("topPanel") instanceof TopPanel3) {
								TopPanel3 topPanel = new TopPanel3(mf);
								changePanel(topPanel);
								
							}
							
						} else {
							System.out.println("[ERROR] : 로그인 실패(비밀번호가 일치하지 않습니다.)");
							JOptionPane.showMessageDialog(null, "로그인 실패 : 비밀번호가 일치하지 않습니다.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						System.out.println("[ERROR] : 로그인 실패(아이디가 존재 하지 않습니다.)");
						JOptionPane.showMessageDialog(null, "로그인 실패 : 존재하지 않는 아이디 입니다.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("[ERROR] : 모든 정보를 입력해주세요)");
					JOptionPane.showMessageDialog(null, "모든 정보를 입력하세요!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		JButton Find_id = new JButton("아이디 찾기");
		Find_id.setBounds(50, 295, 205, 40);
		Find_id.setBackground(Color.black);
		Find_id.setForeground(Color.white);
		this.add(Find_id);

		//////////////////////////////////////
		//////// 아이디 찾기 버튼
		Find_id.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton Find_pw = new JButton("비밀번호 찾기");
		Find_pw.setBounds(270, 295, 205, 40);
		Find_pw.setBackground(Color.black);
		Find_pw.setForeground(Color.white);
		this.add(Find_pw);

		//////////////////////////////////////
		//////// 비밀번호 찾기 버튼
		Find_pw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton Signup = new JButton("회원가입");
		Signup.setBounds(50, 360, 425, 45);
		Signup.setBackground(Color.black);
		Signup.setForeground(Color.white);
		this.add(Signup);
		/*
		//////////////////////////////////////
		//////// 회원가입 버튼
		Signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 맴버함수 확정 후 패널 구현 시작
				SignUpPanel signup = new SignUpPanel(mf);
				changePanel(signup);
			}
		});
*/
		JButton back = new JButton("◀");
		back.setBounds(10, 10, 45, 30);
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		this.add(back);

		//////////////////////////////////////
		//////// 뒤로가기 버튼
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LeftPanel1 leftPanel = new LeftPanel1(mf, 0);
				changePanel(leftPanel);
			}
		});

		mf.add(this);
	}

	public void changePanel(JPanel panel) { // 패널 바꾸는 메소드
		mf.remove(component.get("backImg")); // 배경화면 삭제
		if (panel instanceof toppanel) {
			mf.remove(component.get("topPanel"));
			component.put("topPanel", panel);
			mf.repaint();
		
		} else if (panel instanceof rightpanel) {
			mf.remove(component.get("rightPanel"));
			component.put("rightPanel", panel);
			mf.repaint();
		} else {
			mf.remove(component.get("leftPanel"));
			component.put("leftPanel", panel);
			mf.repaint();
		}
		mf.add(component.get("backImg")); // 배경화면 추가
	}
	
}
