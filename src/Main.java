import java.math.BigInteger;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] primes ={ "1", "3", "3613", "7297", "226673591177742970257407", "2932031007403"};
		String[] nonPrimes = { "3341", "2932021007403",
		"226673591177742970257405" };
		for(String p : primes){
			if( rabinMillerTest(new BigInteger(p))){
				System.out.println("yes");
			}
		}
		for(String p : nonPrimes){
			if(!rabinMillerTest(new BigInteger(p))){
				System.out.println("yes no prime");
			}
		}
		

	}

	public static boolean rabinMillerTest(BigInteger n) {
		if (n.compareTo(new BigInteger("3")) < 0) {
			return true;
		}
		BigInteger r = BigInteger.ZERO;
		BigInteger s = n.subtract(BigInteger.ONE);
		while (!s.testBit(0)) {
			s = s.divide(new BigInteger("2"));
			r = r.add(BigInteger.ONE);
		}
		for (int i = 0; i < 20; i++) {
			BigInteger a = getRandomInteger(new BigInteger("2"), n.subtract(BigInteger.ONE));
			BigInteger x = a.modPow(s, n);
			if (x.compareTo(BigInteger.ONE) == 0 || x.compareTo(n.subtract(BigInteger.ONE)) == 0) {
				continue;
			}
			for (BigInteger j = BigInteger.ONE; j.compareTo(r.subtract(BigInteger.ONE)) < 0; j = j
					.add(BigInteger.ONE)) {
				
				System.out.println("hej");
				x = x.modPow(new BigInteger("2"), n);
				if (x.equals(BigInteger.ONE)) {
					return false;
				}
				if (x.equals(n.subtract(BigInteger.ONE))) {
					break;
				}
				
			}
			if (r == s) {
				return false;
			}

		}

		return true;
	}

	private static BigInteger getRandomInteger(BigInteger first, BigInteger last) {
		Random rand = new Random();
		BigInteger a;
		do {
			a = new BigInteger(last.bitLength(), rand);
		} while (a.compareTo(first) < 0 || a.compareTo(last) > 0);
		return a;

	}
	
	

}