package br.com.attornatus.mscadastro.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.attornatus.mscadastro.domain.model.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 2, max = 250, message="O tamanho deve ser entre 2 e 250 caracteres")
	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeNascimento;
	
	private List<Endereco> enderecos = new ArrayList<>();

	public PersonNewDTO() {
		super();
	}

	public PersonNewDTO(String nome, LocalDate dataDeNascimento) {
		super();
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}
	
	
	
	
}
