package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.modal.User;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic="spring-kafka";
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		
		template.send(topic, "Hi"+name);
		return "Data has Published";
	}
	
    @GetMapping("/publishJson")
    public String publishMessage() {
    	User user=new User(123, "Anji", new String[] {"Hyderabad", "SR nagar", "6th line"});
    	template.send(topic, user);
    	return "Json Data has been published";
    	}
    
	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
