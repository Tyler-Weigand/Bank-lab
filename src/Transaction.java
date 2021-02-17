
public class Transaction {
	private final int sender, receiver, amount;
	public Transaction(int sender, int receiever, int amount) {
		this.sender = sender;
		this.receiver = receiever;
		this.amount = amount;
	}
	
	public int getSender() {
		return sender;
	}
	
	public int getReceiver() {
		return receiver;
	}
	
	public int getAmount() {
		return amount;
	}
}
