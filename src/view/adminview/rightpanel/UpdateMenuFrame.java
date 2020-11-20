package view.adminview.rightpanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import restaurant.model.vo.Menu;
import view.commonview.RestaurantFrame;
import view.commonview.rightpanel.rightpanel;
import view.commonview.toppanel.toppanel;

public class UpdateMenuFrame extends JFrame implements rightpanel{
	private RestaurantFrame mf;
	private JLabel modifylabel, menulabel, pricelabel;
	private JTextField modify_item, updateName, updatePrice;
	private JButton check, cancel;

	public UpdateMenuFrame(RestaurantFrame mf, Menu m) {
		this.setBounds(900, 350, 300, 300);
		this.setTitle("메뉴 수정");
		this.setLayout(null);
		this.mf = mf;
		
		modifylabel = new JLabel("수정할 항목");
		modifylabel.setFont(new Font("굴림", Font.BOLD, 12));
		modifylabel.setBounds(105, 26, 70, 15);
		this.add(modifylabel);
		
		menulabel = new JLabel("메뉴 수정");
		menulabel.setFont(new Font("굴림", Font.BOLD, 12));
		menulabel.setBounds(24, 107, 70, 19);
		this.add(menulabel);
		
		pricelabel = new JLabel("가격 수정");
		pricelabel.setFont(new Font("굴림", Font.BOLD, 12));
		pricelabel.setBounds(24, 161, 70, 19);
		this.add(pricelabel);
		
		modify_item = new JTextField();
		modify_item.setBounds(24, 51, 234, 21);
		modify_item.setEditable(false);
		modify_item.setText(m.getmName()+"  "+m.getmPrice());
		modify_item.setFont(new Font("굴림", Font.BOLD, 12));
		modify_item.setBackground(Color.white);
		this.add(modify_item);
		
		updateName = new JTextField();
		updateName.setFont(new Font("굴림", Font.BOLD, 12));
		updateName.setBounds(94, 107, 164, 21);
		this.add(updateName);
		
		updatePrice = new JTextField();
		updatePrice.setFont(new Font("굴림", Font.BOLD, 12));
		updatePrice.setBounds(94, 160, 164, 21);
		this.add(updatePrice);
		
		updatePrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if( c < 48 || c >57 ) {
					updatePrice.setText("");
					if( c != 8 && c!= 9 && c!= 13 && c!=10) {
					System.out.println("[ERROR]: 숫자를 입력하세요.");
					JOptionPane.showMessageDialog(null, "숫자를 입력하세요.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		check = new JButton("확인");
		check.setBounds(36, 212, 90, 25);
		this.add(check);
		
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(updateName.getText().length() == 0 || updatePrice.getText().length() == 0) {
					System.out.println("[ERROR]:모든 정보를 입력하세요");
					JOptionPane.showMessageDialog(null, "입력 오류", "ERROR", JOptionPane.ERROR_MESSAGE);
					return ;
				}else {
					
					m.setmName(updateName.getText());
					m.setmPrice(Integer.parseInt(updatePrice.getText()));
					int res = mf.getRc().updateMenu(m);
					
					if(res > 0) {
						System.out.println("메뉴 Update 성공");
						RightPanel1 rightPanel = new RightPanel1(mf);
						changePanel(rightPanel);
						dispose();
					}else if(res == -3) {
						System.out.println("[ERROR -3]: 중복된 메뉴이름입니다.");
						JOptionPane.showMessageDialog(null, "중복된 메뉴 이름!!", "ERROR", JOptionPane.ERROR_MESSAGE);;
					}else {
						System.out.println("[ERROR]:메뉴 Update 실패");
					}
					
					
				}
			}
		});
		
		cancel = new JButton("취소");
		cancel.setBounds(168, 212, 90, 25);
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
