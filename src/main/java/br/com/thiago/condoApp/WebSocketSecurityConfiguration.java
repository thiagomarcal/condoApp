//package br.com.thiago.condoApp;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
//import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
//
//@Configuration
//public class WebSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer{
//	
//	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
//        messages.simpDestMatchers("/user/*").authenticated();
//    }
//}