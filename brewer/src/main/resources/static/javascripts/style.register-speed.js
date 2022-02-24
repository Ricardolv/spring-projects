var Brewer = Brewer || {};

Brewer.StyleRegisterSpeed = (function () {
	
	function StyleRegisterSpeed() {
		
		this.modalStyle = $('#modalRegisterSpeedStyle');
		this.buttonSave = $('.js-modal-register-style-save-btn');
		this.form = this.modalStyle.find('form');
		this.url = this.form.attr('action');
		this.inputNameStyle = $('#nameStyle');
		this.containerMessageError = $('.js-message-register-speed-style');
		
	}
	
	StyleRegisterSpeed.prototype.start = function () {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modalStyle.on('shown.bs.modal', onModalShow.bind(this));
		this.modalStyle.on('hide.bs.modal', onModalClose.bind(this));
		this.buttonSave.on('click', onButtonSaveClick.bind(this))
	}
	
	function onModalShow() {
		this.inputNameStyle.focus();
	}
	
	function onModalClose() {
		this.inputNameStyle.val('');
		this.containerMessageError.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onButtonSaveClick() {
		var nameStyle = this.inputNameStyle.val().trim(); 
		$.ajax({
			url : this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ name: nameStyle }),
			error: onErrorSaveStyle.bind(this),
			success: onSaveStyle.bind(this)
		});
	}
	
	function onErrorSaveStyle(obj) {
		var messageError = obj.responseText;
		this.containerMessageError.removeClass('hidden');
		this.containerMessageError.html('<span>' + messageError + '</span>');
		this.form.find('.form-group').addClass('has-error');
		
	}
	
	function onSaveStyle(style) {
		var inputStyle = $('#input-style');
		inputStyle.append('<option value=' + style.code + '>' + style.name + '</option>');
		inputStyle.val(style.code);
		this.modalStyle.modal('hide');
	}
	
	return StyleRegisterSpeed;
	
}());

$(function() {
	var styleRegisterSpeed = new Brewer.StyleRegisterSpeed();
	styleRegisterSpeed.start();
});
