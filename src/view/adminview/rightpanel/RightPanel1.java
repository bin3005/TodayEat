package view.adminview.rightpanel;

import static resource.R.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import restaurant.model.vo.Menu;
import restaurant.model.vo.Restaurant;
import view.commonview.RestaurantFrame;
import view.commonview.leftpanel.LoginPanel;
import view.commonview.rightpanel.MenuPictureFrame;
import view.commonview.rightpanel.rightpanel;
import view.commonview.toppanel.toppanel;

public class RightPanel1 extends JPanel implements rightpanel { /////////// 식당 메뉴, 리뷰 보여주는패널
	private RestaurantFrame mf;

	public RightPanel1(RestaurantFrame mf) { // 생성자
		
		/////////////////////////////
		/////// 프레임 구조 잡기
		
		ArrayList<Restaurant> list = mf.getRc().selectAllRestaurant();
		this.mf = mf;

		this.setBackground(new Color(255, 0, 0, 0));
		this.setBounds(530, 186, 433, 549);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black, 2));

		/////////////////////
		///// 메뉴 테이블 생성
		String[][] menuData = new String[list.get(mf.getResNum()).getMenuList().size()][2];
		String[] menuCol = { "메뉴", "가격" };

		for (int i = 0; i < list.get(mf.getResNum()).getMenuList().size(); i++) {

			menuData[i][0] = list.get(mf.getResNum()).getMenuList().get(i).getmName();
		}

		// 숫자 3자리마다 , 찍는 부분

		for (int i = 0; i < list.get(mf.getResNum()).getMenuList().size(); i++) {
			if ((list.get(mf.getResNum()).getMenuList().get(i).getmPrice()) >= 1000) {
				String str = (list.get(mf.getResNum()).getMenuList().get(i).getmPrice()) + "";
				String str1 = "";
				int cnt = 0;

				for (int j = str.length() - 1; j >= 0; j--) {
					cnt++;
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
				menuData[i][1] = str;
			}else {
				menuData[i][1] = list.get(mf.getResNum()).getMenuList().get(i).getmPrice()+"";
			}
		}
		DefaultTableModel model1 = new DefaultTableModel(menuData, menuCol) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		JTable menuList = new JTable(model1);

		menuList.setFont(new Font("굴림", Font.BOLD, 13));
		menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroller = new JScrollPane(menuList);

		this.add(scroller);
		scroller.setBounds(20, 15, 387, 270);

		
		/////////////// 메뉴 더블 클릭시 사진 띄우는 이벤트

		menuList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String menuName = (String) menuList.getValueAt(menuList.getSelectedRow(), 0);
					new MenuPictureFrame(mf.getResNum(), menuList.getSelectedRow(), menuName);
				}
			}
		});

		//////////////////////////////////////////////////////////////////
		/////////// 메뉴 추가 수정 삭제

		JButton addmenu = new JButton("메뉴 추가");
		addmenu.setBounds(41, 300, 90, 25);
		addmenu.setFont(new Font("굴림", Font.BOLD, 11));
		addmenu.setBackground(Color.black);
		addmenu.setForeground(Color.white);
		this.add(addmenu);

		addmenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new AddMenuFrame(mf);
			}
		});

		JButton modifymenu = new JButton("메뉴 수정");
		modifymenu.setBounds(172, 300, 90, 25);
		modifymenu.setFont(new Font("굴림", Font.BOLD, 11));
		modifymenu.setBackground(Color.black);
		modifymenu.setForeground(Color.white);
		this.add(modifymenu);
		
		modifymenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menuList.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "수정할 메뉴 클릭 후 버튼을 눌러주세요", "경고", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				int row = menuList.getSelectedRow();
								
				ArrayList<Restaurant> restList = mf.getRc().selectAllRestaurant();
				int menuNo =  restList.get(mf.getResNum()).getMenuList().get(row).getMenuNo();
				String mName = restList.get(mf.getResNum()).getMenuList().get(row).getmName();
				int mPrice = restList.get(mf.getResNum()).getMenuList().get(row).getmPrice();
				int resNo = restList.get(mf.getResNum()).getResNo();
				Menu m = new Menu(menuNo, mName, mPrice, resNo);
				
				new UpdateMenuFrame(mf, m);
			}
		});

		JButton deletemenu = new JButton("메뉴 삭제");
		deletemenu.setBounds(303, 300, 90, 25);
		deletemenu.setFont(new Font("굴림", Font.BOLD, 11));
		deletemenu.setBackground(Color.black);
		deletemenu.setForeground(Color.white);
		this.add(deletemenu);

		deletemenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = menuList.getSelectedRow();

				if (row != -1) {
					int result = JOptionPane.showConfirmDialog(null, (String) menuList.getValueAt(row, 0)
																	+ " 메뉴를 삭제하시겠습니까?", "경고",
							JOptionPane.OK_CANCEL_OPTION);
					if (result == 0) { // OK=0 , Cancel=2 리턴
						ArrayList<Restaurant> restList = mf.getRc().selectAllRestaurant();
						int menuNo =  restList.get(mf.getResNum()).getMenuList().get(row).getMenuNo();
						String mName = restList.get(mf.getResNum()).getMenuList().get(row).getmName();
						int mPrice = restList.get(mf.getResNum()).getMenuList().get(row).getmPrice();
						int resNo = restList.get(mf.getResNum()).getResNo();
						Menu m = new Menu(menuNo, mName, mPrice, resNo);
						int res = mf.getRc().deleteMenu(m);
						if ( res > 0) {
							System.out.println("메뉴 Delete 성공");
						}else {
							System.out.println("[ERROR]: 메뉴 Delete 실패");
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "삭제 취소", "완료", JOptionPane.INFORMATION_MESSAGE);
						return ;
					}
					RightPanel1 rightPanel1 = new RightPanel1(mf);
					changePanel(rightPanel1);
				}else {
					JOptionPane.showMessageDialog(null, "삭제할 메뉴 클릭 후 버튼을 눌러주세요", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		//////////////////////////////////////////////////////////////////

		//////////////////////////////
		/////////// 리뷰 클릭시 데이터 띄우는 테이블 생성
		SimpleDateFormat sd = new SimpleDateFormat("yy/MM/dd hh:mm"); // 리뷰 시간 포멧

		String[][] reviewData = new String[list.get(mf.getResNum()).getReviewList().size()][5];
		String[] reviewCol = { "리뷰 번호", "작성자", "평점", "내용", "작성시간" };

		// 데이터 삽입
		int cnt = 0;
		for (int i = list.get(mf.getResNum()).getReviewList().size() - 1; i >= 0; i--) {
			reviewData[cnt][0] = list.get(mf.getResNum()).getReviewList().get(i).getReviewNo() + "";
			reviewData[cnt][1] = list.get(mf.getResNum()).getReviewList().get(i).getViewName();
			if (list.get(mf.getResNum()).getReviewList().get(i).getViewGrade() < 1.5) {
				reviewData[cnt][2] = "★☆☆☆☆";
			} else if (list.get(mf.getResNum()).getReviewList().get(i).getViewGrade() < 2.5) {
				reviewData[cnt][2] = "★★☆☆☆";
			} else if (list.get(mf.getResNum()).getReviewList().get(i).getViewGrade() < 3.5) {
				reviewData[cnt][2] = "★★★☆☆";
			} else if (list.get(mf.getResNum()).getReviewList().get(i).getViewGrade() < 4.5) {
				reviewData[cnt][2] = "★★★★☆";
			} else if (list.get(mf.getResNum()).getReviewList().get(i).getViewGrade() < 5.5) {
				reviewData[cnt][2] = "★★★★★";
			}
			reviewData[cnt][3] = list.get(mf.getResNum()).getReviewList().get(i).getComment();
			reviewData[cnt++][4] = sd.format(list.get(mf.getResNum()).getReviewList().get(i).getToday());
		}

		DefaultTableModel model = new DefaultTableModel(reviewData, reviewCol) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		

		JTable reviewList = new JTable(model);
		reviewList.getColumn("리뷰 번호").setPreferredWidth(45);
		reviewList.getColumn("평점").setPreferredWidth(50);
		reviewList.getColumn("작성자").setPreferredWidth(50);
		reviewList.setFont(new Font("굴림", Font.BOLD, 10));
		JScrollPane scroller1 = new JScrollPane(reviewList);
		reviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(scroller1);
		scroller1.setBounds(20, 339, 387, 168);

		//가운데정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = reviewList.getColumnModel();
		
		
		tcm.getColumn(0).setCellRenderer(dtcr); 
		
		
		/////////////////////////////////////
		/////// 리뷰 작성버튼

		JButton reviewButton = new JButton("리뷰 작성");
		reviewButton.setBounds(200, 516, 91, 23);
		this.add(reviewButton);
		reviewButton.setFont(new Font("굴림", Font.BOLD, 10));
		reviewButton.setBackground(Color.black);
		reviewButton.setForeground(Color.white);

		reviewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(loginedMember.get("loginedMember")!=null) {
					RightPanel2 rightPanel2 = new RightPanel2(mf);
					changePanel(rightPanel2);
				}else {
					LoginPanel loginPanel = new LoginPanel(mf);
					changePanel(loginPanel);
				}
			

			}
		});

		///////////////////////////////////////
		/// 리뷰 보기 버튼
		JButton reviewShowButton = new JButton("리뷰 보기");
		reviewShowButton.setBounds(315, 516, 91, 23);
		this.add(reviewShowButton);
		reviewShowButton.setFont(new Font("굴림", Font.BOLD, 10));
		reviewShowButton.setBackground(Color.black);
		reviewShowButton.setForeground(Color.white);

		reviewShowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (reviewList.getSelectedRow() == -1) {
					return;
				} else {
					JOptionPane.showMessageDialog(mf, reviewData[reviewList.getSelectedRow()][3],
							reviewData[reviewList.getSelectedRow()][1] + "님의 리뷰", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		

		///////////////////////////////////////////////
		/////////////////////////// 프레임에 추가
		mf.add(this);

	}

	////////////////////////////////////
	////// 패널 변경 메소드
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
