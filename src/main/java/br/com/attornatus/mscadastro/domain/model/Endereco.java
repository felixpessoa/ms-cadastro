package br.com.attornatus.mscadastro.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;
	private boolean principal;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public Endereco() {
		super();
	} 
	
	public Endereco(Long id, String logradouro, String cep, String numero, String cidade, boolean principal,
			Person person) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.principal = principal;
		this.person = person;
	}



	
	
	

}
