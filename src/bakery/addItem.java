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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import org.jdesktop.swingx.JXDatePicker;

public class addItem extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean listLoaded = false;
    private JButton btn_back;
    private JButton btn_calcAmount;
    private JButton btn_clearAll;
    private JButton btn_clearitem;
    private JButton btn_save;
    private JComboBox<String> cbo_iName;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
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
    private JTextField txt_iSp;

    public addItem() {
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
        this.cbo_iName.removeAllItems();

        try {
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "Yes");
            this.rs = this.pst.executeQuery();

            while(this.rs.next()) {
                this.cbo_iName.addItem(this.rs.getString(1));
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
        this.btn_back = new JButton();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.txt_bNumber = new JTextField();
        this.jLabel3 = new JLabel();
        this.cbo_iName = new JComboBox();
        this.jLabel4 = new JLabel();
        this.txt_bCustName = new JTextField();
        this.jLabel5 = new JLabel();
        this.txt_bQty = new JTextField();
        this.jLabel7 = new JLabel();
        this.txt_iId = new JTextField();
        this.jLabel9 = new JLabel();
        this.txt_iSp = new JTextField();
        this.jLabel8 = new JLabel();
        this.jLabel10 = new JLabel();
        this.txt_bAmount = new JTextField();
        this.jLabel11 = new JLabel();
        this.txt_iCp = new JTextField();
        this.jLabel12 = new JLabel();
        this.btn_calcAmount = new JButton();
        this.btn_clearitem = new JButton();
        this.btn_save = new JButton();
        this.txt_bDate = new JXDatePicker();
        this.btn_clearAll = new JButton();
        this.scrollPane_iDescription = new JScrollPane();
        this.txt_iDescription = new JTextArea();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Add item to billing");
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.btn_back.setText("Back");
        this.btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addItem.this.btn_backActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_back);
        this.btn_back.setBounds(660, 10, 110, 30);
        this.jLabel1.setFont(new Font("Calibri", 1, 18));
        this.jLabel1.setText("Add item to billing :");
        this.pnl_main.add(this.jLabel1);
        this.jLabel1.setBounds(30, 10, 170, 23);
        this.jLabel2.setFont(new Font("Calibri", 0, 12));
        this.jLabel2.setText("Bill number : ");
        this.pnl_main.add(this.jLabel2);
        this.jLabel2.setBounds(30, 60, 110, 16);
        this.txt_bNumber.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_bNumber);
        this.txt_bNumber.setBounds(140, 50, 210, 30);
        this.jLabel3.setFont(new Font("Calibri", 0, 12));
        this.jLabel3.setText("Bill date : ");
        this.pnl_main.add(this.jLabel3);
        this.jLabel3.setBounds(30, 110, 110, 16);
        this.cbo_iName.setFont(new Font("Calibri", 0, 12));
        this.cbo_iName.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                addItem.this.cbo_iNameItemStateChanged(evt);
            }
        });
        this.pnl_main.add(this.cbo_iName);
        this.cbo_iName.setBounds(140, 200, 210, 30);
        this.jLabel4.setFont(new Font("Calibri", 0, 12));
        this.jLabel4.setText("Customer name : ");
        this.pnl_main.add(this.jLabel4);
        this.jLabel4.setBounds(30, 160, 110, 16);
        this.txt_bCustName.setFont(new Font("Calibri", 0, 12));
        this.pnl_main.add(this.txt_bCustName);
        this.txt_bCustName.setBounds(140, 150, 210, 30);
        this.jLabel5.setFont(new Font("Calibri", 0, 12));
        this.jLabel5.setText("Item name : ");
        this.pnl_main.add(this.jLabel5);
        this.jLabel5.setBounds(30, 210, 110, 16);
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
        this.txt_iId.setBounds(140, 250, 210, 30);
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
        this.jLabel8.setBounds(30, 260, 120, 16);
        this.jLabel10.setFont(new Font("Calibri", 0, 12));
        this.jLabel10.setText("Item amount : ");
        this.pnl_main.add(this.jLabel10);
        this.jLabel10.setBounds(450, 210, 120, 16);
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
        this.jLabel12.setFont(new Font("Calibri", 0, 12));
        this.jLabel12.setText("Item Description :");
        this.pnl_main.add(this.jLabel12);
        this.jLabel12.setBounds(30, 310, 110, 16);
        this.btn_calcAmount.setText("Calculate amount");
        this.btn_calcAmount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addItem.this.btn_calcAmountActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_calcAmount);
        this.btn_calcAmount.setBounds(570, 300, 130, 40);
        this.btn_clearitem.setText("Clear item details");
        this.btn_clearitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addItem.this.btn_clearitemActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_clearitem);
        this.btn_clearitem.setBounds(420, 350, 130, 40);
        this.btn_save.setText("Save item");
        this.btn_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addItem.this.btn_saveActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_save);
        this.btn_save.setBounds(420, 300, 100, 40);
        this.pnl_main.add(this.txt_bDate);
        this.txt_bDate.setBounds(140, 100, 210, 30);
        this.btn_clearAll.setText("Clear all details");
        this.btn_clearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addItem.this.btn_clearAllActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_clearAll);
        this.btn_clearAll.setBounds(570, 350, 130, 40);
        this.txt_iDescription.setEditable(false);
        this.txt_iDescription.setColumns(20);
        this.txt_iDescription.setFont(new Font("Calibri", 0, 12));
        this.txt_iDescription.setLineWrap(true);
        this.txt_iDescription.setRows(5);
        this.scrollPane_iDescription.setViewportView(this.txt_iDescription);
        this.pnl_main.add(this.scrollPane_iDescription);
        this.scrollPane_iDescription.setBounds(140, 300, 210, 130);
        this.lbl_background.setIcon(new ImageIcon(this.getClass().getResource("/com.images/Background image.jpeg")));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 820, 440);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 819, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 439, 32767));
        this.pack();
    }

    private void cbo_iNameItemStateChanged(ItemEvent evt) {
        if (this.listLoaded) {
            String sql = "select * from item where iName=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.cbo_iName.getSelectedItem().toString());
                this.rs = this.pst.executeQuery();
                this.rs.next();
                this.txt_iId.setText(this.rs.getString(1));
                this.txt_iDescription.setText(this.rs.getString(3));
                this.txt_iCp.setText(this.rs.getString(5));
                this.txt_iSp.setText(this.rs.getString(6));
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

    private void btn_calcAmountActionPerformed(ActionEvent evt) {
        int sp = Integer.parseInt(this.txt_iSp.getText());
        int qty = Integer.parseInt(this.txt_bQty.getText());
        int amount = sp * qty;
        String amt = Integer.toString(amount);
        this.txt_bAmount.setText(amt);
    }

    private void btn_saveActionPerformed(ActionEvent evt) {
        if (this.txt_iId.getText().equals("")) {
            JOptionPane.showMessageDialog((Component)null, "Please enter the bill number");
        } else {
            String sql = "insert into billing (bNumber, bDate, bCustName, item_iId, iName, iDescription, bQty, iSp,iCp) values (?,?,?,?,?,?,?,?,?) ";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.txt_bNumber.getText());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                this.pst.setString(2, format.format(this.txt_bDate.getDate()));
                this.pst.setString(3, this.txt_bCustName.getText());
                this.pst.setInt(4, Integer.parseInt(this.txt_iId.getText()));
                this.pst.setString(5, this.cbo_iName.getSelectedItem().toString());
                this.pst.setString(6, this.txt_iDescription.getText());
                this.pst.setInt(7, Integer.parseInt(this.txt_bQty.getText()));
                this.pst.setInt(8, Integer.parseInt(this.txt_iSp.getText()));
                this.pst.setInt(9, Integer.parseInt(this.txt_iCp.getText()));
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
        this.txt_bNumber.setText("");
        this.txt_bDate.setDate((Date)null);
        this.txt_bCustName.setText("");
        this.txt_iId.setText("");
        this.cbo_iName.setSelectedItem(0);
        this.txt_iDescription.setText("");
        this.txt_bQty.setText("");
        this.txt_iSp.setText("");
        this.txt_iCp.setText("");
        this.txt_bAmount.setText("");
    }

    private void btn_clearitemActionPerformed(ActionEvent evt) {
        this.txt_iId.setText("");
        this.cbo_iName.setSelectedItem(0);
        this.txt_iDescription.setText("");
        this.txt_bQty.setText("");
        this.txt_iSp.setText("");
        this.txt_iCp.setText("");
        this.txt_bAmount.setText("");
    }

    private void btn_backActionPerformed(ActionEvent evt) {
        generateBill gb = new generateBill();
        gb.setVisible(true);
        this.dispose();
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
            Logger.getLogger(addItem.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(addItem.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(addItem.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(addItem.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new addItem()).setVisible(true);
            }
        });
    }
}

