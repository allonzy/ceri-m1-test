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
	public Pokemon bulbizzare;
	public Pokemon aquali;
	
	@Before public void mockSetUp() throws PokedexException{
		when(pokedex.getPokemon(-1))
		.thenThrow(new PokedexException("false exception"));
	}
	
	@Before
	public void setUp() throws PokedexException{
		bulbizzare = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
		aquali = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
	}
	@Test
	public void testAddPokemon() throws PokedexException{
		pokedex.addPokemon(bulbizzare);
		when(pokedex.getPokemon(0)).thenReturn(bulbizzare);
		
		assertTrue(pokedex.getPokemons().contains(bulbizzare));
		assertFalse(pokedex.getPokemons().contains(aquali));
		try {
			assertEquals(bulbizzare,pokedex.getPokemon(0));
			assertNotEquals(aquali,pokedex.getPokemon(0));
		} catch (PokedexException e) {
			fail("unexpected PokedexException");
		}
	}
	@Test(
		expected = PokedexException.class
	)
	public void testGetUnvalidPokemon() throws PokedexException{
		pokedex.getPokemon(-1);
	}
}