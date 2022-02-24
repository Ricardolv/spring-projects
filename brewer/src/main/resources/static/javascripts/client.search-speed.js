Brewer = Brewer || {};

Brewer.ClientSearchSpeed = (function() {
	
	function ClientSearchSpeed() {
		this.clientModalSearchSpeed = $('#searchSpeedClients'); 			// obter url na pagina pesquisa-rapida-cliente
		this.nameInput = $('#nameClientModal'); 							// obter nome do cliente para realizar pesqusia
		this.searchSpeedBtn = $('.js-search-speed-clients-btn'); 			// criando evento apos clique do botao pesquisar 
		this.containerTableSearch = $('#containerTableSearchSpeedClients'); // obter container <div> para inserir o handlebars 
		this.htmlTableSearch = $('#table-search-speed-client').html(); 		// obter o html 
		this.template = Handlebars.compile(this.htmlTableSearch); 			// compilando template 
		this.messageError = $('.js-message-error'); 						// obter campo para exibicao de informacao de erro
	}
	
	ClientSearchSpeed.prototype.init = function() {
		this.searchSpeedBtn.on('click', onSearchSpeedClicked.bind(this));
		this.clientModalSearchSpeed.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onModalShow() {
		this.nameInput.focus();
	}
	
	function onSearchSpeedClicked(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.clientModalSearchSpeed.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				name: this.nameInput.val()
			}, 
			success: onSearchClicked.bind(this),
			error: onSearchError.bind(this)
		});
	}
	
	function onSearchClicked(result) {
		this.messageError.addClass('hidden');

		var html = this.template(result);
		this.containerTableSearch.html(html);
		
		var tableClientSearchSpeed = new Brewer.TableClientSearchSpeed(this.clientModalSearchSpeed);
		tableClientSearchSpeed.start();
		
	} 
	
	function onSearchError() {
		this.messageError.removeClass('hidden');
	}
	
	return ClientSearchSpeed;
	
}());


Brewer.TableClientSearchSpeed = (function() {
	
	function TableClientSearchSpeed(modal) {
		this.clientModal = modal; 				  	 // instanciando modal
		this.client = $('.js-client-search-speed');  // obter dados coidgo e nome no handlebars table-search-speed-clients.html 
	}
	
	TableClientSearchSpeed.prototype.start = function() {
		this.client.on('click', onClientSelected.bind(this));
	}
	
	function onClientSelected(event) {
		this.clientModal.modal('hide'); 						// fechando modal
					
		var clienteSelected = $(event.currentTarget);			// pegando cliente selecionado
		$('#nameClient').val(clienteSelected.data('name'));		// atribuindo nome do cliente no input da pagina cadatsro de venda 
		$('#codeClient').val(clienteSelected.data('code'));     // atribuindo codigo do cliente no input da pagina cadatsro de venda 
	}
	
	return TableClientSearchSpeed;
	
}());

$(function() {
	var clientSearchSpeed = new Brewer.ClientSearchSpeed();
	clientSearchSpeed.init();
});