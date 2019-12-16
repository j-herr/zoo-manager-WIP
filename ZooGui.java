import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ZooGui extends JFrame implements ActionListener {
  private JButton animalButton, giftShopButton, restaurantButton, ticketButton;
  private JButton viewReportButton;
  private JLabel zooLabel, imageLabel;
  private JPanel buttonPanel, zooPanel;
  
  private ImageIcon zooImage;
  private GridBagConstraints gc;
  
  public ZooGui() {
    //Set frame title
    setTitle("Welcome!");
    
    //Create label
    zooLabel = new JLabel("Zoo Manager");
    
    zooImage = new ImageIcon("ZooImage.jpg");
    imageLabel = new JLabel("", zooImage, JLabel.CENTER);
    
    //Create buttons
    animalButton = new JButton("Animals");
    animalButton.addActionListener(this);
    
    giftShopButton = new JButton("Gift Shop");
    giftShopButton.addActionListener(this);
    
    restaurantButton = new JButton("Restaurants");
    restaurantButton.addActionListener(this);
    
    ticketButton = new JButton("Ticket Sales");
    ticketButton.addActionListener(this);
    
    viewReportButton = new JButton("View Report");
    viewReportButton.addActionListener(this);
    
    //Setting panels
    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.BLUE);
    zooPanel = new JPanel();
    
    //Layout Managers
    buttonPanel.setLayout(new GridBagLayout());
    zooPanel.setLayout(new GridBagLayout());
    setLayout(new BorderLayout());
    
    //ButtonPanel GridBagLayout
    gc = new GridBagConstraints();
    gc.insets = new Insets(5, 10, 10, 10);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 0;
    gc.gridy = 0;
    buttonPanel.add(animalButton, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 10, 10, 10);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 0;
    gc.gridy = 1;
    buttonPanel.add(giftShopButton, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 10, 10, 10);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 0;
    gc.gridy = 2;
    buttonPanel.add(restaurantButton, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 10, 5, 10);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 0;
    gc.gridy = 3;
    buttonPanel.add(ticketButton, gc);

    //ZooPanel GridBagLayout
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 10, 5, 10);
    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 0;
    gc.gridy = 0;
    zooPanel.add(imageLabel, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(0, 10, 10, 10);
    gc.anchor = GridBagConstraints.CENTER;
    gc.gridx = 0;
    gc.gridy = 1;
    zooPanel.add(zooLabel, gc);
    
    gc = new GridBagConstraints();
    gc.insets = new Insets(10, 10, 25, 10);
    gc.fill = GridBagConstraints.BOTH;
    gc.gridx = 0;
    gc.gridy = 2;
    zooPanel.add(viewReportButton, gc);
 
    
    //Panel BorderLayout
    add(buttonPanel, BorderLayout.LINE_START);
    add(zooPanel, BorderLayout.CENTER);
  }
  
  @Override
  public void actionPerformed(ActionEvent event) {
    
    //Get source of event
    JButton sourceEvent = (JButton) event.getSource();
    
    if (sourceEvent == animalButton) {
      AnimalFrame.main(null);
      dispose();
    }
    else if (sourceEvent == ticketButton) {
      TicketFrame.main(null);
      dispose();
    }
    else if (sourceEvent == viewReportButton) {
      ViewReportFrame.main(null);
      dispose();
    }
  }
  
  public static void main(String[] args) {
    ZooGui zooGui = new ZooGui();
    
    zooGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    zooGui.pack();
    zooGui.setVisible(true);
  }
}