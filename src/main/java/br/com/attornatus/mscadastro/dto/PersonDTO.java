package br.com.attornatus.mscadastro.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.attornatus.mscadastro.domain.model.Endereco;
import br.com.attornatus.mscadastro.domain.model.Person;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {
    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    private List<Endereco> endereco;

    public PersonDTO() {

    }

    public PersonDTO(Person obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.dataDeNascimento = obj.getDataDeNascimento();
        this.endereco = obj.getEnderecos().stream().filter( endereco -> endereco.isPrincipal()).collect(Collectors.toList());

    }

}
