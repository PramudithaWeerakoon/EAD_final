package bakery;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;



public class itemList extends JFrame {
    Connection conn = null;
    private String selectedImagePath = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean listLoaded = false;
    private JButton btn_Add;
    private JButton btn_Clear;
    private JButton btn_Remove1;
    private JButton btn_Update;
    private JButton btn_back;
    private JButton btn_UploadImage; // New button for image upload
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
    private JTextField txt_iCp;
    private JTextField txt_iId;
    private JTextField txt_iMinStock;
    private JTextField txt_iName;
    private JTextField txt_iSp;
    private JLabel lbl_Image; // New JLabel for displaying selected image

    // New BufferedImage variable to store the selected image
    private BufferedImage selectedImage = null;

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
        this.btn_Update = new JButton();
        this.btn_Add = new JButton();
        this.btn_Clear = new JButton();
        this.btn_Remove1 = new JButton();
        this.txt_iCp = new JTextField();
        this.txt_iMinStock = new JTextField();
        this.txt_iName = new JTextField();
        this.txt_iId = new JTextField();
        this.lbl_background = new JLabel();
        this.lbl_Image = new JLabel(); // New JLabel for displaying selected image
        this.btn_UploadImage = new JButton(); // New button for image upload

        this.setDefaultCloseOperation(2);
        this.setTitle("Item details");
        this.setResizable(false);
        this.pnl_main.setLayout((LayoutManager)null);

