var pageNumber = 0;

$(document).ready(function(){
	$("#loader-img").hide();
	$("#fim-btn").hide();
});

// efeito infinte scroll
$(window).scroll(function() {
	
	var scrollTop = $(this).scrollTop();
	var conteudo = $(document).height() - $(window).height();
	//teste no console comimpecionar
	//console.log('scrollTop: ', scrollTop, ' | ', 'conteudo', conteudo);
	
	if (scrollTop >= conteudo) {
		pageNumber++;
		setTimeout(function(){
			loadByScrollBar(pageNumber);
		}, 200);
	}	
});


//======================= function loadByScrollBar ==========================

function loadByScrollBar(pageNumber) {
	var site = $("#autocomplete-input").val();
	$.ajax({
		method: "GET",
		url: "/promocao/list/ajax",
		data: {
			page: pageNumber,
			site: site
		},
		
		beforeSend: function() {
			$("#loader-img").show();
		},		
		
		success: function( response ) {
			//teste no console comimpecionar
			//console.log("resposta > ", response);
			console.log("lista > ", response.length);
			
			if (response.length > 150) {
			
				$(".row").fadeIn(250, function() {
					$(this).append(response);
				});
			
			} else {
				$("#fim-btn").show();
				$("#loader-img").removeClass("loader");
			}
		},
		
		error: function(xhr) {
			alert("Ops, ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
		},
		
		complete: function() {
			$("#loader-img").hide();			
		}
		
	})
}

//========= Autocomplete ==========================

$("#autocomplete-input").autocomplete({
	source: function(request, response) {
		$.ajax({
			method: "GET",
			url: "/promocao/site", 
			data: {
				termo: request.term
			},
			success: function(result) {
				response(result);
			}
		});
	}
});

//========= Autocomplete pela lista do sites ==========================

$("#autocomplete-submit").on("click", function() {
	var site = $("#autocomplete-input").val();
	$.ajax({
		method: "GET",
		url: "/promocao/site/list",
		data: {
			site : site
		},
		beforeSend: function() {
			pageNumber = 0;
			$("#fim-btn").hide();
			$(".row").fadeOut(400, function(){
				$(this).empty();
			});
		},
		
		success: function(response) {
			$(".row").fadeIn(250, function(){
				$(this).append(response);
			});
		},
		error: function(xhr) {
			alert("Ops, algo deu errado: " + xhr.status + ", " + xhr.statusText);
		}
	});
})

//========= Adicionar capitura de likes * FUNÇÃO PARA TESTE * ==========================
/*
*			$("button[id*='likes-btn-']").on("click",function() {
*				var id = $(this).attr("id").split("-")[2];
*				console.log("id: ", id);
*			});
*/
//======================= Adicionar likes ==============================================

$(document).on("click", "button[id*='likes-btn-']", function() {
	var id = $(this).attr("id").split("-")[2];
	console.log("id: ", id);
	
	$.ajax({
		method: "POST",
		url: "/promocao/like/" + id,
		success: function(response) {
			$("#likes-count-" + id).text(response);
		},
		error: function(xhr) {
			alert("Ops, ocorreu um erro: " + xhr.status + ", " + xhr.statusText);
		}
	});
});

