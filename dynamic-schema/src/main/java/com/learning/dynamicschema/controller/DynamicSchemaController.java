package com.learning.dynamicschema.controller;

import java.io.IOException;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dynamicschema.service.DynamicSchemaService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/schema")
public class DynamicSchemaController {

	@Autowired
	private DynamicSchemaService dynamicSchemaService;

	@PostMapping(path = "/retrieve", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Publisher<?> createData(@RequestBody String incomingRequest) throws IOException {

		return Mono.just(dynamicSchemaService.retrieveInfoPerRequest(incomingRequest));

	}
}
