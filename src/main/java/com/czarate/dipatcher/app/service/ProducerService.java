package com.czarate.dipatcher.app.service;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class ProducerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);
	
    //creando un objeto que puede enviar múltiples mensajes de tipo String a través de un flujo reactiva.
   //private final Sinks.Many<Message<String>> many = Sinks.many().unicast().onBackpressureBuffer();
	private final Sinks.Many<Message<Object>> many = Sinks.many().unicast().onBackpressureBuffer();
	
   @Bean
   public Supplier<Flux<Message<Object>>> supply() {
	//public Supplier<Flux<Message<String>>> supply() {
       return () -> many.asFlux()
               .doOnNext(m -> LOGGER.info("Manually sending message {}", m))
               .doOnError(t -> LOGGER.error("Error encountered", t));
   }
   
   
   public void emitNext1(String message) {
       many.emitNext(new GenericMessage<>(message), Sinks.EmitFailureHandler.FAIL_FAST);
   }
   
   /*
   public void emitNext2(User user) {
   	//Message<User> message = MessageBuilder.withPayload(user).build();
       many.emitNext(new GenericMessage<>(user), Sinks.EmitFailureHandler.FAIL_FAST);
   }
   */
}
