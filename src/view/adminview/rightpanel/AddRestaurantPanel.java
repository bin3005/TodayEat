package view.adminview.rightpanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import restaurant.model.vo.Restaurant;
import view.adminview.leftpanel.LeftPanel1;
import view.commonview.RestaurantFrame;
import view.commonview.rightpanel.RightPanel3;
import view.commonview.rightpanel.rightpanel;
import view.commonview.toppanel.TopPanel3;
import view.commonview.toppanel.toppanel;

public class AddRestaurantPanel extends JPanel implements rightpanel {// 식당추가 패널
	private RestaurantFrame mf;

	public AddRestaurantPanel(RestaurantFrame mf, LeftPanel1 leftPanel) { // 생성자
		//////////////
		// 패널 구조
		this.mf = mf;

		this.setBounds(531, 186, 430, 549);
		this.setBackground(new Color(255, 0, 0, 0));
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black, 2));

		/////////////////////////////////////
		///////////
		JLabel resName = new JLabel("식당 이름");
		JTextField nameText = new JTextField();

		resName.setFont(new Font("휴먼엑스포", Font.BOLD, 15));
		resName.setBounds(20, 80, 80, 30);
		nameText.setBounds(140, 85, 200, 21);
		nameText.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		this.add(resName);
		this.add(nameText);

		JLabel resTime = new JLabel("걸린 시간(분)");
		JTextField timeText = new JTextField();

		resTime.setFont(new Font("휴먼엑스포", Font.BOLD, 15));
		resTime.setBounds(20, 180, 120, 30);
		timeText.setBounds(140, 185, 200, 21);
		timeText.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		timeText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < 48 || c > 57) {
					timeText.setText("");
					if (c != 8 && c != 9 && c != 13 && c != 10) {
						System.out.println("[ERROR]:숫자를 입력하세요.");
						JOptionPane.showMessageDialog(mf, "숫자를 입력하세요.", "오류!!", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		this.add(resTime);
		this.add(timeText);
		JLabel resPhone = new JLabel("전화번호");
		JTextField phoneText = new JTextField("xxx-xxxx-xxxx");

		resPhone.setFont(new Font("휴먼엑스포", Font.BOLD, 15));
		resPhone.setBounds(20, 280, 80, 30);
		phoneText.setBounds(140, 285, 200, 21);
		phoneText.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		this.add(resPhone);
		this.add(phoneText);

		JButton createRes = new JButton("식당 추가");

		createRes.setBackground(Color.black);
		createRes.setForeground(Color.white);
		createRes.setBounds(180, 400, 91, 23);

		createRes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String rName = nameText.getText();
				int rTime = 0;
				if (!timeText.getText().isEmpty()) {
					rTime = Integer.parseInt(timeText.getText());
				}
				String rPhone = phoneText.getText();

				if (rName.isEmpty() || (rTime + "").isEmpty() || rPhone.isEmpty() || rPhone.equals("xxx-xxxx-xxxx")
						|| rTime == 0) {
					JOptionPane.showMessageDialog(mf, "모든 정보를 입력해주세요!!", "오류", JOptionPane.ERROR_MESSAGE);
					System.out.println("[ERROR] 식당 추가 실패 : 모든 정보를 입력하세요. ");
				} else {

					int res = mf.getRc().insertRestaurant(new Restaurant(0, rName, rTime, 0, rPhone));

					if (res > 0) {
						System.out.println("식당추가 성공");
						JOptionPane.showMessageDialog(mf, "식당 추가가 정상적으로 완료되었습니다!!", "성공했습니다",
								JOptionPane.INFORMATION_MESSAGE);
						RightPanel3 rightPanel3 = new RightPanel3(mf);
						changePanel(rightPanel3);
						LeftPanel1  leftPanel = new LeftPanel1(mf, 0);
						changePanel(leftPanel);
						TopPanel3 topPanel = new TopPanel3(mf);
						changePanel(topPanel);
					} else if (res == -1) {
						JOptionPane.showMessageDialog(mf, "동일한 이름을 가진 식당이 존재합니다.", "오류", JOptionPane.ERROR_MESSAGE);
						System.out.println("[ERROR] : UNIQUE제약조건 위배!!" + "||식당 insert 실패: 동일한 이름을 가진 식당존재 ");
					} else {
						System.out.println("[ERROR]식당추가 실패");
					}
				}

			}
		});
		this.add(createRes);
		mf.add(this);

	}

	//////////////////
	// 패널변경 메소드
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
