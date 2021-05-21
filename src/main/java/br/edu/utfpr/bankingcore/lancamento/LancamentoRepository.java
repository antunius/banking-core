package br.edu.utfpr.bankingcore.lancamento;

import br.edu.utfpr.bankingcore.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;


@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	Set<Lancamento> findAllByContaAndDataAfter(Conta conta, LocalDate data);
}
