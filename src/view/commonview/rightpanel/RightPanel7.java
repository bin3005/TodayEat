package view.commonview.rightpanel;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.commonview.RestaurantFrame;

public class RightPanel7 extends JPanel implements rightpanel{/////식당 위치 띄우는패널
	private RestaurantFrame mf;
	
	public RightPanel7(RestaurantFrame mf) {
		//////
		//패널 구조잡기
		this.mf = mf;
		this.setBounds(531, 186, 430, 549);	
		this.setLayout(null);
		this.setBackground(new Color(255,0,0,0));
		
		
		/////////////////////////
		///사진을 뛰울 라벨
		String url = "restaurantMap" + mf.getResNum();
		Image icon = new ImageIcon("images/restaurantMap/"+url+".png").getImage()
				.getScaledInstance(430,549,Image.SCALE_SMOOTH);
		
		JLabel restaurantMap = new JLabel(new ImageIcon(icon));
		restaurantMap.setBorder(new LineBorder(Color.black,2));
		restaurantMap.setBounds(0,0,430,549);
		this.add(restaurantMap);
		
		mf.add(this);
	}
}
