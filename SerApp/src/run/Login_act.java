package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_act implements ActionListener {
	JTextField use;
	JPasswordField pass;
	App frame;

	public Login_act(JTextField x, JPasswordField y, App jp) {
		use = x;
		pass = y;
		frame = jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		String a = use.getText();
//		if (!a.equals("admin"))
//			return;
//		a = new String(pass.getPassword());
//		if (!a.equals("dangcongcan"))
//			return;
//		System.out.println("aa");
		frame.login.setVisible(false);
		frame.lg=true;
		frame.showtao_tk();
	}

}
