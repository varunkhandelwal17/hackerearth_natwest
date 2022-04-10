package com.natwesthackerearth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwesthackerearth.model.ProcessingRequest;
import com.natwesthackerearth.model.TransactionRequest;
import com.natwesthackerearth.util.EncryptDecryptUtil;

@Service
public class ProcessService {

	@Autowired
	EncryptDecryptUtil encryptDecryptUtil;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	Logger logger = LoggerFactory.getLogger(ProcessService.class);

	// TODO: Process the below method in async manner
	public void decryptAndAuditRequest(ProcessingRequest request) {
		byte[] encryptedRequest = request.getEncryptedRequest();
		if (encryptedRequest != null) {
			try {
				String decryptedRequest = encryptDecryptUtil.decrypt(encryptedRequest);
				logger.info("ProcessService:decryptAndAuditRequest() :: The decrypted request is -> {}",
						decryptedRequest);
				TransactionRequest transactionRequest = null;
				try {
					transactionRequest = objectMapper.readValue(decryptedRequest, TransactionRequest.class);
					if (transactionRequest != null) {
						// TODO: Insert transaction request values into DB
					}
				} catch (JsonProcessingException e) {
					logger.error(
							"ProcessService:decryptAndAuditRequest() :: Could not convert decrypted request to transaction request POJO", e);
				}
			} catch (Exception e) {
				logger.error("ProcessService:decryptAndAuditRequest() :: Could not decrypt the encrypted request", e);
			}
		} else {
			logger.error("ProcessService:decryptAndAuditRequest() :: The received encrypted request is empty or null");
		}
	}

}
