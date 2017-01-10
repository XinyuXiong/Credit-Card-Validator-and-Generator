

import java.util.*;

public class CreditCardNumberGenerator {
	
	
   private String[] VISA_PREFIX_LIST = new String[] {"4"};
	private String[] MASTER_PREFIX_LIST = new String[] { "51",
			"52", "53", "54", "55" };
	private String[] AMEX_PREFIX_LIST = new String[] { "34", "37" };
	private String[] DISCOVER_PREFIX_LIST = new String[] { "6011" };

	
   public String getCreditCardNumber(String[] prefixList, int length)
   {

		int randomArrayIndex = (int) (Math.random()* prefixList.length);
		String ccnumber = prefixList[randomArrayIndex];
		
		// generate digits

		while (ccnumber.length() < (length - 1)) {
			ccnumber += new Double((int)(Math.random() * 10)).intValue();
		}

		// reverse number and convert to int

      StringBuilder stb=new StringBuilder(ccnumber);
      
		String reversedCCnumberString = (stb.reverse()).toString();

		ArrayList<Integer> reversedCCnumberList = new ArrayList<Integer>();
		for (int i = 0; i < reversedCCnumberString.length(); i++) {
			reversedCCnumberList.add(new Integer(String
					.valueOf(reversedCCnumberString.charAt(i))));
      
		}

		// calculate sum

		int sum = 0;
		int pos = 0;

		Integer[] reversedCCnumber = reversedCCnumberList
				.toArray(new Integer[reversedCCnumberList.size()]);
		while (pos < length - 1) {

			int odd = reversedCCnumber[pos] * 2;
			if (odd > 9) {
				odd -= 9;
			}

			sum += odd;

			if (pos != (length - 2)) {
				sum += reversedCCnumber[pos + 1];
			}
			pos += 2;
		}

		// calculate check digit

		int checkdigit = new Double(
				((Math.floor(sum / 10) + 1) * 10 - sum) % 10).intValue();
		ccnumber += checkdigit;

		return ccnumber;

  }

	public  String generateMasterCardNumbers()
   {
      int length=(int)(Math.random()*3)+16;
      return getCreditCardNumber(MASTER_PREFIX_LIST, length);
	}
   
   public  String generateVisaCardNumbers() 
   {
       int length=(int)(Math.random()*3)+13;

		return getCreditCardNumber(VISA_PREFIX_LIST, length);
	}

   public  String generateAmexCardNumbers() 
   {
      
		return getCreditCardNumber(AMEX_PREFIX_LIST, 15);
	}

   public  String generateDiscoverCardNumbers() 
   {
      
		return getCreditCardNumber(DISCOVER_PREFIX_LIST, 16);
	}

	
		}