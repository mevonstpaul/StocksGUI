public class StockHolding {
	
	private String ticker;
	private int numShares;
	private double initialSharePrice;
	private double currentSharePrice;
	
	public StockHolding(String ticker, int numberShares, double initialPrice) {
		this.ticker = ticker;
		numShares = numberShares;
		initialSharePrice = initialPrice;
		currentSharePrice = initialPrice;
	}
	
	public double getCurrentValue() {
		return numShares * getCurrentSharePrice();
	}
	
	public double getInitialCost() {
		return numShares * getInitialSharePrice();
	}
	
	public double getCurrentProfit() {
		return getCurrentValue() - getInitialCost();
	}

	public double getCurrentSharePrice() {
		return currentSharePrice;
	}

	public void setCurrentSharePrice(double currentSharePrice) {
		this.currentSharePrice = currentSharePrice;
	}

	public String getTicker() {
		return ticker;
	}

	public int getShares() {
		return numShares;
	}

	public double getInitialSharePrice() {
		return initialSharePrice;
	}
	
	public void setShares(int shares) {
		numShares = numShares - shares;
	}

	@Override
	public String toString() {
		return "\n Stock: " + ticker + ", Shares: " + numShares +  ", Bought at: $" + initialSharePrice;
	}

}