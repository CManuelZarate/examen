package com.czarate.dipatcher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("")
	public ResponseEntity<String> getToken(@RequestBody String textoArchivo) {
		log.info("vamos agregar al message {}", textoArchivo);
		producerService.emitNext1(textoArchivo);
        
		return ResponseEntity.ok("token añadido: " + "result");
        

	}

	@PostMapping("/upload")
  public String uploadFile(@RequestBody MultipartFile file) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    Object json = objectMapper.readValue(file.getInputStream(), Object.class);
    String jsonData = objectMapper.writeValueAsString(json);
    producerService.emitNext1(jsonData);
    return "File uploaded successfully";
  }
}
