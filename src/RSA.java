/*
 * RSA Algorithm
 * @author Eduardo Lundgren
 */

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

	private final static BigInteger one = new BigInteger("1");

	private final static SecureRandom random = new SecureRandom();

	public BigInteger privateKey;

	public BigInteger publicKey;

	public BigInteger n;
	
	BigInteger phi;
	
	BigInteger p;
	
	BigInteger q;


	RSA (BigInteger p, BigInteger q) {
		
		this.p = p;
		this.q = q;
		
		phi = ( p.subtract(one) ).multiply( q.subtract(one) ); // phi = (p - 1)*(q - 1)

		n = p.multiply(q); // n = p*q

		publicKey  = new BigInteger("65537"); // e = common prime = 2^16 + 1

		privateKey = publicKey.modInverse(phi); // d = (publicKey^-1) * mod(phi)

	}
	
	RSA (int bits) {
		
		p = BigInteger.probablePrime(bits, random); // generating a big prime number (bits)
		q = BigInteger.probablePrime(bits, random); // generating a big prime number (bits)
		phi = ( p.subtract(one) ).multiply( q.subtract(one) ); // phi = (p - 1)*(q - 1)

		n = p.multiply(q); // n = p*q

		publicKey  = new BigInteger("65537"); // e = common prime = 2^16 + 1

		privateKey = publicKey.modInverse(phi); // d = (publicKey^-1) * mod(phi)

	}

	BigInteger encrypt(BigInteger message) {
		return message.modPow(publicKey, n); // C = (message^publicKey) * mod n
	}

	BigInteger decrypt(BigInteger encrypted) {
		return encrypted.modPow(privateKey, n); // C = (encrypted^privateKey) * mod m
	}

	public static void main(String[] args) {

		int bits = Integer.parseInt(args[0]);
		
		RSA rsa = new RSA( bits );
		
		String message = args[1];
		
		BigInteger mensagemNumero = Converte.string2number(message);
		
		System.out.println("-");
		
		System.out.println( "p: " + rsa.p );
		System.out.println( "q: " + rsa.q );
		System.out.println( "n = p*q: " + rsa.n );
		System.out.println( "phi = (p - 1)*(q - 1): " + rsa.phi );
		System.out.println( "Chave publica: " + rsa.publicKey + " (most common prime 2^16 + 1)" );
		System.out.println( "Chave privada: " + rsa.privateKey );
		
		System.out.println("-");
		
		System.out.println( "Mensagem texto claro: " + message );
		System.out.println( "Mensagem numero: " + mensagemNumero );
		
		BigInteger encryptedMessage = rsa.encrypt(mensagemNumero);
		BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
		
		System.out.println( "Mensagem criptografada: " + encryptedMessage );
		System.out.println( "Mensagem decriptografada: " + decryptedMessage );
		
		String mensagemTextoClaroDecptografada = Converte.number2string(decryptedMessage);
		
		System.out.println( "Mensagem decriptografada texto claro: " + mensagemTextoClaroDecptografada );

	}
}