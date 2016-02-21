import java.math.BigInteger;
import java.util.Random;

public class Main {

	// static random to use when a new random BigInteger is generated
	static Random rand = new Random();

	public static void main(String[] args) {

		System.out.println("----------------------------------ASSIGNMENT 2-----------------------------------\n");
		generator(512);
		generator(1024);
		generator(2048);
		System.out.println("Assignment 2 complete");
		System.out.println("----------------------------------ASSIGNMENT 3-5-----------------------------------\n");
		RSAExponent();
		System.out.println("Assignment 3-5 complete");

	}

	/*
	 * Static method that is using the Rabin-Miller algorithm to check if the
	 * BigInteger n is a prime or a composite. Return true if it is probably a
	 * prime, and false if it is a composite.
	 * 
	 */
	public static boolean rabinMillerTest(BigInteger n, int bitlength) {
		if (n.equals(BigInteger.valueOf(0)) || n.equals(BigInteger.valueOf(1))) {
			return false;
		}
		if (n.equals(BigInteger.valueOf(2)) || n.equals(BigInteger.valueOf(3))) {
			return true;
		}

		int r = 0;
		BigInteger s = n.subtract(BigInteger.ONE);
		while (!s.testBit(0)) {
			s = s.divide(BigInteger.valueOf(2));
			r++;

		}
		for (int i = 0; i < 20; i++) {
			BigInteger a;
			do {
				a = new BigInteger(bitlength, rand);
			} while (a.compareTo(BigInteger.valueOf(2)) < 0 || a.compareTo(n.subtract(BigInteger.valueOf(2))) > 0);
			BigInteger x = a.modPow(s, n);
			if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
				return true;
			}

			for (int j = 1; j < r - 1; j++) {
				x = x.modPow(BigInteger.valueOf(2), n);

				if (x.equals(BigInteger.ONE)) {
					return false;
				}
				if (x.equals(n.subtract(BigInteger.ONE))) {
					return true;
				}

			}
			return false;

		}

		return true;
	}

	/*
	 * A method that is generating 100 primes using the Rabin-Miller test. The
	 * method checks the time it takes and prints the result in the console
	 * 
	 */
	private static void generator(int bitlength) {
		BigInteger temp;
		long start = System.currentTimeMillis();
		System.out.println("Generation with bitlength: " + bitlength + " started!");
		for (int i = 1; i <= 100; i++) {
			do {
				temp = new BigInteger(bitlength, rand);
			} while (!rabinMillerTest(temp, bitlength) || !temp.testBit(0));
			System.out.println(i);

		}

		System.out.println("Generation with bitlength: " + bitlength + " complited!\n Total time: "
				+ (System.currentTimeMillis() - start) + " ms\n");

	}

	/* A method that finds v such that d=gcd(a,m) = a x v mod m, 
	 * if d = 1 then v is the inverse of a modulo m.
	 */
	private static BigInteger inverseMod(BigInteger m, BigInteger a) {
		BigInteger d1 = m;
		BigInteger d2 = a;
		BigInteger v1 = BigInteger.ZERO;
		BigInteger v2 = BigInteger.ONE;

		while (!d2.equals(BigInteger.ZERO)) {
			BigInteger q = d1.divide(d2);
			BigInteger t2 = v1.subtract(q.multiply(v2));
			BigInteger t3 = d1.subtract(q.multiply(d2));
			v1 = v2;
			v2 = t2;
			d1 = d2;
			d2 = t3;

		}
		BigInteger v = v1;
		BigInteger d = d1;

		if (v.compareTo(BigInteger.ZERO) < 0) {
			return v.add(m);
		}
		if (d.compareTo(BigInteger.ONE) > 0) {
			return null;
		}

		return v;

	}

	/*A static method that encrypt and decrypt a message and checks if it is correct encrypted and decrypted.
	 * 
	 */
	private static void RSAExponent() {
		BigInteger p = new BigInteger(
				"14439387693894199240705138671761644884574009876339628877673995849573528630478892694840078004210638934773188106051113021390538674243130589326729054983724673");
		BigInteger q = new BigInteger(
				"7341551497387753784433514062898919305791812005701318426076740460540616069112545818856877680345369712693892766934354460424979200377180578670689966389908181");
		BigInteger m = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2)));
		BigInteger e = BigInteger.valueOf((long) (Math.pow(2, 16) + 1));
		BigInteger d = inverseMod(m, e);
		System.out.println("d value is: " + d);
		BigInteger N = p.multiply(q);
		BigInteger s;
		do {
			s = new BigInteger(256, rand);
		} while (!s.testBit(0));
		System.out.println("s value is: " + s);
		BigInteger c = s.modPow(e, N);
		System.out.println("c value is: " + c);
		BigInteger z = c.modPow(d, N);
		System.out.println("z value is: " + z);
		if (z.equals(s)) {
			System.out.println("It is correct z==s");
		} else {
			System.out.println("Incorrect z != s");
		}
	}
}