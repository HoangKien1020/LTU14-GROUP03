package Client;

import Remote.Bank;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientLoginUI extends JFrame {
    private JButton myButton;
    private JPanel rootPanel;
    private JTextField cardID;
    private JPasswordField pinPassword;

    public ClientLoginUI() {
        add(rootPanel);
        setTitle("Login");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardIDValue = cardID.getText();
                String pinPasswordValue = String.valueOf(pinPassword.getPassword());

                try {
                    // Looking up the registry for the remote object
                    Bank bank = (Bank) Naming.lookup("rmi://127.0.0.1:1099/RMI");
                    // Calling the remote method using the obtained object

                    // bank.createAccount("LE VAN LUYEN", "1234230120928449", "123456");

                    Global.token = bank.login(cardIDValue, pinPasswordValue);
                    if (Global.token == null){
                        JOptionPane.showMessageDialog(null, "Your infomation is not valid" );
                        return;
                    }

                    System.out.println("Token : " + Global.token );
                    Global.isTokenValid = bank.verifyToken(Global.token);
                    System.out.println("isTokenValid : " + Global.isTokenValid);
                    if (Global.isTokenValid) {
                        Global.accountBalance = bank.getBalance(cardIDValue, Global.token);
                        setVisible(false);
                        ClientAccountUI clientAccountUI = new ClientAccountUI();
                        clientAccountUI.setVisible(true);
                    }

                } catch (NotBoundException | RemoteException | MalformedURLException ex) {
                    ex.printStackTrace();

                }
            }
        });
    }


}
