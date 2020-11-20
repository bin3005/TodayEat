package view.commonview;

import static resource.R.component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import member.controller.MemberController;
import member.model.vo.Member;
import resource.R;
import restaurant.controller.RestaurantController;
import view.adminview.leftpanel.LeftPanel1;
import view.clientview.leftpanel.MenuPanel;
import view.commonview.rightpanel.RightPanel3;
import view.commonview.toppanel.TopPanel3;

public class RestaurantFrame extends JFrame implements R{ // 메인 프레임
	
	private int resNum = 0;
	private RestaurantController rc;
	private MemberController mc;
	private int prePage;
	

	public RestaurantFrame() { //메인 프레임 생성자 
		//////////////////
		///프레임 구조 잡기
		this.setTitle("오늘 뭐 먹지?");
		this.setBounds(250, 40, 1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		rc = new RestaurantController(); //컨트롤러 생성
		mc = new MemberController();

		////////////////////////
		////레프트 패널 생성
		component.put("rightPanel",  new RightPanel3(this));
		component.put("leftPanel",new MenuPanel(this, 0));
		component.put("topPanel", new TopPanel3(this));

		///////////////////
		///초기 배경사진 생성
		JLabel backImg = new JLabel();
		backImg.setIcon(new ImageIcon("images/restaurantBack.jpg"));
		backImg.setBounds(0, 0, 986, 763);
		component.put("backImg", backImg);
		this.add(backImg);

		this.setVisible(true);
	}


	public int getResNum() {
		return resNum;
	}

	public void setResNum(int resNum) {
		this.resNum = resNum;
	}

	

	
	public int getPrePage() {
		return prePage;
	}
	
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}



	public RestaurantController getRc() {
		return rc;
	}

	public void setRc(RestaurantController rc) {
		this.rc = rc;
	}

	

	public MemberController getMc() {
		return mc;
	}

	public void setMc(MemberController mc) {
		this.mc = mc;
	}
	
	
}
