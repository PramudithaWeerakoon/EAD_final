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

public class removeItem extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean listLoaded = false;
    private JButton btn_back;
    private JButton btn_clea;
    private JButton btn_delete;
    private JComboBox<String> cbo_bId;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JLabel lbl_background;
    private JPanel pnl_main;
    private JScrollPane scrollPane_iDescription;
    private JTextField txt_bAmount;
    private JTextField txt_bCustName;
    private JXDatePicker txt_bDate;
    private JTextField txt_bNumber;
    private JTextField txt_bQty;
    private JTextField txt_iCp;
    private JTextArea txt_iDescription;
    private JTextField txt_iId;
    private JTextField txt_iName;
    private JTextField txt_iSp;

    public removeItem() {
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
        String sql = "select bId from billing where bOk=?";
        this.cbo_bId.removeAllItems();

        try {
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "Yes");
            this.rs = this.pst.executeQuery();

            while(this.rs.next()) {
                this.cbo_bId.addItem(this.rs.getString(1));
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
        this.jLabel2 = new JLabel();
        this.txt_bNumber = new JTextField();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.txt_iName = new JTextField();
        this.jLabel5 = new JLabel();
        this.txt_bQty = new JTextField();
        this.jLabel7 = new JLabel();
        this.txt_iId = new JTextField();
        this.jLabel9 = new JLabel();
        this.txt_iSp = new JTextField();
        this.jLabel8 = new JLabel();
        this.jLabel10 = new JLabel();
        this.txt_bCustName = new JTextField();
        this.txt_bAmount = new JTextField();
        this.jLabel11 = new JLabel();
        this.txt_iCp = new JTextField();
        this.jLabel6 = new JLabel();
        this.jLabel12 = new JLabel();
        this.cbo_bId = new JComboBox();
        this.btn_delete = new JButton();
        this.txt_bDate = new JXDatePicker();
        this.btn_clea = new JButton();
        this.scrollPane_iDescription = new JScrollPane();
        this.txt_iDescription = new JTextArea();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Delete item from billing");
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.btn_back.setText("Back");
        this.btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeItem.this.btn_backActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_back);
        this.btn_back.setBounds(670, 10, 100, 30);
        this.jLabel1.setFont(new Font("Calibri", 1, 18));
        this.jLabel1.setText("Remove item from billing :");
        this.pnl_main.add(this.jLabel1);
        this.jLabel1.setBounds(30, 10, 230, 23);
        this.jLabel2.setFont(new Font("Calibri", 0, 12));
        this.jLabel2.setText("Bill number : ");
        this.pnl_main.add(this.jLabel2);
        this.jLabel2.setBounds(40, 110, 110, 16);
        this.txt_bNumber.setEditable(false);
        this.txt_bNumber.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_bNumber);
        this.txt_bNumber.setBounds(150, 100, 210, 30);
        this.jLabel3.setFont(new Font("Calibri", 0, 12));
        this.jLabel3.setText("Bill date : ");
        this.pnl_main.add(this.jLabel3);
        this.jLabel3.setBounds(40, 160, 110, 16);
        this.jLabel4.setFont(new Font("Calibri", 0, 12));
        this.jLabel4.setText("Customer name : ");
        this.pnl_main.add(this.jLabel4);
        this.jLabel4.setBounds(40, 210, 110, 16);
        this.txt_iName.setEditable(false);
        this.txt_iName.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_iName);
        this.txt_iName.setBounds(150, 250, 210, 30);
        this.jLabel5.setFont(new Font("Calibri", 0, 12));
        this.jLabel5.setText("Item name : ");
        this.pnl_main.add(this.jLabel5);
        this.jLabel5.setBounds(40, 260, 110, 16);
        this.txt_bQty.setEditable(false);
        this.txt_bQty.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_bQty);
        this.txt_bQty.setBounds(560, 50, 210, 30);
        this.jLabel7.setFont(new Font("Calibri", 0, 12));
        this.jLabel7.setText("Item quantity : ");
        this.pnl_main.add(this.jLabel7);
        this.jLabel7.setBounds(450, 60, 120, 16);
        this.txt_iId.setEditable(false);
        this.txt_iId.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_iId);
        this.txt_iId.setBounds(150, 300, 210, 30);
        this.jLabel9.setFont(new Font("Calibri", 0, 12));
        this.jLabel9.setText("Selling price : ");
        this.pnl_main.add(this.jLabel9);
        this.jLabel9.setBounds(450, 110, 120, 16);
        this.txt_iSp.setEditable(false);
        this.txt_iSp.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_iSp);
        this.txt_iSp.setBounds(560, 100, 210, 30);
        this.jLabel8.setFont(new Font("Calibri", 0, 12));
        this.jLabel8.setText("Item Id :");
        this.pnl_main.add(this.jLabel8);
        this.jLabel8.setBounds(40, 310, 120, 16);
        this.jLabel10.setFont(new Font("Calibri", 0, 12));
        this.jLabel10.setText("Item amount : ");
        this.pnl_main.add(this.jLabel10);
        this.jLabel10.setBounds(450, 210, 120, 16);
        this.txt_bCustName.setEditable(false);
        this.txt_bCustName.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_bCustName);
        this.txt_bCustName.setBounds(150, 200, 210, 30);
        this.txt_bAmount.setEditable(false);
        this.txt_bAmount.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_bAmount);
        this.txt_bAmount.setBounds(560, 200, 210, 30);
        this.jLabel11.setFont(new Font("Calibri", 0, 12));
        this.jLabel11.setText("Cost price : ");
        this.pnl_main.add(this.jLabel11);
        this.jLabel11.setBounds(450, 160, 120, 16);
        this.txt_iCp.setEditable(false);
        this.txt_iCp.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_iCp);
        this.txt_iCp.setBounds(560, 150, 210, 30);
        this.jLabel6.setFont(new Font("Calibri", 0, 12));
        this.jLabel6.setText("Select Bill id : ");
        this.pnl_main.add(this.jLabel6);
        this.jLabel6.setBounds(40, 60, 110, 16);
        this.jLabel12.setFont(new Font("Calibri", 0, 12));
        this.jLabel12.setText("Item Description :");
        this.pnl_main.add(this.jLabel12);
        this.jLabel12.setBounds(40, 360, 110, 16);
        this.cbo_bId.setFont(new Font("Calibri", 0, 12));
        this.cbo_bId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                removeItem.this.cbo_bIdItemStateChanged(evt);
            }
        });
        this.pnl_main.add(this.cbo_bId);
        this.cbo_bId.setBounds(150, 50, 210, 30);
        this.btn_delete.setText("Delete item");
        this.btn_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeItem.this.btn_deleteActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_delete);
        this.btn_delete.setBounds(480, 330, 130, 40);
        this.txt_bDate.setEditable(false);
        this.pnl_main.add(this.txt_bDate);
        this.txt_bDate.setBounds(150, 150, 210, 30);
        this.btn_clea.setText("Clear");
        this.btn_clea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeItem.this.btn_cleaActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_clea);
        this.btn_clea.setBounds(620, 330, 130, 40);
        this.txt_iDescription.setEditable(false);
        this.txt_iDescription.setColumns(20);
        this.txt_iDescription.setFont(new Font("Calibri", 0, 12));
        this.txt_iDescription.setLineWrap(true);
        this.txt_iDescription.setRows(5);
        this.scrollPane_iDescription.setViewportView(this.txt_iDescription);
        this.pnl_main.add(this.scrollPane_iDescription);
        this.scrollPane_iDescription.setBounds(150, 350, 210, 130);
        this.lbl_background.setIcon(new ImageIcon("C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\logout.png"));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 820, 500);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 819, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 496, 32767));
        this.pack();
    }

    private void btn_deleteActionPerformed(ActionEvent evt) {
        if (this.txt_bNumber.getText().equals("")) {
            JOptionPane.showMessageDialog((Component)null, "Please select the bill id to delete");
        } else {
            String sql = "update billing set bOk=? where bId=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, "No");
                this.pst.setString(2, this.cbo_bId.getSelectedItem().toString());
                this.pst.execute();
                JOptionPane.showMessageDialog((Component)null, "Record removed");
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
        this.txt_bNumber.setText("");
        this.txt_bDate.setDate((Date)null);
        this.txt_bCustName.setText("");
        this.txt_iName.setText("");
        this.txt_iId.setText("");
        this.txt_iName.setText("");
        this.txt_iDescription.setText("");
        this.txt_bQty.setText("");
        this.txt_iSp.setText("");
        this.txt_iCp.setText("");
        this.txt_bAmount.setText("");
    }

    private void cbo_bIdItemStateChanged(ItemEvent evt) {
        if (this.listLoaded) {
            String sql = "select * from billing where bId=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.cbo_bId.getSelectedItem().toString());
                this.rs = this.pst.executeQuery();
                this.rs.next();
                this.txt_bNumber.setText(this.rs.getString(2));
                this.txt_bDate.setDate(this.rs.getDate(3));
                this.txt_bCustName.setText(this.rs.getString(4));
                this.txt_iId.setText(this.rs.getString(5));
                this.txt_iName.setText(this.rs.getString(6));
                this.txt_iDescription.setText(this.rs.getString(7));
                this.txt_bQty.setText(this.rs.getString(8));
                this.txt_iSp.setText(this.rs.getString(9));
                this.txt_iCp.setText(this.rs.getString(10));
                this.txt_bAmount.setText(this.rs.getString(12));
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
            Logger.getLogger(removeItem.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(removeItem.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(removeItem.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(removeItem.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new removeItem()).setVisible(true);
            }
        });
    }
}
