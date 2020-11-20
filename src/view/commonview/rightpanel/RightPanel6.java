package view.commonview.rightpanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.commonview.RestaurantFrame;


public class RightPanel6 extends JPanel implements rightpanel{ ///베스트 식당 패널
	private RestaurantFrame mf;
	
	public RightPanel6(RestaurantFrame mf) {
		////////////////////////
		//패널 구조 잡기
		this.mf = mf;
		this.setBounds(530, 186, 433, 549);
		this.setLayout(null);
		this.setBackground(new Color(255,0,0,0));
		this.setBorder(new LineBorder(Color.black,2));
		JLabel bestLabel = new JLabel(" Best 식당");
		bestLabel.setBounds(100, 90, 200, 45);
		bestLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		this.add(bestLabel);

		///////////////////
		///베스트식당라벨 생성
		String[] best1 = new String[3];
		best1 = mf.getRc().bestRestaurant().split("\n");
		
		JLabel best = new JLabel();
		best.setBounds(105, 120, 400, 360);
		best.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		best.setText("<html>"+best1[0]+"<br/><br/><br/>"+best1[1]+"<br/><br/><br/>"+best1[2]+"</html>");
		this.add(best);

		mf.add(this);
		
		mf.getContentPane().add(this);
	}
}
