package view.commonview.rightpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import restaurant.model.vo.Restaurant;
import view.commonview.RestaurantFrame;

public class RightPanel4 extends JPanel implements rightpanel {/// 식당검색패널
	private RestaurantFrame mf;
	private RightPanel4 rightPanel;
	JScrollPane scroller = new JScrollPane();
	int cnt = 1;

	public RightPanel4(RestaurantFrame mf) {
		rightPanel = this;
		this.mf = mf;
		this.setBackground(new Color(255, 0, 0, 0));
		this.setBounds(530, 186, 433, 549);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black, 2));

		// 프레임
		JLabel findName = new JLabel("검색: ");
		findName.setBounds(105, 66, 73, 23);
		this.add(findName);

		JTextField textField = new JTextField();
		textField.setBounds(140, 67, 144, 21);
		this.add(textField);

		JButton findButton = new JButton("검색");
		findButton.setBounds(305, 66, 57, 23);
		this.add(findButton);
		findButton.setFont(new Font("굴림", Font.BOLD, 10));
		findButton.setBackground(Color.black);
		findButton.setForeground(Color.white);

		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty()) {
					System.out.println("[ERROR]: 검색할 메뉴를 입력하세요.");
					JOptionPane.showMessageDialog(mf, "검색할 메뉴를 입력하세요.","ERROR", JOptionPane.WARNING_MESSAGE);
				} else {
					ArrayList<Restaurant> findmenu = mf.getRc().findMenu(textField.getText());
					String[][] findData = new String[findmenu.size()][4];
					String[] findCol = { "식당", "평점", "메뉴", "가격" };
					if (findmenu.size() == 0) { // 메뉴가 없으면!
						rightPanel.remove(scroller);

						String[][] no = new String[1][1];
						String[] noCol = new String[1];
						noCol[0] = "";
						no[0][0] = "메뉴가 없습니다.";
						DefaultTableModel model1 = new DefaultTableModel(no, noCol) {
							public boolean isCellEditable(int i, int c) {
								return false;
							}
						};

						JTable findList = new JTable(model1);
						findList.setFont(new Font("굴림", Font.BOLD, 13));
						findList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

						JScrollPane scroller1 = new JScrollPane(findList);
						scroller1.setBounds(27, 114, 377, 377);
						scroller = scroller1;
						rightPanel.add(scroller);
					} else { // 메뉴가 있으면

						rightPanel.remove(scroller);
						for (int i = 0; i < findmenu.size(); i++) {
							findData[i][0] = findmenu.get(i).getrName();
						}

						for (int i = 0; i < findmenu.size(); i++) {

							if (findmenu.get(i).getReGrade() < 1.5) {
								findData[i][1] = "★☆☆☆☆";
							} else if (findmenu.get(i).getReGrade() < 2.5) {
								findData[i][1] = "★★☆☆☆";
							} else if (findmenu.get(i).getReGrade() < 3.5) {
								findData[i][1] = "★★★☆☆";
							} else if (findmenu.get(i).getReGrade() < 4.5) {
								findData[i][1] = "★★★★☆";
							} else if (findmenu.get(i).getReGrade() < 5.5) {
								findData[i][1] = "★★★★★";
							} else if (findmenu.get(i).getReGrade() == 0) {
								findData[i][1] = " ";
							}
						}

						for (int i = 0; i < findmenu.size(); i++) {
							for (int j = 0; j < findmenu.get(i).getMenuList().size(); j++) {
								findData[i][2] = findmenu.get(i).getMenuList().get(j).getmName();
							}
						}

						for (int i = 0; i < findmenu.size(); i++) {
							for (int j = 0; j < findmenu.get(i).getMenuList().size(); j++) {
								if (findmenu.get(i).getMenuList().get(j).getmPrice() >= 1000) {
									String str = findmenu.get(i).getMenuList().get(j).getmPrice() + "";
									String str1 = "";
									int cnt = 0;

									for (int k = str.length() - 1; k >= 0; k--) {
										cnt++;
										char c = str.charAt(k);
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
									findData[i][3] = str;
								} else {
									findData[i][3] = findmenu.get(i).getMenuList().get(j).getmPrice() + "";
								}
							}
						}

						DefaultTableModel model = new DefaultTableModel(findData, findCol) {
							public boolean isCellEditable(int i, int c) {
								return false;
							}
						};

						JTable findList = new JTable(model);
						findList.setFont(new Font("굴림", Font.BOLD, 13));
						findList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

						JScrollPane scroller1 = new JScrollPane(findList);
						scroller1.setBounds(27, 114, 377, 377);
						scroller = scroller1;
						rightPanel.add(scroller);
					}

				}
			}
		});
		mf.add(this);

	}
}
