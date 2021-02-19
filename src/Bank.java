import java.util.*;


public class Bank {
	private ArrayDeque transactions = new ArrayDeque();
	private int numAccounts = 20;
	private Account[] accounts = new Account[20];


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
	
	public static void main (String [] args) {
		//initialize acccounts
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
