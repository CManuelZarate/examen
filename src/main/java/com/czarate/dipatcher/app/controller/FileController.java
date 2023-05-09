package com.czarate.dipatcher.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czarate.dipatcher.app.service.ProducerService;


import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	ProducerService producerService;
	

	@PostMapping("/upload")
	@Transactional
	public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) throws Exception {
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
		    Object json = objectMapper.readValue(file.getInputStream(), Object.class);
		    String jsonData = objectMapper.writeValueAsString(json);
		    producerService.emitNext1(jsonData);
		    
			Map<String,Object> response = new HashMap<>();
			response.put("message", "Archivo cargado");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	    

	}
}
