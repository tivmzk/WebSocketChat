package kr.ac.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/chat")
	public String chat() {
		return "chat";
	}
}
