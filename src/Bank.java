import java.util.*;
import java.io.*;


public class Bank {
	private static ArrayDeque<Transaction> transactions = new ArrayDeque<Transaction>();
	private static int numAccounts = 20;
	private static Account[] accounts = new Account[20];
	private static int numWorkers = 0;


	class Worker implements Runnable {
		public void run() {
			while(true) {
				Transaction trans = transactions.poll();
				//System.out.println("heyol");
				if(trans != null) { //making sure that we actually pulled something to process
					if(trans.getSender() == -1) { //if we got an exit sequence
						//System.out.println("hey");
						numWorkers--;
						//System.out.println(numWorkers);
						return;
					}
					else { //process transaction
						//System.out.println("heyo");
						Account sender = accounts[trans.getSender()];
						Account receiver = accounts[trans.getReceiver()];
						sender.withdraw(trans.getAmount());
						receiver.deposit(trans.getAmount());
						//System.out.println(sender.getBal());
					}
				}
				System.out.println("test");
			}
		}
	}
	
	public static void main (String [] args) throws IOException {
		//initialize acccounts
		for (int i=0; i<20; i++) {
			accounts[i] = new Account(i,1000,0);
		}

		//read args
		String filename = args[0];
		numWorkers = Integer.parseInt(args[1]);
		System.out.println(filename + " " + numWorkers);
		//initialize worker threads
		Bank b = new Bank();
		for (int i = 0; i<numWorkers; i++) {
			(new Thread(b.new Worker())).start();
		}
		
		//read file
		File t = new File(filename);
		Scanner s = new Scanner(t);
		int sender = 0;
		int receiver = 0;
		int amt = 0;
		while (s.hasNextInt()) {
			sender = s.nextInt();
			receiver = s.nextInt();
			amt = s.nextInt();
			transactions.add(new Transaction(sender,receiver, amt));
		}
		
		//kill instruction
		for (int i=0; i<numWorkers; i++) {
			transactions.add(new Transaction(-1,-1,-1));
		}
		//System.out.println("lol");
		while (numWorkers > 0) {
			System.out.println("stuck");
		}
		//System.out.println("lolo");
		for (Account acc : accounts) {
			System.out.println(acc);
		}



	}
}
