package com.richard.brewer.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.richard.brewer.model.Beer;
import com.richard.brewer.model.SalesItem;

/**
 * Anotacao @SessionScope que insere este componente na sessao para cada usuario logado
 * @author richard
 */
@SessionScope 
@Component
public class TableItemsSession {
	
	private Set<TableSalesItems> tables = new HashSet<>();

	public void addItem(String uuid, Beer beer, int quantity) {
		TableSalesItems table = searchTableByUuid(uuid);
		
		table.addItem(beer, quantity);
		tables.add(table);
	}

	public void alterAmountItems(String uuid, Beer beer, Integer quantity) {
		TableSalesItems table = searchTableByUuid(uuid);
		table.alterAmountItems(beer, quantity);
	}

	public void deleteItem(String uuid, Beer beer) {
		TableSalesItems table = searchTableByUuid(uuid);
		table.deleteItem(beer);
	}

	public List<SalesItem> getItems(String uuid) {
		return searchTableByUuid(uuid).getItems();
	}
	
	public BigDecimal getTotalValue(String uuid) {
		return searchTableByUuid(uuid).getTotalValue();
	}
	
	private TableSalesItems searchTableByUuid(String uuid) {
		TableSalesItems table = tables.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny().orElse(new TableSalesItems(uuid));
		return table;
	}

	

}
