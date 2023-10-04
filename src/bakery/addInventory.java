package bakery;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import org.jdesktop.swingx.JXDatePicker;

public class addInventory extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean listLoaded = false;
    private JButton btn_back;
    private JButton btn_clearAll;
    private JButton btn_clearitem;
    private JButton btn_save;
    private JComboBox<String> cbo_iniName;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel lbl_background;
    private JPanel pnl_main;
    private JXDatePicker txt_inDate;
    private JTextField txt_inQty;
    private JTextField txt_iniId;

    public addInventory() {
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
        String sql = "select iName from item where iActive=? order by iName asc";
        this.cbo_iniName.removeAllItems();

        try {
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "Yes");
            this.rs = this.pst.executeQuery();

            while(this.rs.next()) {
                this.cbo_iniName.addItem(this.rs.getString(1));
            }
        } catch (Exception var11) {
            JOptionPane.showMessageDialog((Component)null, "Item list cannot be loaded");
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
        this.jLabel1 = new JLabel();
        this.jLabel3 = new JLabel();
        this.cbo_iniName = new JComboBox();
        this.jLabel5 = new JLabel();
        this.txt_inQty = new JTextField();
        this.jLabel7 = new JLabel();
        this.txt_iniId = new JTextField();
        this.jLabel8 = new JLabel();
        this.btn_clearitem = new JButton();
        this.btn_save = new JButton();
        this.btn_back = new JButton();
        this.txt_inDate = new JXDatePicker();
        this.btn_clearAll = new JButton();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Add inventory");
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.jLabel1.setFont(new Font("Calibri", 1, 18));
        this.jLabel1.setText("Add item to inventory :");
        this.pnl_main.add(this.jLabel1);
        this.jLabel1.setBounds(30, 10, 210, 23);
        this.jLabel3.setFont(new Font("Calibri", 0, 12));
        this.jLabel3.setText("Inventory date : ");
        this.pnl_main.add(this.jLabel3);
        this.jLabel3.setBounds(30, 60, 110, 16);
        this.cbo_iniName.setFont(new Font("Calibri", 0, 12));
        this.cbo_iniName.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                addInventory.this.cbo_iniNameItemStateChanged(evt);
            }
        });
        this.pnl_main.add(this.cbo_iniName);
        this.cbo_iniName.setBounds(140, 100, 210, 30);
        this.jLabel5.setFont(new Font("Calibri", 0, 12));
        this.jLabel5.setText("Select item name : ");
        this.pnl_main.add(this.jLabel5);
        this.jLabel5.setBounds(30, 110, 110, 16);
        this.txt_inQty.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_inQty);
        this.txt_inQty.setBounds(140, 200, 210, 30);
        this.jLabel7.setFont(new Font("Calibri", 0, 12));
        this.jLabel7.setText("Item quantity : ");
        this.pnl_main.add(this.jLabel7);
        this.jLabel7.setBounds(30, 210, 120, 16);
        this.txt_iniId.setEditable(false);
        this.txt_iniId.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_iniId);
        this.txt_iniId.setBounds(140, 150, 210, 30);
        this.jLabel8.setFont(new Font("Calibri", 0, 12));
        this.jLabel8.setText("Item Id :");
        this.pnl_main.add(this.jLabel8);
        this.jLabel8.setBounds(30, 160, 120, 16);
        this.btn_clearitem.setText("Clear item details");
        this.btn_clearitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addInventory.this.btn_clearitemActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_clearitem);
        this.btn_clearitem.setBounds(190, 290, 130, 40);
        this.btn_save.setText("Save item");
        this.btn_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addInventory.this.btn_saveActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_save);
        this.btn_save.setBounds(70, 290, 100, 40);
        this.btn_back.setText("Back");
        this.btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addInventory.this.btn_backActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_back);
        this.btn_back.setBounds(280, 10, 70, 30);
        this.pnl_main.add(this.txt_inDate);
        this.txt_inDate.setBounds(140, 50, 210, 30);
        this.btn_clearAll.setText("Clear all details");
        this.btn_clearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addInventory.this.btn_clearAllActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_clearAll);
        this.btn_clearAll.setBounds(130, 350, 130, 40);
        this.lbl_background.setIcon(new ImageIcon(this.getClass().getResource("/com.images/Background image.jpeg")));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 400, 420);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -2, 398, -2));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -2, 419, -2));
        this.pack();
    }

    private void cbo_iniNameItemStateChanged(ItemEvent evt) {
        if (this.listLoaded) {
            String sql = "select * from item where iName=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.cbo_iniName.getSelectedItem().toString());
                this.rs = this.pst.executeQuery();
                this.rs.next();
                this.txt_iniId.setText(this.rs.getString(1));
            } catch (Exception var12) {
                JOptionPane.showMessageDialog((Component)null, "Item details cannot be found");
            } finally {
                try {
                    this.rs.close();
                    this.pst.close();
                } catch (Exception var11) {
                }

            }
        }

    }

    private void btn_saveActionPerformed(ActionEvent evt) {
        if (this.txt_iniId.getText().equals("")) {
            JOptionPane.showMessageDialog((Component)null, "Please select the item to enter in the inventory");
        } else {
            String sql = "insert into inventory (inDate, item_iId, iniName, inQty)values (?,?,?,?) ";

            try {
                this.pst = this.conn.prepareStatement(sql);
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                this.pst.setString(1, format.format(this.txt_inDate.getDate()));
                this.pst.setInt(2, Integer.parseInt(this.txt_iniId.getText()));
                this.pst.setString(3, this.cbo_iniName.getSelectedItem().toString());
                this.pst.setInt(4, Integer.parseInt(this.txt_inQty.getText()));
                this.pst.execute();
                JOptionPane.showMessageDialog((Component)null, "Record Added");
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

    private void btn_clearAllActionPerformed(ActionEvent evt) {
        this.txt_inDate.setDate((Date)null);
        this.txt_iniId.setText("");
        this.cbo_iniName.setSelectedItem(0);
        this.txt_inQty.setText("");
    }

    private void btn_clearitemActionPerformed(ActionEvent evt) {
        this.txt_iniId.setText("");
        this.cbo_iniName.setSelectedItem(0);
        this.txt_inQty.setText("");
    }

    private void btn_backActionPerformed(ActionEvent evt) {
        inventory i = new inventory();
        i.setVisible(true);
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
            Logger.getLogger(addInventory.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(addInventory.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(addInventory.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(addInventory.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new addInventory()).setVisible(true);
            }
        });
    }
}