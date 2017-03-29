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
	public Pokemon pokemon2;

	@Before
	public void setUp() throws PokedexException{
		pokemon1 = pokemonFactory.createPokemon(1, 1, 100, 1, 1);
		pokemon2 = pokemonFactory.createPokemon(2, 2, 200, 2, 2);
	}
	@Test
	public void testAddPokemon() throws PokedexException{
		pokedex.addPokemon(pokemon1);
		when(pokedex.getPokemon(1)).thenReturn(pokemon1);
		
		assertTrue(pokedex.getPokemons().contains(pokemon1));
		assertFalse(pokedex.getPokemons().contains(pokemon2));
		try {
			assertEquals(pokemon1,pokedex.getPokemon(1));
			assertNotEquals(pokemon2,pokedex.getPokemon(1));
		} catch (PokedexException e) {
			fail("unexpected PokedexException");
		}
	}
	@Test(
		expected=PokedexException.class
	)
	public void testGetUnvalidPokemon() throws PokedexException{
		pokedex.getPokemon(-1);
	}
}