import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;


public class Bank {
	private static int numAccounts = 20;
	private static ArrayBlockingQueue<Transaction> transactions = new ArrayBlockingQueue<Transaction>(1000);
	private static Account[] accounts = new Account[20];
	//private static int numWorkers = 0;


	class Worker implements Runnable{
		public void run(){
			while(true) {
				try {
					Transaction trans = transactions.take();
					//System.out.println("heyol");
					if(trans.getSender() == -1) { //if we got an exit sequence
						//System.out.println("hey");
						Num.change();
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
					//System.out.println("test");
				} catch(Exception E) {
					
				}
			}
		}

		/*public synchronized void changeWorkers() {
			numWorkers--;
		}*/
	}
	
	public static void main (String [] args) throws IOException, InterruptedException{
		//initialize acccounts
		for (int i=0; i<20; i++) {
			accounts[i] = new Account(i,1000,0);
		}

		//read args
		String filename = args[0];
		Num.setNum(Integer.parseInt(args[1]));
		//System.out.println(filename + " " + numWorkers);
		//initialize worker threads
		Bank b = new Bank();
		for (int i = 0; i<Num.numWorkers; i++) {
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
			transactions.put(new Transaction(sender,receiver, amt));
		}
		
		int temp = Num.numWorkers;
		//kill instruction
		for (int i=0; i<temp; i++) {
			transactions.put(new Transaction(-1,-1,-1));
		}
		//System.out.println("lol");
		while (Num.numWorkers > 0) {
			try {
				Thread.sleep(100);
			}
			catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		//System.out.println("lolo");
		for (Account acc : accounts) {
			System.out.println(acc);
		}
	}
}
/*
 * acct:0 bal:1000 trans:10360
acct:1 bal:1000 trans:8880
acct:2 bal:1000 trans:10440
acct:3 bal:1000 trans:9840
acct:4 bal:1000 trans:10520
acct:5 bal:1000 trans:10520
acct:6 bal:1000 trans:9480
acct:7 bal:1000 trans:9440
acct:8 bal:1000 trans:8720
acct:9 bal:1000 trans:9000
acct:10 bal:1000 trans:9960
acct:11 bal:1000 trans:10520
acct:12 bal:1000 trans:9760
acct:13 bal:1000 trans:9640
acct:14 bal:1000 trans:10320
acct:15 bal:1000 trans:9840
acct:16 bal:1000 trans:10400
acct:17 bal:1000 trans:10560
acct:18 bal:1000 trans:11720
acct:19 bal:1000 trans:10080
 */


