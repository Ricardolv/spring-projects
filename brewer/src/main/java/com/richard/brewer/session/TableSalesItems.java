package com.richard.brewer.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.richard.brewer.model.Beer;
import com.richard.brewer.model.SalesItem;

 class TableSalesItems implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private List<SalesItem> items = new ArrayList<>();
	
	public TableSalesItems(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getTotalValue() {
		return items.stream()
				.map(SalesItem::getTotalValue)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void addItem(Beer beer, Integer quantity) {

		Optional<SalesItem> salesItemOptional = searchItemByBeer(beer);
		
		SalesItem salesItem = null;
		if (salesItemOptional.isPresent()) {
			salesItem = salesItemOptional.get();
			salesItem.setQuantity(salesItem.getQuantity() + quantity);
			
		} else {
			
			salesItem = new SalesItem();
			salesItem.setBeer(beer);
			salesItem.setUnitaryValue(beer.getValue());
			salesItem.setQuantity(quantity);
			items.add(0, salesItem);
		}

	}
	
	public void alterAmountItems(Beer beer, Integer quantity) {
		SalesItem item = searchItemByBeer(beer).get();
		item.setQuantity(quantity);
	}
	
	public void deleteItem(Beer beer) {
		int index = IntStream.range(0, items.size())
				.filter(i -> items.get(i).getBeer().equals(beer))
				.findAny().getAsInt();
		items.remove(index);
	}

	public int total() {
		return items.size();
	}

	public List<SalesItem> getItems() {
		return items;
	}
	
	private Optional<SalesItem> searchItemByBeer(Beer beer) {
		return items.stream()
					.filter(i -> i.getBeer().equals(beer))
					.findAny();
	}
	
	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableSalesItems other = (TableSalesItems) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
