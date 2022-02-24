var Brewer = Brewer || {};

Brewer.MaskCpfCnpf = (function () {
	
	function MaskCpfCnpf() {
		this.radioPersonType = $('.js-radio-personType');
		this.labelCpfCnpj = $('[for=cpfCnpj]');
		this.inputCpfCnpj = $('#cpfCnpj');
	}
	
	MaskCpfCnpf.prototype.start = function () {
		this.radioPersonType.on('change', onPersonTypeAlter.bind(this));
		var personTypeSelected = this.radioPersonType.filter(':checked')[0];
		
		if(personTypeSelected) {
			applicationMask.call(this, $(personTypeSelected));
		}
	}
	
	function onPersonTypeAlter(event) {
		var personTypeSelected = $(event.currentTarget);
		applicationMask.call(this, personTypeSelected )
		this.inputCpfCnpj.val('');
		
	}
	
	function applicationMask(personTypeSelected) {
		this.labelCpfCnpj.text(personTypeSelected.data('document'));
		this.inputCpfCnpj.mask(personTypeSelected.data('inputmask'));
		this.inputCpfCnpj.removeAttr('disabled');
	}
	
	return MaskCpfCnpf;
	
}());

$(function() {
	
	var maskCpfCnpf = new Brewer.MaskCpfCnpf();
	maskCpfCnpf.start();
	
});