package com.czarate.dipatcher.app.service;

import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

import com.czarate.dipatcher.app.model.Example;
import com.czarate.dipatcher.app.model.ListExample;
import com.czarate.dipatcher.app.repository.ExampleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {
	
	@Autowired
	private ExampleRepository exampleRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @Bean
    public Consumer<Flux<Message<String>>> consume() {
    	try {
    	
        //return flux -> flux.subscribe(message -> { LOGGER.info("New message received: '{}'", message.getPayload());guardarObjetos(message.getPayload()); } );
    	return flux -> flux.subscribe(message -> { /*LOGGER.info("New message received: '{}'", message.getPayload());*/try {
			guardarObjetos(message.getPayload());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} } );
    	
    	}catch(Exception e) {
    		log.error(e.getMessage());
    		return  flux -> flux.subscribe(message -> { LOGGER.info("New message received: '{}'", message.getPayload());} );
    	}
    }
    
    public void guardarObjetos(String jsonData) throws JsonMappingException, JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
    	//List<Example> arrExamples = (List<Example>) objectMapper.readValue(jsonData, ListExample.class);
    	 List<Example> arrExamples = objectMapper.readValue(jsonData, new TypeReference<List<Example>>() {});
    	exampleRepository.guardarObjetos(arrExamples);
    	
    }
    
}
