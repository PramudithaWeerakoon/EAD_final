package bakery;

import Main.BakeryManagementApp;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class login extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private JButton btn_Login;
    private JButton btn_Back; // New Back button
    private JComboBox<String> cbo_UserType;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JLabel lbl_background;
    private JPasswordField txt_Password;
    private JTextField txt_Username;

    public login() {
        this.initComponents();
        this.centerFrame();
        this.conn = ConnectToDatabase.getConnection();

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\anuga_2023_bread_bakery.png");
        lbl_background.setIcon(backgroundImage);
        lbl_background.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
    }


    public void centerFrame() {
        Toolkit toolkit = this.getToolkit();
        Dimension size = toolkit.getScreenSize();
        this.setLocation(size.width / 2 - this.getWidth() / 2, size.height / 2 - this.getHeight() / 2);
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.txt_Username = new JTextField();
        this.txt_Password = new JPasswordField();
        this.cbo_UserType = new JComboBox();
        this.btn_Login = new JButton();
        this.btn_Back = new JButton(); // Initialize the Back button
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Login");
        this.setResizable(false);
        this.setSize(new Dimension(800, 800));
        this.jPanel1.setLayout((LayoutManager)null);
        this.jLabel1.setFont(new Font("Calibri", 1, 24));
        this.jLabel1.setText("ANUGA BREAD & BAKERY");
        this.jPanel1.add(this.jLabel1);
        this.jLabel1.setBounds(170, 40, 290, 50);
        this.jLabel2.setFont(new Font("Calibri", 0, 14));
        this.jLabel2.setText("User type : ");
        this.jPanel1.add(this.jLabel2);
        this.jLabel2.setBounds(140, 250, 80, 17);
        this.jLabel3.setFont(new Font("Calibri", 0, 14));
        this.jLabel3.setText("Username : ");
        this.jPanel1.add(this.jLabel3);
        this.jLabel3.setBounds(140, 150, 80, 17);
        this.jLabel4.setFont(new Font("Calibri", 0, 14));
        this.jLabel4.setText("Password : ");
        this.jPanel1.add(this.jLabel4);
        this.jLabel4.setBounds(140, 200, 80, 17);
        this.txt_Username.setFont(new Font("Calibri", 0, 14));
        this.jPanel1.add(this.txt_Username);
        this.txt_Username.setBounds(220, 143, 250, 30);
        this.txt_Password.setFont(new Font("Calibri", 0, 14));
        this.jPanel1.add(this.txt_Password);
        this.txt_Password.setBounds(220, 190, 250, 30);
        this.cbo_UserType.setFont(new Font("Calibri", 0, 14));
        this.cbo_UserType.setModel(new DefaultComboBoxModel(new String[]{"Admin", "Manager", "Billing Executive"}));
        this.jPanel1.add(this.cbo_UserType);
        this.cbo_UserType.setBounds(220, 240, 250, 30);
        this.btn_Login.setText("Login");
        this.btn_Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_LoginActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.btn_Login);
        this.btn_Login.setBounds(280, 320, 90, 30);

        // Configure the Back button
        this.btn_Back.setText("Back");
        this.btn_Back.setFont(new Font("Calibri", Font.PLAIN, 14));
        this.btn_Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_BackActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.btn_Back);
        this.btn_Back.setBounds(180, 320, 90, 30);

        this.lbl_background.setIcon(new ImageIcon("C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\logout.png"));
        this.jPanel1.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 640, 450);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -1, 636, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -1, 446, 32767));
        this.pack();
    }

    private void btn_LoginActionPerformed(ActionEvent evt) {
        String sql = "select uId, uName, uPassword, uActive, uType from user where (uName = ? and uPassword = ? and uType = ?)";

        try {
            int count = 0;
            boolean userLoginCredentials = false;
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, this.txt_Username.getText());
            this.pst.setString(2, this.txt_Password.getText());
            this.pst.setString(3, this.cbo_UserType.getSelectedItem().toString());

            int var5;
            for(this.rs = this.pst.executeQuery(); this.rs.next(); var5 = this.rs.getInt(1)) {
                ++count;
            }

            if (count > 0) {
                userLoginCredentials = true;
            }

            if (userLoginCredentials) {
                String access = this.cbo_UserType.getSelectedItem().toString();
                if (access != "Admin" && access == "Manager") {
                }

                JOptionPane.showMessageDialog((Component)null, "Login successful");
                mainMenu j = new mainMenu();
                j.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog((Component)null, "Username and password incorrect");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage());
        } finally {
            try {
                this.rs.close();
                this.pst.close();
            } catch (Exception var14) {
            }

        }

    }
    private void btn_BackActionPerformed(ActionEvent evt) {
        // Close the current login frame
        this.dispose();

        // Create an instance of BakeryManagementApp
        BakeryManagementApp bakeryApp = new BakeryManagementApp();

        // Call the createLoginPanel method from the BakeryManagementApp instance
        JPanel loginPanel = bakeryApp.createLoginPanel();

        // Create a new frame and display the loginPanel
        JFrame frame = new JFrame();
        frame.getContentPane().add(loginPanel);
        frame.pack();
        frame.setVisible(true);
    }

    static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                UIManager.LookAndFeelInfo info = var1[var3];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException var5) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new login()).setVisible(true);
            }
        });
    }
}