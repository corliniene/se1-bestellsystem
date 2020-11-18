package system;

import java.util.function.Consumer;

import datamodel.Order;
import datamodel.OrderItem;

final class OrderProcessor implements Components.OrderProcessor {

	private InventoryManager InventoryManager;
	
	OrderProcessor(InventoryManager invm) {
		 InventoryManager = invm;
	}

	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(Order order, Consumer<Order> acceptCode, Consumer<Order> rejectCode,
			Consumer<OrderItem> rejectedOrderItemCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue) {
		return vat(grossValue, 1);
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		long tax = 0;
		if(rateIndex == 1) {
			tax = Math.round((grossValue/1.19)*0.19);
		}
		if(rateIndex == 2) {
			tax = Math.round((grossValue/1.07)*0.07);
		}
		return tax;
	}

}
