package br.edu.utfpr.bankingcore.exception;

import br.edu.utfpr.bankingcore.model.Operacao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardError {
	private final String message;
	private final Operacao operacao;
	private final Long errorCode;
}
