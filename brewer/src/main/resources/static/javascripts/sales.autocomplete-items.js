Brewer = Brewer || {};

Brewer.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuOuNomeInput = $('.js-sku-name-beers-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-beer').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.init = function() {
		var options = {
			url: function(skuOrName) {
				return this.skuOuNomeInput.data('url') + '?skuOrName=' + skuOrName;
			}.bind(this),
			getValue: 'name',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: template.bind(this)
			},
			list: {
				onChooseEvent: onSelectedItem.bind(this)
			}
		};
		
		this.skuOuNomeInput.easyAutocomplete(options);
	}
	
	function template(name, beer) {
		beer.valueFormat = Brewer.currencyFormat(beer.value);
		return this.template(beer);
	}
	
	function onSelectedItem() {
		this.emitter.trigger('selected-item', this.skuOuNomeInput.getSelectedItemData());
		this.skuOuNomeInput.val('');
		this.skuOuNomeInput.focus();
	}
	
	
	return Autocomplete
	
}());

