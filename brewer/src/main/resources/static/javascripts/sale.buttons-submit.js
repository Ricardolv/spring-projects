Brewer = Brewer || {};

Brewer.ButtonsSubmit = (function() {
	
	function ButtonsSubmit() {
		this.submitBtn = $('.js-submit-btn');
		this.form = $('.js-form-principal');
	}
	
	ButtonsSubmit.prototype.init = function() {
		this.submitBtn.on('click', onSubmit.bind(this));
	}
	
	function onSubmit(event) {
		event.preventDefault();
		
		var buttonCliecked = $(event.target);
		var action = buttonCliecked.data('action');
		
		var actionInput = $('<input>');
		actionInput.attr('name', action);
		
		this.form.append(actionInput);
		this.form.submit();
	}
	
	return ButtonsSubmit;
	
}());

$(function() {
	
	var buttonsSubmit = new Brewer.ButtonsSubmit();
	buttonsSubmit.init();
	
})