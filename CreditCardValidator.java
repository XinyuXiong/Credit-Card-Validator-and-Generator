import javax.swing.*;

public class CreditCardValidator
{
   
   public void validate(String cardName, String cardNumber)
	      {
            
            if(checkCriteria(cardName, cardNumber))
            {
               if(checkSum(cardNumber))
                   JOptionPane.showMessageDialog(null, "It's a valid number");
               else
                  JOptionPane.showMessageDialog(null, "It's a invalid number");

            }
            else
            {
               JOptionPane.showMessageDialog(null, "It's a invalid number");

            }
            
            //JOptionPane.showMessageDialog(null, cardName+" "+cardNumber);
         
         }

      public boolean checkSum(String check)
      {
        int sum = 0;
        String trimCheck = check.replaceAll(" ", "");
        
        String reverse = new StringBuffer(trimCheck).reverse().toString();
        
        for(int i =0;i<trimCheck.length();i++) 
        {
            int checkThis = Character.digit(reverse.charAt(i), 10);

            if(i%2==0) 
            {
                sum+=checkThis;
            }
            else 
            {
                sum += checkThis *2;
                if(checkThis >=5)
                {
                    sum-=9;
                }
            }
        }
        if(sum%10==0) 
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
   
   public boolean checkCriteria(String cardName, String cardNumber) 
   {
        int length = cardNumber.replaceAll(" ","").length();
       

        if (cardName.equals("master")) 
        {
            String pre = cardNumber.substring(0,2);
            
            if((pre.equals("55")||pre.equals("51")||pre.equals("54")||pre.equals("52")||pre.equals("53")) && (length>=16&&length<=19)) 
               return true;
            else
               return false;
        }

       else if (cardName.equals("visa")) 
       {
            String pre = cardNumber.substring(0,1);
           
                 
            if(pre.equals("4") && (length<=16&&length>=13)) 
               return true;
            else
               return false;
        }

       else if (cardName.equals("discover"))
       {
         
           
                 
            if((cardNumber.substring(0,4).equals("6011")||cardNumber.substring(0,2).equals("65") )&& (length==16)) 
               return true;
            else
               return false;


       }
       else if(cardName.equals("amex"))
       {
          String pre = cardNumber.substring(0,2);
           
                 
            if((pre.equals("37")||pre.equals("34")) && length==15) 
               return true;
            else
               return false;

       }
       else
       {
        return false;

       }
       
     }


}