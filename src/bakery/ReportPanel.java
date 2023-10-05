package bakery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Report Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ReportPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class ReportPanel extends JPanel {
    JTable table = new JTable();
    JTextField bIdField = new JTextField(10);
    JTextField bNumberField = new JTextField(10);
    JTextField bDateField = new JTextField(10);
    JTextField iNameField = new JTextField(10);
    JTextField bQtyField = new JTextField(10);
    JTextField iSpField = new JTextField(10);
    JButton submitButton = new JButton("Submit");
    JButton clearButton = new JButton("Clear");

    public ReportPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("bId:"));
        inputPanel.add(bIdField);
        inputPanel.add(new JLabel("bNumber:"));
        inputPanel.add(bNumberField);
        inputPanel.add(new JLabel("bDate:"));
        inputPanel.add(bDateField);
        inputPanel.add(new JLabel("iName:"));
        inputPanel.add(iNameField);
        inputPanel.add(new JLabel("bQty:"));
        inputPanel.add(bQtyField);
        inputPanel.add(new JLabel("iSp:"));
        inputPanel.add(iSpField);
        inputPanel.add(submitButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        submitButton.addActionListener(e -> fillTable(table, bIdField.getText(), bNumberField.getText(), bDateField.getText(), iNameField.getText(), bQtyField.getText(), iSpField.getText()));

        // Clear button action
        clearButton.addActionListener(e -> {
            bIdField.setText("");
            bNumberField.setText("");
            bDateField.setText("");
            iNameField.setText("");
            bQtyField.setText("");
            iSpField.setText("");
        });
    }
     void fillTable(JTable table, String... columns) {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakery1", "root", "");


            Statement stmt = con.createStatement();


            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM billing");


            boolean isAnyColumnFilled = false;
            for (String column : columns) {
                if (column != null && !column.trim().isEmpty()) {
                    isAnyColumnFilled = true;
                    break;
                }
            }

            if (isAnyColumnFilled) {
                queryBuilder.append(" WHERE ");
                String[] columnNames = {"bId", "bNumber", "bDate", "iName", "bQty", "iSp"};
                for (int i = 0; i < columns.length; i++) {
                    if (columns[i] != null && !columns[i].trim().isEmpty()) {
                        queryBuilder.append(columnNames[i]).append(" = '").append(columns[i]).append("' AND ");
                    }
                }

                queryBuilder.setLength(queryBuilder.length() - 5);
            }

            String query = queryBuilder.toString();


            ResultSet rs = stmt.executeQuery(query);


            ResultSetMetaData rsmd = rs.getMetaData();


            int columnCount = rsmd.getColumnCount();


            DefaultTableModel dtm = new DefaultTableModel();


            for (int i = 1; i <= columnCount; i++) {
                dtm.addColumn(rsmd.getColumnName(i));
            }


            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                dtm.addRow(row);
            }


            table.setModel(dtm);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}