package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Doi_act implements ActionListener {
	JTextField id, pass;
	App ap;
	JLabel lb;

	public Doi_act(JTextField a, JTextField b, App as, JLabel l) {
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
			int a = ap.cps.doimatkhau(d, pass.getText());
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
