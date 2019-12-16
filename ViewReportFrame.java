import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class ViewReportFrame extends JFrame implements ActionListener {
  private JScrollPane scrollPane;
  private JTextArea outputArea;
  private JLabel currentFileLabel, fileContentsLabel;
  private JTextField currentFileField;
  private JFileChooser fileChooser;
  private JButton openButton, backButton;
  private GridBagConstraints gc;
  
  public ViewReportFrame() {
    setTitle("View Report");
    //Create labels
    fileContentsLabel = new JLabel("File contents:");
    currentFileLabel = new JLabel("Current file:");
    
    //Set fields
    currentFileField = new JTextField(20);
    currentFileField.setEditable(false);
    
    //Set output area
    outputArea = new JTextArea(15, 40);
    scrollPane = new JScrollPane(outputArea);
    outputArea.setEditable(false);
    
    //Create buttons
    openButton = new JButton("Open");
    openButton.addActionListener(this);
    
    backButton = new JButton("Back");
    backButton.addActionListener(this);
    
    
    //Initialize filechooser
    fileChooser = new JFileChooser();
    
    //Set GridBagLayout
    setLayout(new GridBagLayout());
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(0, 10, 5, 5);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 0;
    gc.gridy = 4;
    add(backButton, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 10, 5, 5);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 0;
    gc.gridy = 0;
    add(openButton, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 5, 5, 1);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 1;
    gc.gridy = 0;
    add(currentFileLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 1, 5, 10);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 2;
    gc.gridy = 0;
    gc.gridwidth = 2;
    gc.gridheight = 1;
    add(currentFileField, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(5, 10, 0, 0);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 0;
    gc.gridy = 1;
    add(fileContentsLabel, gc);

    gc = new GridBagConstraints();
    gc.insets = new Insets(1, 10, 10, 10);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 0;
    gc.gridy = 2;
    gc.gridheight = 2;
    gc.gridwidth = 4;
    add(scrollPane, gc);
  }
  
  @Override
  public void actionPerformed(ActionEvent event) {
    FileInputStream fs;
    Scanner scnr;
    String line;
    File file;
    int fileChooserVal;
    
    //Checks which Button
    JButton sourceEvent = (JButton) event.getSource();
    
    if (sourceEvent == openButton) {
      fileChooserVal = fileChooser.showOpenDialog(this);
      if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
        file = fileChooser.getSelectedFile();
        
        currentFileField.setText(file.getName());
        if (file.canRead()) {
          try {
            fs = new FileInputStream(file);
            scnr = new Scanner(fs);
            
            outputArea.setText("");
            
            while (scnr.hasNext()) {
              line = scnr.nextLine();
              outputArea.append(line + "\n");
            }
            
          }
          
          catch (IOException exception) {
            System.err.println("Failure!");
          }
        }
        
        else {
          JOptionPane.showMessageDialog(this, "Can't read file!");
        }
      }
    }
    else {
      ZooGui.main(null);
      dispose();
    }
  }
    
    public static void main(String[] args) {
      ViewReportFrame vr = new ViewReportFrame();
      
      vr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      vr.pack();
      vr.setVisible(true);
    }
}