package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientAccountUI extends JFrame {
    private JPanel rootPanel;
    private JButton balanceButton;

    public ClientAccountUI() {
        add(rootPanel);
        setTitle("Account");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Your current account balance: " + Global.accountBalance + "VND");
            }
        });
    }
}
