package br.edu.utfpr.bankingcore.exception;

import br.edu.utfpr.bankingcore.model.Operacao;

public class ContaNaoEncontradaException extends RuntimeException {
	private final String message;
	private final Operacao operacao;

	public ContaNaoEncontradaException(String message, Operacao operacao) {
		this.message = message;
		this.operacao = operacao;
	}

	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public ContaNaoEncontradaException(Operacao operacao) {
		this.operacao = operacao;
		this.message = "Conta não encontrada";
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Operacao getOperacao() {
		return operacao;
	}
}
