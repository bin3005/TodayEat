package view.commonview.toppanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import restaurant.model.vo.Restaurant;
import view.clientview.leftpanel.MenuPanel;
import view.commonview.RestaurantFrame;
import view.commonview.leftpanel.LoginPanel;
import view.commonview.rightpanel.RightPanel3;
import view.commonview.rightpanel.RightPanel7;
import view.commonview.rightpanel.rightpanel;

public class TopPanel1 extends JPanel implements toppanel{ // 식당 선택시 식당 정보를 띄우는 탑패널
	private RestaurantFrame mf;

	public TopPanel1(RestaurantFrame mf) { // leftpanel과 mainframe을 매개변수로 가지는 생성자
		////////////////////////////////
		////// top패널 구조 잡기
		this.mf = mf;
		setBounds(12, 10, 950, 160);
		setLayout(null);
		this.setBorder(new LineBorder(Color.black, 2));
		this.setBackground(new Color(255, 0, 0, 0));

		/////////////////////////////////
		////// 패널 넘기기 생성
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
		//////////////////////////////////
		/// 탑 정보 패널
		JPanel info = new JPanel();
		info.setBounds(0, 40, 950, 120);
		info.setLayout(null);
		info.setBackground(new Color(255, 0, 0, 0));
		this.add(info);

		//////////////////
		/// 식당 정보 생성
		ArrayList<Restaurant> list = mf.getRc().selectAllRestaurant();
		String resName = list.get(mf.getResNum()).getrName();
		String resInfo = "";
		if (list.get(mf.getResNum()).getReGrade() == 0) {
			resInfo += "평점정보없음";
		} else if (list.get(mf.getResNum()).getReGrade() < 1.5) {
			resInfo += "★";
		} else if (list.get(mf.getResNum()).getReGrade() < 2.5) {
			resInfo += "★★";
		} else if (list.get(mf.getResNum()).getReGrade() < 3.5) {
			resInfo += "★★★";
		} else if (list.get(mf.getResNum()).getReGrade() < 4.5) {
			resInfo += "★★★★";
		} else if (list.get(mf.getResNum()).getReGrade() < 5.5) {
			resInfo += "★★★★★";
		}
		resInfo += "/예상 시간:" + list.get(mf.getResNum()).getRetime() + "분/";
		resInfo += list.get(mf.getResNum()).getpNum();

		////////////////
		// 지도 보기 버튼
		Image icon = new ImageIcon("images/map2.png").getImage();
		JLabel showMap = new JLabel(new ImageIcon(icon));
		showMap.setBounds(850, 47, 64, 64);
		info.add(showMap);

		showMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RightPanel7 rightPanel7 = new RightPanel7(mf);
				changePanel(rightPanel7);

			}
		});

		////////////////////////
		/// 식당정보관련 정보 라벨 생성
		JLabel reName = new JLabel();
		reName.setText(resName);
		reName.setBounds(3, 10, 900, 50);
		reName.setFont(new Font("휴먼엑스포", Font.BOLD, 50));
		info.add(reName);

		JLabel reInfo = new JLabel();
		reInfo.setText(resInfo);
		reInfo.setBounds(3, 80, 900, 40);
		reInfo.setFont(new Font("휴먼엑스포", Font.BOLD, 30));
		info.add(reInfo);

		//////////////////////
		//// 메인 프레임에 추가
		mf.add(this);
	}

	////////////////////////////////
	//////////// 패널 변경 메소드
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
