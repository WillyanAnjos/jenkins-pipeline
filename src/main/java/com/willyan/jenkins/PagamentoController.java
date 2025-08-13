package com.willyan.jenkins;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
	
	@GetMapping
	public String status() {
		return "Pagamento service is running!";
	}
}
