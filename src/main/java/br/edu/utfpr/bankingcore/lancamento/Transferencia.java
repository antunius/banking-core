package br.edu.utfpr.bankingcore.lancamento;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transferencia {
    private Lancamento lancamentoOrigem;
    private Lancamento lancamentoDestino;
}
