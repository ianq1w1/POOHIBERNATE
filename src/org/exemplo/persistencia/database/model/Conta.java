package org.exemplo.persistencia.database.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@javax.persistence.Entity
@Table(name="contar ]]ds")

public class Conta {
	
		@Id
		private Integer id;
		@Column(name = "numero_da_conta")
		private Integer numeroConta;
		@Column(name = "saldo")
		private BigDecimal saldo;
		@Column(name = "data_abertura")
		private LocalDateTime dataAbertura;
		@Column(name = "status")
		private boolean status;
		//@Column(name = "transacoes")
		//private 
		
	    @ManyToOne
	    @JoinColumn(name = "cliente_id")
	    private Cliente cliente;
	    
	  //  @OneToMany(mappedBy = "contaCorrente")
	    // private List<Transacao> transacoes;

		
		public Conta() {};
		
		public Conta(Integer id) {
			this.id = id;
		};
		
		public Conta(Integer id, Integer numeroConta, BigDecimal saldo, ) {
			this.id = id;
			this.nome = nome;
			this.cpf = cpf;
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

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		@Override
		public String toString() {
			return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
		

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cliente other = (Cliente) obj;
			return Objects.equals(id, other.id);
		}
	}
}
