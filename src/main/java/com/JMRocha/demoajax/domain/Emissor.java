package com.JMRocha.demoajax.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class Emissor {

	private String id = UUID.randomUUID().toString();
	
	private SseEmitter emitter;
	
	private LocalDateTime ultimaData;	

	public Emissor(SseEmitter emitter, LocalDateTime ultimaData) {
		super();
		this.emitter = emitter;
		this.ultimaData = ultimaData;
	}

	public SseEmitter getEmitter() {
		return emitter;
	}

	public void setEmitter(SseEmitter emitter) {
		this.emitter = emitter;
	}

	public LocalDateTime getUltimaData() {
		return ultimaData;
	}

	public void setUltimaData(LocalDateTime ultimaData) {
		this.ultimaData = ultimaData;
	}

	public String getId() {
		return id;
	}
	
	
}
