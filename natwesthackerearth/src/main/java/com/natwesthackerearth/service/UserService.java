package com.natwesthackerearth.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwesthackerearth.model.ProcessingRequest;
import com.natwesthackerearth.model.TransactionRequest;
import com.natwesthackerearth.util.EncryptDecryptUtil;

@Service
public class UserService {

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	@Autowired
	EncryptDecryptUtil encryptDecryptUtil;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	public boolean encryptAndSendRequest(TransactionRequest request) {
		String jsonString = null;
		try {
			jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
			logger.info("UserService:encryptAndSendRequest() :: JSON String of Transaction Object -> {}", jsonString);
			if (!StringUtils.isBlank(jsonString)) {
				byte[] encryptedRequest = null;
				encryptedRequest = encryptDecryptUtil.encrypt(jsonString.getBytes());
				ProcessingRequest processingRequest = new ProcessingRequest();
				processingRequest.setEncryptedRequest(encryptedRequest);
				// create headers
				HttpHeaders headers = new HttpHeaders();
				// set `content-type` header
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<ProcessingRequest> entity = new HttpEntity<>(processingRequest, headers);
				ResponseEntity<String> response = restTemplate
						.postForEntity("http://localhost:8081/process/decryptAuditTransaction", entity, String.class);
				logger.info(
						"UserService:encryptAndSendRequest() :: Response msg from decrypt and audit api call -> {} | HTTP Status Code -> {}",
						response.getBody(), response.getStatusCodeValue());
				if (response.getStatusCode().equals(HttpStatus.ACCEPTED)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (JsonProcessingException e) {
			logger.error(
					"UserService:encryptAndSendRequest() :: Error in converting transaction object into JSON string",
					e);
			return false;
		} catch (Exception e) {
			logger.error("UserService:encryptAndSendRequest() :: Error in encrypting JSON string", e);
			return false;
		}

	}
}
