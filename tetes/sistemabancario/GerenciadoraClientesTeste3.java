package sistemabancario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
public class GerenciadoraClientesTeste3 {

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
		
		/* ==== 1.Montagem do Cenário ====*/
		//Executada automaticamente através da injeção do método setUp (@Before)
		
		/* ==== 2.Execução ====*/
		Cliente cliente = gerClientes.pesquisaCliente(2);
		
		
		/* ==== 3.Verificação e avaliação ====*/
		assertThat(cliente.getId(), is(2));
		assertThat(cliente.getNome(), is("Maria da Silva"));
	}
	
	/**
	 * Teste da pesquisa de um cliente inexistente a partir do seu ID
	 * @author gabriel.silva
	 * @date 05/09/2025
	 */
	@Test
	public void testePesquisaClienteInexistente() {
		/* ==== 1.Montagem do Cenário ====*/
		//Executada automaticamente através da injeção do método setUp (@Before)
		
		/* ==== 2.Execução ====*/
		Cliente clienteTeste = gerClientes.pesquisaCliente(10);
		
		/* ==== 3.Verificação e avaliação ====*/
		assertNull(clienteTeste);
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}
	
	/**
	 * Teste de remoção de um cliente a partir do ID
	 * @author gabriel.silva
	 * @date 29/08/2025
	 */
	@Test
	public void testeRemoveCliente() {
		/* ==== 1.Montagem do Cenário ====*/
		//Executada automaticamente através da injeção do método setUp (@Before)
		
		/* ==== 2.Execução ====*/
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);
		
		/* ==== 3.Verificação e avaliação ====*/
		assertThat(clienteRemovido, is(true));
		assertTrue(clienteRemovido); //igual ao de cima
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente02));
	}
	
	/**
	 * Teste de remoção de um cliente inexistente a partir do ID
	 * @author gabriel.silva
	 * @date 05/09/2025
	 */
	@Test
	public void testeRemoveClienteInexistente() {
		/* ==== 1.Montagem do Cenário ====*/
		//Executada automaticamente através da injeção do método setUp (@Before)
		
		/* ==== 2.Execução ====*/
		boolean clienteRemovido = gerClientes.removeCliente(10);
		
		/* ==== 3.Verificação e avaliação ====*/
		assertFalse(clienteRemovido);
		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
		assertNull(gerClientes.pesquisaCliente(10));
	}
	
	//validação quando o cliente está no intervalo de idade permitido, na su porção central
		@Test
		public void testeClienteIdadePermitida1() throws IdadeNaoPermitidaException {
			//cenario customizado para esse teste
			Cliente cliente = new Cliente(3, "Fabio", 30, "fabio@fabio.com", idCliente01, true);
			
			//execução
			boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
			
			//avaliação e verificação
			assertTrue(idadeValida);
		}
		
	//validação quando o cliente está no intervalo de idade permitido, no limite inferior
		@Test
		public void testeClienteIdadePermitidaLimiteInferior() throws IdadeNaoPermitidaException {
			//cenario customizado para esse teste
			Cliente cliente = new Cliente(4, "Roger", 18, "roger@roger.com", 4, true);
					
			//execução
			boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
					
			//avaliação e verificação
			assertTrue(idadeValida);
		}
		
	//validação quando o cliente está no intervalo de idade permitido, no limite inferior
		@Test
		public void testeClienteIdadePermitidaLimiteSuperior() throws IdadeNaoPermitidaException {
			//cenario customizado para esse teste
			Cliente cliente = new Cliente(5, "Clayton", 65, "clayton@clayton.com", 5, true);
							
			//execução
			boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
							
			//avaliação e verificação
			assertTrue(idadeValida);
		}
		
	//validação quando o cliente está no intervalo de idade NÃO permitido, no limite inferior
		@Test
		public void testeClienteIdadeNaoPermitidaLimiteInferior() throws IdadeNaoPermitidaException {
			//cenario customizado para esse teste
			Cliente cliente = new Cliente(6, "Yasmin", 17, "yasmin@yasmin.com", 6, true);
									
			//execução
			try {
				gerClientes.validaIdade(cliente.getIdade());
				fail();
			} catch (Exception e) {
				assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
			}
									
		}
		
	//validação quando o cliente está no intervalo de idade NÃO permitido, no limite superior
		@Test
		public void testeClienteIdadeNaoPermitidaLimiteSuperior() throws IdadeNaoPermitidaException {
			//cenario customizado para esse teste
			Cliente cliente = new Cliente(7, "Darley", 66, "darley@darley.com", 7, true);
											
			//execução
			try {
				gerClientes.validaIdade(cliente.getIdade());
				fail();
			} catch (Exception e) {
				assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
			}
											
		}
	
	
}
