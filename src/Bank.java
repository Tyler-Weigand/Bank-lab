import java.util.*;


public class Bank {
	private ArrayDeque transactions = new ArrayDeque();
	private int numWorkers = 20;
	class Worker implements Runnable{
		public void run() {
			numWorkers++;
		}
	}
	
	public static void main (String [] args) {
		System.out.println("lol");
	}
}
