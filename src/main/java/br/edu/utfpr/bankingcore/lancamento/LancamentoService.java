package br.edu.utfpr.bankingcore.lancamento;

import java.util.Set;

public interface LancamentoService {
	Lancamento saque(Long contaId, Double valor);

	Transferencia transferencia(Long contaOrigem, Long contaDestino, Double valor);

	Set<Lancamento> extrato(Long contaId);

	Lancamento depositar(Long contaId, Double valor);
}
