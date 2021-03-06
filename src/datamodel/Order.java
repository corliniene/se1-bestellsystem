package datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private final long id;
	private final Date date;
	private final Customer customer;
	private final List<OrderItem> items;
	
	protected Order(long id, Date date, Customer customer) {
		this.id = id;
		if(date == null) {
			date = new Date();
		}
		this.date = date;
		this.customer = customer;
		this.items = new ArrayList<OrderItem>();
	}

	public long getId() {
		
		return this.id;
	}
	
	public Date getDate() {

		return this.date;
	}
	
	public Customer getCustomer() {
		
		return this.customer;
	}

	public Iterable <OrderItem> getItems() {
		
		return this.items;
	}
	
	public Order addItem(OrderItem item) {
		if(items.contains(item) || item == null) {
			return null;
		} else {
			items.add(item);
			return this;
		}
	}

	public Order removeItem(OrderItem item) {
		items.remove(item);
		return this;
	}

	public Order clearItems() {
		items.clear();
	return this;
}

	public int count() {
		return items.size();
	}
	
}
