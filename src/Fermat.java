import java.math.BigInteger;


public class Fermat {
	
	public BigInteger factor(BigInteger n) {
		// Fermat's Factorization Method
		BigInteger A = BigMath.sqrt(n);
		BigInteger Bsq = A.multiply(A).subtract(n);
		BigInteger B = BigMath.sqrt(Bsq);
		BigInteger AminusB = A.subtract(B);
		
		// c is a chosen bound which controls when Fermat stops
		BigInteger c = new BigInteger("30");
		BigInteger AminusB_prev = A.subtract(B).add(c);
		BigInteger result = null;
		
		while (!BigMath.sqrt(Bsq).pow(2).equals(Bsq) && AminusB_prev.subtract(AminusB).compareTo(c) > -1) {
			A = A.add(BigInteger.ONE);
			Bsq = A.multiply(A).subtract(n);
			
			B = BigMath.sqrt(Bsq);
			AminusB_prev = AminusB;
			AminusB = A.subtract(B);
		}
		
		if (BigMath.sqrt(Bsq).pow(2).equals(Bsq)) {
			result = AminusB;
		} 
		
		// Trial Division
		else {
			boolean solved = false;
			BigInteger p = AminusB.add(BigMath.TWO); 
			
			if (p.remainder(BigMath.TWO).intValue() == 0) { 
				p = p.add(BigInteger.ONE);
			}
			while (!solved) {
				p = p.subtract(BigMath.TWO);
				if (n.remainder(p).equals(BigInteger.ZERO)) {
					solved = true;
				}
			}
			
			result = p;
		}
		
		return result;
	}
	
}
