package com.codersdesks;

import com.codersdesks.model.Order;
import com.codersdesks.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication()
@RestController
public class AwsSqsDemoApplication {

	private Logger logger = LoggerFactory.getLogger(AwsSqsDemoApplication.class);

	@Autowired
	private MessageService service;

	public static void main(String[] args) {
		SpringApplication.run(AwsSqsDemoApplication.class, args);

	}

	@GetMapping("/send-message/{message}")
	public String sendMessage(@PathVariable String message){
		service.send(message);
		return "Hello";
	}

	@PostMapping("/order/create")
	public ResponseEntity<Order> createOrder(@RequestBody Order order){
		logger.info("order received "+order);
		service.sendOrder(order);
		return new ResponseEntity<>(order,HttpStatus.OK);

	}

}
