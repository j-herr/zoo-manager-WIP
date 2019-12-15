import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class AnimalPanel extends JPanel implements ActionListener {
  private JTextArea outputArea;
  private JButton calcButton, clearButton;
  private JTextField animalNameField;
  private JFormattedTextField popField, foodField, mealsField;
  private JLabel animalNameLabel, popLabel, foodLabel, mealsLabel, outputLabel;
  private JScrollPane scrollPane;
  private GridBagConstraints gc;
  private NumberFormat numberFormat;

  public AnimalPanel() {
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
    String animalName;
    int population, meals;
    double foodCost, totalCost;
    
    //Get source of event
    JButton sourceEvent = (JButton) event.getSource();
    
    if (sourceEvent == calcButton) {
    
      animalName = animalNameField.getText();
      population = ((Number)popField.getValue()).intValue();
      meals = ((Number)mealsField.getValue()).intValue();
      foodCost = ((Number)foodField.getValue()).doubleValue();
    
      //Calculate total cost and send to output area
      totalCost = population*meals*foodCost;
      outputArea.append(animalName + " cost: " + "$" + totalCost + " (Population: " + population + 
                        ", Food Cost Per Meal: $" + foodCost + ", Meals A Day: " + meals + ")" + "\n");
    }
    else if (sourceEvent == clearButton) {
      outputArea.setText(" ");
    }
  }
  
  public static void main(String[] args) {
    JFrame myFrame = new JFrame();
    AnimalPanel a = new AnimalPanel();
    
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.add(a);
    myFrame.pack();
    myFrame.setVisible(true);
  }
}
