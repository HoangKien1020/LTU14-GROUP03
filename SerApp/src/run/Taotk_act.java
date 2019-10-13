package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import conn.Msql;

public class Taotk_act implements ActionListener {
	JTextField id, pin, address, fullname, card, phone;
	JLabel status;
	App app;

	public Taotk_act(JTextField tcard, JTextField tpin, JTextField tid, JTextField tphone, JTextField tfullname,
			JTextField tadd, JLabel status, App a) {
		app = a;
		pin = tpin;
		card = tcard;
		this.status = status;
		id = tid;
		phone = tphone;
		address = tadd;
		fullname = tfullname;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean p = Msql.insert(id.getText(), fullname.getText(), pin.getText(), address.getText(), card.getText(),
				phone.getText(), "0", App.con);
		if(p) status.setText("status:tao thanh cong");
		else  status.setText("status:tao khong thanh cong");

	}

}
