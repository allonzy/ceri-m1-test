package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import fr.univavignon.pokedex.imp.Pokedex;

public class IPokedexTest {
	
	@Mock
	private IPokedex pokedex;
	@Mock
	private IPokemonMetadataProvider pokemonMetadataProvider;
	@Mock
	IPokemonFactory pokemonFactory;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	/**
	 * Try to modify the api to get this attribute from it 
	 */
	private Locale apiLocale = Locale.ENGLISH;
	private Pokemon bulbizzare;
	private Pokemon aquali;
	private List<Pokemon> pokemonList;
	private List<Pokemon> _pokeListOrderedByIndex;
	private List<Pokemon> _pokeListOrderedByName;
	private List<Pokemon> _pokeListOrderedByCp;
	public void mockSetUp() throws PokedexException{
		setOrderedPokeListMock();
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
	private void setOrderedPokeListMock(){
		_pokeListOrderedByIndex = new ArrayList<Pokemon>();
		_pokeListOrderedByIndex.add(bulbizzare);
		_pokeListOrderedByIndex.add(aquali);
		when(pokedex.getPokemons(PokemonComparators.NAME)).thenReturn(_pokeListOrderedByName);
		
		_pokeListOrderedByName = new ArrayList<Pokemon>();
		_pokeListOrderedByName.add(bulbizzare);
		_pokeListOrderedByName.add(aquali);
		when(pokedex.getPokemons(PokemonComparators.INDEX)).thenReturn(_pokeListOrderedByIndex);
		
		_pokeListOrderedByCp = new ArrayList<Pokemon>();
		_pokeListOrderedByCp.add(bulbizzare);
		_pokeListOrderedByCp.add(aquali);
		when(pokedex.getPokemons(PokemonComparators.INDEX)).thenReturn(_pokeListOrderedByCp);
	}
	
	@Before 
	public void setUp() throws PokedexException{
		bulbizzare = new Pokemon(
				0,//final int index,
				"Bulbasaur",//final String name,
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
				"Vaporeon",//final String name,
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
		pokedex = new Pokedex(pokemonMetadataProvider,pokemonFactory);
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
	
	
	
	
	
	@Test
	public void testPokemonsOrderedByName(){
		List<Pokemon> pokeListOrdered = pokedex.getPokemons(PokemonComparators.NAME);
		if(	apiLocale == Locale.ENGLISH){
			List<Pokemon> testPokeListOrdered = new ArrayList<Pokemon>();
			testPokeListOrdered.add(bulbizzare);
			testPokeListOrdered.add(aquali);
			assertEquals(testPokeListOrdered,pokeListOrdered);
		}else if(	apiLocale == Locale.FRENCH){
			List<Pokemon> testPokeListOrdered = new ArrayList<Pokemon>();
			testPokeListOrdered.add(aquali);
			testPokeListOrdered.add(bulbizzare);
			assertEquals(testPokeListOrdered,pokeListOrdered);
		}else{
			fail("the LOCALE attribute was not EN or FR, code the test for other language");
		}
	}
	@Test
	public void testPokemonsOrderedByCp(){
		List<Pokemon> pokeListOrdered = pokedex.getPokemons(PokemonComparators.CP);
		List<Pokemon> testPokeListOrdered = new ArrayList<Pokemon>();
		testPokeListOrdered.add(bulbizzare);
		testPokeListOrdered.add(aquali);
		assertEquals(testPokeListOrdered,pokeListOrdered);
	}
	@Test
	public void testPokemonsOrderedByIndex(){
		List<Pokemon> pokeListOrdered = pokedex.getPokemons(PokemonComparators.INDEX);
		List<Pokemon> testPokeListOrdered = new ArrayList<Pokemon>();
		testPokeListOrdered.add(bulbizzare);
		testPokeListOrdered.add(aquali);
		assertEquals(testPokeListOrdered,pokeListOrdered);
	}
}