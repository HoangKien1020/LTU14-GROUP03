package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Act_nap implements ActionListener {

	JTextField id, pass;
	App ap;
	JLabel lb;

	public Act_nap(JTextField a, JTextField b, App as, JLabel l) {
		// TODO Auto-generated constructor stub
		ap = as;
		id = a;
		pass = b;
		lb = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int d = Integer.parseInt(id.getText());
			double k = Double.parseDouble(pass.getText());
			int a = ap.cps.naptien(d, k);
			if (a == 00)
				lb.setText("status:ok");
			else
				lb.setText("status:false");
		} catch (Exception e2) {
			// TODO: handle exception
			lb.setText("status:false");
		}
	}

}
