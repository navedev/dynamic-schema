/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/
package com.lowes.dynamicschema.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;;

@Service
public class DynamicSchemaService {

	@SuppressWarnings("unchecked")
	public Object retrieveInfoPerRequest(String incomingRequest) throws IOException {

		ClassPathResource propsFile = new ClassPathResource("response.json");
		byte[] bdata = FileCopyUtils.copyToByteArray(propsFile.getInputStream());
		String actualJson = new String(bdata, StandardCharsets.UTF_8);

		JSONObject actualJsonObj = new JSONObject(actualJson);

		JSONObject expectedJsonObj = new JSONObject(incomingRequest);

		Map<String, Object> actualJsonObjMap = actualJsonObj.toMap();

		Map<String, Object> expectedJsonObjMap = expectedJsonObj.toMap().containsKey("template")
				? (Map<String, Object>) expectedJsonObj.toMap().get("template")
				: expectedJsonObj.toMap();

		return retrieveNestedMapping((Map<String, Object>) expectedJsonObjMap, (Map<String, Object>) actualJsonObjMap);

	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> retrieveNestedMapping(Map<String, Object> inputObjectMap,
			Map<String, Object> existingObjectMap) {

		JSONObject interObj = new JSONObject();

		inputObjectMap.forEach((inputKey, inputValue) -> existingObjectMap.forEach((existingKey, existingValue) -> {
			if (inputKey.equals(existingKey)) {

				if (inputValue instanceof String) {

					interObj.put(inputKey, existingObjectMap.get(inputKey));

				} else if (inputValue instanceof Map<?, ?>) {

					interObj.put(inputKey, retrieveNestedMapping((Map<String, Object>) inputObjectMap.get(inputKey),
							(Map<String, Object>) existingObjectMap.get(inputKey)));

				} else if (inputValue instanceof List<?>) {

					List<?> interObjList = (List<?>) existingValue;
					List<Map<?, ?>> constructedInterObjList = new ArrayList<>();
					List<?> inputListSingleObj = new ArrayList<>();

					if (inputObjectMap.get(inputKey) instanceof List<?>) {
						inputListSingleObj = (List<?>) inputValue;
					}

					for (Object interValueObj : interObjList) {
						constructedInterObjList.add(retrieveNestedMapping(
								(Map<String, Object>) inputListSingleObj.get(0), (Map<String, Object>) interValueObj));
					}

					interObj.put(inputKey, constructedInterObjList);

				}
			}
		}));

		return interObj.toMap();
	}

}
