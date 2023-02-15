package com.JMRocha.demoajax.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

	@GetMapping("/")
	public String init() {
		return "redirect:/promocao/add";
	}
/*
	@GetMapping("/eventos")
	public ResponseEntity<?> eventos() {
		Canvas c1 = new Canvas();
		c1.setDia("02-11-202");
		c1.setTag("<a href=\"#\" target=_blank>Fevereiro LIndo</a>");

		Canvas c2 = new Canvas();
		c2.setDia("11-11-2021");
		c2.setTag("<a href=\"#\" target=_blankNatal Premiado</a>");

		Canvas c3 = new Canvas();
		c3.setDia("12-23-2021");
		c3.setTag("<a href=\"\" target=_blank>Jazz Festival.</a>");

		List<Canvas> list = Arrays.asList(c1,c2,c3);

		return ResponseEntity.ok().body(list);
	}
*/

}
/*
class Canvas {
	String dia;
	String tag;



	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Canvas{" +
				"dia='" + dia + '\'' +
				", tag='" + tag + '\'' +
				'}';
	}
}*/
