Brewer = Brewer || {};

Brewer.SelectionMulti = (function() {
	
	function SelectionMulti() {
		this.statusBtn = $('.js-status-btn');
		this.selectionCheckbox = $('.js-selection');
		this.selectionCheckboxAll= $('.js-selection-all');
	}
	
	SelectionMulti.prototype.init = function() {
		this.statusBtn.on('click', onStatusBtnClicado.bind(this));
		this.selectionCheckboxAll.on('click', onSlectionAllClicked.bind(this));
		this.selectionCheckbox.on('click', onSelectionClicked.bind(this));
	}
	
	function onStatusBtnClicado(event) {
		var buttonClick = $(event.currentTarget);
		var status = buttonClick.data('status');
		var url = buttonClick.data('url');
		
		var selectionCheckboxs = this.selectionCheckbox.filter(':checked');
		var codes = $.map(selectionCheckboxs, function (c) {
			return $(c).data('code');
		});
		
		if (codes.length > 0) {
			
			$.ajax({
				url: url,
				method: 'PUT',
				data: {
					codes: codes,
					status: status
				},
				success: function() {
					window.location.reload();
				}
			});
		}
		
	}
	
	function onSlectionAllClicked() {
		var status = this.selectionCheckboxAll.prop('checked');
		this.selectionCheckbox.prop('checked', status);
		statusButtonAction.call(this, status);
	}

	function onSelectionClicked() {
		var selectionCheckboxCheckeds = this.selectionCheckbox.filter(':checked');
		this.selectionCheckboxAll.prop('checked', selectionCheckboxCheckeds.length >= this.selectionCheckbox.length);
		statusButtonAction.call(this, selectionCheckboxCheckeds.length);
	}
	
	function statusButtonAction(active) {
		active ? this.statusBtn.removeClass('disabled') : this.statusBtn.addClass('disabled');
	}
	
	return SelectionMulti;
	
}());

$(function() {
	
	var selectionMulti = new Brewer.SelectionMulti();
	selectionMulti.init();
	
});
