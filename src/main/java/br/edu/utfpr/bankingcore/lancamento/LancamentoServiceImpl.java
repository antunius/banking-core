package br.edu.utfpr.bankingcore.lancamento;

import br.edu.utfpr.bankingcore.conta.Conta;
import br.edu.utfpr.bankingcore.conta.ContaRepository;
import br.edu.utfpr.bankingcore.exception.ContaNaoEncontradaException;
import br.edu.utfpr.bankingcore.exception.SemSaldoException;
import br.edu.utfpr.bankingcore.model.Operacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private final LancamentoRepository lancamentoRepository;

	private final ContaRepository contaRepository;

	public LancamentoServiceImpl(LancamentoRepository lancamentoRepository, ContaRepository contaRepository) {
		this.lancamentoRepository = lancamentoRepository;
		this.contaRepository = contaRepository;
	}

	@Override
	public Lancamento saque(Long contaId, Double valor) {
		Conta conta = contaRepository.findById(contaId).orElseThrow(() -> new ContaNaoEncontradaException(Operacao.SAQUE));
		if (semLimite(conta, valor))
			throw new SemSaldoException(Operacao.SAQUE);
		conta.debitar(valor);
		contaRepository.save(conta);
		return criaLancamento(conta, valor, Operacao.SAQUE);

	}

	private boolean semLimite(Conta conta, Double valor) {
		return (conta.getSaldo().doubleValue() + conta.getLimiteCredito().doubleValue() < valor);
	}

	private Lancamento criaLancamento(Conta conta, Double valor, Operacao operacao) {
		Lancamento lancamento = new Lancamento();
		lancamento.setConta(conta);
		lancamento.setData(LocalDate.now());
		lancamento.setOperacao(operacao);
		lancamento.setValor(BigDecimal.valueOf(valor));
		lancamentoRepository.save(lancamento);
		return lancamento;
	}

	@Override
	@Transactional
	public Transferencia transferencia(Long contaOrigemId, Long contaDestinoId, Double valor) {
		var contaOrigem = contaRepository
			.findById(contaOrigemId)
			.orElseThrow(RuntimeException::new);
		var contaDestino = contaRepository
			.findById(contaDestinoId)
			.orElseThrow(RuntimeException::new);

		if (semLimite(contaOrigem, valor)) throw new SemSaldoException(Operacao.SAQUE);

		Lancamento lancamentoOrigem = criaLancamento(contaOrigem, valor, Operacao.DEBITO);
		Lancamento lancamentoDestino = criaLancamento(contaDestino, valor, Operacao.CREDITO);
		return new Transferencia(lancamentoOrigem, lancamentoDestino);
	}

	@Transactional
	public Lancamento depositar(Long contaId, Double valor) {
		var conta = contaRepository
			.findById(contaId)
			.orElseThrow(() -> new ContaNaoEncontradaException(Operacao.CREDITO));
		conta.creditar(valor);
		contaRepository.save(conta);
		return criaLancamento(conta, valor, Operacao.CREDITO);
	}

	@Override
	public Set<Lancamento> extrato(Long contaId) {
		var conta = contaRepository
			.findById(contaId)
			.orElseThrow(() -> new ContaNaoEncontradaException(Operacao.EXTRATO));
		return lancamentoRepository.findAllByContaAndDataAfter(conta, LocalDate.now().minusDays(30));
	}

}
