import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.math.BigDecimal;
import java.io.*;
import java.util.ArrayList;

public class TicketFrame extends JFrame implements ActionListener {
  private JTextArea outputArea;
  private JButton calcButton, clearButton, saveButton;
  private JTextField ticketNameField;
  private JFormattedTextField priceField, numSoldField;
  private JLabel ticketNameLabel, priceLabel, numSoldLabel, outputLabel;
  private JScrollPane scrollPane;
  private GridBagConstraints gc;
  private NumberFormat numberFormat;
  private ArrayList<BigDecimal> sales;

  public TicketFrame() {
    sales = new ArrayList<BigDecimal>();
    
    //Set Title
    setTitle("Ticket Machine");
    
    //Create Labels
    ticketNameLabel = new JLabel("Ticket Name:");
    priceLabel = new JLabel("Price:");
    numSoldLabel = new JLabel("Sold:");
    outputLabel = new JLabel("Total Ticket Sales:");
    
    //Create output area and add it to scroll pane
    outputArea = new JTextArea(10, 15);
    outputArea.setText("");
    scrollPane = new JScrollPane(outputArea);
    outputArea.setEditable(false);
    
    //Buttons
    calcButton = new JButton("Calculate");
    calcButton.addActionListener(this);
    
    clearButton = new JButton("Clear");
    clearButton.addActionListener(this);
    
    saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    
    //Create Fields
    numberFormat = NumberFormat.getCurrencyInstance();
    priceField = new JFormattedTextField(numberFormat);
    priceField.setEditable(true);
    priceField.setText("enter price in dollars($)");
    priceField.setColumns(10);
    
    numberFormat = NumberFormat.getIntegerInstance();
    numSoldField = new JFormattedTextField(numberFormat);
    numSoldField.setEditable(true);
    
    ticketNameField = new JTextField(20);
    ticketNameField.setEditable(true);
    
    //GridBagLayout
    setLayout(new GridBagLayout());
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 0;
    gc.gridy = 0;
    add(ticketNameLabel, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 1;
    gc.gridy = 0;
    add(ticketNameField, gc);
 
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 0;
    gc.gridy = 1;
    add(priceLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 1;
    gc.gridy = 1;
    add(priceField, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 0;
    gc.gridy = 2;
    add(numSoldLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 1;
    gc.gridy = 2;
    add(numSoldField, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 10, 5, 10);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 2;
    gc.gridy = 0;
    add(calcButton, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(5, 10, 5, 10);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 2;
    gc.gridy = 1;
    add(clearButton, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(5, 10, 5, 10);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 2;
    gc.gridy = 2;
    add(saveButton, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 10, 5, 1);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 0;
    gc.gridy = 3;
    add(outputLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(1, 10, 10, 10);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 0;
    gc.gridy = 4;
    gc.gridwidth = 3;
    add(scrollPane, gc);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    String ticketName, sPrice, sNumSold;
    Integer numSold;
    Double price;
    BigDecimal bPrice, bNumSold, total;
    BigDecimal totalSales = new BigDecimal("0");
    
    //Get source of event
    JButton sourceEvent = (JButton) event.getSource();
    
    if (sourceEvent == calcButton) {
    
      ticketName = ticketNameField.getText();
      
      //Obtains value from input field, then converts to string, and finally BigDecimal
      numSold = ((Number)numSoldField.getValue()).intValue();
      sNumSold = numSold.toString();
      bNumSold = new BigDecimal(sNumSold);
      
      price = ((Number)priceField.getValue()).doubleValue();
      sPrice = price.toString();
      bPrice = new BigDecimal(sPrice);
      
      //Calculate total cost and send to output area
      total = bPrice.multiply(bNumSold);
      sales.add(total);
      outputArea.append(ticketName + " cost: " + "$" + total + " (Price: $" + price + 
                        ", Number Sold: " + numSold + ")" + "\n");
    }
    
    //Clears the output area
    else if (sourceEvent == clearButton) {
      sales.clear();
      outputArea.setText("");
    }
    //Creates txt file from outputArea
    else if (sourceEvent == saveButton) {
      JOptionPane.showMessageDialog(new JFrame(), "File saved: TicketSales.txt");
      
      for (BigDecimal elem : sales) {
        totalSales = totalSales.add(elem);
      }
      outputArea.append("Total Sales: $" + totalSales.toString());
      
      try {
        PrintWriter txtFile = new PrintWriter(new FileWriter("TicketSales.txt"));
        outputArea.write(txtFile);
        
      }
      catch (IOException exception) {
        System.err.println("Failure!");
      }
      //Brings back main GUI
      ZooGui.main(null);
      dispose();
    }
  }
    
  public static void main(String[] args) {
    TicketFrame t = new TicketFrame();
    
    t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    t.pack();
    t.setVisible(true);
  }
}