package com.JMRocha.demoajax.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// ======================================LISTAR OFERTAS==========================================
	
	@GetMapping("/list")
	public String listarOfertas(ModelMap model) {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "dtCadastro");
		PageRequest pageRequest = PageRequest.of(0, 12, sort);
		model.addAttribute("promocoes", promocaoRepository.findAll(pageRequest));
		System.out.println("\n\tSort: "+ sort + "\n\tPaga Request: " + pageRequest);
		
		return "promo-list";
	}
	
	@GetMapping("/list/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, ModelMap model) {
		Sort sort = Sort.by(Sort.Direction.DESC, "dtCadastro");
		PageRequest pageRequest = PageRequest.of(page, 12, sort);
		model.addAttribute("promocoes", promocaoRepository.findAll(pageRequest));	
		System.out.println("\n\tSort2: "+ sort + "\n\tPaga Request2: " + pageRequest + "\n\tPaga2: " + page);
		return "promo-card";
	}	
	
	// ======================================ADD OFERTAS=============================================
	
	@PostMapping("/save")
	public ResponseEntity<?> salvarPromocao(@Valid Promocao promocao, BindingResult result) {
		
		if (result.hasErrors()) {
			
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		
		log.info("Promocao {}", promocao.toString());
		promocao.setDtCadastro(LocalDateTime.now());
		promocaoRepository.save(promocao);
		return ResponseEntity.ok().build();
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> getCategorias() {
		
		return categoriaRepository.findAll(); 
	}

	@GetMapping("/add")
	public String abrirCadastro() {
		
		return "promo-add";
	}
}
