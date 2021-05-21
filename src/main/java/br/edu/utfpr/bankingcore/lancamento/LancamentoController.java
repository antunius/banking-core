package br.edu.utfpr.bankingcore.lancamento;

/*@RestController
@Controller()

 */
public class LancamentoController {
	/*
	private final LancamentoService lancamentoService;

	public LancamentoController(LancamentoService lancamentoService) {
		this.lancamentoService = lancamentoService;
	}

	@GetMapping("extrato/{contaId}")
	public Set<Lancamento> extrato(@PathVariable Long contaId) {
		return lancamentoService.extrato(contaId);
	}

	@GetMapping("saque/{contaId}/{valor}")
	public Lancamento saque(@PathVariable Long contaId,
	                        @PathVariable Double valor) {
		return lancamentoService.saque(contaId, valor);
	}

	@GetMapping("transferencia/{contaOrigem}/{contaDestino}/{valor}")
	public Transferencia transferencia(@PathVariable Long contaOrigem,
	                                   @PathVariable Long contaDestino,
	                                   @PathVariable Double valor) {
		return lancamentoService.transferencia(contaOrigem, contaDestino, valor);
	}

	 */
}
