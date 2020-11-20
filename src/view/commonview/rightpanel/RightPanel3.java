package view.commonview.rightpanel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.commonview.RestaurantFrame;

public class RightPanel3 extends JPanel implements rightpanel { ////처음 기본 화면프레임
	private RestaurantFrame mf;
	
	public RightPanel3(RestaurantFrame mf) {
		this.mf = mf;
		this.setBounds(530, 186, 433, 549);
		this.setLayout(null);
		this.setBackground(new Color(255,0,0,0));
		this.setBorder(new LineBorder(Color.black,2));
		mf.add(this);
	}
}
