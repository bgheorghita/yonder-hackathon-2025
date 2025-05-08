package com.tssyonder.CodeReviewer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tssyonder.CodeReviewer.services.CodeReviewService;

import reactor.core.publisher.Flux;

@RestController
public class CodeReviewController {
	
	@Autowired
	private CodeReviewService codeReviewService;

	@PostMapping(value = "/stream-review", produces = MediaType.TEXT_PLAIN_VALUE)
	public Flux<String> streamReview(@RequestBody String code) {
		return codeReviewService.streamReview(code);
	}
}
