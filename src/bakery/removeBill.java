package bakery;


import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class removeBill extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean listLoaded = false;
    private JButton btn_back;
    private JButton btn_clea;
    private JButton btn_deleteBill;
    private JComboBox<String> cbo_bNumber;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel lbl_background;
    private JPanel pnl_main;
    private JTextField txt_bAmount;
    private JTextField txt_bCustName;
    private JXDatePicker txt_bDate;

    public removeBill() {
        this.initComponents();
        this.centerFrame();
        this.conn = ConnectToDatabase.getConnection();
        this.setComboBoxValues();
        this.listLoaded = true;
    }

    public void centerFrame() {
        Toolkit toolkit = this.getToolkit();
        Dimension size = toolkit.getScreenSize();
        this.setLocation(size.width / 2 - this.getWidth() / 2, size.height / 2 - this.getHeight() / 2);
    }

    public void setComboBoxValues() {
        String sql = "select bNumber from billing where bOk=? group by bNumber";
        this.cbo_bNumber.removeAllItems();

        try {
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "Yes");
            this.rs = this.pst.executeQuery();

            while(this.rs.next()) {
                this.cbo_bNumber.addItem(this.rs.getString(1));
            }
        } catch (Exception var11) {
            JOptionPane.showMessageDialog((Component)null, "Billing list cannot be loaded");
        } finally {
            try {
                this.rs.close();
                this.pst.close();
            } catch (Exception var10) {
            }

        }

    }

    private void initComponents() {
        this.pnl_main = new JPanel();
        this.btn_back = new JButton();
        this.jLabel1 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.txt_bCustName = new JTextField();
        this.jLabel6 = new JLabel();
        this.cbo_bNumber = new JComboBox();
        this.btn_deleteBill = new JButton();
        this.txt_bDate = new JXDatePicker();
        this.btn_clea = new JButton();
        this.jLabel5 = new JLabel();
        this.txt_bAmount = new JTextField();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Remove entire bill");
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.btn_back.setText("Back");
        this.btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeBill.this.btn_backActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_back);
        this.btn_back.setBounds(260, 10, 100, 30);
        this.jLabel1.setFont(new Font("Calibri", 1, 18));
        this.jLabel1.setText("Remove  entire bill :");
        this.pnl_main.add(this.jLabel1);
        this.jLabel1.setBounds(30, 10, 230, 23);
        this.jLabel3.setFont(new Font("Calibri", 0, 12));
        this.jLabel3.setText("Bill date : ");
        this.pnl_main.add(this.jLabel3);
        this.jLabel3.setBounds(40, 110, 110, 16);
        this.jLabel4.setFont(new Font("Calibri", 0, 12));
        this.jLabel4.setText("Customer name : ");
        this.pnl_main.add(this.jLabel4);
        this.jLabel4.setBounds(40, 160, 110, 16);
        this.txt_bCustName.setEditable(false);
        this.txt_bCustName.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_bCustName);
        this.txt_bCustName.setBounds(150, 150, 210, 30);
        this.jLabel6.setFont(new Font("Calibri", 0, 12));
        this.jLabel6.setText("Select Bill number : ");
        this.pnl_main.add(this.jLabel6);
        this.jLabel6.setBounds(40, 60, 110, 16);
        this.cbo_bNumber.setFont(new Font("Calibri", 0, 12));
        this.cbo_bNumber.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                removeBill.this.cbo_bNumberItemStateChanged(evt);
            }
        });
        this.pnl_main.add(this.cbo_bNumber);
        this.cbo_bNumber.setBounds(150, 50, 210, 30);
        this.btn_deleteBill.setText("Delete bill");
        this.btn_deleteBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeBill.this.btn_deleteBillActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_deleteBill);
        this.btn_deleteBill.setBounds(40, 270, 130, 40);
        this.txt_bDate.setEditable(false);
        this.pnl_main.add(this.txt_bDate);
        this.txt_bDate.setBounds(150, 100, 210, 30);
        this.btn_clea.setText("Clear");
        this.btn_clea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeBill.this.btn_cleaActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_clea);
        this.btn_clea.setBounds(210, 270, 130, 40);
        this.jLabel5.setFont(new Font("Calibri", 0, 12));
        this.jLabel5.setText("Bill Amount : ");
        this.pnl_main.add(this.jLabel5);
        this.jLabel5.setBounds(40, 210, 110, 16);
        this.txt_bAmount.setEditable(false);
        this.txt_bAmount.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_bAmount);
        this.txt_bAmount.setBounds(150, 200, 210, 30);
        this.lbl_background.setIcon(new ImageIcon("C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\logout.png"));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 430, 360);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -2, 427, -2));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 362, 32767));
        this.pack();
    }

    private void btn_deleteBillActionPerformed(ActionEvent evt) {
        if (this.cbo_bNumber.getSelectedItem().toString().equals("")) {
            JOptionPane.showMessageDialog((Component)null, "Please select the bill number to delete");
        } else {
            String sql = "update billing set bOk=? where bNumber=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, "No");
                this.pst.setString(2, this.cbo_bNumber.getSelectedItem().toString());
                this.pst.execute();
                JOptionPane.showMessageDialog((Component)null, "Bill removed");
                this.listLoaded = false;
                this.setComboBoxValues();
                this.listLoaded = true;
            } catch (Exception var12) {
                JOptionPane.showMessageDialog((Component)null, var12);
            } finally {
                try {
                    this.rs.close();
                    this.pst.close();
                } catch (Exception var11) {
                }

            }
        }

    }

    private void btn_cleaActionPerformed(ActionEvent evt) {
        this.txt_bDate.setDate((Date)null);
        this.txt_bCustName.setText("");
        this.txt_bAmount.setText("");
    }

    private void cbo_bNumberItemStateChanged(ItemEvent evt) {
        if (this.listLoaded) {
            String sql = "select bNumber, bDate, bCustName, sum(bAmount) from billing where bNumber=? group by bnumber";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.cbo_bNumber.getSelectedItem().toString());
                this.rs = this.pst.executeQuery();
                this.rs.next();
                this.txt_bDate.setDate(this.rs.getDate(2));
                this.txt_bCustName.setText(this.rs.getString(3));
                this.txt_bAmount.setText(this.rs.getString(4));
            } catch (Exception var12) {
                JOptionPane.showMessageDialog((Component)null, "Billing details cannot be found");
            } finally {
                try {
                    this.rs.close();
                    this.pst.close();
                } catch (Exception var11) {
                }

            }
        }

    }

    private void btn_backActionPerformed(ActionEvent evt) {
        generateBill gb = new generateBill();
        gb.setVisible(true);
        this.dispose();
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
            Logger.getLogger(removeBill.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(removeBill.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(removeBill.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(removeBill.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new removeBill()).setVisible(true);
            }
        });
    }
}
