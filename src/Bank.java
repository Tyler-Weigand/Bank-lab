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
				Transaction trans = (Transaction)(transactions.poll());
				if(trans != null) { //making sure that we actually pulled something to process
					if(trans.getSender() == -1) { //if we got an exit sequence
						return;
					}
					else { //process transaction
						Account sender = accounts[trans.getSender()];
						Account receiver = accounts[trans.getReceiver()];
						sender.withdraw(trans.getAmount());
						sender.setTrans(sender.getTrans()+1);
						receiver.deposit(trans.getAmount());
						receiver.setTrans(receiver.getTrans()+1);
					}
				}
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

		//initialize worker threads
		Bank b = new Bank();
		for (int i = 0; i<threads; i++) {
			(new Thread(b.new Worker())).start();
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
		//kill instruction
		transactions.add(new Transaction(-1,-1,-1));


	}
}
