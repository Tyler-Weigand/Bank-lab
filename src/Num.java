public class Num {
	public static int numWorkers = 0;

	public static synchronized void change() {
		numWorkers--;
	}

	public static void setNum(int w) {
		numWorkers = w;
	}
}