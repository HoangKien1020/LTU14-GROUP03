package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import conn.Connect;
import conn.Msql;
import luan.Code;

public class App extends JFrame implements ActionListener {
	protected static Connection con;
	protected boolean lg = true;
	protected Code cps;

	public static void main(String[] args) {
		con = Connect.getConn();
		new App();
	}

	public App() {
		initlogin();
		inittao_tk();
		initql();
		initxoa();
		initdoink();
		initnap();
		initxoa();
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		initdoink();

		initmenu();
		this.setVisible(true);
		this.setSize(500, 385);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cps = new Code(con);
		showlogin();
	};

	protected void showdoimk() {
		doimk.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(doimk, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap(16, Short.MAX_VALUE)
						.addComponent(doimk, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		getContentPane().setLayout(groupLayout);
	}

	void shownap() {
		naptien.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(naptien, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap().addComponent(naptien, GroupLayout.DEFAULT_SIZE,
						325, Short.MAX_VALUE)));

		getContentPane().setLayout(groupLayout);
	}

	void initdoink() {
		doimk = new JPanel();
		doimk.setVisible(false);
		JLabel lblDoiMk = new JLabel("doi mk");

		JLabel lblId = new JLabel("id");

		JLabel lblNewPin = new JLabel("new pin");

		JTextField textField = new JTextField();
		textField.setColumns(10);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnAsubmit = new JButton("asubmit");

		JLabel lblStatus_2 = new JLabel("status");
		GroupLayout gl_doimk = new GroupLayout(doimk);
		gl_doimk.setHorizontalGroup(gl_doimk.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_doimk.createSequentialGroup().addGroup(gl_doimk.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_doimk.createSequentialGroup().addContainerGap().addComponent(textField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_doimk.createSequentialGroup().addGap(34).addComponent(lblId)))
						.addPreferredGap(ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
						.addGroup(gl_doimk.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewPin, Alignment.LEADING).addComponent(textField_1, Alignment.LEADING,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(46))
				.addGroup(gl_doimk.createSequentialGroup().addGap(167).addComponent(btnAsubmit).addContainerGap(213,
						Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_doimk
						.createSequentialGroup().addGap(177).addGroup(gl_doimk.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblStatus_2).addComponent(lblDoiMk))
						.addContainerGap(227, Short.MAX_VALUE)));
		gl_doimk.setVerticalGroup(gl_doimk.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_doimk.createSequentialGroup().addContainerGap().addComponent(lblDoiMk).addGap(33)
						.addGroup(gl_doimk.createParallelGroup(Alignment.BASELINE).addComponent(lblId)
								.addComponent(lblNewPin, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_doimk.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(56).addComponent(lblStatus_2).addGap(34).addComponent(btnAsubmit)
						.addContainerGap(61, Short.MAX_VALUE)));
		doimk.setLayout(gl_doimk);
		btnAsubmit.addActionListener(new Doi_act(textField, textField_1, this, lblStatus_2));
	}

	protected void initnap() {
		naptien = new JPanel();
		JLabel lnNaptien = new JLabel("naptien");

		JLabel lblId = new JLabel("id");

		JLabel lblSotien = new JLabel("sotien");

		JLabel lblStatus = new JLabel("status");

		JTextField textField = new JTextField();
		textField.setColumns(10);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnSubmit = new JButton("submit");
		GroupLayout gl_naptien = new GroupLayout(naptien);
		gl_naptien.setHorizontalGroup(gl_naptien.createParallelGroup(Alignment.LEADING).addGroup(gl_naptien
				.createSequentialGroup().addGap(40)
				.addGroup(gl_naptien.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
				.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
				.addGroup(gl_naptien.createParallelGroup(Alignment.LEADING).addComponent(lblSotien).addComponent(
						textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(46))
				.addGroup(gl_naptien.createSequentialGroup().addGap(191).addComponent(btnSubmit).addContainerGap(197,
						Short.MAX_VALUE))
				.addGroup(gl_naptien
						.createSequentialGroup().addGap(199).addGroup(gl_naptien.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblStatus).addComponent(lnNaptien))
						.addContainerGap(205, Short.MAX_VALUE)));
		gl_naptien.setVerticalGroup(gl_naptien.createParallelGroup(Alignment.LEADING).addGroup(gl_naptien
				.createSequentialGroup().addContainerGap().addComponent(lnNaptien).addGap(36)
				.addGroup(
						gl_naptien.createParallelGroup(Alignment.BASELINE).addComponent(lblId).addComponent(lblSotien))
				.addGap(18)
				.addGroup(gl_naptien.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(76).addComponent(lblStatus).addGap(18).addComponent(btnSubmit)
				.addContainerGap(76, Short.MAX_VALUE)));
		btnSubmit.addActionListener(new Act_nap(textField, textField_1, this, lblStatus));
		naptien.setLayout(gl_naptien);
		naptien.setVisible(false);
	}

	protected void showxoa() {
		xoa.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(xoa, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(xoa, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(21, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	protected void showrs(JTable table_1) {
		scrollPane.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		scrollPane.setViewportView(table_1);
		getContentPane().setLayout(groupLayout);
	}

	protected void initxoa() {
		xoa = new JPanel();

		JLabel lblXoaTaiKhoan = new JLabel("xoa tai khoan");

		JButton btnSubmit = new JButton("submit");

		JLabel lblId = new JLabel("id");

		JTextField textField = new JTextField();
		textField.setColumns(10);

		JLabel lblStatus_1 = new JLabel("status:");
		GroupLayout gl_xoa = new GroupLayout(xoa);
		gl_xoa.setHorizontalGroup(gl_xoa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_xoa.createSequentialGroup().addGap(151)
						.addGroup(gl_xoa.createParallelGroup(Alignment.LEADING).addComponent(lblXoaTaiKhoan)
								.addComponent(lblStatus_1)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSubmit).addComponent(lblId))
						.addContainerGap(195, Short.MAX_VALUE)));
		gl_xoa.setVerticalGroup(gl_xoa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_xoa.createSequentialGroup()
						.addComponent(lblXoaTaiKhoan, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(lblId).addGap(18)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(34).addComponent(lblStatus_1).addGap(28).addComponent(btnSubmit)
						.addContainerGap(92, Short.MAX_VALUE)));
		xoa.setLayout(gl_xoa);
		btnSubmit.addActionListener(new Xoa_act(textField, this, lblStatus_1));
		xoa.setVisible(false);
	}

	private void initmenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("chuc nang");
		menuBar.add(mnFile);

		mntmXemTk = new JMenuItem("xem tk");
		mntmXemTk.addActionListener(this);
		mnFile.add(mntmXemTk);

		mntmQuanlyTk = new JMenuItem("quanly tk");
		mntmQuanlyTk.addActionListener(this);
		mnFile.add(mntmQuanlyTk);

		mntmTaoTk = new JMenuItem("tao tk");
		mntmTaoTk.addActionListener(this);
		mnFile.add(mntmTaoTk);

		mntmDoiMatKhau = new JMenuItem("doi mat khau");
		mntmDoiMatKhau.addActionListener(this);
		mnFile.add(mntmDoiMatKhau);

		mntmXoaTaiKhoan = new JMenuItem("xoa tai khoan");
		mntmXoaTaiKhoan.addActionListener(this);
		mnFile.add(mntmXoaTaiKhoan);

		mntmNap = new JMenuItem("nap");
		mntmNap.addActionListener(this);
		mnFile.add(mntmNap);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (lg) {
			closepanel();
			if (e.getSource() == mntmXemTk) {
				String[][] res = Msql.getUser(con);
				String[] colum = { "id", "full name", "address", "phone" };
				showrs(new JTable(res, colum));
			} else if (e.getSource() == mntmNap) {
				shownap();
			} else if (e.getSource() == mntmXoaTaiKhoan) {
				showxoa();
			} else if (e.getSource() == mntmQuanlyTk) {
				showql();
			} else if (e.getSource() == mntmTaoTk) {
				showtao_tk();
			}
		}

	}

	protected void initql() {
		quanly = new JPanel();

		JLabel lblQuanLyDd = new JLabel("quan ly giao dich");
		JTextField textField = new JTextField();
		textField.setColumns(10);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnSubmit_2 = new JButton("submit");

		JLabel lblId = new JLabel("id1");

		JLabel lblId_1 = new JLabel("id2");

		JCheckBox chckbxNguoiGui = new JCheckBox("nguoi gui");

		JCheckBox chckbxNguoiNhan = new JCheckBox("nguoi nhan");
		GroupLayout gl_quanly = new GroupLayout(quanly);
		gl_quanly.setHorizontalGroup(gl_quanly.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_quanly.createSequentialGroup().addGap(46)
						.addGroup(gl_quanly.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_quanly.createSequentialGroup().addGap(10).addComponent(lblId))
								.addComponent(chckbxNguoiGui))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_quanly.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId_1).addComponent(chckbxNguoiNhan))
						.addGap(12))
				.addGroup(gl_quanly.createSequentialGroup().addGap(205).addComponent(btnSubmit_2).addContainerGap(200,
						Short.MAX_VALUE))
				.addGroup(gl_quanly.createSequentialGroup().addGap(178).addComponent(lblQuanLyDd).addContainerGap(192,
						Short.MAX_VALUE)));
		gl_quanly.setVerticalGroup(gl_quanly.createParallelGroup(Alignment.LEADING).addGroup(gl_quanly
				.createSequentialGroup().addGap(9)
				.addComponent(lblQuanLyDd, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_quanly.createParallelGroup(Alignment.BASELINE).addComponent(lblId_1).addComponent(lblId))
				.addGap(18)
				.addGroup(gl_quanly.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(33)
				.addGroup(gl_quanly.createParallelGroup(Alignment.BASELINE).addComponent(chckbxNguoiGui)
						.addComponent(chckbxNguoiNhan))
				.addGap(55).addComponent(btnSubmit_2).addContainerGap(35, Short.MAX_VALUE)));
		quanly.setLayout(gl_quanly);
		btnSubmit_2.addActionListener(new Quanly_act(textField, textField_1, chckbxNguoiGui, chckbxNguoiNhan, this));
		quanly.setVisible(false);
	}

	protected void showql() {
		quanly.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(quanly, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(quanly, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(56, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	protected void showtao_tk() {
		tao_tk.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tao_tk, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tao_tk, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(134, Short.MAX_VALUE)));

		getContentPane().setLayout(groupLayout);
	}

	/**
	 * tao tai khoan
	 */
	protected void inittao_tk() {
		tao_tk = new JPanel();
		JLabel lblTaoTk = new JLabel("tao tk");

		JLabel Cardno = new JLabel("cardno");

		JLabel lblPin = new JLabel("pin");

		JTextField tcard = new JTextField();
		tcard.setColumns(10);

		JTextField tpin = new JTextField();
		tpin.setColumns(10);

		JTextField tid = new JTextField();
		tid.setColumns(10);

		JLabel Address = new JLabel("address");

		JLabel Phone = new JLabel("phone");

		JLabel FullName = new JLabel("full name");

		JTextField tphone = new JTextField();
		tphone.setColumns(10);

		JTextField tname = new JTextField();
		tname.setColumns(10);

		JButton btnSubmit_1 = new JButton("submit");

		JLabel lblNewLabel = new JLabel("Id");

		JTextField tadd = new JTextField();
		tadd.setColumns(10);

		JLabel lblStatus = new JLabel("status");
		GroupLayout gl_tao_tk = new GroupLayout(tao_tk);
		gl_tao_tk.setHorizontalGroup(gl_tao_tk.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tao_tk.createSequentialGroup().addGroup(gl_tao_tk.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tao_tk.createSequentialGroup().addContainerGap()
								.addGroup(gl_tao_tk.createParallelGroup(Alignment.LEADING).addComponent(Cardno)
										.addComponent(tcard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(Phone).addComponent(
												tphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_tao_tk.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_tao_tk.createSequentialGroup().addGap(44).addComponent(lblTaoTk)
												.addPreferredGap(ComponentPlacement.RELATED, 174, Short.MAX_VALUE))
										.addGroup(Alignment.TRAILING, gl_tao_tk.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_tao_tk.createParallelGroup(Alignment.LEADING)
														.addComponent(tname, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(tpin, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(FullName).addComponent(lblPin))
												.addGap(68)))
								.addGroup(
										gl_tao_tk.createParallelGroup(Alignment.LEADING)
												.addComponent(tadd, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel)
												.addGroup(gl_tao_tk.createParallelGroup(Alignment.LEADING, false)
														.addComponent(Address).addComponent(tid, Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_tao_tk.createSequentialGroup().addGap(227).addComponent(btnSubmit_1))
						.addGroup(gl_tao_tk.createSequentialGroup().addContainerGap().addComponent(lblStatus)))
						.addContainerGap()));
		gl_tao_tk.setVerticalGroup(gl_tao_tk.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tao_tk.createSequentialGroup().addComponent(lblTaoTk)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_tao_tk.createParallelGroup(Alignment.BASELINE).addComponent(Cardno)
								.addComponent(lblNewLabel).addComponent(lblPin))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_tao_tk.createParallelGroup(Alignment.BASELINE)
								.addComponent(tcard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tpin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(47)
						.addGroup(gl_tao_tk.createParallelGroup(Alignment.BASELINE).addComponent(Phone)
								.addComponent(Address).addComponent(FullName))
						.addGap(18)
						.addGroup(gl_tao_tk.createParallelGroup(Alignment.BASELINE)
								.addComponent(tphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tadd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(44).addComponent(lblStatus).addGap(27).addComponent(btnSubmit_1)
						.addContainerGap(95, Short.MAX_VALUE)));
		tao_tk.setLayout(gl_tao_tk);
		btnSubmit_1.addActionListener(new Taotk_act(tcard, tpin, tid, tphone, tname, tadd, lblStatus, this));
		tao_tk.setVisible(false);

	}

	/**
	 * login
	 */
	protected void showlogin() {
		login.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(login, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(12, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(login, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(33, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * khoi tao form login
	 */
	protected void initlogin() {
		login = new JPanel();
		JLabel lblUser = new JLabel("user");
		JLabel lblPass = new JLabel("pass");
		JTextField lguser = new JTextField();
		lguser.setColumns(10);
		JPasswordField lgpass = new JPasswordField();
		JButton btnSubmit = new JButton("submit");
		
		JLabel lblLoginAdmin = new JLabel("login admin");
		GroupLayout gl_login = new GroupLayout(login);
		gl_login.setHorizontalGroup(
			gl_login.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_login.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_login.createParallelGroup(Alignment.LEADING)
						.addComponent(lguser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUser))
					.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
					.addGroup(gl_login.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPass)
						.addComponent(lgpass, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(47, Short.MAX_VALUE))
				.addGroup(gl_login.createSequentialGroup()
					.addGap(172)
					.addGroup(gl_login.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLoginAdmin)
						.addComponent(btnSubmit))
					.addContainerGap(216, Short.MAX_VALUE))
		);
		gl_login.setVerticalGroup(
			gl_login.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_login.createSequentialGroup()
					.addGap(20)
					.addComponent(lblLoginAdmin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_login.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_login.createSequentialGroup()
							.addGap(133)
							.addComponent(btnSubmit))
						.addGroup(gl_login.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_login.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPass)
								.addComponent(lblUser))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_login.createParallelGroup(Alignment.BASELINE)
								.addComponent(lguser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lgpass, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(209, Short.MAX_VALUE))
		);
		login.setLayout(gl_login);
		btnSubmit.addActionListener(new Login_act(lguser, lgpass, this));
		login.setVisible(false);

	}

	/**
	 * ban demo
	 */
	protected static final long serialVersionUID = 1L;
	protected JPanel login;
	protected JPanel tao_tk;
	protected JTable table;
	protected JPanel quanly;
	protected JScrollPane scrollPane;
	private JMenuItem mntmQuanlyTk;
	private JMenuItem mntmXemTk;
	private JMenuItem mntmTaoTk;
	private JMenuItem mntmDoiMatKhau;
	private JMenuItem mntmXoaTaiKhoan;
	private JMenuItem mntmNap;
	private JPanel xoa;
	private JPanel doimk;
	private JPanel naptien;

	private void closepanel() {
		login.setVisible(false);
		tao_tk.setVisible(false);
		quanly.setVisible(false);
		xoa.setVisible(false);
		doimk.setVisible(false);
		naptien.setVisible(false);

	}
}
