package view.clientview.leftpanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import restaurant.model.vo.Restaurant;
import view.clientview.rightpanel.ClientRightPanel1;
import view.commonview.RestaurantFrame;
import view.commonview.leftpanel.leftpanel;
import view.commonview.rightpanel.RightPanel4;
import view.commonview.rightpanel.RightPanel5;
import view.commonview.rightpanel.RightPanel6;
import view.commonview.rightpanel.rightpanel;
import view.commonview.toppanel.TopPanel1;
import view.commonview.toppanel.TopPanel3;
import view.commonview.toppanel.toppanel;

public class MenuPanel extends JPanel implements leftpanel { // 왼쪽 패널
	private RestaurantFrame mf;

	private int prePage;

	public MenuPanel(RestaurantFrame mf, int prePage) {// 생성자
		//////////
		/// 기본적인 구조 설계
		
		this.prePage = prePage;
		this.mf = mf;

		this.setBounds(12, 186, 510, 549);

		this.setLayout(null);
		this.setBackground(new Color(255, 0, 0, 0));
		this.setBorder(new LineBorder(Color.black, 2));

		////////////////////////////////////////
		/////////// 패널 추가 식당종류패널
		ArrayList<JPanel> panelList = new ArrayList<JPanel>();
		ArrayList<Restaurant> restList = mf.getRc().selectAllRestaurant();
		
		
		for (int k = 0; k <  (( restList.size() - 1) / 10) + 1; k++) {
			JPanel resButton = new JPanel();
			resButton.setBounds(0, 0, 510, 440);

			resButton.setLayout(null);
			resButton.setBackground(new Color(255, 0, 0, 0));

			JButton[] restaurantBt = new JButton[10];
			for (int i = 0; i < 10; i++) {
				int cnt = 0;
				if (k * 100 == 0) {
					cnt = i;
				} else {
					cnt = i;
					cnt += (k % 100) * 10;
				}
				if (restList.size() > cnt) {

					restaurantBt[i] = new JButton(restList.get(cnt).getrName());
					restaurantBt[i].setFont(new Font("휴먼엑스포", Font.BOLD, 20));
					restaurantBt[i].setBackground(Color.black);
					restaurantBt[i].setForeground(Color.white);
					restaurantBt[i].setBorder(new LineBorder(Color.LIGHT_GRAY, 2));

					//////////////
					//// 식당 버튼 이벤트
					final int index = cnt;
					restaurantBt[i].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							mf.setResNum(index);

							ClientRightPanel1 rightPanel1 = new ClientRightPanel1(mf);
							changePanel(rightPanel1);
							TopPanel1 topPanel1 = new TopPanel1(mf);
							changePanel(topPanel1);

						}
					});
				}
			}
			
			/////////////////
			/// 식당 버튼 사이즈 잡기
			int xLo = 10;
			int yLo = 18;
			int resWidth = 243;
			int resHeight = 60;

			for (int i = 0; i < 10; i++) {
				if (restaurantBt[i] != null) {

					restaurantBt[i].setBounds(xLo, yLo, resWidth, resHeight);
					xLo += 250;
					if (i % 2 == 1) {
						yLo += 75;
						xLo = 10;

					}
					if (i % 10 == 9) {
						yLo = 18;
					}
				}
			}

			for (int i = 0; i < restaurantBt.length; i++) {
				if (restaurantBt[i] != null) {
					resButton.add(restaurantBt[i]);
				}
			}

			
			////////////////
			// 이전 페이지
			JButton backPage = new JButton("◀");
			backPage.setBounds(400, 400, 51, 23);
			backPage.setBackground(Color.BLACK);
			backPage.setForeground(Color.white);
			backPage.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));

			backPage.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (0 < getThis().prePage) {
						getThis().remove(panelList.get(getThis().prePage));
						getThis().prePage -= 1;
						getThis().add(panelList.get(getThis().prePage));
						mf.repaint();
					} else {
						System.out.println("[ERROR]: 첫번째 페이지 입니다.");
						JOptionPane.showMessageDialog(null, "첫번째 페이지 입니다.", "오류!!", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			resButton.add(backPage);

			////////
			// 다음페이지
			JButton nextPage = new JButton("▶");
			nextPage.setBounds(450, 400, 51, 23);
			nextPage.setBackground(Color.BLACK);
			nextPage.setForeground(Color.white);
			nextPage.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
			nextPage.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if ((restList.size() - 1) / 10 > getThis().prePage) {
						getThis().remove(panelList.get(getThis().prePage));
						getThis().prePage += 1;
						getThis().add(panelList.get(getThis().prePage));
						mf.repaint();
					} else {
						System.out.println("[ERROR]: 마지막 페이지 입니다.");
						JOptionPane.showMessageDialog(null, "마지막 페이지 입니다.", "오류!!", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			resButton.add(nextPage);

			panelList.add(resButton);

		}
		

		this.add(panelList.get(prePage));

		////////////////////////////////
		//////////// 오늘뭐먹지?패널

		JPanel activeButton = new JPanel();

		activeButton.setBounds(0, 440, 510, 109);

		activeButton.setLayout(null);
		activeButton.setBackground(new Color(255, 0, 0, 0));

		this.add(activeButton);

		/////////////////
		////// 검색버튼
		JButton findButton = new JButton("메뉴 검색");
		findButton.setBounds(10, 20, 130, 70);
		findButton.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		findButton.setBorder(new LineBorder(Color.black, 2));
		findButton.setBackground(Color.black);
		findButton.setForeground(Color.white);
		findButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		activeButton.add(findButton);

		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RightPanel4 rightPanel4 = new RightPanel4(mf);
				changePanel(rightPanel4);
				TopPanel3 topPanel3 = new TopPanel3(mf);
				changePanel(topPanel3);
			}

		});

		///////////////////////////////
		///////// 오늘 뭐먹지버튼
		JButton randomButton = new JButton("오늘 뭐먹지");
		randomButton.setBounds(190, 20, 130, 70);
		activeButton.add(randomButton);
		randomButton.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		randomButton.setBorder(new LineBorder(Color.black, 2));
		randomButton.setBackground(Color.black);
		randomButton.setForeground(Color.white);
		randomButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RightPanel5 rightpanel5 = new RightPanel5(mf);
				changePanel(rightpanel5);
				TopPanel3 topPanel3 = new TopPanel3(mf);
				changePanel(topPanel3);
			}
		});

		//////////////////////////
		/////////// 베스트 식당 버튼
		JButton bestButton = new JButton("Best 식당");
		bestButton.setBounds(367, 20, 130, 70);
		activeButton.add(bestButton);
		bestButton.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		bestButton.setBorder(new LineBorder(Color.black, 2));
		bestButton.setBackground(Color.black);
		bestButton.setForeground(Color.white);
		bestButton.setBorder(new LineBorder(Color.black, 2));
		bestButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));

		bestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RightPanel6 rightpanel6 = new RightPanel6(mf);
				changePanel(rightpanel6);
				TopPanel3 topPanel3 = new TopPanel3(mf);
				changePanel(topPanel3);
			}
		});

		///////////////////////////////////////////////////////////////////////////////////
		////////// 메인프레임에 패널 추가
		mf.add(this);
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
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

	public MenuPanel getThis() {
		return this;
	}

}
