package com.JMRocha.demoajax.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JMRocha.demoajax.domain.Categoria;
import com.JMRocha.demoajax.domain.Promocao;
import com.JMRocha.demoajax.repository.CategoriaRepository;
import com.JMRocha.demoajax.repository.PromocaoRepository;

@Controller
@RequestMapping(value = PromocaoController.PROMOTION)
public class PromocaoController {
	
	protected static final String PROMOTION = "/promocao";
	
	private static Logger log = LoggerFactory.getLogger(PromocaoController.class);
	
    @Autowired
    private PromocaoRepository promocaoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping("/save")
	public ResponseEntity<Promocao> salvarPromocao(Promocao promocao) {
		
		log.info("Promocao {}", promocao.toString());
		promocao.setDtCadastro(LocalDateTime.now());
		promocaoRepository.save(promocao);
		return ResponseEntity.ok().build();
	}
	
	@ModelAttribute("categorias") // Fazendo referencia a pagina promo-add.html com o th:each
	public List<Categoria> getCategorias() {
		
		return categoriaRepository.findAll(); 
	}

	@GetMapping("/add")
	public String abrirCadastro() {
		
		return "promo-add";
	}
}
