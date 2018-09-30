import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class StocksGUI {
	
	private JLabel ticker;
	private JTextField tickerInput;
	private JLabel shares;
	private JTextField sharesInput;
	private JLabel price;
	private JTextField priceInput;
	private JLabel cash;
	private double currentCash = 1000;
	private JButton buy;
	private JButton sell;
	private JLabel error;
	private JTextArea stockList;
	
	PortfolioList folio = new PortfolioList();
	
	
	public StocksGUI() {
		
		WidgetView wv = new WidgetView();
		
		ticker = new JLabel("Stock:");
		tickerInput = new JTextField(5);
		shares = new JLabel("Shares:");
		sharesInput = new JTextField(5);
		price = new JLabel("Price:");
		priceInput = new JTextField(5);
		cash = new JLabel("Cash: $" + String.valueOf(currentCash));
		buy = new JButton("Buy");
		sell = new JButton("Sell");
		error = new JLabel("");
		stockList = new JTextArea();
		
		BuyAction buyButton = new BuyAction(tickerInput, sharesInput, priceInput);
		SellAction sellButton = new SellAction(tickerInput, sharesInput, priceInput);
		
		buy.addActionListener(buyButton);
		sell.addActionListener(sellButton);
		
		wv.add(ticker);
		wv.add(tickerInput);
		wv.add(shares);
		wv.add(sharesInput);
		wv.add(price);
		wv.add(priceInput);
		wv.add(cash);
		wv.add(buy);
		wv.add(sell);
		wv.add(error);
		wv.add(stockList);
		
	}
	
	class BuyAction extends WidgetViewActionEvent {
		
		
		private JTextField myTicker;
		private JTextField myShares;
		private JTextField myPrice;
		
		public BuyAction(JTextField ticker, JTextField shares, JTextField price) {
			
			myTicker = ticker;
			myShares = shares;
			myPrice = price;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if ((Integer.parseInt(myShares.getText()) * Double.parseDouble(myPrice.getText())) < currentCash && Integer.parseInt(myShares.getText()) > 0 && Double.parseDouble(myPrice.getText()) > 0) {
				
				String buyTicker = myTicker.getText();
				int buyShares = Integer.parseInt(myShares.getText());
				double buyPrice = Double.parseDouble(myPrice.getText());
			
				StockHolding stock = new StockHolding(buyTicker, buyShares, buyPrice);
				
				currentCash = currentCash - (buyShares * buyPrice);
				
				cash.setText("Cash: $" + String.valueOf(currentCash));
				tickerInput.setText("");
				sharesInput.setText("");
				priceInput.setText("");
				error.setText("");
				
				folio.add(stock);
				
				stockList.setText(folio.toString());
			}
			
			else {
				error.setText("Invalid transaction.");
				return;
			}
			
		}
	}
	
	class SellAction extends WidgetViewActionEvent {
		
		private JTextField myTicker;
		private JTextField myShares;
		private JTextField myPrice;
		
		public SellAction(JTextField ticker, JTextField shares, JTextField price) {
			myTicker = ticker;
			myShares = shares;
			myPrice = price;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			StockHolding object;
			
			if (Double.parseDouble(myPrice.getText()) >= 0 && folio.exist(myTicker.getText()) == true) {
					
				object = folio.find(myTicker.getText());
				
				if (Integer.parseInt(myShares.getText()) == object.getShares()) {
					currentCash = currentCash + (Integer.parseInt(myShares.getText()) * Double.parseDouble(myPrice.getText()));
					folio.remove(myTicker.getText());
					cash.setText("Cash: $" + String.valueOf(currentCash));
					error.setText("");
					tickerInput.setText("");
					sharesInput.setText("");
					priceInput.setText("");
					stockList.setText(folio.toString());
				}
				else if (Integer.parseInt(myShares.getText()) < object.getShares()) {
					object.setShares(Integer.parseInt(myShares.getText()));
					currentCash = currentCash + (Integer.parseInt(myShares.getText()) * Double.parseDouble(myPrice.getText()));
					folio.remove(myTicker.getText());
					folio.add(object);
					cash.setText("Cash: $" + String.valueOf(currentCash));
					error.setText("");
					tickerInput.setText("");
					sharesInput.setText("");
					priceInput.setText("");
					stockList.setText(folio.toString());
				}
				else if (Integer.parseInt(myShares.getText()) > object.getShares() || Integer.parseInt(myShares.getText()) <= 0) {
					error.setText("Invalid transaction.");
					return;
				}
			}
			else {
				error.setText("Invalid transaction.");
				return;
			}
		}
	}
}
