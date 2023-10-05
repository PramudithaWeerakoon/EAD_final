package Main;

import bakery.login;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;

class Product {
    private int id;
    private String name;
    private String description;
    private double price;

    public Product(int id,String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName();
    }
}

class ShoppingCartItem {
    private Product product;
    private int quantity;

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class UserDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bakery1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void addUser(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer (name, password) VALUES (?, ?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByUsername(String username) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE name = ?")) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String password = resultSet.getString("password");
                    return new User(username, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class Cart {
    private List<ShoppingCartItem> items = new ArrayList<>();

    public List<ShoppingCartItem> getItems() {
        return items;
    }
}

public class BakeryManagementApp extends JFrame {
    private List<Product> catalog = new ArrayList<>();
    private Connection connection;
    private JList<String> catalogList;
    private DefaultListModel<String> catalogListModel;
    private JTable cartTable;
    private DefaultTableModel cartTableModel;
    private JLabel totalLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton checkoutButton;
    private User currentUser;
    private Cart cart = new Cart();
    private String loggedInUsername;

    private JPanel loginPanel;
    private JPanel mainPanel;
    private JPanel checkoutPanel;
    private JPanel bannerPanel; // New panel to display the banner and "Show All" button
    private CardLayout cardLayout;
    private double totalAmount = 0.0;

    private String[] bannerImages = {
            "C:/Users/Pramuditha/OneDrive/Desktop/New project/277000-easy-vanilla-cake-ddmfs-1X2-0101-8ac1f0aed3294888921a87d9bce9c43c.jpg",
            "C:/Users/Pramuditha/OneDrive/Desktop/New project/Cream-Cheese-Danish-Pastry-11.jpg",
            "C:/Users/Pramuditha/OneDrive/Desktop/New project/hamburger-buns-TRR-1200-19-of-21.jpg",
            "C:/Users/Pramuditha/OneDrive/Desktop/New project/Japanese-Milk-Bread-Shokupan-I-1.jpg",
            "C:/Users/Pramuditha/OneDrive/Desktop/New project/Shortbread-cookies-Recipe-New.jpg"
    };

    public void showLoginPanel() {
        cardLayout.show(mainPanel, "LoginPanel");
    }
    public BakeryManagementApp() {
        setTitle("Online Bakery Management System");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a main panel and set CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Create a login panel
        createLoginPanel();

        // Create the main content panel
        JPanel mainContentPanel = createMainContentPanel();

        // Create the checkout panel
        checkoutPanel = createCheckoutPanel();

        // Create the banner panel
        createBannerPanel();

        // Add all panels to the main panel
        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(mainContentPanel, "MainContentPanel");
        mainPanel.add(checkoutPanel, "CheckoutPanel");
        mainPanel.add(bannerPanel, "BannerPanel");

        // Show the login panel by default
        cardLayout.show(mainPanel, "LoginPanel");

        add(mainPanel, BorderLayout.CENTER);
    }

    public JPanel createLoginPanel() {

        loginPanel = new JPanel()
        {
            private float alpha = 0.0f;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\anuga_2023_bread_bakery.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

                // Apply transparency for a fade-in effect
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                alpha += 0.02f; // Increase alpha for a fade-in effect

                if (alpha >= 1.0f) {
                    alpha = 1.0f; // Limit alpha to 1.0
                }
            }
        };



        // Use a layout manager for the login panel
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add spacing between components

        // Create and position the components on the login panel
        JLabel nameLabel = new JLabel("ANUGA BREAD & BAKERY");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2; // Span 2 columns
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(nameLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridwidth = 1; // Reset gridwidth
        gbc.gridy = 1;
        loginPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expand horizontally
        loginPanel.add(loginButton, gbc);

        registerButton = new JButton("Register");
        gbc.gridy = 4;
        loginPanel.add(registerButton, gbc);

        // Customize button appearance
        loginButton.setBackground(new Color(0, 128, 0)); // Dark green
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false); // Remove button border

        registerButton.setBackground(new Color(0, 0, 128)); // Dark blue
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorderPainted(false); // Remove button border

        // Set button background color on hover
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(0, 160, 0)); // Light green
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(0, 128, 0)); // Dark green
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(new Color(0, 0, 160)); // Light blue
            }

            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(new Color(0, 0, 128)); // Dark blue
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(BakeryManagementApp.this, "Please enter both username and password.");
                    return;
                }
                User user = UserDatabase.getUserByUsername(username);

