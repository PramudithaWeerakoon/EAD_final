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

public class removeInventory extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean listLoaded = false;
    private JButton btn_back;
    private JButton btn_clea;
    private JButton btn_delete;
    private JComboBox<String> cbo_inId;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel lbl_background;
    private JPanel pnl_main;
    private JXDatePicker txt_inDate;
    private JTextField txt_inQty;
    private JTextField txt_iniName;
    private JTextField txt_item_iId;

    public removeInventory() {
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
        String sql = "select inId from inventory where inActive=?";
        this.cbo_inId.removeAllItems();

        try {
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "Yes");
            this.rs = this.pst.executeQuery();

            while(this.rs.next()) {
                this.cbo_inId.addItem(this.rs.getString(1));
            }
        } catch (Exception var11) {
            JOptionPane.showMessageDialog((Component)null, "Inventory list cannot be loaded");
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
        this.txt_iniName = new JTextField();
        this.jLabel5 = new JLabel();
        this.txt_inQty = new JTextField();
        this.btn_back = new JButton();
        this.jLabel7 = new JLabel();
        this.txt_item_iId = new JTextField();
        this.jLabel8 = new JLabel();
        this.jLabel6 = new JLabel();
        this.cbo_inId = new JComboBox();
        this.btn_delete = new JButton();
        this.txt_inDate = new JXDatePicker();
        this.btn_clea = new JButton();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Remove inventory from inventory table");
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.jLabel1.setFont(new Font("Calibri", 1, 18));
        this.jLabel1.setText("Remove item from inventory :");
        this.pnl_main.add(this.jLabel1);
        this.jLabel1.setBounds(30, 10, 230, 23);
        this.jLabel3.setFont(new Font("Calibri", 0, 12));
        this.jLabel3.setText("Inventory date : ");
        this.pnl_main.add(this.jLabel3);
        this.jLabel3.setBounds(40, 110, 110, 16);
        this.txt_iniName.setEditable(false);
        this.txt_iniName.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_iniName);
        this.txt_iniName.setBounds(150, 200, 210, 30);
        this.jLabel5.setFont(new Font("Calibri", 0, 12));
        this.jLabel5.setText("Item name : ");
        this.pnl_main.add(this.jLabel5);
        this.jLabel5.setBounds(40, 210, 110, 16);
        this.txt_inQty.setEditable(false);
        this.txt_inQty.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_inQty);
        this.txt_inQty.setBounds(150, 250, 210, 30);
        this.btn_back.setText("Back");
        this.btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeInventory.this.btn_backActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_back);
        this.btn_back.setBounds(310, 10, 80, 30);
        this.jLabel7.setFont(new Font("Calibri", 0, 12));
        this.jLabel7.setText("Item quantity : ");
        this.pnl_main.add(this.jLabel7);
        this.jLabel7.setBounds(40, 260, 120, 16);
        this.txt_item_iId.setEditable(false);
        this.txt_item_iId.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_item_iId);
        this.txt_item_iId.setBounds(150, 150, 210, 30);
        this.jLabel8.setFont(new Font("Calibri", 0, 12));
        this.jLabel8.setText("Item Id :");
        this.pnl_main.add(this.jLabel8);
        this.jLabel8.setBounds(40, 160, 120, 16);
        this.jLabel6.setFont(new Font("Calibri", 0, 12));
        this.jLabel6.setText("Select Inventory id : ");
        this.pnl_main.add(this.jLabel6);
        this.jLabel6.setBounds(40, 60, 110, 16);
        this.cbo_inId.setFont(new Font("Calibri", 0, 12));
        this.cbo_inId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                removeInventory.this.cbo_inIdItemStateChanged(evt);
            }
        });
        this.pnl_main.add(this.cbo_inId);
        this.cbo_inId.setBounds(150, 50, 210, 30);
        this.btn_delete.setText("Delete item");
        this.btn_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeInventory.this.btn_deleteActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_delete);
        this.btn_delete.setBounds(60, 310, 130, 40);
        this.txt_inDate.setEditable(false);
        this.pnl_main.add(this.txt_inDate);
        this.txt_inDate.setBounds(150, 100, 210, 30);
        this.btn_clea.setText("Clear");
        this.btn_clea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeInventory.this.btn_cleaActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_clea);
        this.btn_clea.setBounds(200, 310, 130, 40);
        this.lbl_background.setIcon(new ImageIcon(this.getClass().getResource("/com.images/Background image.jpeg")));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 410, 380);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -2, 409, -2));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -2, 381, -2));
        this.pack();
    }

    private void btn_deleteActionPerformed(ActionEvent evt) {
        if (this.txt_iniName.getText().equals("")) {
            JOptionPane.showMessageDialog((Component)null, "Please select the inventory id to delete");
        } else {
            String sql = "update inventory set inActive=? where inId=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, "No");
                this.pst.setString(2, this.cbo_inId.getSelectedItem().toString());
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
        this.txt_inDate.setDate((Date)null);
        this.txt_iniName.setText("");
        this.txt_item_iId.setText("");
        this.txt_iniName.setText("");
        this.txt_inQty.setText("");
    }

    private void cbo_inIdItemStateChanged(ItemEvent evt) {
        if (this.listLoaded) {
            String sql = "select * from inventory where inId=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.cbo_inId.getSelectedItem().toString());
                this.rs = this.pst.executeQuery();
                this.rs.next();
                this.txt_inDate.setDate(this.rs.getDate(2));
                this.txt_item_iId.setText(this.rs.getString(3));
                this.txt_iniName.setText(this.rs.getString(4));
                this.txt_inQty.setText(this.rs.getString(5));
            } catch (Exception var12) {
                JOptionPane.showMessageDialog((Component)null, "Inventory details cannot be found");
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
            Logger.getLogger(removeInventory.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(removeInventory.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(removeInventory.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(removeInventory.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new removeInventory()).setVisible(true);
            }
        });
    }
}

