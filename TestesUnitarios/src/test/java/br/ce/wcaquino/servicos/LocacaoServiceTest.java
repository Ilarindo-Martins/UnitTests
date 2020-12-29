package br.ce.wcaquino.servicos;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;


public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule 
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testeLocacao() throws Exception {
		
		//Cenario
		LocacaoService	service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1",2,5.0);
		
		//Acao
		Locacao locacao = service.alugarFilme(usuario,filme);
		
		//Verifica��o
		error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
	
	}
	
	@Test(expected=Exception.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		//Cenario
		LocacaoService	service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1",0,5.0);
				
		//Acao
		service.alugarFilme(usuario,filme);
							
	}
	
	@Test
	public void testLocacao_filmeSemEstoque__2() {
		//Cenario
		LocacaoService	service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1",0,5.0);
				
		//Acao
		try {
			service.alugarFilme(usuario,filme);
			Assert.fail("Deveria ter lancado uma excessao");
		} catch (Exception e) {
			error.checkThat(e.getMessage(),CoreMatchers.is("Filme sem estoque"));
		}
							
	}
	
	
	@Test
	public void testLocacao_filmeSemEstoque_3() throws Exception {
		//Cenario
		LocacaoService	service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1",0,5.0);
		
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		//Acao
		service.alugarFilme(usuario,filme);
		
		//teste					
	}
	
}
	
	
	
	
	





