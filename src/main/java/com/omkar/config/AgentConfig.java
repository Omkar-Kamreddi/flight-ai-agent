package com.omkar.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {
	
	@Bean
	public ChatClient getChatClient(ChatClient.Builder builder) {
		return builder.build();
	}

}
