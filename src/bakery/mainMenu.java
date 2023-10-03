package bakery;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;

public class mainMenu extends JFrame {
    private JButton btn_addUser;
    private JButton btn_generateBill1;
    private JButton btn_generateSalesReport1;
    private JButton btn_inventoryDetails1;
    private JButton btn_inventoryRequirement;
    private JButton btn_itemDetails2;
    private JButton btn_logout;
    private JLabel lbl_background;
    private JLabel lbl_billingMenu;
    private JLabel lbl_billingMenu1;
    private JPanel pnl_main;

    public mainMenu() {
        this.initComponents();
        this.centerFrame();
    }

    public void centerFrame() {
        Toolkit toolkit = this.getToolkit();
        Dimension size = toolkit.getScreenSize();
        this.setLocation(size.width / 2 - this.getWidth() / 2, size.height / 2 - this.getHeight() / 2);
    }

    private void initComponents() {
        this.pnl_main = new JPanel();
        this.lbl_billingMenu = new JLabel();
        this.lbl_billingMenu1 = new JLabel();
        this.btn_logout = new JButton();
        this.btn_inventoryRequirement = new JButton();
        this.btn_addUser = new JButton();
        this.btn_generateBill1 = new JButton();
        this.btn_inventoryDetails1 = new JButton();
        this.btn_itemDetails2 = new JButton();
        this.btn_generateSalesReport1 = new JButton();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Main menu");
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.lbl_billingMenu.setFont(new Font("Calibri", 0, 18));
        this.lbl_billingMenu.setText("Mangement menu");
        this.pnl_main.add(this.lbl_billingMenu);
        this.lbl_billingMenu.setBounds(440, 80, 160, 20);
        this.lbl_billingMenu1.setFont(new Font("Calibri", 0, 18));
        this.lbl_billingMenu1.setText("Billing menu");
        this.pnl_main.add(this.lbl_billingMenu1);
        this.lbl_billingMenu1.setBounds(130, 80, 130, 20);
        this.btn_logout.setIcon(new ImageIcon(this.getClass().getResource("/com.images/logout.png")));
        this.btn_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mainMenu.this.btn_logoutActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_logout);
        this.btn_logout.setBounds(610, 20, 50, 30);
        this.btn_inventoryRequirement.setFont(new Font("Calibri", 0, 12));
        this.btn_inventoryRequirement.setText("Generate inventory requirement pdf");
        this.btn_inventoryRequirement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mainMenu.this.btn_inventoryRequirementActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_inventoryRequirement);
        this.btn_inventoryRequirement.setBounds(70, 350, 260, 40);
        this.btn_addUser.setFont(new Font("Calibri", 0, 12));
        this.btn_addUser.setText("Add user");
        this.btn_addUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mainMenu.this.btn_addUserActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_addUser);
        this.btn_addUser.setBounds(380, 210, 260, 40);
        this.btn_generateBill1.setFont(new Font("Calibri", 0, 12));
        this.btn_generateBill1.setText("Update item in Bill / Generate bill pdf");
        this.btn_generateBill1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mainMenu.this.btn_generateBill1ActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_generateBill1);
        this.btn_generateBill1.setBounds(70, 210, 260, 40);
        this.btn_inventoryDetails1.setFont(new Font("Calibri", 0, 12));
        this.btn_inventoryDetails1.setText("Add / Delete item from inventory");
        this.btn_inventoryDetails1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mainMenu.this.btn_inventoryDetails1ActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_inventoryDetails1);
        this.btn_inventoryDetails1.setBounds(70, 280, 260, 40);
        this.btn_itemDetails2.setFont(new Font("Calibri", 0, 12));
        this.btn_itemDetails2.setText("Add / Delete / Update item details");
        this.btn_itemDetails2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mainMenu.this.btn_itemDetails2ActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_itemDetails2);
        this.btn_itemDetails2.setBounds(70, 140, 260, 40);
        this.btn_generateSalesReport1.setFont(new Font("Calibri", 0, 12));
        this.btn_generateSalesReport1.setText("Generate sales report");
        this.btn_generateSalesReport1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mainMenu.this.btn_generateSalesReport1ActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_generateSalesReport1);
        this.btn_generateSalesReport1.setBounds(380, 140, 260, 40);
        this.lbl_background.setIcon(new ImageIcon(this.getClass().getResource("/com.images/Background image.jpeg")));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 680, 480);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 680, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 481, 32767));
        this.pack();
    }

    private void btn_logoutActionPerformed(ActionEvent evt) {
        login l = new login();
        l.setVisible(true);
        this.dispose();
    }

    private void btn_itemDetails2ActionPerformed(ActionEvent evt) {
        itemList iL = new itemList();
        iL.setVisible(true);
        this.dispose();
    }

    private void btn_generateBill1ActionPerformed(ActionEvent evt) {
        generateBill gB = new generateBill();
        gB.setVisible(true);
        this.dispose();
    }

    private void btn_inventoryDetails1ActionPerformed(ActionEvent evt) {
        inventory i = new inventory();
        i.setVisible(true);
        this.dispose();
    }

    private void btn_inventoryRequirementActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog((Component)null, "Functionality not available till now. Plese return later");
    }

    private void btn_generateSalesReport1ActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog((Component)null, "Functionality not available till now. Plese return later");
    }

    private void btn_addUserActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog((Component)null, "Functionality not available till now. Plese return later");
    }

    public static void main(String[] args) {
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
            Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new mainMenu()).setVisible(true);
            }
        });
    }
}

