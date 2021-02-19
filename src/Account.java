import java.util.*;

public class Account {
	private int id;
	private int bal;
	private int trans;

	public Account(int id, int bal, int trans) {
		this.id = id;
		this.bal = bal;
		this.trans = trans;
	}

	public int getId() {
		return this.id;
	}

	public int getBal() {
		return this.bal;
	}

	public int getTrans() {
		return this.trans;
	}

	public void setBal(int b) {
		this.bal = b;
	}

	public void setTrans(int t) {
		this.trans = t;
	}

	public void withdraw(int w) {
		this.bal -= w;
		this.trans++; 
	}

	public void deposit(int d) {
		this.bal += d;
		this.trans++; 
	}

	public String toString() {
		return ("acct:" + id + " bal:" + bal + " trans:" + trans);
	}
}
