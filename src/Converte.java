/*
 * Converte
 * @author Eduardo Lundgren
 */

import java.math.BigInteger;


public class Converte {

	public static BigInteger string2number(String message) {

		StringBuffer numberString = new StringBuffer("1");
		
		for ( int i = 0; i < message.length(); ++i ) {
			char c = message.charAt( i );
			int asc = (int) c;
			
			if ( String.valueOf(asc).length() <= 2 ) {
				numberString.append("0");
			}
			
			numberString.append(asc);
		}
		
		BigInteger number = new BigInteger(numberString.toString());
		
		return number;
	}
	
	public static String number2string(BigInteger number) {

		StringBuffer message = new StringBuffer();
		
		String messageNumber = number.toString();

		// removendo o 1 adicionado no comeco do numero
		// para previnir erro na decodificacao
		messageNumber = messageNumber.substring(1, messageNumber.length());
		
		for ( int i = 0; i < messageNumber.length(); i += 3 ) {
			String blockString = messageNumber.substring(i, i+3);
			int block = Integer.parseInt( blockString );
			message.append( (char) block );
		}
		
		return message.toString();
	}
	
}
