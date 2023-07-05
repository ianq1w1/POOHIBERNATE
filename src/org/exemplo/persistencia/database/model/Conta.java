package org.exemplo.persistencia.database.model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@javax.persistence.Entity
@Table(name="conta")

public class Conta {
	
		@Id private Integer id;
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		@Column(name = "numero_da_conta")
		private Integer numeroConta;
		@Column(name = "saldo")
		private BigDecimal saldo;
		@Column(name = "data_abertura")
		private LocalDateTime dataAbertura;
		@Column(name = "status")
		private boolean status;
		
		@Column(name = "tipo_conta")
		@Enumerated(EnumType.STRING)
		private TipoConta tipoConta;
		
		
	    @ManyToOne
	    @JoinColumn(name = "cliente_id")
	    private Cliente cliente;
	    
	   @OneToMany(mappedBy = "contaCorrente")
	    private List<Transacao> transacoes;

		
		public Conta() {};
		
		public Conta(Integer id) {
			this.id = id;
		};
		
		public Conta(Integer id, Integer numeroConta, BigDecimal saldo, LocalDateTime dataAbertura, Boolean status) {
			this.numeroConta = new Random().nextInt(999999999);
			this.saldo = BigDecimal.ZERO;
			saldo.setScale(4, RoundingMode.HALF_UP);
			this.dataAbertura = LocalDateTime.now();
			this.status = true;
			transacoes = new ArrayList<>();
		}



		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getNumeroConta() {
			return numeroConta;
		}

		public void setNumeroConta(Integer numeroConta) {
			this.numeroConta = numeroConta;
		}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		public LocalDateTime getDataAbertura() {
			return dataAbertura;
		}

		public void setDataAbertura(LocalDateTime dataAbertura) {
			this.dataAbertura = dataAbertura;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public TipoConta getTipoConta() {
			return tipoConta;
		}

		public void setTipoConta(TipoConta tipoConta) {
			this.tipoConta = tipoConta;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public List<Transacao> getTransacoes() {
			return transacoes;
		}

		public void setTransacoes(List<Transacao> transacoes) {
			this.transacoes = transacoes;
		}

		@Override
		public String toString() {
			return "Conta [ numero da conta=" + numeroConta + ", tipo conta=" + tipoConta + ", saldo=" + saldo + ", status=" + status + ", data de abertura" + dataAbertura + "]";
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
