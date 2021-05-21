package br.edu.utfpr.bankingcore.conta;

import br.edu.utfpr.bankingcore.model.Correntista;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "correntistaId")
	private Correntista correntista;

	@Column(precision = 10, scale = 2)
	@NotNull
	private BigDecimal limiteCredito;

	@NotNull
	private BigDecimal saldo;

	private LocalDateTime dataAbertura;

	public void creditar(Double valor) {
		this.saldo = this.saldo.add(BigDecimal.valueOf(valor));
	}

	public void debitar(Double valor) {
		if ((saldo.doubleValue() + limiteCredito.doubleValue()) >= valor) {
			saldo = saldo.subtract(BigDecimal.valueOf(valor));
		}
	}

	protected void deposito(Double valor) {
		this.saldo = this.saldo.add(BigDecimal.valueOf(valor));
	}
}
