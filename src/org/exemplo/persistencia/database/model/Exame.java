package org.exemplo.persistencia.database.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exame")
public class Exame {

	@Id
	private Integer id;
	@Column (name="nome")
	private String nome;
	@Column (name="descricao")
	private String descricao;
	@ManyToOne
    @JoinColumn(name = "idPaciente")
	private Paciente paciente;
	
	public Exame() {
		// TODO Auto-generated constructor stub
	}
	
	public Exame(Integer id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Exame(Integer id, String nome, String descricao, Paciente p) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.paciente = p;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exame other = (Exame) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Exame [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	

	
	
}
