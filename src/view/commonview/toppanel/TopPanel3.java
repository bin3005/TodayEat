package view.commonview.toppanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import view.clientview.leftpanel.MenuPanel;
import view.commonview.RestaurantFrame;
import view.commonview.leftpanel.LoginPanel;
import view.commonview.rightpanel.RightPanel3;
import view.commonview.rightpanel.rightpanel;

public class TopPanel3 extends JPanel implements toppanel{///초기 빈 패널
	private RestaurantFrame mf;
	public TopPanel3(RestaurantFrame mf) {///초기 빈 패널
		this.mf = mf;
		this.setBounds(12, 10, 950, 160);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black,2));
		this.setBackground(new Color(255, 0, 0, 0));
		
		JPanel logPanel = new JPanel();
		logPanel.setBounds(0, 0, 950, 30);
		logPanel.setLayout(null);
		logPanel.setBackground(new Color(255, 0, 0, 0));
		if (loginedMember.get("loginedMember") == null) {
			JButton login = new JButton("로그인");
			login.setBounds(800, 5, 90, 25);
			logPanel.add(login);
			
			login.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LoginPanel loginPanel = new LoginPanel(mf);
					changePanel(loginPanel);
				}
			});
		} else {
			JLabel member = new JLabel(loginedMember.get("loginedMember").getNickName() + "님 환영합니다", SwingConstants.RIGHT);
			member.setBounds(600, 5, 190, 25);
			logPanel.add(member);
			
			JButton login = new JButton("로그아웃");
			login.setBounds(800, 5, 90, 25);
			logPanel.add(login);
			login.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					loginedMember.put("loginedMember", null);
					
					MenuPanel leftPanel = new MenuPanel(mf,0);
					changePanel(leftPanel);
					RightPanel3 rightPanel = new RightPanel3(mf);
					changePanel(rightPanel);
					TopPanel3 topPanel = new TopPanel3(mf);
					changePanel(topPanel);
				}
			});
		}
		this.add(logPanel);
		mf.add(this);
		
	}
	public void changePanel(JPanel panel) {
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
