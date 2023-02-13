// Criando a tabela, referencia --> DataTable
$(document).ready(function(){
	
	moment.locale('pt-br'); // Data formatada para o formato do Brasil
	
	$("#table-server").DataTable({
		processing: true,
		serverSide: true,
		responsive: true,
		lengthMenu: [ 10, 15, 20, 25 ],
		
		ajax: {
			url: "/promocao/datatables/server",
			data: "data"
			},
			
			columns: [
			{data: 'id'},
			{data: 'titulo'},
			{data: 'site'},
			{data: 'linkPromocao'},
			{data: 'descricao'},
			{data: 'linkImagem'},
			//Formatando para valor de preço do Brasil
			{data: 'preco', render: $.fn.dataTable.render.number('.', ',', 2, 'R$ : ')}, 
			{data: 'likes'},
			//Formatando para data do Brasil
			{data: 'dtCadastro', render: 
					function(dtCadastro) {
						return moment( dtCadastro ).format('LLL'); 
					}	
			},
			{data: 'categoria.titulo'}
		]
			
	});
	
});