package br.com.attornatus.mscadastro.dto;


import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoNewDTO {
	
	
	private String logradouro;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 8, max = 9, message="O tamanho deve ser entre 8 e 9 caracteres")
	private String cep;
	private String numero;
	private String cidade;
	private boolean principal;
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
