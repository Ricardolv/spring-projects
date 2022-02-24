Brewer.TableItems = (function() {
	
	function TableItems(autoComplete) {
		this.autoComplete = autoComplete;
		this.tableBeersContainer = $('.js-table-beers-container');
		this.uuid = $('#uuid').val();
		
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TableItems.prototype.init = function() {
		this.autoComplete.on('selected-item', onSelectedItem.bind(this));
		
		bindQuantity.call(this);
		bindTableItem.call(this);
	}
	
	TableItems.prototype.totalValue = function() {
		return this.tableBeersContainer.data('value');
	}
	
	function onSelectedItem(event, item) {
		var response = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codeBeer: item.code,
				uuid: this.uuid
			}
			
		});
		
		response.done(onUpdatededOnTheServer.bind(this));
	}
	
	function onUpdatededOnTheServer(html) {
		this.tableBeersContainer.html(html);
		
		bindQuantity.call(this);
		
		var tableItem = bindTableItem.call(this);
		
		this.emitter.trigger('table-items-updated', tableItem.data('total-value'));
	}
	
	function onQuantityChangedItem(event) {
		var input = $(event.target);
		var quantity = input.val();
		
		if(quantity <= 0) {
			input.val(1);
			quantity = 1;
		}
		
		var codeBeer = input.data('code-beer');
		
		var response = $.ajax({
			url: 'item/' + codeBeer, 
			method: 'PUT',
			data: {
				quantity: quantity,
				uuid: this.uuid
			}
			
		});
		
		response.done(onUpdatededOnTheServer.bind(this));
	}
	
	function onDoubleClick(event) {
		$(this).toggleClass('requesting-exclusion');
	}
	
	function onExclusionItemClick(event) {
		var codeBeer = $(event.target).data("code-beer");
		var response = $.ajax({
			url: 'item/' + this.uuid + '/' + codeBeer, 
			method: 'DELETE',
			
		});
		
		response.done(onUpdatededOnTheServer.bind(this));
	}
	
	function bindQuantity() {
		var inputQuantityItem = $('.js-table-beer-quantity-item');
		inputQuantityItem.on('change', onQuantityChangedItem.bind(this));
		//inputQuantityItem.maskMoney({precision: 0, thousands: ''});
		inputQuantityItem.maskNumber({ integer: true, thousands: '' });
	}
	
	function bindTableItem() {
		var tableItem = $('.js-table-item');
		tableItem.on('dblclick', onDoubleClick);
		$('.js-exclusion-item-btn').on('click', onExclusionItemClick.bind(this));
		return tableItem;
	}
	
	return TableItems;
	
}());

