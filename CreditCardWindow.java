import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreditCardWindow extends JFrame 
{
	private JRadioButton visaRB;
	private JRadioButton masterRB;
	private JRadioButton discoverRB;
	private JRadioButton amexRB;
   private JPanel titlePanel;
	private JPanel rBPanel;
   private JPanel buttonPanel;
   private JPanel inputPanel;
	private JTextField inputField;
   private JButton vButton;
   private JButton gButton;
   private JButton cButton;
   private ButtonGroup group;

   
   private boolean isMaster=false;
   private boolean isVisa=false;
   private boolean isDiscover=false;
   private boolean isAMEX=false;
	
   public CreditCardWindow()
	{
      
	    setTitle("Credit Card Number Validator and Generator");
       
      		 setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
       buildTitlePanel();
       buildInputPanel();
       buildButtonPanel();

       buildRadioButtonPanel();
       
      // add(titlePanel);
       add(inputPanel);
       add(buttonPanel);
		 add(rBPanel);
      				
       pack();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         setLocation((dim.width-this.getSize().width)/2, (dim.height-this.getSize().height)/2);

       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
     public void buildTitlePanel()
     {
         titlePanel=new JPanel();
         JLabel title=new JLabel("Credit Card Number Validator and Generator");
         titlePanel.add(title);
         
     }
     
      public void buildButtonPanel()
     {
         buttonPanel=new JPanel();
         vButton=new JButton("Validate");
         gButton=new JButton("Generate");
         cButton=new JButton("Clear");

         ButtonListener bListener=new ButtonListener();
         vButton.addActionListener(bListener); 
         gButton.addActionListener(bListener);
         cButton.addActionListener(bListener);
                 
         buttonPanel.add(vButton);
         buttonPanel.add(gButton);
         buttonPanel.add(cButton);
         
     }

      	
     public void buildRadioButtonPanel()
     {
     
         rBPanel=new JPanel();
         rBPanel.setLayout(new BoxLayout(rBPanel, BoxLayout.X_AXIS) );
         //panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Color"));
         
         rBPanel.setBorder(BorderFactory.createTitledBorder("Credit Cards"));

         RadioButtonListener listener= new RadioButtonListener();
         ImageIcon visa=new ImageIcon("visa.png");
         JLabel visaLabel=new JLabel(visa);
         visaRB=new JRadioButton();
         JPanel visaPanel=new JPanel();
         
         visaPanel.add(visaRB);
         visaPanel.add(visaLabel);
         
         visaRB.addActionListener(listener);
   		visaRB.setMnemonic(KeyEvent.VK_R);
         visaRB.setToolTipText("Change the background to red.");
          
                 
         ImageIcon master=new ImageIcon("MasterCard.png");
         JLabel masterLabel=new JLabel(master);
         masterRB=new JRadioButton();
         JPanel masterPanel=new JPanel();
         masterRB.addActionListener(listener);

         masterPanel.add(masterRB);
         masterPanel.add(masterLabel);

                  
         ImageIcon discover=new ImageIcon("discover.png");
         JLabel discoverLabel=new JLabel(discover);
         discoverRB=new JRadioButton();
         discoverRB.addActionListener(listener);

         JPanel discoverPanel=new JPanel();
         
         discoverPanel.add(discoverRB);
         discoverPanel.add(discoverLabel);

                 
         
         ImageIcon amex=new ImageIcon("amex.png");
         JLabel amexLabel=new JLabel(amex);
         amexRB=new JRadioButton();
         amexRB.addActionListener(listener);

         JPanel amexPanel=new JPanel();
         
         amexPanel.add(amexRB);
         amexPanel.add(amexLabel);


         group=new ButtonGroup();
         group.add(visaRB);
         group.add(masterRB);
         group.add(discoverRB);
         group.add(amexRB);
      		
                  
         rBPanel.add(visaPanel);
         rBPanel.add(masterPanel);
         rBPanel.add(discoverPanel);
         rBPanel.add(amexPanel);
         		

     
     }   
	
     public void buildInputPanel()
     {
         
         
         inputPanel=new JPanel();
         JLabel numberLabel=new JLabel("Card Number");
         inputField=new JTextField(15);
         inputField.addActionListener(new textFieldListener());
         
         inputPanel.add(numberLabel);
         inputPanel.add(inputField);
         
     
     }
     	
     private class RadioButtonListener implements ActionListener
     {
         public void actionPerformed(ActionEvent evt)
         {
         	if(visaRB.isSelected()) 
         	{
               isMaster=false;
               isVisa=true;
               isDiscover=false;
               isAMEX=false;
            
            
            }
         	else if(masterRB.isSelected())
              // validate("master", inputField.getText());
            {
               isMaster=true;
               isVisa=false;
               isDiscover=false;
               isAMEX=false;
             }

         	else if(discoverRB.isSelected())
         		//validate("discover", inputField.getText());
            {
               isMaster=false;
               isVisa=false;
               isDiscover=true;
               isAMEX=false;

            }
         	else
         	{
             	//validate("amex",inputField.getText());
               isMaster=false;
               isVisa=false;
               isDiscover=false;
               isAMEX=true;
            }
         			       				
         }
          
     }

     private class ButtonListener implements ActionListener
     {
         public void actionPerformed(ActionEvent evt)
         {
         	if(evt.getSource()==vButton)
            {
               System.out.println(inputField.getText());
               if(inputField.getText().equals(""))
               {
                  JOptionPane.showMessageDialog(null, "Please enter a credit number before clicking on the validate button.");
               }
               else
               {
                  CreditCardValidator validator=new CreditCardValidator();
                  if(isVisa)
                     validator.validate("visa",inputField.getText());
                  else if(isMaster)
                     validator.validate("master",inputField.getText());
                  else if(isDiscover)
                     validator.validate("discover",inputField.getText());
                  else
                     validator.validate("amex",inputField.getText()); 
               }
             }
            else if(evt.getSource()==gButton) 
            {
               CreditCardNumberGenerator generator=new CreditCardNumberGenerator();
               if(isVisa)
                  inputField.setText(generator.generateVisaCardNumbers());
               else if(isMaster)
                  inputField.setText(generator.generateMasterCardNumbers());

               else if(isDiscover)
                   inputField.setText(generator.generateDiscoverCardNumbers());
               else if(isAMEX)
                  inputField.setText(generator.generateAmexCardNumbers());
               else
                  JOptionPane.showMessageDialog(null, "Please select the credit card type first.");
           

            }
            else
            {
               inputField.setText("");
               isMaster=false;
               isVisa=false;
               isDiscover=false;
               isAMEX=false;
               
               group.clearSelection();

            
            }

            
         }
         
          
      }

      
   
   
   
   	
     
     private class textFieldListener implements ActionListener
     {
         public void actionPerformed(ActionEvent evt)
         {
         	         			       				
         }
          
     }	


	public static void main(String[] args)
	{
		new CreditCardWindow();
		
	}

}
		

      