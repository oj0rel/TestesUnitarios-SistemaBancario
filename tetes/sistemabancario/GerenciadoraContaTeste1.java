package sistemabancario;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author gabriel.silva
 * @date 12/09/25
 * Classe responsável por testar os métodos da classe GerenciadoraContas
 *
 */
public class GerenciadoraContaTeste1 {

	private GerenciadoraContas gerContas;
	
	private int idConta01 = 1;
	private int idConta02 = 2;
	
	
	//Método responsável pela montagem do cenário
	@Before
	public void setUp() {
		
		/* ==== Montagem de Cenário ====*/
		
		ContaCorrente conta01 = new ContaCorrente(idConta01, 200, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);
		
		List<ContaCorrente> contaDoBanco = new ArrayList<ContaCorrente>();
		contaDoBanco.add(conta01);
		contaDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contaDoBanco);
	}
	
	//Método responsável pela desmontagem do cenário
	//Esvazia a lista
	@After
	public void tearDown() {
		
		/* ==== Desmontagem de Cenário ====*/
		
		gerContas.limpa();
	}
	
	@Test
	public void testeTransfereValor() {
		
		/* ==== Execuçaõ do negócio selecionado para o Teste ====*/
		
		boolean resultadoTransfere = gerContas.transfereValor(idConta01, 50, idConta02);
		
		/* ==== Verficações e Análise ====*/
		assertThat(resultadoTransfere, is(true));
		assertTrue(resultadoTransfere);
		assertThat(gerContas.getContasDoBanco().size(), is(2));
		
		
		//Tentar verificar se o valor da conta foi descontado
		
		ContaCorrente conta01 = gerContas.pesquisaConta(idConta01);
		double conta01Saldo = conta01.getSaldo();
		
		assertThat(conta01Saldo, is(150.0));
		
		//esse aki faz a mesma coisa do de cima, só pega a conta direto no assert
		assertThat(gerContas.pesquisaConta(idConta02).getSaldo(), is(50.0));
	}
	
}
