import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Invoice extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JTextField textInvoiceNo;
    private JTextField textCustomerName;
    private JTextField textContact;
    private JTextField textProduct;
    private JTextField textQuantity;
    private JTextField textUnitPrice;
    private JButton addButton;
    private JTextField textTotal;
    private JButton saveButton;
    private JTable showTable;
    private double total = 0.0;

    public Invoice() {
        setSize(1000, 600);
        setContentPane(contentPane);
        setVisible(true);
        addButton.addActionListener(this);
        saveButton.addActionListener(this);
        createTable();
        createMaskedContactField();
    }

    public static void main(String[] args) {
        Invoice i = new Invoice();
    }

    private void createTable() {
        showTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Product", "Quantity", "Unit Price"}
        ));
    }

    private void createMaskedContactField() {
        try {
            MaskFormatter formatter = new MaskFormatter("####-#####-##");
            JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
            formattedTextField.setColumns(10);
            textContact = formattedTextField;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String product = textProduct.getText();
            int quantity = Integer.parseInt(textQuantity.getText());
            double unitPrice = Double.parseDouble(textUnitPrice.getText());
            double itemTotal = quantity * unitPrice;
            total += itemTotal;
            textTotal.setText(String.valueOf(total));
            DefaultTableModel model = (DefaultTableModel) showTable.getModel();
            model.addRow(new Object[]{product, quantity, unitPrice});
            textProduct.setText("");
            textQuantity.setText("");
            textUnitPrice.setText("");
        } else if (e.getSource() == saveButton) {
            String invoiceNo = textInvoiceNo.getText();
            String customerName = textCustomerName.getText();
            String maskedContact = textContact.getText();
            JOptionPane.showMessageDialog(this,
                    "Invoice Saved!\n" +
                            "Invoice No.: " + invoiceNo + "\n" +
                            "Customer Name: " + customerName + "\n" +
                            "Contact: " + maskedContact);
        }
    }
}
