import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		BigInteger i = new BigInteger("1232123123");
		System.out.println(i);
		i = i.add(new BigInteger("123123123"));
		System.out.println(i);
		i = i.mod(new BigInteger("23"));
		System.out.println(i);
		
		if(i.testBit(0)){
			System.out.println(i+"Sant");
		}
		i = i.add(BigInteger.ONE);
		if(i.testBit(0)){
			System.out.println(i+"Sant");
		}else{
			System.out.println("adasdasdsda");
		}
	}
	
	public boolean rabinMillerTest(BigInteger n){
		// return true if n is probably prime, false if composite
		
		// Alla positiva heltal i intervallet 0<x<4 är primtal. Returnera true direkt.
		if(n.compareTo(new BigInteger("3")) <= 0){
			return true; 
		}
		
		return false;
	}

}
