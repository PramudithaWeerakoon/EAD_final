package bakery;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class inventory extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private JButton btn_addInventory;
    private JButton btn_backToMainMenu;
    private JButton btn_removeInventory;
    private JLabel jLabel1;
    private JScrollPane jScrollPaneForTable;
    private JLabel lbl_background;
    private JPanel pnl_main;
    private JTable tbl_Inventory;

    public inventory() {
        this.initComponents();
        this.centerFrame();
        this.conn = ConnectToDatabase.getConnection();
        this.generateBillTable();
    }

    public void centerFrame() {
        Toolkit toolkit = this.getToolkit();
        Dimension size = toolkit.getScreenSize();
        this.setLocation(size.width / 2 - this.getWidth() / 2, size.height / 2 - this.getHeight() / 2);
    }

    public void generateBillTable() {
        try {
            String sql = "select * from inventory where inActive=?";
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "Yes");
            this.rs = this.pst.executeQuery();
            this.tbl_Inventory.setModel(DbUtils.resultSetToTableModel(this.rs));
        } catch (Exception var10) {
            JOptionPane.showMessageDialog((Component)null, var10);
        } finally {
            try {
                this.pst.close();
                this.rs.close();
            } catch (Exception var9) {
            }

        }

    }

    private void initComponents() {
        this.pnl_main = new JPanel();
        this.btn_backToMainMenu = new JButton();
        this.btn_addInventory = new JButton();
        this.btn_removeInventory = new JButton();
        this.jScrollPaneForTable = new JScrollPane();
        this.tbl_Inventory = new JTable();
        this.jLabel1 = new JLabel();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Inventory details");
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.btn_backToMainMenu.setFont(new Font("Calibri", 0, 14));
        this.btn_backToMainMenu.setText("Back to main menu");
        this.btn_backToMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inventory.this.btn_backToMainMenuActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_backToMainMenu);
        this.btn_backToMainMenu.setBounds(480, 10, 200, 30);
        this.btn_addInventory.setFont(new Font("Calibri", 0, 14));
        this.btn_addInventory.setText("Add inventory");
        this.btn_addInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inventory.this.btn_addInventoryActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_addInventory);
        this.btn_addInventory.setBounds(210, 430, 140, 30);
        this.btn_removeInventory.setFont(new Font("Calibri", 0, 14));
        this.btn_removeInventory.setText("Remove inventory");
        this.btn_removeInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inventory.this.btn_removeInventoryActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_removeInventory);
        this.btn_removeInventory.setBounds(370, 430, 140, 30);
        this.tbl_Inventory.setFont(new Font("Calibri", 0, 12));
        this.tbl_Inventory.setModel(new DefaultTableModel(new Object[][]{{null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}}, new String[]{"Title 1", "Title 2", "Title 3", "Title 4"}));
        this.tbl_Inventory.setEnabled(false);
        this.tbl_Inventory.setRowSelectionAllowed(false);
        this.jScrollPaneForTable.setViewportView(this.tbl_Inventory);
        this.pnl_main.add(this.jScrollPaneForTable);
        this.jScrollPaneForTable.setBounds(20, 50, 660, 370);
        this.jLabel1.setFont(new Font("Calibri", 1, 18));
        this.jLabel1.setText("Inventory details : ");
        this.pnl_main.add(this.jLabel1);
        this.jLabel1.setBounds(20, 20, 150, 23);
        this.lbl_background.setIcon(new ImageIcon(this.getClass().getResource("/com.images/Background image.jpeg")));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 700, 470);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 697, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 470, 32767));
        this.pack();
    }

    private void btn_backToMainMenuActionPerformed(ActionEvent evt) {
        mainMenu mm = new mainMenu();
        mm.setVisible(true);
        this.dispose();
    }

    private void btn_addInventoryActionPerformed(ActionEvent evt) {
        addInventory ai = new addInventory();
        ai.setVisible(true);
        this.dispose();
    }

    private void btn_removeInventoryActionPerformed(ActionEvent evt) {
        removeInventory ri = new removeInventory();
        ri.setVisible(true);
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
            Logger.getLogger(inventory.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(inventory.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(inventory.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(inventory.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new inventory()).setVisible(true);
            }
        });
    }
}

