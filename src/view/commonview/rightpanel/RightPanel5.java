package view.commonview.rightpanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import restaurant.model.vo.Restaurant;
import view.commonview.RestaurantFrame;
import view.commonview.toppanel.TopPanel1;
import view.commonview.toppanel.toppanel;

public class RightPanel5 extends JPanel implements rightpanel{ //////////////////// 오늘 뭐먹지 패널
	private RestaurantFrame mf;

	public RightPanel5(RestaurantFrame mf) {
		//////////////////////
		// 프레임 구조잡기
		this.mf = mf;

		this.setBounds(531, 186, 430, 549);
		this.setLayout(null);
		this.setBackground(new Color(255, 0, 0, 0));
		this.setBorder(new LineBorder(Color.black, 2));

		//////////////////////////////////
		///// 랜덤 메뉴 출력 textarea
		JTextArea textArea = new JTextArea();
		textArea.setBounds(80, 372, 280, 30);
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);
		this.add(textArea, "Center");

		////////////////////////////////////////
		/// 오늘 뭐먹지 이미지 라벨 생성
		Image icon = new ImageIcon("images/todayMenu.png").getImage().getScaledInstance(338, 272, 0);

		JLabel label = new JLabel(new ImageIcon(icon));
		label.setBounds(80, 51, 338, 272);

		///////////////////////////
		//// 이미지 라벨 이벤트
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Restaurant randRest = mf.getRc().randomMenu();
				String menu = randRest.getMenuList().get(0).getmName();
				int price = randRest.getMenuList().get(0).getmPrice();
				ArrayList<Restaurant> list = mf.getRc().selectAllRestaurant();
				
				int resNum = 0;
				for (int i = 0 ; i < list.size();i++) {
					if(randRest.getResNo() == list.get(i).getResNo()) {
						resNum = i;
					}
				}
				mf.setResNum(resNum);

				if (price >= 1000) {
					// 숫자 3개마다 , 넣기
					String str = price + "";
					String str1 = "";
					int cnt = 0;

					for (int j = str.length() - 1; j >= 0; j--) {
						cnt++;
						// System.out.println(cnt);
						char c = str.charAt(j);
						str1 += c;
						if (cnt % 4 == 3) {
							str1 += ",";
						}
					}
					str = "";
					for (int k = str1.length() - 1; k >= 0; k--) {
						char c = str1.charAt(k);
						str += c;
					}

					textArea.setText(menu + " " + str); // 랜덤 메뉴 출력
				}else {
					textArea.setText(menu + " "+ price);
				}

				TopPanel1 topPanel2 = new TopPanel1(mf);
				changePanel(topPanel2);
			}
		});
		this.add(label);

		mf.add(this);

	}

	///////////////////////////////
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
