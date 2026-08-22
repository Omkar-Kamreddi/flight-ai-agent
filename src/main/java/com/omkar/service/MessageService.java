package com.omkar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.omkar.entity.Message;
import com.omkar.repository.MessageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;

	public void saveUserMessage(String conversationId, String content) {

		Message message = new Message(conversationId, "USER", content, LocalDateTime.now());

		messageRepository.save(message);
	}

	public void saveAssistantMessage(String conversationId, String content) {

		Message message = new Message(conversationId, "ASSISTANT", content, LocalDateTime.now());

		messageRepository.save(message);
	}

	public List<Message> getConversation(String conversationId) {

		return messageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId);
	}

}
