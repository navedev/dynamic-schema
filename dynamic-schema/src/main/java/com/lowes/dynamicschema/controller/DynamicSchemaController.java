/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/
package com.lowes.dynamicschema.controller;

import java.io.IOException;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lowes.dynamicschema.service.DynamicSchemaService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/schema")
public class DynamicSchemaController {

  @Autowired
  private DynamicSchemaService dynamicSchemaService;

  @PostMapping(path = "/retrieve", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Publisher<?> createData(@RequestBody String incomingRequest) throws IOException {

    log.info("Inside /schema/retrieve Method");

    return Mono.just(dynamicSchemaService.retrieveInfoPerRequest(incomingRequest));

  }
}
