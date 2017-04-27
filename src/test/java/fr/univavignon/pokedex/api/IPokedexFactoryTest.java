package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;

import fr.univavignon.pokedex.imp.PokedexFactory;

public class IPokedexFactoryTest {
	@Mock
	private IPokedexFactory pokedexFactory;
	
	@Mock
	protected IPokemonMetadataProvider metadataProvider;
	
	@Mock
	protected IPokemonFactory pokemonFactory;
	
	@Mock
	protected IPokedex pokedex;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	public void setUp(){
		mockSetUp();
		impSetUp();
	}
	public void impSetUp(){
		pokedexFactory = new PokedexFactory();
	}
	public void mockSetUp(){
		when(pokedexFactory.createPokedex(metadataProvider,pokemonFactory))
		.thenReturn(pokedex);
	}
	@Test
	public void createPokedex(){
		IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider,pokemonFactory);
		assertNotNull(pokedex);
	}
}
