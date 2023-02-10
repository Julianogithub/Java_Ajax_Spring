// funcao para capturar as meta tags
// Tendo acesso ao link da pagina promo-add.html --> id="linkPromocao"

$("#linkPromocao").on('change', function() {

	var url = $(this).val();// Recuperando o valo do campo imput na pagina --> promo-add.html
	
	if (url.length > 7) {
		
		$.ajax({
			method:"POST", // Recuperando o valo do tipo " Post na classes Java controlle" --> @PostMapping("/info")
			url: "/meta/info?url=" + url, // Capiturando ocaminho da URL na classes Java para exibição na pagina WEB
			cache: false,//Não fazer o uso do cache
			
			success: function( data ) {
				console.log(data);
				//comecção MVC com o --> * HTML, JAVA, SQL *
				$("#titulo").val(data.title);//Para a pagina HTML--> $("#titulo") || Busca pela a classe Java-->val(data.title);
				$("#site").text(data.site.replace("@", ""));
				$("#linkImagem").attr("src", data.image);
			}
		});
	}
});