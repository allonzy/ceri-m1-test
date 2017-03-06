package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexTest {
	@Mock
	IPokedex pokedex;
	
	@Mock
	IPokemonFactory pokemonFactory;
	
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	public Pokemon pokemon1;

	@Before
	public void setUp(){
		pokemon1 = pokemonFactory.createPokemon(1, 1, 100, 1, 1);
	}
	@Test
	public void testAddPokemon(){
		pokedex.addPokemon(pokemon1);
		assertFalse(pokedex.getPokemons().contains(pokemon1));
		
	}
	
}
