import java.util.ArrayList;

public class PortfolioList {
	
	private ArrayList<StockHolding> aList= new ArrayList<StockHolding>();
	
	public PortfolioList() {
		
	}
	
	public void add(StockHolding stock) {
		aList.add(stock);
	}
	
	public void remove(String ticker) {
		StockHolding stock = find(ticker);
		int index = aList.indexOf(stock);
		aList.remove(index);
		
	}
	
	public StockHolding find(String ticker) {
		
		int index = 0;
		while (index < aList.size()) {
			StockHolding stock = aList.get(index);
			String s = stock.getTicker();
			
			if (s.equals(ticker)) {
				return aList.get(index);
			}
			else {
				index++;
			}
		}
		return aList.get(index);
	}
	
	public Boolean exist(String ticker) {
		int index = 0;
		while (index < aList.size()) {
			if (aList.get(index).getTicker().equals(ticker)) {
				return true;
			}
			else {
				index++;
			}
		}
		return false;
	}
	
	public String toString() {
		String s = "";
		int index = 0;
		while (index < aList.size()) {
			StockHolding object = aList.get(index);
			s = s + object.toString();
			index++;
		}
		return s;
	}

}