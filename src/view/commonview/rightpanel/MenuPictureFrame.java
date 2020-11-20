package view.commonview.rightpanel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import resource.R;

public class MenuPictureFrame extends JFrame implements rightpanel { //메뉴 클릭시 띄울 팝업프레임

	public MenuPictureFrame(int resNum, int rowNum, String menuName) { //레스토랑 인덱스, 메뉴 인덱스. 메뉴이름 전달받는 생성자
		this.setBounds(1200, 200, 300, 300);
		this.setTitle(menuName);
		
		String menuPic =""+resNum+rowNum; //메뉴사진 이름 생성
		JLabel menuPicture = new JLabel(); //메뉴 사진이 들어갈 이미지
		Image icon = new ImageIcon("images/restaurantMenu/"+menuPic+".png").getImage()
				.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		menuPicture.setIcon(new ImageIcon(icon));  //라벨에 사진 추가
		
		this.add(menuPicture); //메뉴 사진 추가
		
		this.setVisible(true); 
	}
}
