package com.natwesthackerearth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.natwesthackerearth.model.TransactionRequest;
import com.natwesthackerearth.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/banking/processTransaction")
	public ResponseEntity<String> encryptSend(@RequestBody TransactionRequest request) {
		if (request == null) {
			return new ResponseEntity<>("Your request is empty and therefore cant be processed.",
					HttpStatus.BAD_REQUEST);
		} else {
			boolean isRequestSentForProcessing = userService.encryptAndSendRequest(request);
			if (isRequestSentForProcessing) {
				return new ResponseEntity<>("Your request has been received and will be processed in due time.",
						HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(
						"Your request was received but couldn't be processed at this time. Please try again after some time.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
	}

}