        this.btn_back.setText("Back to main menu");
        this.btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_backActionPerformed(evt);
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
                cbo_searchItemStateChanged(evt);
            }
        });
        this.cbo_search.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                cbo_searchMouseReleased(evt);
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


        this.btn_Update.setFont(new Font("Calibri", 0, 14));
        this.btn_Update.setText("Update item");
        this.btn_Update.setCursor(new Cursor(12));
        this.btn_Update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_Update);
        this.btn_Update.setBounds(460, 220, 120, 30);

        this.btn_Add.setFont(new Font("Calibri", 0, 14));
        this.btn_Add.setText("Add item");
        this.btn_Add.setCursor(new Cursor(12));
        this.btn_Add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_Add);
        this.btn_Add.setBounds(460, 170, 120, 30);

        this.btn_Clear.setFont(new Font("Calibri", 0, 14));
        this.btn_Clear.setText("Clear");
        this.btn_Clear.setCursor(new Cursor(12));
        this.btn_Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_Clear);
        this.btn_Clear.setBounds(460, 320, 120, 30);

        this.btn_Remove1.setFont(new Font("Calibri", 0, 14));
        this.btn_Remove1.setText("Remove item");
        this.btn_Remove1.setCursor(new Cursor(12));
        this.btn_Remove1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_Remove1ActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_Remove1);
        this.btn_Remove1.setBounds(460, 270, 120, 30);

        this.txt_iCp.setBounds(150, 290, 220, 30);
        this.pnl_main.add(this.txt_iCp);

        this.txt_iMinStock.setBounds(150, 250, 220, 30);
        this.pnl_main.add(this.txt_iMinStock);

        this.txt_iName.setBounds(150, 210, 220, 30);
        this.pnl_main.add(this.txt_iName);

        this.txt_iId.setEditable(false);
        this.txt_iId.setFont(new Font("Calibri", 0, 14));
        this.pnl_main.add(this.txt_iId);
        this.txt_iId.setBounds(150, 170, 220, 30);

        //this.lbl_background.setIcon(new ImageIcon(this.getClass().getResource("/com.images/Background image.jpeg")));
        this.pnl_main.add(this.lbl_background);
        this.lbl_background.setBounds(0, 0, 620, 470);

        // Set the bounds for the JLabel used for displaying the image
        this.lbl_Image.setBounds(400, 380, 100, 100);
        this.pnl_main.add(this.lbl_Image);
        // Set up the "Upload Image" button
        this.pnl_main.add(this.btn_UploadImage);
        this.btn_UploadImage.setFont(new Font("Calibri", 0, 14));
        this.btn_UploadImage.setText("Upload Image");
        this.btn_UploadImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_UploadImageActionPerformed(evt);
            }
        });
        this.pnl_main.add(this.btn_UploadImage);
        this.btn_UploadImage.setVisible(true);
        this.btn_UploadImage.setBounds(150, 370, 220, 30);

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -2, 620, -2));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.pnl_main, -1, 472, 32767));
        this.pack();
    }
    private void btn_UploadImageActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedImagePath = selectedFile.getAbsolutePath(); // Update the selectedImagePath variable with the selected file path

            try {
                selectedImage = ImageIO.read(selectedFile);
                // Display the selected image in the JLabel
                Image scaledImage = selectedImage.getScaledInstance(lbl_Image.getWidth(), lbl_Image.getHeight(), Image.SCALE_SMOOTH);
                lbl_Image.setIcon(new ImageIcon(scaledImage));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading the selected image.");
            }
        }
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
                this.txt_iMinStock.setText(this.rs.getString(4));
                this.txt_iCp.setText(this.rs.getString(5));
                this.txt_iSp.setText(this.rs.getString(6));
                // Set image if available
                byte[] imageBytes = this.rs.getBytes(7);
                if (imageBytes != null) {
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(lbl_Image.getWidth(), lbl_Image.getHeight(), Image.SCALE_SMOOTH);
                    lbl_Image.setIcon(new ImageIcon(scaledImage));
                } else {
                    // Clear image if not available
                    lbl_Image.setIcon(null);
                }
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

    private void btn_ClearActionPerformed(ActionEvent evt) {
        this.txt_iId.setText("");
        this.txt_iName.setText("");
        this.txt_iMinStock.setText("");
        this.txt_iCp.setText("");
        this.txt_iSp.setText("");
        lbl_Image.setIcon(null); // Clear the displayed image
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
            this.txt_iMinStock.setText("");
            this.txt_iCp.setText("");
            this.txt_iSp.setText("");
            lbl_Image.setIcon(null); // Clear the displayed image
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
            String sql = "INSERT INTO item (iName, iMinStock, iCp, iSp, image_data) VALUES (?, ?, ?, ?, ?)";

            try {
                this.pst = this.conn.prepareStatement(sql);

                this.pst.setString(1, this.txt_iName.getText());
                this.pst.setInt(2, Integer.parseInt(this.txt_iMinStock.getText()));
                this.pst.setInt(3, Integer.parseInt(this.txt_iCp.getText()));
                this.pst.setInt(4, Integer.parseInt(this.txt_iSp.getText()));
                this.pst.setString(5, selectedImagePath); // Save the image path

                this.pst.execute();
                JOptionPane.showMessageDialog((Component)null, "Record Added");
                this.txt_iId.setText("");
                this.txt_iName.setText("");
                this.txt_iMinStock.setText("");
                this.txt_iCp.setText("");
                this.txt_iSp.setText("");
                this.listLoaded = false;
                this.setComboBoxValues();
                this.listLoaded = true;
            } catch (Exception var12) {
                var12.printStackTrace();
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
            String sql = "update item set iName=?, iMinStock=?, iCp=?, iSp=?, image_data=? where iId=?";

            try {
                this.pst = this.conn.prepareStatement(sql);
                this.pst.setString(1, this.txt_iName.getText());
                this.pst.setInt(2, Integer.parseInt(this.txt_iMinStock.getText()));
                this.pst.setInt(3, Integer.parseInt(this.txt_iCp.getText()));
                this.pst.setInt(4, Integer.parseInt(this.txt_iSp.getText()));
                // Update image in the database
                if (selectedImage != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(selectedImage, "jpg", baos);
                    byte[] imageBytes = baos.toByteArray();
                    this.pst.setBytes(5, imageBytes);
                } else {
                    this.pst.setBytes(5, null);
                }
                this.pst.setInt(6, Integer.parseInt(this.txt_iId.getText()));
                this.pst.execute();
                JOptionPane.showMessageDialog((Component)null, "Record updated");
                this.txt_iId.setText("");
                this.txt_iName.setText("");
                this.txt_iMinStock.setText("");
                this.txt_iCp.setText("");
                this.txt_iSp.setText("");
                lbl_Image.setIcon(null); // Clear the displayed image
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

    // Handle the "Upload Image" button click event

    // Helper method to convert Image to byte[]
    private byte[] imageToBytes(Image image) {
        try {
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(image, null, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void btn_backActionPerformed(ActionEvent evt) {
        this.dispose();
        new mainMenu().setVisible(true);
    }

    static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(itemList.class.getName()).log(Level.SEVERE, null, ex);
                }
                new itemList().setVisible(true);
            }
        });
    }
}