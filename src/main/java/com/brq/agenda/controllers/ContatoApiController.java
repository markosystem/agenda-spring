package com.brq.agenda.controllers;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.brq.agenda.service.ContatoService;

@RestController
public class ContatoApiController {

	@Autowired
	private ContatoService service;

	@GetMapping(value = "/api/contatos")
	public ResponseEntity<?> all() {
		try {
			return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
		} catch (ApplicationContextException e) {
			Map<String, String> m = new HashMap<>();
			m.put("msg", e.getMessage());
			return new ResponseEntity<>(JSONObject.toJSONString(m), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/api/contatos/id/{id}")
	public ResponseEntity<?> single(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
		} catch (ApplicationContextException e) {
			Map<String, String> m = new HashMap<>();
			m.put("msg", e.getMessage());
			return new ResponseEntity<>(JSONObject.toJSONString(m), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/api/contatos/{search}")
	public ResponseEntity<?> singleBySearch(@PathVariable String search) {
		try {
			return new ResponseEntity<>(service.findSearchAll(search.toLowerCase()), HttpStatus.OK);
		} catch (ApplicationContextException e) {
			Map<String, String> m = new HashMap<>();
			m.put("msg", e.getMessage());
			return new ResponseEntity<>(JSONObject.toJSONString(m), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
