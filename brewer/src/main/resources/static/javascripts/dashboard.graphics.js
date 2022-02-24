var Brewer = Brewer || {};

Brewer.GraphicSaleByMonths = (function() {
	
	function GraphicSaleByMonths() {
		this.ctx = $('#graphicSaleByMonths')[0].getContext('2d');
		this.lineEmpty = $('#lineEmpty');
	}
	
	GraphicSaleByMonths.prototype.init = function() {
		$.ajax({
			url:'sales/totalByMonth',
			method: 'GET',
			success: onDataReceived.bind(this)
		});
		
		
	}
	
	function onDataReceived(salesMonth) {
		var month = [];
		var values = [];
		
		salesMonth.forEach(function(obj) {
			month.unshift(obj.month);
			values.unshift(obj.total);
		});
		
		if (month != '' && values != '') {
			this.lineEmpty.empty();
		}
		
		var graphicSaleByMonths = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: month,
		    	datasets: [{
		    		label: 'Vendas por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: values
		    	}]
		    },
		});
	}
	
	return GraphicSaleByMonths;
	
}());


Brewer.GraphicSaleByOrigin = (function() {
	
	function GraphicSaleByOrigin() {
		this.ctx = $('#graphicSaleByOrigin')[0].getContext('2d');
		this.barEmpty = $('#barEmpty');
	}
	
	GraphicSaleByOrigin.prototype.init = function() {
		$.ajax({
			url: 'sales/byOrigin',
			method: 'GET', 
			success: onDataReceived.bind(this)
		});
	}
	
	function onDataReceived(saleOrigin) {
		var month = [];
		var salesNational = [];
		var salesInternational = [];
		
		saleOrigin.forEach(function(obj) {
			month.unshift(obj.month);
			salesNational.unshift(obj.totalNational);
			salesInternational.unshift(obj.totalInternational)
		});
		
		if (salesNational != '' && salesInternational != '') {
			this.barEmpty.empty();
		}
		
		var graphicSaleByOrigin = new Chart(this.ctx, {
		    type: 'bar',
		    data: {
		    	labels: month,
		    	datasets: [{
		    		label: 'Nacional',
		    		backgroundColor: "rgba(220,220,220,0.5)",
	                data: salesNational
		    	},
		    	{
		    		label: 'Internacional',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                data: salesInternational
		    	}]
		    },
		});
	}
	
	return GraphicSaleByOrigin;
	
}());

$(function() {
	
	var graphicSaleByMonths = new Brewer.GraphicSaleByMonths();
	graphicSaleByMonths.init();
	
	var graphicSaleByOrigin = new Brewer.GraphicSaleByOrigin();
	graphicSaleByOrigin.init();
	
});