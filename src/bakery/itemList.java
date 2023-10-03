package bakery;


import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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

public class itemList extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean listLoaded = false;
    private JButton btn_Add;
    private JButton btn_Clear;
    private JButton btn_Remove1;
    private JButton btn_Update;
    private JButton btn_back;
    private JComboBox<String> cbo_search;
    private JLabel jLabel1;
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
    private JScrollPane scrollPane_Description;
    private JTextField txt_iCp;
    private JTextArea txt_iDescription;
    private JTextField txt_iId;
    private JTextField txt_iMinStock;
    private JTextField txt_iName;
    private JTextField txt_iSp;

    public itemList() {
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
        this.cbo_search.removeAllItems();

        try {
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "Yes");
            this.rs = this.pst.executeQuery();

            while(this.rs.next()) {
                this.cbo_search.addItem(this.rs.getString(1));
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
        this.cbo_search = new JComboBox();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.txt_iSp = new JTextField();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jLabel8 = new JLabel();
        this.jLabel9 = new JLabel();
        this.btn_Update = new JButton();
        this.btn_Add = new JButton();
        this.btn_Clear = new JButton();
        this.btn_Remove1 = new JButton();
        this.txt_iCp = new JTextField();
        this.txt_iMinStock = new JTextField();
        this.txt_iName = new JTextField();
        this.txt_iId = new JTextField();
        this.scrollPane_Description = new JScrollPane();
        this.txt_iDescription = new JTextArea();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Item details");
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.btn_back.setText("Back to main menu");
        this.btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemList.this.btn_backActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_back);
        this.btn_back.setBounds(450, 13, 160, 30);
        this.jLabel1.setFont(new Font("Calibri", 1, 18));
        this.jLabel1.setText("Search item : ");
        this.pnl_main.add(this.jLabel1);
        this.jLabel1.setBounds(40, 20, 102, 23);
        this.jLabel2.setFont(new Font("Calibri", 0, 14));
        this.jLabel2.setText("Select the item name :");
        this.pnl_main.add(this.jLabel2);
        this.jLabel2.setBounds(40, 60, 160, 17);
        this.cbo_search.setFont(new Font("Calibri", 0, 14));
        this.cbo_search.setModel(new DefaultComboBoxModel(new String[]{"Item List"}));
        this.cbo_search.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                itemList.this.cbo_searchItemStateChanged(evt);
            }
        });
        this.cbo_search.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                itemList.this.cbo_searchMouseReleased(evt);
            }
        });
        this.cbo_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemList.this.cbo_searchActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.cbo_search);
        this.cbo_search.setBounds(200, 57, 270, 23);
        this.jLabel3.setFont(new Font("Calibri", 1, 18));
        this.jLabel3.setText("Item details :");
        this.pnl_main.add(this.jLabel3);
        this.jLabel3.setBounds(40, 130, 110, 23);
        this.jLabel4.setFont(new Font("Calibri", 0, 14));
        this.jLabel4.setText("Item Id :");
        this.pnl_main.add(this.jLabel4);
        this.jLabel4.setBounds(50, 180, 90, 17);
        this.pnl_main.add(this.txt_iSp);
        this.txt_iSp.setBounds(150, 330, 220, 30);
        this.jLabel5.setFont(new Font("Calibri", 0, 14));
        this.jLabel5.setText("Item Name :");
        this.pnl_main.add(this.jLabel5);
        this.jLabel5.setBounds(50, 220, 90, 17);
        this.jLabel6.setFont(new Font("Calibri", 0, 14));
        this.jLabel6.setText("Min stock :");
        this.pnl_main.add(this.jLabel6);
        this.jLabel6.setBounds(50, 260, 90, 17);
        this.jLabel7.setFont(new Font("Calibri", 0, 14));
        this.jLabel7.setText("Cost price :");
        this.pnl_main.add(this.jLabel7);
        this.jLabel7.setBounds(50, 300, 90, 17);
        this.jLabel8.setFont(new Font("Calibri", 0, 14));
        this.jLabel8.setText("Selling price :");
        this.pnl_main.add(this.jLabel8);
        this.jLabel8.setBounds(50, 340, 90, 17);
        this.jLabel9.setFont(new Font("Calibri", 0, 14));
        this.jLabel9.setText("Item description :");
        this.pnl_main.add(this.jLabel9);
        this.jLabel9.setBounds(50, 380, 100, 17);
        this.btn_Update.setFont(new Font("Calibri", 0, 14));
        this.btn_Update.setText("Update item");
        this.btn_Update.setCursor(new Cursor(12));
        this.btn_Update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemList.this.btn_UpdateActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_Update);
        this.btn_Update.setBounds(460, 220, 120, 30);
        this.btn_Add.setFont(new Font("Calibri", 0, 14));
        this.btn_Add.setText("Add item");
        this.btn_Add.setCursor(new Cursor(12));
        this.btn_Add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemList.this.btn_AddActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_Add);
        this.btn_Add.setBounds(460, 170, 120, 30);
        this.btn_Clear.setFont(new Font("Calibri", 0, 14));
        this.btn_Clear.setText("Clear");
        this.btn_Clear.setCursor(new Cursor(12));
        this.btn_Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemList.this.btn_ClearActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_Clear);
        this.btn_Clear.setBounds(460, 320, 120, 30);
        this.btn_Remove1.setFont(new Font("Calibri", 0, 14));
        this.btn_Remove1.setText("Remove item");
        this.btn_Remove1.setCursor(new Cursor(12));
        this.btn_Remove1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemList.this.btn_Remove1ActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_Remove1);
        this.btn_Remove1.setBounds(460, 270, 120, 30);
        this.pnl_main.add(this.txt_iCp);
        this.txt_iCp.setBounds(150, 290, 220, 30);
        this.pnl_main.add(this.txt_iMinStock);
        this.txt_iMinStock.setBounds(150, 250, 220, 30);
        this.pnl_main.add(this.txt_iName);
        this.txt_iName.setBounds(150, 210, 220, 30);
        this.txt_iId.setEditable(false);
        this.txt_iId.setFont(new Font("Calibri", 0, 14));
        this.pnl_main.add(this.txt_iId);
        this.txt_iId.setBounds(150, 170, 220, 30);
        this.txt_iDescription.setColumns(20);
        this.txt_iDescription.setFont(new Font("Calibri", 0, 14));
        this.txt_iDescription.setLineWrap(true);
        this.txt_iDescription.setRows(5);
        this.scrollPane_Description.setViewportView(this.txt_iDescription);
        this.pnl_main.add(this.scrollPane_Description);
        this.scrollPane_Description.setBounds(150, 380, 220, 80);
        this.lbl_background.setIcon(new ImageIcon(this.getClass().getResource("/com.images/Background image.jpeg")));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 620, 470);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -2, 620, -2));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 472, 32767));
        this.pack();
    }

    private void cbo_searchItemStateChanged(ItemEvent evt) {
        if (this.listLoaded) {
            String sql = "select * from item where iName=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.cbo_search.getSelectedItem().toString());
                this.rs = this.pst.executeQuery();
                this.rs.next();
                this.txt_iId.setText(this.rs.getString(1));
                this.txt_iName.setText(this.rs.getString(2));
                this.txt_iDescription.setText(this.rs.getString(3));
                this.txt_iMinStock.setText(this.rs.getString(4));
                this.txt_iCp.setText(this.rs.getString(5));
                this.txt_iSp.setText(this.rs.getString(6));
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

    private void cbo_searchMouseReleased(MouseEvent evt) {
    }

    private void cbo_searchActionPerformed(ActionEvent evt) {
    }

    private void btn_ClearActionPerformed(ActionEvent evt) {
        this.txt_iId.setText("");
        this.txt_iName.setText("");
        this.txt_iDescription.setText("");
        this.txt_iMinStock.setText("");
        this.txt_iCp.setText("");
        this.txt_iSp.setText("");
    }

    private void btn_Remove1ActionPerformed(ActionEvent evt) {
        String sql = "update item set iActive = ? where iId = ?";

        try {
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "No");
            this.pst.setInt(2, Integer.parseInt(this.txt_iId.getText()));
            this.pst.execute();
            JOptionPane.showMessageDialog((Component)null, "Record Removed");
            this.txt_iId.setText("");
            this.txt_iName.setText("");
            this.txt_iDescription.setText("");
            this.txt_iMinStock.setText("");
            this.txt_iCp.setText("");
            this.txt_iSp.setText("");
            this.listLoaded = false;
            this.setComboBoxValues();
            this.listLoaded = true;
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

    private void btn_AddActionPerformed(ActionEvent evt) {
        if (this.txt_iId.getText().equals("")) {
            String sql = "insert into item (iName,iDescription,iMinStock,iCp,iSp) values (?,?,?,?,?) ";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.txt_iName.getText());
                this.pst.setString(2, this.txt_iDescription.getText());
                this.pst.setInt(3, Integer.parseInt(this.txt_iMinStock.getText()));
                this.pst.setInt(4, Integer.parseInt(this.txt_iCp.getText()));
                this.pst.setInt(5, Integer.parseInt(this.txt_iSp.getText()));
                this.pst.execute();
                JOptionPane.showMessageDialog((Component)null, "Record Added");
                this.txt_iId.setText("");
                this.txt_iName.setText("");
                this.txt_iDescription.setText("");
                this.txt_iMinStock.setText("");
                this.txt_iCp.setText("");
                this.txt_iSp.setText("");
                this.listLoaded = false;
                this.setComboBoxValues();
                this.listLoaded = true;
            } catch (Exception var12) {
                JOptionPane.showMessageDialog((Component)null, "Item cannot be added. Please fill all the details completely");
            } finally {
                try {
                    this.rs.close();
                    this.pst.close();
                } catch (Exception var11) {
                }

            }
        } else {
            JOptionPane.showMessageDialog((Component)null, "Item already present. Please click update to update the item details");
        }

    }

    private void btn_UpdateActionPerformed(ActionEvent evt) {
        if (this.txt_iId.getText().equals("")) {
            JOptionPane.showMessageDialog((Component)null, "New item. Please click on the add button to add");
        } else {
            String sql = "update item set iName=?, iDescription=?, iMInstock=?, iCp=?, iSp=? where iId=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.txt_iName.getText());
                this.pst.setString(2, this.txt_iDescription.getText());
                this.pst.setInt(3, Integer.parseInt(this.txt_iMinStock.getText()));
                this.pst.setInt(4, Integer.parseInt(this.txt_iCp.getText()));
                this.pst.setInt(5, Integer.parseInt(this.txt_iSp.getText()));
                this.pst.setInt(6, Integer.parseInt(this.txt_iId.getText()));
                this.pst.execute();
                JOptionPane.showMessageDialog((Component)null, "Record updated");
                this.txt_iId.setText("");
                this.txt_iName.setText("");
                this.txt_iDescription.setText("");
                this.txt_iMinStock.setText("");
                this.txt_iCp.setText("");
                this.txt_iSp.setText("");
                this.listLoaded = false;
                this.setComboBoxValues();
                this.listLoaded = true;
            } catch (Exception var12) {
                JOptionPane.showMessageDialog((Component)null, "Record cannot be updated. Please fill in all the details completely");
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
        mainMenu mm = new mainMenu();
        mm.setVisible(true);
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
            Logger.getLogger(itemList.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(itemList.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(itemList.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(itemList.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new itemList()).setVisible(true);
            }
        });
    }
}
