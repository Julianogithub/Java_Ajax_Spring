// Criando a tabela, referencia --> DataTable
$(document).ready(function(){
	
	moment.locale('pt-br'); // Data formatada para o formato do Brasil
		
	$("#table-server").DataTable({
		
		"language": { // cdn para traduzir a pagina na linguagem Brasileira ( URL abaixo )
            "url": "//cdn.datatables.net/plug-ins/1.13.2/i18n/pt-BR.json"
            },
		
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
			{data: 'categoria.titulo'},
			
			//Cração do botões ( Editar e Excluir )
			{orderable: false, 
             data: 'id',
                "render": function(id) {					
                    return '<a class="btn btn-success" href="/especialidades/editar/'+ 
                    	id +'" role="button"> Editar | <i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
             data: 'id',
                "render": function(id) {					
                    return '<a class="btn btn-danger" href="/especialidades/excluir/'+ 
                    	id +'" role="button" data-toggle="modal" data-target="#confirm-modal"> Excluir | <i class="fas fa-times-circle"></i></a>';
                }               
            }
            
		]
			
	});
});



