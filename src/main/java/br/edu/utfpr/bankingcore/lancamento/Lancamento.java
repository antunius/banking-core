package br.edu.utfpr.bankingcore.lancamento;

import br.edu.utfpr.bankingcore.conta.Conta;
import br.edu.utfpr.bankingcore.model.Operacao;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDate data;

	@ManyToOne
	@JoinColumn(name = "contaId")
	private Conta conta;

	@Enumerated(EnumType.STRING)
	private Operacao operacao;

	private BigDecimal valor;

	@Override
	public String toString() {
		return "Lancamento{" +
			"id=" + id +
			", data=" + data +
			", conta=" + conta +
			", operacao=" + operacao +
			", valor=" + valor +
			'}';
	}
}
