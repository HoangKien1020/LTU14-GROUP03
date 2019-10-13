package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Xoa_act implements ActionListener {

	JTextField xoa;
	App ap;
	JLabel lb;

	public Xoa_act(JTextField x, App d, JLabel l) {
		xoa = x;
		ap = d;
		lb = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			String st = xoa.getText();
			int id = Integer.parseInt(st);
			int h = ap.cps.xoataikhoan(id);
			if (h == 0)
				lb.setText("status:thanh cong");
			else
				lb.setText("status:false");
		} catch (Exception es) {
			lb.setText("status:false");
		}

	}

}
