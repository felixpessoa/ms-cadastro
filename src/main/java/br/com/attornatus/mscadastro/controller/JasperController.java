package br.com.attornatus.mscadastro.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.mscadastro.domain.service.JasperService;

@RestController
@RequestMapping(value = "/api/pdf")
public class JasperController {
	
	@Autowired
	private JasperService jasperService;
	
	@GetMapping("/all")
	public void exibiarTodosRegistro(HttpServletResponse response) throws IOException {
		byte[] bytes = jasperService.exportarPDF();
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-disposition", "attachment; filename=relatorio-geral-cadastro.pdf");
		response.getOutputStream().write(bytes);
	}

}
