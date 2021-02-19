import java.util.*;


public class Bank {
	private ArrayDeque<Transaction> transactions = new ArrayDeque<Transaction>();
	private int numAccounts = 20;
	private Account[] accounts = new Account[20];


	class Worker implements Runnable {
		public void run() {
			numWorkers++;
		}
	}
	
	public static void main (String [] args) {
		//initialize acccounts
		for (int i=0; i<20; i++) {
			accounts[i] = new Account(i,1000,0);
		}

		//read args
		String filename = args[0];
		int threads = Integer.parseInt(args[1]);

		//initialize worker threads
		for (int i = 0; i<threads; i++) {
			(new Thread(new Worker())).start();
		}

		//read file
		File t = new File(filename);
		Scanner s = new Scanner(t);
		int sender = 0;
		int receiver = 0;
		int amt = 0;
		while (s.hasNextLine()) {
			sender = s.nextInt();
			receiver = s.nextInt();
			amt = s.nextInt();
			transactions.add(new Transaction(sender,receiver, amt));
		}


	}
}
