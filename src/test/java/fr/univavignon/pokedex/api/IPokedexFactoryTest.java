package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

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
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void createPokedex(){
		IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider,pokemonFactory);
		assertNotNull(pokedex);
	}
}
