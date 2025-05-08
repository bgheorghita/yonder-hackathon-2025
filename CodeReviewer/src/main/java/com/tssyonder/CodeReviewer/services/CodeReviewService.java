package com.tssyonder.CodeReviewer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

import static com.tssyonder.CodeReviewer.utils.Prompts.REVIEW_INSTRUCTIONS_FOR_SYSTEM_PROMPT;

@Service
public class CodeReviewService {

	@Autowired
	private OllamaChatModel chatClient;

	public Flux<String> streamReview(String code) {
		SystemMessage systemMessage = new SystemMessage(REVIEW_INSTRUCTIONS_FOR_SYSTEM_PROMPT);
		UserMessage userMessage = new UserMessage(code);
		List<Message> messageList = new ArrayList<>();
		messageList.add(systemMessage);
		messageList.add(userMessage);
		Prompt userPrompt = new Prompt(messageList);
		return chatClient.stream(userPrompt).map(chunk -> {
			return chunk.getResult().getOutput().getText();
		});
	}
}
