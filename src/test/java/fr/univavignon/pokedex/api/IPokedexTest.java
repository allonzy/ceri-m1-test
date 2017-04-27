package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexTest {
	
	@Mock
	private IPokedex pokedex;
		
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	public Pokemon bulbizzare;
	public Pokemon aquali;
	public List<Pokemon> pokemonList;

	public void mockSetUp() throws PokedexException{
		/**/
		when(pokedex.getPokemon(-1))
			.thenThrow(new PokedexException("false exception"));
	
		pokemonList = new ArrayList<Pokemon>();
		pokemonList.add(bulbizzare);
		
		when(pokedex.getPokemons())
			.thenReturn(Collections.unmodifiableList(pokemonList));
			
		when(pokedex.getPokemon(0))
			.thenReturn(bulbizzare);
		/**/
	}
	@Before 
	public void setUp() throws PokedexException{
		bulbizzare = new Pokemon(
				0,//final int index,
				"Vaporeon",//final String name,
				126,//final int attack,
				126,//final int defense,
				90,//final int stamina,
				613,//final int cp,
				64,//final int hp,
				4000,//final int dust,
				4,//final int candy,
				0.56//final double iv
			);
		aquali = new Pokemon(
				133,//final int index,
				"Aquali",//final String name,
				186,//final int attack,
				168,//final int defense,
				260,//final int stamina,
				2729,//final int cp,
				202,//final int hp,
				5000,//final int dust,
				4,//final int candy,
				1//final double iv
			);
		mockSetUp();
		
	}
	
	
	@Test
	public void testAddPokemon() throws PokedexException{
		pokedex.addPokemon(bulbizzare);
		
		List<Pokemon> pokeList = pokedex.getPokemons();
		assertTrue(pokeList.contains(bulbizzare));
		assertFalse(pokeList.contains(aquali));
		
		assertEquals(bulbizzare,pokedex.getPokemon(0));
		assertNotEquals(aquali,pokedex.getPokemon(0));

	}
	@Test(
		expected = PokedexException.class
	)
	public void testGetUnvalidPokemon() throws PokedexException{
		pokedex.getPokemon(-1);
	}
	public void testPokemonsOrdered(){
	}
}