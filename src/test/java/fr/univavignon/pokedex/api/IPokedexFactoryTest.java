package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;

public class IPokedexFactoryTest {
	@Mock
	private IPokedexFactory pokedexFactory;
	
	@Mock
	private IPokemonMetadataProvider metadataProvider;
	
	@Mock
	private IPokemonFactory pokemonFactory;
	
	@Mock
	private IPokedex pokedex;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before public void setUp() throws PokedexException{
		//pokedexFactory = new PokedexFactory();
		when(pokedexFactory.createPokedex(metadataProvider,pokemonFactory))
			.thenReturn(pokedex);
	}
	@Test
	public void createPokedex(){
		IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider,pokemonFactory);
		assertNotNull(pokedex);
	}
}
