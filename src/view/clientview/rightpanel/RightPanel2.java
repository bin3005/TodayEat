package view.clientview.rightpanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import restaurant.model.vo.Restaurant;
import restaurant.model.vo.Review;
import view.commonview.RestaurantFrame;
import view.commonview.rightpanel.rightpanel;
import view.commonview.toppanel.TopPanel1;
import view.commonview.toppanel.toppanel;

public class RightPanel2 extends JPanel implements rightpanel { //리뷰 작성 패널
	private RestaurantFrame mf;
	public RightPanel2(RestaurantFrame mf) { //생성자
		////////////////////////////
		///패널 구조잡기
		this.mf = mf;
		
		this.setBounds(531, 186, 430, 549);
		this.setBackground(new Color(255,0,0,0));
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black,2));

		///////////////////////////
		////////////필요한 라벨 및 텍스트필드, 라디오버튼 생성
		JLabel viewName = new JLabel("작성자");
		JLabel viewTime = new JLabel("걸린 시간(분)");
		JLabel viewGrade = new JLabel("평점");
		JLabel viewContent = new JLabel("리뷰 내용");

		
		viewName.setBounds(26, 32, 50, 15);
		this.add(viewName);

		viewTime.setBounds(26, 81, 80, 15);
		this.add(viewTime);

		viewGrade.setBounds(26, 134, 50, 15);
		this.add(viewGrade);

		viewContent.setBounds(26, 220, 59, 15);
		this.add(viewContent);

		JTextArea textArea = new JTextArea(10, 5);// 리뷰내용
		textArea.setBounds(40, 240, 348, 190);
		this.add(textArea);

		JRadioButton radioBtn1 = new JRadioButton("1");
		radioBtn1.setBounds(113, 134, 36, 23);
		this.add(radioBtn1);

		JRadioButton radioBtn2 = new JRadioButton("2");
		radioBtn2.setBounds(174, 134, 36, 23);
		this.add(radioBtn2);

		JRadioButton radioBtn3 = new JRadioButton("3");
		radioBtn3.setBounds(230, 134, 36, 23);
		this.add(radioBtn3);

		JRadioButton radioBtn4 = new JRadioButton("4");
		radioBtn4.setBounds(294, 134, 36, 23);
		this.add(radioBtn4);

		JRadioButton radioBtn5 = new JRadioButton("5");
		radioBtn5.setBounds(346, 134, 36, 23);
		this.add(radioBtn5);

		ButtonGroup grade = new ButtonGroup(); //버튼 그루핑(하나만 눌리게)
		grade.add(radioBtn1);
		grade.add(radioBtn2);
		grade.add(radioBtn3);
		grade.add(radioBtn4);
		grade.add(radioBtn5);

		JButton createReviewBtn = new JButton("작성완료");
		createReviewBtn.setBounds(160, 482, 91, 23);
		this.add(createReviewBtn);

		JTextField textField_1 = new JTextField();// 작성자
		textField_1.setBounds(113, 29, 96, 21);
		textField_1.setEditable(false);
		
		textField_1.setText(loginedMember.get("loginedMember").getNickName());
		
		this.add(textField_1);
		textField_1.setColumns(10);

		JTextField textField_2 = new JTextField();// 걸린시간
		textField_2.setBounds(113, 78, 96, 21);
		this.add(textField_2);
		textField_2.setColumns(10);
		

		///////////////////////////////////////////////////////////////////////////////
		/////시간에 enter, tab, backspace, 숫자를 제외한 키 입력못받게 하기위한 이벤트
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if( c < 48 || c >57 ) {
					textField_2.setText("");
					if( c != 8 && c!= 9 && c!= 13 && c!=10) {
					System.out.println("[ERROR]: 숫자를 입력하세요.");
					JOptionPane.showMessageDialog(mf, "숫자를 입력하세요.","오류!!", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		JLabel radioCheck = new JLabel("0"); //라디오 버튼 체크한 숫자를 받기위한 라벨
		
		//////////////////////////////////////////////////
		//////// 라디오버튼 이벤트 (클릭되면 숫자를변경해줌)
		radioBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				radioCheck.setText("1");
			}
		});
		radioBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				radioCheck.setText("2");
			}
		});
		radioBtn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				radioCheck.setText("3");
			}
		});
		radioBtn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				radioCheck.setText("4");
			}
		});
		radioBtn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				radioCheck.setText("5");
			}
		});
		
		////////////////////////////////////
		//////////////// 리뷰작성완료 이벤트
		createReviewBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String reName = textField_1.getText();// 작성자
				String reComment = textArea.getText();
				
				int reTime = 0;
				if (!textField_2.getText().isEmpty()) {
					reTime = Integer.parseInt(textField_2.getText());
				}
				
				double reGrade = Double.parseDouble(radioCheck.getText());
				
				if( !reName.isEmpty() && !reComment.isEmpty() && !(reTime+"").isEmpty() && reTime != 0 
						&& reGrade != 0) {
					
					ArrayList<Restaurant> restList = mf.getRc().selectAllRestaurant();
					int rNum = restList.get(mf.getResNum()).getResNo();
					Review r = new Review(0,reName,reGrade, reTime, reComment, new Date(), rNum);
					int res = mf.getRc().insertReview(r);
					if ( res > 0) {
						System.out.println("리뷰 Insert 성공!");
						JOptionPane.showMessageDialog(mf, "리뷰가 작성되었습니다!!","리뷰 작성 완료", JOptionPane.INFORMATION_MESSAGE);
						ClientRightPanel1 rightpanel1 = new ClientRightPanel1(mf);
						changePanel(rightpanel1);
						TopPanel1 topPanel1 = new TopPanel1(mf);
						changePanel(topPanel1);
					}else {
						System.out.println("[ERROR]: 리뷰 Insert 실패!!");
						JOptionPane.showMessageDialog(mf, "리뷰 작성 실패!!","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					System.out.println("[ERROR]: 모든 정보를 입력하세요.");
					JOptionPane.showMessageDialog(mf, "모든 정보를 입력하세요.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
		
		
		/////////////////
		///프레임에 추가
		mf.add(this);
	}
	
	///////////////////
	///패널 변경 메소드
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
