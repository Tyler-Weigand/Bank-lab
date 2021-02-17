import java.util.*;


public class Bank {
	private ArrayDeque transactions = new ArrayDeque();
	private int numWorkers = 20;

	class Worker implements Runnable {
		public void run() {
			numWorkers++;
		}
	}
	
	public static void main (String [] args) {
		String filename = args[0];
		int threads = Integer.parseInt(args[1]);
		for (int i = 0; i<threads; i++) {
			(new Thread(new Worker())).start();
		}
	}
}
