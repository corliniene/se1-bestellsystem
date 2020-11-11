package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import datamodel.Article;
import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;

final class OutputProcessor implements Components.OutputProcessor{
	private InventoryManager InventoryManager;
	private OrderProcessor OrderProcessor;
	private final int printLineWidth = 95;

	
	
	
	
	OutputProcessor(InventoryManager invm, OrderProcessor orp) {
		InventoryManager = invm;
		OrderProcessor = orp;
		
		
	}
	
	@Override
	public void printOrders(List<Order> orders, boolean printVAT) {
		
		StringBuffer sbAllOrders = new StringBuffer( "-------------\n" );
		StringBuffer sbLineItem = new StringBuffer();
		long totalPriceAllOrders = 0;
	
		for(Order order : orders) {
			Iterable<OrderItem> oI = order.getItems();
			long totalPricePerOrder = 0;
			Customer customer = order.getCustomer();
			String customerName = splitName(customer, customer.getFirstname() + " " + customer.getLastname());
			
			for(OrderItem orderitem : oI) {
				Article art = orderitem.getArticle();
				long price = art.getUnitPrice();
				long unitsOrdered = orderitem.getUnitsOrdered();
				String descr = orderitem.getDescription();
				long endPrice = price * unitsOrdered;
				totalPricePerOrder += endPrice;
				
				if(sbLineItem.length()!=0) {
					sbLineItem.append(", ");
				}
				sbLineItem.append(unitsOrdered + "x ");
				sbLineItem.append(descr);
			}

			sbAllOrders.append(fmtLine("#" + order.getId() + ", " + customerName + "'s " + "Bestellung: " + sbLineItem.toString(), fmtPrice(totalPricePerOrder, "EUR", 14), printLineWidth) + "\n");
			sbLineItem.setLength(0);
			totalPriceAllOrders += totalPricePerOrder;
		}

		// calculate total price
		String fmtPriceTotal = fmtPrice( totalPriceAllOrders, " EUR", 14);

		// append final line with totals
		sbAllOrders.append( fmtLine( "-------------", "------------- -------------", printLineWidth ) )
			.append( "\n" )
			.append( fmtLine( "Gesamtwert aller Bestellungen:", fmtPriceTotal, printLineWidth ) );

		// print sbAllOrders StringBuffer with all output to System.out
		System.out.println( sbAllOrders.toString() );
	
		
	}

	@Override
	public void printInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String fmtPrice(long price, String currency) {
		String fmtPrice = fmtPrice(price, " " + currency, 14);
		
		return fmtPrice;
	}

	@Override
	public String fmtPrice(long price, String currency, int width) {
		StringBuffer sb = new StringBuffer();
			
			double dblPrice = ( (double)price ) / 100.0;			// convert cent to Euro
			DecimalFormat df = new DecimalFormat( "#,##0.00",
				new DecimalFormatSymbols( new Locale( "de" ) ) );	// rounds double to 2 digits

			String fmtPrice = df.format( dblPrice );				// convert result to String in DecimalFormat
			sb.append( fmtPrice + " " + currency );
			return sb.toString();
		
		
		
	}

	@Override
	public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
		StringBuffer sb = new StringBuffer( leftStr );
		int shiftable = 0;		// leading spaces before first digit
		for( int i=1; rightStr.charAt( i ) == ' ' && i < rightStr.length(); i++ ) {
			shiftable++;
		}
		final int tab1 = width - rightStr.length() + 1;	// - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max( 0, excess - shiftable );
		if( shift2 > 0 ) {
			rightStr = rightStr.substring( shift2, rightStr.length() );
			excess -= shift2;
		}
		if( excess > 0 ) {
			switch( excess ) {
			case 1:	sb.delete( sbLen - excess, sbLen ); break;
			case 2: sb.delete( sbLen - excess - 2 , sbLen ); sb.append( ".." ); break;
			default: sb.delete( sbLen - excess - 3, sbLen ); sb.append( "..." ); break;
			}
		}
		String strLineItem = String.format( "%-" + ( tab1 - 1 ) + "s%s", sb.toString(), rightStr );
		sb.setLength( 0 );
		sb.append( strLineItem );
		return sb;
	}

	@Override
	public String splitName(Customer customer, String name) {
		String firstName = "";
		if(name.trim().contains(",")) {
			String[] names = name.split(",");
			customer.setFirstname(names[1].toString().trim());
			customer.setLastname(names[0].toString().trim());
		} else if(name.trim().contains(" ")) {
			String[] names = name.split(" ");

			for(int i = 0; i < (names.length - 1); i++) {
				if(customer.getFirstname() != null && !customer.getFirstname().trim().equals(names[i])) {
					if(firstName != "") {
						firstName += " " + names[i];
					} else {
						firstName += names[i];
					}
				} else {
					firstName = names[i];
				}
			}
			customer.setFirstname(firstName);
			String lastName = names[(names.length - 1)];
			customer.setLastname(lastName);
		}
		
		return customer.getLastname() + ", " + customer.getFirstname();
	}
	

	@Override
	public String singleName(Customer customer) {
		String singleName = customer.getLastname() + ", " + customer.getFirstname();
		return singleName;
	}

}
