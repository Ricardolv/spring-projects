var Brewer = Brewer || {};

Brewer.StateCombo = (function() {
	
	function StateCombo() {
		this.combo = $('#state');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
		
	}
	
	StateCombo.prototype.start = function() {
		this.combo.on('change', onStateChanges.bind(this));
	}
	
	function onStateChanges() {
		this.emitter.trigger('changed', this.combo.val());
	}
	
	return StateCombo;
	
}());

Brewer.CityCombo = (function() {
	
	function CityCombo(stateCombo) {
		this.stateCombo = stateCombo;
		this.combo = $('#city');
		this.imgLoading = $('.js_img-loading');
		this.inputHiddenCodeCitySelected = $('#codeCitySelected');
		
	}
	
	CityCombo.prototype.start = function() {
		reset.call(this);
		this.stateCombo.on('changed', onStateChanged.bind(this));
		var codeState = this.stateCombo.combo.val();
		initializeCitys.call(this, codeState);
	}
	
	function onStateChanged(event, codeState) {
		this.inputHiddenCodeCitySelected.val('');
		initializeCitys.call(this, codeState);
	}
	
	function initializeCitys(codeState) {
		if (codeState) {
			var response = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: {'state': codeState},
				beforeSend: startRequest.bind(this),
				complete: finalizeRequest.bind(this)
				
			});
		
			response.done(onCitysSearchFinalize.bind(this));

		} else {
			reset.call(this);
		}
	}
	
	function onCitysSearchFinalize(citys) {
		var options = [];
		
		citys.forEach(function(city) {
			options.push('<option value="' + city.code + '">' + city.name + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
		var codeCitySelected = this.inputHiddenCodeCitySelected.val();
		if (codeCitySelected) {
			this.combo.val(codeCitySelected);
		}
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione a cidade</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function startRequest() {
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizeRequest() {
		this.imgLoading.hide();
	}
	
	return CityCombo;
	
}());



$(function() {
	
	var stateCombo = new Brewer.StateCombo();
	stateCombo.start();
	
	var cityCombo = new Brewer.CityCombo(stateCombo);
	cityCombo.start();
	
});