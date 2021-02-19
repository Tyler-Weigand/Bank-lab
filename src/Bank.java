import java.util.*;


public class Bank {
	private ArrayDeque transactions = new ArrayDeque();
	private int numAccounts = 20;

	class Worker implements Runnable {
		public void run() {
			numWorkers++;
		}
	}
	
	public static void main (String [] args) {
		//initialize acccounts
		Account[] accounts = new Account[20];
		for (int i=0; i<20; i++) {
			accounts[i] = new Account();
		}

		//read args
		String filename = args[0];
		int threads = Integer.parseInt(args[1]);

		//initialize worker threads
		for (int i = 0; i<threads; i++) {
			(new Thread(new Worker())).start();
		}


	}
}
