package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;

public class IPokedexFactoryTest {
	@Mock
	IPokedexFactory pokedexFactory;
	
	@Mock
	IPokemonMetadataProvider metadataProvider;
	
	@Mock
	IPokemonFactory pokemonFactory;
	
	@Mock
	IPokedex pokedex;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before public void mockSetUp() throws PokedexException{
		when(pokedexFactory.createPokedex(metadataProvider,pokemonFactory))
			.thenReturn(pokedex);
	}
	@Test
	public void createPokedex(){
		IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider,pokemonFactory);
		assertNotNull(pokedex);
	}
}
