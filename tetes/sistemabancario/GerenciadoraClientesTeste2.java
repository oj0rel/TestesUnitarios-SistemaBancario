package sistemabancario;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais
 * operações sobre clientes, realizados pela classe {@link GerenciadoraClientes}
 * @author gabriel.silva
 *
 */
public class GerenciadoraClientesTeste2 {

	private GerenciadoraClientes gerClientes;
	
	//transformando os ids dos clientes em variáveis
	private int idCliente01 = 1;
	private int idCliente02 = 2;
	
	/**
	 * Método responsável pela montagem do cenário dos testes
	 * @author gabriel.silva
	 * @date 29/08/2025
	 */
	@Before //essa anotação faz rodar N vezes, é executado toda vez que executar 1 teste.
	public void setUp() {
		
		/* ==== Montagem do Cenário global ====*/
		
		//criando alguns clientes
		Cliente cliente01 = new Cliente(idCliente01, "João da Silva", 30, "joao@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Maria da Silva", 20, "maria@gmail.com", 2, true);
		
		//inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<Cliente>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}
	
	@After
	public void tearDown() {
		
		/* ==== Desmontagem do cenário global ====*/
		gerClientes.limpa();
	}
	
	/**
	 * Teste da pesqusia de um cliente a partir do seu ID
	 * @author gabriel.silva
	 * @date 22/08/2025
	 */
	@Test
	public void testePesquisaCliente() {
		/*
		 * Passos do teste:
		 * 1 - Montagem de cenário
		 * 2 - Execução
		 * 3 - Verificação e avaliação
		 */
		
		/* ==== Execução ====*/
		Cliente cliente = gerClientes.pesquisaCliente(2);
		
		
		/* ==== Verificação e avaliação ====*/
		assertThat(cliente.getId(), is(2));
		assertThat(cliente.getNome(), is("Maria da Silva"));
	}
	
	/**
	 * Teste de remoção de um cliente a partir do ID
	 * @author gabriel.silva
	 * @date 29/08/2025
	 */
	@Test
	public void testeRemoveCliente() {
		
		/* ==== Execução ====*/
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);
		
		/* ==== Verificação e avaliação ====*/
		assertThat(clienteRemovido, is(true));
		assertTrue(clienteRemovido); //igual ao de cima
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente02));
	}
	
	
	
}