                if (user != null && user.getPassword().equals(password)) {
                    currentUser = user;
                    loggedInUsername = username; // Set loggedInUsername when the user logs in successfully
                    cardLayout.show(mainPanel, "BannerPanel"); // Switch to the banner panel
                } else {
                    JOptionPane.showMessageDialog(BakeryManagementApp.this, "Invalid username or password.");
                }

                usernameField.setText("");
                passwordField.setText("");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(BakeryManagementApp.this, "Please enter both username and password.");
                    return;
                }

                User existingUser = UserDatabase.getUserByUsername(username);

                if (existingUser != null) {
                    JOptionPane.showMessageDialog(BakeryManagementApp.this, "Username already exists.");
                } else {
                    User newUser = new User(username, password);
                    UserDatabase.addUser(newUser);
                    JOptionPane.showMessageDialog(BakeryManagementApp.this, "Registration successful!");
                }

                usernameField.setText("");
                passwordField.setText("");
            }
        });

        // Add the login panel to the main panel
        mainPanel.add(loginPanel, "LoginPanel");
        // Create an "Admin Login" button
        JButton adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Position the button in the lower-left corner
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expand horizontally
        loginPanel.add(adminLoginButton, gbc);

        // Customize button appearance
        adminLoginButton.setBackground(new Color(128, 0, 0)); // Dark red
        adminLoginButton.setForeground(Color.WHITE);
        adminLoginButton.setBorderPainted(false); // Remove button border

        // Set button background color on hover
        adminLoginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                adminLoginButton.setBackground(new Color(160, 0, 0)); // Light red
            }

            public void mouseExited(MouseEvent e) {
                adminLoginButton.setBackground(new Color(128, 0, 0)); // Dark red
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the admin panel when the Admin Login button is clicked
                login adminLoginFrame = new login();
                adminLoginFrame.setVisible(true);
                dispose(); // Close the current login frame
            }
        });

        javax.swing.Timer timer = new javax.swing.Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPanel.repaint();
            }
        });
        timer.start();
        return loginPanel;
    }


    private JPanel createMainContentPanel() {
        JPanel mainContentPanel = new JPanel(new BorderLayout());

        catalogListModel = new DefaultListModel<>();
        catalogList = new JList<>(catalogListModel);
        JScrollPane catalogScrollPane = new JScrollPane(catalogList);
        JLabel mainTotalLabel = new JLabel("Total: $0.00");
        mainContentPanel.add(catalogScrollPane, BorderLayout.CENTER);

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakery1", "root", "");

            // Execute a query to fetch the products
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and add the products to the catalog
            while (resultSet.next()) {
                int id = resultSet.getInt("iId"); // Get the item id
                String name = resultSet.getString("iName");

                // Retrieve the image file path
                String imagePath = resultSet.getString("image_data");
                
                if (imagePath != null) {
                    // Replace single backslashes with double backslashes
                    imagePath = imagePath.replace("\\", "\\\\");

                    // Now imagePath contains the path with double backslashes
                    // You can use imagePath in your code
                    String description = "<html><img src='file:" + imagePath + "' width='50' height='50'> " + name + " - $" + resultSet.getDouble("iSp") + "</html>";

                    double price = resultSet.getDouble("iSp");
                    System.out.println("Description: " + description);
                    System.out.println("Image Path: " + imagePath);

                    catalog.add(new Product(id, name, description, price));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection error
        }

        for (Product product : catalog) {
            catalogListModel.addElement(product.getDescription());
        }

        catalogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton addToCartButton = new JButton("Add to Cart");
        mainContentPanel.add(addToCartButton, BorderLayout.SOUTH);

        cartTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Only allow editing quantity column
            }
        };

        cartTableModel.addColumn("Product");
        cartTableModel.addColumn("Price");
        cartTableModel.addColumn("Quantity");
        cartTableModel.addColumn("Total");

        cartTable = new JTable(cartTableModel);
        cartTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        cartTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        mainContentPanel.add(cartScrollPane, BorderLayout.EAST);

        totalLabel = new JLabel("Total: $0.00");
        mainContentPanel.add(totalLabel, BorderLayout.NORTH);

        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = catalogList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Product selectedProduct = catalog.get(selectedIndex);

                    // Ask for quantity
                    String quantityString = JOptionPane.showInputDialog("Enter quantity:");
                    if (quantityString != null && !quantityString.isEmpty()) {
                        try {
                            int quantity = Integer.parseInt(quantityString);
                            if (quantity > 0) {
                                // Check if the product is already in the cart
                                boolean productExistsInCart = false;
                                for (ShoppingCartItem item : cart.getItems()) {
                                    if (item.getProduct().getName().equals(selectedProduct.getName())) {
                                        item.setQuantity(item.getQuantity() + quantity);
                                        productExistsInCart = true;
                                        break;
                                    }
                                }

                                if (!productExistsInCart) {
                                    ShoppingCartItem newItem = new ShoppingCartItem(selectedProduct, quantity);
                                    cart.getItems().add(newItem);
                                }

                                updateCartTable();
                                updateTotal();
                            } else {
                                JOptionPane.showMessageDialog(BakeryManagementApp.this, "Quantity must be greater than zero.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(BakeryManagementApp.this, "Invalid quantity format.");
                        }
                    }
                }
                updateTotal();
                mainTotalLabel.setText("Total: $" + String.format("%.2f", totalAmount));
            }
        });

        checkoutButton = new JButton("Checkout");
        mainContentPanel.add(checkoutButton, BorderLayout.WEST);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakery1", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection error
        }

        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!cart.getItems().isEmpty()) {
                    String username = loggedInUsername; // Use the currently logged-in username
                    String billNumber = "RB/" + UUID.randomUUID().toString();

                    // Get the current date and time
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedNow = now.format(formatter);


                    try {
                        StringBuilder productsWithQuantities = new StringBuilder();
                        int totalQuantity = 0;

                        for (ShoppingCartItem item : cart.getItems()) {
                            String productName = item.getProduct().getName();
                            int quantity = item.getQuantity();
                            totalQuantity += quantity;

                            // Concatenate the product name and quantity as a string
                            String productWithQuantity = productName + " * " + quantity;

                            // Append the productWithQuantity to the productsWithQuantities StringBuilder
                            if (productsWithQuantities.length() > 0) {
                                productsWithQuantities.append(", ");
                            }
                            productsWithQuantities.append(productWithQuantity);
                        }

                        double totalAmount = cart.getItems().stream().mapToDouble(ShoppingCartItem::getTotalPrice).sum();

                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakery1", "root", "");
                        // Insert a single entry in the database with all products and quantities
                        PreparedStatement insertStatement = connection.prepareStatement(
                                "INSERT INTO billing (bCustName, iName, bQty, iSp, bNumber, bDate) VALUES (?, ?, ?, ?, ?, ?)"
                        );
                        insertStatement.setString(1, username);
                        insertStatement.setString(2, productsWithQuantities.toString());
                        insertStatement.setInt(3, totalQuantity);
                        insertStatement.setDouble(4, totalAmount);
                        insertStatement.setString(5, billNumber);
                        insertStatement.setString(6, formattedNow);
                        //insertStatement.setString(7, productsWithQuantities.toString());
                        insertStatement.executeUpdate();

                        // Query the database to get the total quantity and total price
                        String query = "SELECT SUM(bQty) AS total_quantity, SUM(iSp) AS total_price FROM billing WHERE bCustName = ?";
                        PreparedStatement queryStatement = connection.prepareStatement(query);
                        queryStatement.setString(1, username);
                        ResultSet resultSet = queryStatement.executeQuery();

                        if (resultSet.next()) {
                            int totalQuantityFromDB = resultSet.getInt("total_quantity");
                            double totalPrice = resultSet.getDouble("total_price");

                            System.out.println("Total Quantity: " + totalQuantityFromDB);
                            System.out.println("Total Price: $" + totalPrice);
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Handle SQL error
                    }

                    CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                    cardLayout.show(mainPanel, "CheckoutPanel");
                } else {
                    JOptionPane.showMessageDialog(BakeryManagementApp.this, "Cart is empty. Add items to the cart first.");
                }
            }
        });

        // Set background and foreground colors for components
        mainContentPanel.setBackground(Color.WHITE);
        catalogList.setBackground(Color.WHITE);
        catalogList.setSelectionBackground(Color.ORANGE);
        addToCartButton.setBackground(Color.ORANGE);
        addToCartButton.setForeground(Color.WHITE);
        cartTable.setBackground(Color.WHITE);
        checkoutButton.setBackground(Color.RED);
        checkoutButton.setForeground(Color.WHITE);

        return mainContentPanel;
    }



    private void updateCartTable() {
        cartTableModel.setRowCount(0);
        for (ShoppingCartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            double totalPrice = product.getPrice() * quantity;
            cartTableModel.addRow(new Object[]{product.getName(), product.getPrice(), quantity, totalPrice});
        }
    }

    private void updateTotal() {
        double total = 0;
        for (ShoppingCartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }
    private void createBannerPanel() {
        bannerPanel = new JPanel(new BorderLayout());

        // Create a banner label with the banner image
        ImageIcon bannerIcon = new ImageIcon("C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\12.jpeg");
        JLabel bannerLabel = new JLabel(bannerIcon);

        bannerPanel.add(bannerLabel, BorderLayout.WEST); // Place the banner label on the left

        // Set background color for the banner panel
        bannerPanel.setBackground(Color.WHITE);

        // Add a mouse listener to the banner label
        bannerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // Check if the click is within the specified bounds (35, 227, 265, 70)
                if (x >= 35 && x <= 300 && y >= 227 && y <= 297) {
                    cardLayout.show(mainPanel, "MainContentPanel"); // Show the main panel
                }
            }
        });

        // Create a list of slideshow image paths (Replace with your image paths)
        String[] slideshowImages = {
                "C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\Cream-Cheese-Danish-Pastry-11.jpg",
                "C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\hamburger-buns-TRR-1200-19-of-21.jpg",
                "C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\Japanese-Milk-Bread-Shokupan-I-1.jpg",
                "C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\Shortbread-cookies-Recipe-New.jpg",
                "C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\Sour-Cream-Blueberry-Muffins-Recipe-4.jpg",
                "C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\The-Ultimate-Croissant-Sandwich-Recipe-SQUARE2.jpg",
                "C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\triple-chocolate-cake-4.jpg",
                // Add more image paths as needed
        };

        JPanel slideshowPanel = new JPanel(new BorderLayout());
        JLabel slideshowLabel = new JLabel();

        javax.swing.Timer slideshowTimer = new javax.swing.Timer(0, null); // Create an initially stopped timer

        slideshowTimer.addActionListener(new ActionListener() {
            private int currentIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Load the current slideshow image and set it as the label's icon
                ImageIcon slideshowIcon = new ImageIcon(slideshowImages[currentIndex]);
                slideshowLabel.setIcon(slideshowIcon);
                currentIndex++;

                // Restart the slideshow if we've reached the end of the images
                if (currentIndex >= slideshowImages.length) {
                    currentIndex = 0;
                }
            }
        });

        slideshowPanel.add(slideshowLabel, BorderLayout.CENTER);

        // Add the slideshowPanel to the bannerPanel on the right
        bannerPanel.add(slideshowPanel, BorderLayout.CENTER);

        // Add the bannerPanel to the main panel
        mainPanel.add(bannerPanel, "BannerPanel");

        // Start the slideshow timer as soon as the panel loads
        slideshowTimer.setDelay(4000); // Delay in milliseconds
        slideshowTimer.setInitialDelay(0); // Start immediately
        slideshowTimer.start();

        // Create an "About Us" button
        JButton aboutUsButton = new JButton("About Us");
        aboutUsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        aboutUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "AboutUsPanel"); // Switch to the About Us panel
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.add(aboutUsButton);
        bannerPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(bannerPanel, "BannerPanel");

        // Create an "About Us" panel
        JPanel aboutUsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\aboutusbackground.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        aboutUsPanel.setLayout(new BorderLayout());

        // Create a label with black text for the "About Us" content
        JLabel aboutUsTextLabel = new JLabel("<html><body><div style='text-align: center; color: black; font-size: 14px;'>"
                + "Welcome to ANUGA BREAD & BAKERY!<br><br>"
                + "We take pride in creating delicious pastries and baked goods "
                + "with the finest ingredients. Our bakery is located in the heart of Europe, "
                + "where we have been serving our customers for decades. "
                + "We are committed to delivering quality and taste in every bite. "
                + "Visit us today and experience the joy of our delectable treats!<br><br>"
                + "Here's what sets us apart:<br>"
                + "<ul>"
                + "<li>Handcrafted with love and care</li>"
                + "<li>Wide variety of freshly baked bread</li>"
                + "<li>Specialty cakes for every occasion</li>"
                + "<li>Seasonal and local ingredients</li>"
                + "</ul>"
                + "</div></body></html>");


        aboutUsTextLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
        aboutUsPanel.add(aboutUsTextLabel, BorderLayout.CENTER);

        // Create a back button with custom styling
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18)); // Bold text
        backButton.setForeground(Color.black); // Set text color to white
        backButton.setBackground(Color.white); // Set the background color to black
        backButton.setBorder(BorderFactory.createLineBorder(Color.black, 2)); // Add a slightly larger rectangular border
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "BannerPanel"); // Switch back to the banner panel
            }
        });

        // Create a panel to contain the back button and position it in the top-left corner
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.setOpaque(false); // Make the panel transparent
        backButtonPanel.add(backButton);
        aboutUsPanel.add(backButtonPanel, BorderLayout.NORTH);

        mainPanel.add(aboutUsPanel, "AboutUsPanel");
    }

    private JPanel createCheckoutPanel() {
        JPanel checkoutPanel = new JPanel(new BorderLayout());

        // Payment Method
        JPanel paymentPanel = new JPanel(new GridLayout(2, 2));
        JLabel paymentLabel = new JLabel("Payment Method:");
        JRadioButton cardRadio = new JRadioButton("Card");
        JRadioButton cashRadio = new JRadioButton("Cash on Delivery");

        // Address Details
        JPanel addressPanel = new JPanel(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField(20);
        JLabel promoLabel = new JLabel("Address:");
        JTextField promoField = new JTextField(20);

        // Group the payment radio buttons
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cardRadio);
        paymentGroup.add(cashRadio);

        paymentPanel.add(paymentLabel);
        paymentPanel.add(cardRadio);
        paymentPanel.add(new JLabel("")); // Empty label for spacing
        paymentPanel.add(cashRadio);

        addressPanel.add(nameLabel);
        addressPanel.add(nameField);
        addressPanel.add(phoneLabel);
        addressPanel.add(phoneField);
        addressPanel.add(promoLabel);
        addressPanel.add(promoField);

        // Create a panel for payment and address details
        JPanel paymentAddressPanel = new JPanel(new GridLayout(2, 1));
        paymentAddressPanel.add(paymentPanel);
        paymentAddressPanel.add(addressPanel);

        // Create a placeholder panel for the slideshow
        JPanel slideshowPanel = new JPanel();
        JLabel slideshowLabel = new JLabel(); // JLabel to display the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Pramuditha\\OneDrive\\Desktop\\New project\\delivery-and-courier-motorbike-logo-icon-and-symbol-template-free-vector.jpg");
        slideshowLabel.setIcon(imageIcon);
        slideshowPanel.add(slideshowLabel);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back to Cart");
        JButton confirmButton = new JButton("Confirm Checkout");
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);

        checkoutPanel.add(paymentAddressPanel, BorderLayout.WEST);
        checkoutPanel.add(slideshowPanel, BorderLayout.CENTER); // Placeholder for slideshow
        checkoutPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Total Label with initial text
        JLabel totalCheckoutLabel = new JLabel("Total: Calculating...");
        checkoutPanel.add(totalCheckoutLabel, BorderLayout.NORTH);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!cart.getItems().isEmpty()) {
                    String paymentMethod = cardRadio.isSelected() ? "Card" : "Cash on Delivery";
                    String fullName = nameField.getText();
                    String phoneNumber = phoneField.getText();
                    String promoCode = promoField.getText();

                    // Save checkout information to the database
                    updateCheckoutInformation(paymentMethod,fullName, phoneNumber, promoCode, promoCode);


                    // Rest of your code for cart clearing and updating
                    cart.getItems().clear();
                    updateCartTable(); // Update your cart table (if needed)
                    updateTotal(); // Update your total (if needed)
                    cardLayout.show(mainPanel, "InvoicePanel");

                    //JOptionPane.showMessageDialog(BakeryManagementApp.this, "Checkout successful. Cart details saved to the database.");
                    JPanel invoicePanel = createInvoicePanel();

                    // Add the "invoice" panel to your main panel using CardLayout
                    cardLayout.addLayoutComponent(invoicePanel, "InvoicePanel");
                    mainPanel.add(invoicePanel, "InvoicePanel");
                } else {
                    JOptionPane.showMessageDialog(BakeryManagementApp.this, "Cart is empty. Add items to the cart first.");
                }
            }
        });


        // Back Button Action
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MainContentPanel");
            }
        });

        // Set background and foreground colors for components
        checkoutPanel.setBackground(Color.WHITE);
        paymentAddressPanel.setBackground(Color.WHITE);
        cardRadio.setBackground(Color.WHITE);
        cashRadio.setBackground(Color.WHITE);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        confirmButton.setBackground(Color.GREEN);
        confirmButton.setForeground(Color.WHITE);

        return checkoutPanel;
    }

     void updateCheckoutInformation(String paymentMethod, String fullName, String phoneNumber, String promoCode, String address) {
        String url = "jdbc:mysql://localhost:3306/bakery1";
        String dbUsername = "root";
        String dbPassword = ""; // Replace with your MySQL password
        String username = loggedInUsername;

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sql = "UPDATE customer SET payment = ?, fullname = ?, phone = ?, promo = ?, address = ? WHERE name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, paymentMethod);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, promoCode);
            preparedStatement.setString(5, promoCode);
            preparedStatement.setString(6, username);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Checkout information updated successfully.");
                JOptionPane.showMessageDialog(BakeryManagementApp.this, "Your order is Succesfully created ");
                JPanel invoicePanel = createInvoicePanel();

                // Add the "invoice" panel to your main panel using CardLayout
                cardLayout.addLayoutComponent(invoicePanel, "InvoicePanel");
                mainPanel.add(invoicePanel, "InvoicePanel");

                // Switch to the "invoice" panel
                cardLayout.show(mainPanel, "InvoicePanel");
            } else {
                System.out.println("Failed to update checkout information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private JPanel createInvoicePanel() {
        JPanel invoicePanel = new JPanel(new BorderLayout()); // Use BorderLayout

        // Create a JTextPane for HTML content
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html"); // Set content type to HTML

        // Fetch invoice details from MySQL
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakery1", "root", "");

            // Create a statement
            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();

            String username = loggedInUsername; // Replace with the actual username you want to search for

            // Query to fetch customer details
            String query = "SELECT fullname, address, phone FROM customer WHERE name = '" + username + "' ORDER BY cus_id DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            // Display the fetched customer details
            while (rs.next()) {
                String name = rs.getString("fullname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                // Create HTML content for customer details
                String customerDetailsHtml = "<h2>Customer Details</h2>" +
                        "<p><strong>Name:</strong> " + name + "</p>" +
                        "<p><strong>Address:</strong> " + address + "</p>" +
                        "<p><strong>Phone:</strong> " + phone + "</p>";

                // Now, query for billing details
                String query1 = "SELECT bNumber, iSp, iName, bDate FROM billing WHERE bCustName = '" + username + "' ORDER BY bId DESC LIMIT 1";
                ResultSet rs1 = stmt1.executeQuery(query1);

                // Display the fetched billing details
                StringBuilder productNames = new StringBuilder();
                // Declare variables outside of the loop
                String invoice = "";
                double total = 0;

                // Display the fetched billing details
                while (rs1.next()) {
                    invoice = rs1.getString("bNumber");
                    total += rs1.getDouble("iSp");
                    String productName = rs1.getString("iName");
                    String bDate = rs1.getString("bDate");
                    productNames.append(productName).append("<br>");
                }

                // Create HTML content for billing details
                String billingDetailsHtml = "<h2>Invoice Details</h2>" +
                        "<table border='1' style='width: 100%;'>" +
                        "<tr><td><strong>Invoice ID:</strong></td><td>" + invoice + "</td></tr>" +
                        "<tr><td><strong>Total:</strong></td><td>" + total + "</td></tr>" +
                        "<tr><td><strong>Product Names:</strong></td><td>" + productNames.toString() + "</td></tr>" +
                        "</table>";

                // Create final HTML content
                String bakeryName = "ANUGA BREAD & BAKERY";
                String randomAddress = "12 York Street, Denmark, Denmark";
                String phoneNumber = "+123-456-7890";

                String htmlContent = "<html><body>" +
                        "<h1 style='text-align: center;'>Invoice</h1>" +
                        "<p style='text-align: center;'><strong>Bakery Name:</strong> " + bakeryName + "</p>" +
                        "<p style='text-align: center;'><strong>Address:</strong> " + randomAddress + "</p>" +
                        "<p style='text-align: center;'><strong>Telephone Number:</strong> " + phoneNumber + "</p>" +
                        "<hr>" +
                        customerDetailsHtml + // Add customer details
                        "<hr>" +
                        billingDetailsHtml +  // Add billing details
                        "<hr>" +
                        "<p>Thank you for your purchase!</p>" +
                        "</body></html>";

                textPane.setText(htmlContent); // Set HTML content

                // Close the ResultSet for billing details
                rs1.close();
            }

            // Close the connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Add JTextPane to the panel
        invoicePanel.add(new JScrollPane(textPane), BorderLayout.CENTER);

        return invoicePanel;
    }
    // Custom ButtonRenderer class for rendering buttons in the quantity column
    class ButtonRenderer extends DefaultTableCellRenderer {
        private JButton button = new JButton("Edit");

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;
        }
    }

    // Custom ButtonEditor class for editing buttons in the quantity column
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isClicked;
        private JTable table;
        private int row;
        private int column;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String currentQuantity = (String) button.getText();
                    String newQuantity = JOptionPane.showInputDialog(BakeryManagementApp.this, "Enter new quantity:", currentQuantity);
                    if (newQuantity != null) {
                        if ("0".equals(newQuantity)) {
                            // Delete the whole line
                            cart.getItems().remove(row);
                            cartTableModel.removeRow(row);
                        } else {
                            button.setText(newQuantity);
                            // Update the quantity in the cart
                            int newQuantityInt;
                            try {
                                newQuantityInt = Integer.parseInt(newQuantity);
                            } catch (NumberFormatException ex) {
                                // Handle invalid input
                                return;
                            }
                            ShoppingCartItem item = cart.getItems().get(row);
                            item.setQuantity(newQuantityInt);
                            // Calculate and update the total for this specific item
                            double itemTotal = item.getProduct().getPrice() * newQuantityInt;
                            cartTableModel.setValueAt(itemTotal, row, 3); // Update the total column
                            // Recalculate and update the overall total
                            updateTotal();
                        }
                    }
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            this.column = column;

            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        public Object getCellEditorValue() {
            return button.getText();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BakeryManagementApp app = new BakeryManagementApp();
                app.setVisible(true);
            }
        });
    }
}
class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BakeryManagementApp app = new BakeryManagementApp();
            app.setVisible(true);
        });
    }
}