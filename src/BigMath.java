import java.math.BigInteger;

public class BigMath {
	// Integers
	public static final BigInteger ZERO = new BigInteger("0");
	public static final BigInteger ONE = new BigInteger("1");
	public static final BigInteger TWO = new BigInteger("2");
	public static final BigInteger THREE = new BigInteger("3");
	public static final BigInteger FOUR = new BigInteger("4");
	public static final BigInteger FIVE = new BigInteger("5");
	public static final BigInteger SIX = new BigInteger("6");
	public static final BigInteger SEVEN = new BigInteger("7");
	public static final BigInteger EIGHT = new BigInteger("8");
	public static final BigInteger NINE = new BigInteger("9");
	public static final BigInteger TEN = new BigInteger("10");
	
	public static final BigInteger NEG_ONE = new BigInteger("-1");

	public static BigInteger sqrt(BigInteger n) {
		BigInteger r = BigInteger.ZERO;
		BigInteger m = r.setBit(2 * n.bitLength());
		BigInteger nr;

		do {
			nr = r.add(m);
			if (nr.compareTo(n) != 1) {
				n = n.subtract(nr);
				r = nr.add(m);
			}
			r = r.shiftRight(1);
			m = m.shiftRight(2);
		} while (m.bitCount() != 0);

		return r;
	}

}
