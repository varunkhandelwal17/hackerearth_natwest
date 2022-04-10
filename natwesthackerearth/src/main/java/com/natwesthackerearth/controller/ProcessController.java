package com.natwesthackerearth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.natwesthackerearth.model.ProcessingRequest;
import com.natwesthackerearth.service.ProcessService;

@RestController
public class ProcessController {

	@Autowired
	private ProcessService processService;

	@PostMapping(value = "/process/decryptAuditTransaction")
	public ResponseEntity<String> decryptAudit(@RequestBody ProcessingRequest request) {
		if (request == null) {
			return new ResponseEntity<>("Request is empty", HttpStatus.BAD_REQUEST);
		} else {
			processService.decryptAndAuditRequest(request); // TODO: This should happen in async manner
			return new ResponseEntity<>("Request has been received and will be audited in the database",
					HttpStatus.ACCEPTED);
		}
	}
}
