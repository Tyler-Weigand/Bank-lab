import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;


public class Bank {
	private static int numAccounts = 20;
	private static ArrayBlockingQueue<Transaction> transactions = new ArrayBlockingQueue<Transaction>(1000);
	private static Account[] accounts = new Account[numAccounts];


	class Worker implements Runnable{
		public void run(){
			while(true) {
				try {
					Transaction trans = transactions.take();
					if(trans.getSender() == -1) { //if we got an exit sequence
						Num.change();
						return;
					}
					else { //process transaction
						Account sender = accounts[trans.getSender()];
						Account receiver = accounts[trans.getReceiver()];
						sender.withdraw(trans.getAmount());
						receiver.deposit(trans.getAmount());
					}
				} catch(Exception E) {
					
				}
			}
		}
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
		while (Num.numWorkers > 0) {
			try {
				Thread.sleep(100);
			}
			catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		for (Account acc : accounts) {
			System.out.println(acc);
		}
		s.close();
	}
}