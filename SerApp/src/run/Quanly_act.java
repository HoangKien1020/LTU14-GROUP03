package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import conn.Msql;

public class Quanly_act implements ActionListener {
	JTextField id1, id2;
	JCheckBox gui, nhan;
	App ap;

	public Quanly_act(JTextField sid1, JTextField sid2, JCheckBox sgui, JCheckBox snhan, App set) {
		id1 = sid1;
		id2 = sid2;
		gui = sgui;
		nhan = snhan;
		ap = set;
	}

	public void actionPerformed(ActionEvent e) {
		int id = 0;
		if (gui.isEnabled()) {
			if (nhan.isEnabled())
				id = 2;
			else
				id = 1;
		} else {
			if (nhan.isEnabled())
				id = 1;
		}
		if (id1.getText().equals(""))
			return;
		if (id2.getText().equals(""))
			return;
		String[][] rp = Msql.monitor(App.con,id, id1.getText(), id2.getText());
		String[] colum = { "id1", "id2", "money", "ly do","ngay" };
		ap.quanly.setVisible(false);
		ap.showrs(new JTable(rp, colum));
	}

}
