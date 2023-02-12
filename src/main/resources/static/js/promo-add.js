//submit do formulario para o controller
$("#form-add-promo").submit(function(evt) {
	//bloquear o comportamento padrão do submit
	evt.preventDefault();
	
	var promo = {};
	promo.linkPromocao = $("#linkPromocao").val();
	promo.descricao = $("#descricao").val();
	promo.preco = $("#preco").val();
	promo.titulo = $("#titulo").val();
	promo.categoria = $("#categoria").val();
	promo.linkImagem = $("#linkImagem").attr("src");
	promo.site = $("#site").text();
	
	console.log('promo > ', promo);
	
	$.ajax({
		method: "POST",
		url: "/promocao/save",
		data: promo,
		
		//Metodo para mensagem
		beforeSend: function()  {
			// removendo as mensagens
			$("span").closest('.error-span').remove();
			
			//remover as bordas vermelhas
			$("#categoria").removeClass("is-invalid");
			$("#preco").removeClass("is-invalid");
			$("#linkPromocao").removeClass("is-invalid");
			$("#titulo").removeClass("is-invalid");
			
			//habilita o loading
			$("#form-add-promo").hide();
			$("#loader-form").addClass("loader").show();
		},
		
		success: function() {
			$("#form-add-promo").each(function() {
				this.reset();
			});
			$("#linkImagem").attr("src", "/images/promo-dark.png");
			$("#site").text("");
			$("#alert")
				//removendo mensagem de erro para mensagem de sucesso
				.removeClass("alert alert-danger")
				.addClass("alert alert-success")
				.text("OK! Promoção cadastrada com sucesso.");
		},
		
		statusCode: {
			422: function(xhr) {
				console.log('status error:', xhr.status);
				var errors = $.parseJSON(xhr.responseText);
				$.each(errors, function(key, val){
					$("#" + key).addClass("is-invalid");
					$("#error-" + key)
						.addClass("invalid-feedback")
						.append("<span class='error-span'>" + val + "</span>")
				});
			}
		},
		
		error: function(xhr) {
			console.log("> error: ", xhr.responseText);
			$("#alert").addClass("alert alert-danger").text("Não foi possível salvar esta promoção.");
		},
		
		complete: function() {
			$("#loader-form").fadeOut(1000, function() {
				$("#form-add-promo").fadeIn(250);
				$("#loader-form").removeClass("loader");
			});
		}
	});
});


//____________________________________________________________________________________
// funcao para capturar as meta tags
// Tendo acesso ao link da pagina promo-add.html --> id="linkPromocao"
$("#linkPromocao").on('change', function() {

	var url = $(this).val();// Recuperando o valo do campo imput na pagina --> promo-add.html
	
	if (url.length > 7) {
		
		$.ajax({
			method:"POST", // Recuperando o valo do tipo " Post na classes Java controlle" --> @PostMapping("/info")
			url: "/meta/info?url=" + url, // Capiturando ocaminho da URL na classes Java para exibição na pagina WEB
			cache: false,//Não fazer o uso do cache
			
			
			beforeSend: function() {
				$("#alert").removeClass("alert alert-danger alert-success").text('');
				$("#titulo").val("");
				$("#site").text("");
				$("#linkImagem").attr("src", "");
				$("#loader-img").addClass("loader");
			},
			
			success: function( data ) {
				console.log(data);
				//comecção MVC com o --> * HTML, JAVA, SQL *
				$("#titulo").val(data.title);//Para a pagina HTML--> $("#titulo") || Busca pela a classe Java-->val(data.title);
				$("#site").text(data.site.replace("@", ""));
				$("#linkImagem").attr("src", data.image);
			},
			
			statusCode: {
				404: function() {
					$("#alert").addClass("alert alert-danger").text("Nenhuma informação pode ser recuperada dessa url.");
					$("#linkImagem").attr("src", "/images/promo-dark.png");
				}
			},
			
			error: function() {
				$("#alert").addClass("alert alert-danger").text("Ops... algo deu errado, tente mais tarde.");
				$("#linkImagem").attr("src", "/images/promo-dark.png");
			},
			
			complete: function() {
				$("#loader-img").removeClass("loader");
			}
		});
	}
});