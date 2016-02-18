import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<BigInteger> list = generator(512);
		System.out.println("complete");

	}

	public static boolean rabinMillerTest(BigInteger n, int bitlength) {
		if (n.compareTo(new BigInteger("3")) < 0) {
			return true;
		}
		BigInteger r = BigInteger.ZERO;
		BigInteger s = n.subtract(BigInteger.ONE);
		while (!s.testBit(0)) {
			s = s.divide(BigInteger.valueOf(2));
			r = r.add(BigInteger.ONE);
		}
		for (int i = 0; i < 20; i++) {
			Random rand = new Random();
			BigInteger a;
			do {
				a = new BigInteger(bitlength, rand);
			} while (a.compareTo(BigInteger.valueOf(2)) < 0 || a.compareTo(n.subtract(BigInteger.valueOf(2)))>0);
			BigInteger x = a.modPow(s, n);
			if (x.compareTo(BigInteger.ONE) == 0 || x.compareTo(n.subtract(BigInteger.ONE)) == 0) {
				return true;
			}
			BigInteger j = BigInteger.ONE;
			for (; j.compareTo(r.subtract(BigInteger.ONE)) < 0; j = j.add(BigInteger.ONE)) {
				x = x.modPow(BigInteger.valueOf(2), n);
				if (x.equals(BigInteger.ONE)) {
					return false;
				}
				if (x.equals(n.subtract(BigInteger.ONE))) {
					return true;
				}

			}

		}

		return false;
	}

	private static ArrayList<BigInteger> generator(int bitlength) {
		ArrayList<BigInteger> list = new ArrayList<BigInteger>();
		BigInteger b = BigInteger.valueOf(2).pow(bitlength - 1);
		BigInteger temp;
		BigInteger temp1;
		long start = System.currentTimeMillis();
		int nbr=1;
		while (list.size() < 100) {
			temp = new BigInteger(bitlength, new Random());
			if (!temp.testBit(0)) {
				temp = temp.add(BigInteger.ONE);
			}
			temp1 = b.add(temp);
			if (rabinMillerTest(temp1, bitlength) && !list.contains(temp1)) {
				list.add(temp1);
				long time =System.currentTimeMillis() - start;
				System.out.println("nbr: "+ nbr +" time: "+ time);
				nbr++;
				
				
				
			}

		}

		return list;

	}

}