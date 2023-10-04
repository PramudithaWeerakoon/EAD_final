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

public class generateBill extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private JButton btn_addItem;
    private JButton btn_backToMainMenu;
    private JButton btn_removeBill;
    private JButton btn_removeItem;
    private JButton btn_saveBillInPdf;
    private JLabel jLabel1;
    private JScrollPane jScrollPaneForTable;
    private JLabel lbl_background;
    private JPanel pnl_main;
    private JTable tbl_Bill;

    public generateBill() {
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
            String sql = "select bId, bNumber, bDate, bCustName, item_iId, iName, iDescription, bQty, iSp, iCp, bAmount, bProfit, bOk from billing where bOk=?";
            this.pst = this.conn.prepareStatement(sql);
            this.pst.setString(1, "Yes");
            this.rs = this.pst.executeQuery();
            this.tbl_Bill.setModel(DbUtils.resultSetToTableModel(this.rs));
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
        this.btn_addItem = new JButton();
        this.btn_removeItem = new JButton();
        this.btn_removeBill = new JButton();
        this.btn_saveBillInPdf = new JButton();
        this.jScrollPaneForTable = new JScrollPane();
        this.tbl_Bill = new JTable();
        this.jLabel1 = new JLabel();
        this.lbl_background = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Bill details");
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);
        this.btn_backToMainMenu.setFont(new Font("Calibri", 0, 14));
        this.btn_backToMainMenu.setText("Back to main menu");
        this.btn_backToMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generateBill.this.btn_backToMainMenuActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_backToMainMenu);
        this.btn_backToMainMenu.setBounds(890, 10, 200, 30);
        this.btn_addItem.setFont(new Font("Calibri", 0, 14));
        this.btn_addItem.setText("Add item");
        this.btn_addItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generateBill.this.btn_addItemActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_addItem);
        this.btn_addItem.setBounds(370, 430, 90, 30);
        this.btn_removeItem.setFont(new Font("Calibri", 0, 14));
        this.btn_removeItem.setText("Remove item");
        this.btn_removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generateBill.this.btn_removeItemActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_removeItem);
        this.btn_removeItem.setBounds(470, 430, 110, 30);
        this.btn_removeBill.setFont(new Font("Calibri", 0, 14));
        this.btn_removeBill.setText("Remove bill");
        this.btn_removeBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generateBill.this.btn_removeBillActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_removeBill);
        this.btn_removeBill.setBounds(590, 430, 110, 30);
        this.btn_saveBillInPdf.setFont(new Font("Calibri", 0, 14));
        this.btn_saveBillInPdf.setText("Save bill in PDF");
        this.btn_saveBillInPdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generateBill.this.btn_saveBillInPdfActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_saveBillInPdf);
        this.btn_saveBillInPdf.setBounds(710, 430, 130, 30);
        this.tbl_Bill.setFont(new Font("Calibri", 0, 12));
        this.tbl_Bill.setModel(new DefaultTableModel(new Object[][]{{null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}}, new String[]{"Title 1", "Title 2", "Title 3", "Title 4"}));
        this.tbl_Bill.setEnabled(false);
        this.tbl_Bill.setRowSelectionAllowed(false);
        this.jScrollPaneForTable.setViewportView(this.tbl_Bill);
        this.pnl_main.add(this.jScrollPaneForTable);
        this.jScrollPaneForTable.setBounds(20, 50, 1070, 370);
        this.jLabel1.setFont(new Font("Calibri", 1, 18));
        this.jLabel1.setText("Bill details : ");
        this.pnl_main.add(this.jLabel1);
        this.jLabel1.setBounds(20, 20, 120, 23);
        this.lbl_background.setIcon(new ImageIcon(this.getClass().getResource("/com.images/Background image.jpeg")));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 1110, 470);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 1110, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 470, 32767));
        this.pack();
    }

    private void btn_backToMainMenuActionPerformed(ActionEvent evt) {
        mainMenu mm = new mainMenu();
        mm.setVisible(true);
        this.dispose();
    }

    private void btn_addItemActionPerformed(ActionEvent evt) {
        addItem ai = new addItem();
        ai.setVisible(true);
        this.dispose();
    }

    private void btn_removeItemActionPerformed(ActionEvent evt) {
        removeItem ri = new removeItem();
        ri.setVisible(true);
        this.dispose();
    }

    private void btn_removeBillActionPerformed(ActionEvent evt) {
        removeBill rb = new removeBill();
        rb.setVisible(true);
        this.dispose();
    }

    private void btn_saveBillInPdfActionPerformed(ActionEvent evt) {
        printBill pb = new printBill();
        pb.setVisible(true);
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
            Logger.getLogger(generateBill.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(generateBill.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(generateBill.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(generateBill.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new generateBill()).setVisible(true);
            }
        });
    }
}

