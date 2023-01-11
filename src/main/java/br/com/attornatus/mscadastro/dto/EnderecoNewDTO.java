package br.com.attornatus.mscadastro.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.attornatus.mscadastro.domain.model.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoNewDTO {
	
	
	private String logradouro;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;
	private String numero;
	private String cidade;
	private boolean principal;
	@NotEmpty(message = "Preenchimento obrigatório")
	private Long person;
	
	public EnderecoNewDTO() {
		super();
	}
	
	public EnderecoNewDTO(String logradouro, String cep, String numero, String cidade, boolean principal, Long person) {
		super();
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.principal = principal;
		this.person = person;
	}

	

}
