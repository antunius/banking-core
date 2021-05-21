package br.edu.utfpr.bankingcore.exception;

import br.edu.utfpr.bankingcore.model.Operacao;

public class SemSaldoException extends RuntimeException {
	private final String message;
	private final Operacao operacao;


	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public SemSaldoException(Operacao operacao) {
		this.message = "Sem Saldo!";
		this.operacao = operacao;
	}

	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public SemSaldoException(String message, Operacao operacao) {
		this.message = message;
		this.operacao = operacao;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Operacao getOperacao() {
		return operacao;
	}
}
