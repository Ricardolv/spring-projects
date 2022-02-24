Brewer = Brewer || {};

Brewer.DeleteDialog = (function() {
	
	function DeleteDialog() {
		this.deleteBtn = $('.js-delete-btn');
	}
	
	DeleteDialog.prototype.init = function() {
		this.deleteBtn.on('click', onDeleteClicked.bind(this));
		
		if (window.location.search.indexOf('excluded') > -1) {
			swal('Pronto!', 'Excluido com sucesso!', 'success');
		}
	}
	
	function onDeleteClicked(event) {
		event.preventDefault();
		var clickedButton = $(event.currentTarget);
		var url =  clickedButton.data('url');
		var object = clickedButton.data('object');
		
		swal({
			title: 'Tem certeza?',
			text: 'Excluir "' + object + '"? Você não poderá recuperar depois.',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, exclua agora!',
			closeOnConfirm: false
		}, onDeleteConfirmed.bind(this, url));
	}
	
	function onDeleteConfirmed(url) {
		
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onSuccessDelete.bind(this),
			error: onErrorDelete.bind(this)
		});
	}
	
	function onSuccessDelete() {
		var currentUrl = window.location.href;
		var separator = currentUrl.indexOf('?') > -1 ? '&' : '?';
		var newUrl = currentUrl.indexOf('excluded') > -1 ? currentUrl : currentUrl + separator + 'excluded';
		
		window.location = newUrl;
	}
	
	function onErrorDelete(e) {
		swal('Oops!', e.responseText, 'error');
	}
	
	return DeleteDialog;
	
}());

$(function() {
	
	var dialog = new Brewer.DeleteDialog();
	dialog.init();
	
});