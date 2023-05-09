package com.czarate.dipatcher.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.czarate.dipatcher.app.model.Example;

@Repository
public class ExampleRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	
    public void guardarObjetos(List<Example> listaDeObjetos) {
	    //mongoTemplate.insertAll(listaDeObjetos);
    	
    	for(Example e:listaDeObjetos) {
    		mongoTemplate.save(e);
    	}
    	
	}
}
