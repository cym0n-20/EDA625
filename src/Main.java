import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("----------------------------------ASSIGNMENT 2-----------------------------------\n");
		generator(512);
		generator(1024);
		generator(2048);
		System.out.println("Assignment 2 complete");
		System.out.println("----------------------------------ASSIGNMENT 3-----------------------------------\n");
		RSAExponent();
		
	
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
			} while (a.compareTo(BigInteger.valueOf(2)) < 0 || a.compareTo(n.subtract(BigInteger.valueOf(2))) > 0);
			BigInteger x = a.modPow(s, n);
			if (x.compareTo(BigInteger.ONE) == 0 || x.compareTo(n.subtract(BigInteger.ONE)) == 0) {
				return true;
			}
			
			for (BigInteger j = BigInteger.ONE; j.compareTo(r.subtract(BigInteger.ONE)) < 0; j = j.add(BigInteger.ONE)) {
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

	private static void generator(int bitlength) {
		ArrayList<BigInteger> list = new ArrayList<BigInteger>();
		BigInteger b = BigInteger.valueOf(2).pow(bitlength - 1);
		BigInteger temp;
		BigInteger temp1;
		long start = System.currentTimeMillis();
		long tot = 0;
		int nbr = 1;
		System.out.println("Generation with bitlength: " + bitlength + " started!");
		while (list.size() < 100) {
			temp = new BigInteger(bitlength, new Random());
			temp1 = b.add(temp);
			if (rabinMillerTest(temp1, bitlength) && !list.contains(temp1)) {
				list.add(temp1);
				long time = System.currentTimeMillis() - start;
				System.out.println("nbr: " + nbr + " time: " + time + " ms    value: " + temp1);
				nbr++;
				tot = time;
			}

		}
		System.out.println("Generation with bitlength: "+ bitlength + " complited!\n Total time: "+ tot + " ms\n");

	}
	
	private static BigInteger eucludeanAlgo(BigInteger m, BigInteger a){
		BigInteger d1 = m;
		BigInteger d2 = a;
		BigInteger v1 = BigInteger.ZERO;
		BigInteger v2 = BigInteger.ONE;
		
		while(!d2.equals(BigInteger.ZERO)){
			BigInteger q = d1.divide(d2);
			BigInteger t2 = v1.subtract(q.multiply(v2));
			BigInteger t3 = d1.subtract(q.multiply(d2));
			v1 = v2;
			v2 = t2;
			d1 = d2;
			d2 = t3;
			
		}
		BigInteger v=v1;
		BigInteger d = d1;
		
		if(v.compareTo(BigInteger.ZERO)<0){
			return v.add(m);
		}
		if(d.compareTo(BigInteger.ONE)>0){
			return null;
		}
		
		return v;
		
	}
	
	private static void RSAExponent(){
		BigInteger p = new BigInteger(
				"14439387693894199240705138671761644884574009876339628877673995849573528630478892694840078004210638934773188106051113021390538674243130589326729054983724673");
		BigInteger q = new BigInteger(
				"7341551497387753784433514062898919305791812005701318426076740460540616069112545818856877680345369712693892766934354460424979200377180578670689966389908181");
		BigInteger m = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2)));
		BigInteger e = BigInteger.valueOf((long) (Math.pow(2, 16)+1));
		BigInteger d = eucludeanAlgo(m, e);
		System.out.println("d value is: " + d);
		BigInteger N = p.multiply(q);
		BigInteger s;
		do{
			s =new BigInteger(512, new Random());
		}while(!s.testBit(0));
		System.out.println("s value is: " + s);
		BigInteger c = s.modPow(e, N);
		System.out.println("c value is: " + c);
		BigInteger z = c.modPow(d,N);
		System.out.println("z value is: " + z);
		if(z.equals(s)){
			System.out.println("It is correct z==s");
		}else{
			System.out.println("Incorrect z != s");
		}
	}
}