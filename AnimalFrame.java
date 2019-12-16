import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.math.BigDecimal;
import java.io.*;
import java.util.ArrayList;

public class AnimalFrame extends JFrame implements ActionListener {
  private JTextArea outputArea;
  private JButton calcButton, clearButton, saveButton;
  private JTextField animalNameField;
  private JFormattedTextField popField, foodField, mealsField;
  private JLabel animalNameLabel, popLabel, foodLabel, mealsLabel, outputLabel;
  private JScrollPane scrollPane;
  private GridBagConstraints gc;
  private NumberFormat numberFormat;
  private ArrayList<BigDecimal> costs;

  public AnimalFrame() {
    costs = new ArrayList<BigDecimal>();
    
    //Set Title
    setTitle("Animal");
    
    //Create Labels
    animalNameLabel = new JLabel("Animal:");
    popLabel = new JLabel("Population:");
    foodLabel = new JLabel("Food Cost:");
    mealsLabel = new JLabel("Number of Meals:");
    outputLabel = new JLabel("Total Animal Costs:");
    
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
    foodField = new JFormattedTextField(numberFormat);
    foodField.setEditable(true);
    foodField.setText("enter price in dollars($)");
    foodField.setColumns(10);
    
    numberFormat = NumberFormat.getIntegerInstance();
    popField = new JFormattedTextField(numberFormat);
    popField.setEditable(true);
    
    mealsField = new JFormattedTextField(numberFormat);
    mealsField.setEditable(true);
    
    animalNameField = new JTextField(20);
    animalNameField.setEditable(true);
    
    //GridBagLayout
    setLayout(new GridBagLayout());
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 0;
    gc.gridy = 0;
    add(animalNameLabel, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 1;
    gc.gridy = 0;
    add(animalNameField, gc);
 
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 0;
    gc.gridy = 1;
    add(popLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 1;
    gc.gridy = 1;
    add(popField, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 0;
    gc.gridy = 2;
    add(mealsLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 1;
    gc.gridy = 2;
    add(mealsField, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 0;
    gc.gridy = 3;
    add(foodLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 1;
    gc.gridy = 3;
    add(foodField, gc);

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
    gc.gridy = 4;
    add(outputLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(1, 10, 10, 10);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 0;
    gc.gridy = 5;
    gc.gridwidth = 3;
    add(scrollPane, gc);
  }
  
  @Override
  public void actionPerformed(ActionEvent event) {
    String animalName, sPopulation, sMeals, sFoodCost;
    Integer population, meals;
    Double foodCost;
    BigDecimal bPopulation, bMeals, bFoodCost, totalCost;
    BigDecimal total = new BigDecimal("0");
    
    //Get source of event
    JButton sourceEvent = (JButton) event.getSource();
    
    if (sourceEvent == calcButton) {
    
      animalName = animalNameField.getText();
      
      //Obtains value from input field, then converts to string, and finally BigDecimal
      population = ((Number)popField.getValue()).intValue();
      sPopulation = population.toString();
      bPopulation = new BigDecimal(sPopulation);
      
      meals = ((Number)mealsField.getValue()).intValue();
      sMeals = meals.toString();
      bMeals = new BigDecimal(sMeals);
      
      foodCost = ((Number)foodField.getValue()).doubleValue();
      sFoodCost = foodCost.toString();
      bFoodCost = new BigDecimal(sFoodCost);
      
      //Calculate total cost and send to output area
      totalCost = bFoodCost.multiply(bPopulation.multiply(bMeals));
      costs.add(totalCost);
      outputArea.append(animalName + " cost: " + "$" + totalCost + " (Population: " + population + 
                        ", Food Cost Per Meal: $" + foodCost + ", Meals A Day: " + meals + ")" + "\n");
    }
    
    //Clears the output area
    else if (sourceEvent == clearButton) {
      costs.clear();
      outputArea.setText("");
    }
    
    //Creates txt file from outputArea
    else if (sourceEvent == saveButton) {
      JOptionPane.showMessageDialog(new JFrame(), "File saved: AnimalReport.txt");
      
      for (BigDecimal elem : costs) {
        total = total.add(elem);
      }
      outputArea.append("Total Costs: $" + total.toString());
      
      try {
        PrintWriter txtFile = new PrintWriter(new FileWriter("AnimalReport.txt"));
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
    AnimalFrame a = new AnimalFrame();
    
    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    a.pack();
    a.setVisible(true);
  }
}