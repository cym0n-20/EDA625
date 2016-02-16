import java.math.BigInteger;
import java.util.Random;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
	
	public boolean rabinMillerTest(BigInteger n){
		if(n.compareTo(new BigInteger("3"))<0){
			return true;
		}
		BigInteger r = BigInteger.ZERO;
		BigInteger s = n.subtract(BigInteger.ONE);
		while(s.testBit(1)){
			s= s.divide(new BigInteger("2"));
			r = r.add(BigInteger.ONE);
		}
		for(int i =0; i<20; i++){
			BigInteger a = getRandomInteger(new BigInteger("2"), n.subtract(BigInteger.ONE));
			BigInteger x = a.modPow(s,n);
			if(x.compareTo(BigInteger.ONE)==0 || x.compareTo(n.subtract(BigInteger.ONE))==0){
				return true;
			}
		}
		
		return true;
	}
	
	private static BigInteger getRandomInteger(BigInteger last, BigInteger first) {
		Random rand = new Random();
		BigInteger a;
		do{
			a = new BigInteger(first.bitLength(), rand);
		}while(a.compareTo(last)<0 || a.compareTo(first)>0);
		return a;
		
	}

}