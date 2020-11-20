package view.adminview.rightpanel;

import static resource.R.component;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import restaurant.model.vo.Menu;
import restaurant.model.vo.Restaurant;
import view.commonview.RestaurantFrame;
import view.commonview.rightpanel.rightpanel;
import view.commonview.toppanel.toppanel;

public class AddMenuFrame extends JFrame implements rightpanel {
	private RestaurantFrame mf;
	private JLabel menulabel, pricelabel;
	private JTextField menu, price;
	private JButton check, cancel;

	public AddMenuFrame(RestaurantFrame mf) {
		this.setBounds(900, 350, 300, 200);
		this.setTitle("메뉴 추가");
		this.setLayout(null);
		this.mf = mf;
		
		menulabel = new JLabel("추가 메뉴");
		menulabel.setFont(new Font("굴림", Font.BOLD, 12));
		menulabel.setBounds(15, 25, 70, 15);
		this.add(menulabel);
		
		pricelabel = new JLabel("추가 가격");
		pricelabel.setFont(new Font("굴림", Font.BOLD, 12));
		pricelabel.setBounds(15,65,70,15);
		this.add(pricelabel);
		
		menu = new JTextField();
		menu.setBounds(80, 25, 165, 20);
		this.add(menu);
		
		price = new JTextField();
		price.setBounds(80, 65, 165, 20);
		this.add(price);
		price.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if( c < 48 || c >57 ) {
					price.setText("");
					if( c != 8 && c!= 9 && c!= 13 && c!=10) {
					System.out.println("[ERROR]: 숫자를 입력하세요.");
					JOptionPane.showMessageDialog(null, "숫자를 입력하세요.","오류!!", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		check = new JButton("확인");
		check.setBounds(40, 115, 90, 25);
		this.add(check);
		
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menu.getText().length() == 0 || price.getText().length() == 0) {
					System.out.println("[ERROR]: 모든 정보를 입력하세요.");
					JOptionPane.showMessageDialog(null, "입력 오류", "경고", JOptionPane.WARNING_MESSAGE);
				}else {
					String add_menu = menu.getText();
					int add_price = Integer.parseInt(price.getText());
					ArrayList<Restaurant> restList = mf.getRc().selectAllRestaurant();
					int rNum = restList.get(mf.getResNum()).getResNo();
					
					Menu m = new Menu(0,add_menu,add_price,rNum);
					int res = mf.getRc().insertMenu(m);
					if( res > 0) {
						System.out.println("메뉴 Insert 성공");
						RightPanel1 rightPanel = new RightPanel1(mf);
						changePanel(rightPanel);
						dispose();
					}else if( res == -3) {
						System.out.println("[ERROR -3]: 중복된 메뉴이름 ");
						JOptionPane.showMessageDialog(null, "중복된 메뉴 이름!!", "ERROR", JOptionPane.ERROR_MESSAGE);;
					}else {
						System.out.println("[ERROR]:메뉴 Insert 실패");
						JOptionPane.showMessageDialog(null, "입력 오류", "ERROR", JOptionPane.ERROR_MESSAGE);;
					}					
					
				}
				
			}
		});
		
		cancel = new JButton("취소");
		cancel.setBounds(170, 117, 90, 25);
		this.add(cancel);
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.setVisible(true);
	}
	//////////////////
	//패널변경 메소드
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
