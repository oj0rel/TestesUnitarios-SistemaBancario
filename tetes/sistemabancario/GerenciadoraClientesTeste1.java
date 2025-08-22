package sistemabancario;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais
 * operações sobre clientes, realizados pela classe {@link GerenciadoraClientes}
 * @author gabriel.silva
 *
 */
public class GerenciadoraClientesTeste1 {

	private GerenciadoraClientes gerClientes;
	
	/**
	 * Teste da pesqusia de um cliente a partir do seu ID
	 * @author gabriel.silva
	 * @date 22/08
	 */
	@Test
	public void testePesquisaCliente() {
		/*
		 * Passos do teste:
		 * 1 - Montagem de cenário
		 * 2 - Execução
		 * 3 - Verificação e avaliação
		 */
		
		/* ==== Montagem de Cenário ====*/
		//criando alguns clientes
		Cliente cliente01 = new Cliente(1, "João da Silva", 30, "joao@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Maria da Silva", 20, "maria@gmail.com", 2, true);
		
		//inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<Cliente>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
		
		/* ==== Execução ====*/
		Cliente cliente = gerClientes.pesquisaCliente(2);
		
		
		/* ==== Verificação e avaliação ====*/
		assertThat(cliente.getId(), is(2));
	}
	
	
	
}
