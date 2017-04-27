package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import fr.univavignon.pokedex.imp.PokemonFactory;

import static org.mockito.Mockito.*;

import java.util.Locale;

public class IPokemonFactoryTest {
	@Mock
	private IPokemonFactory pokemonFactory;
	@Mock
	private IPokemonMetadataProvider pokemonMetadataProvider;
	private Locale apiLocale = Locale.ENGLISH;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	public Pokemon bulbizzare;
	public Pokemon aquali;

	 
	public void mockSetUp() throws PokedexException {
		/**/
		when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4))
			.thenReturn(new Pokemon(
				0,//final int index,
				"Bulbasaur",//final String name,
				118,//final int attack,
				118,//final int defense,
				90,//final int stamina,
				613,//final int cp,
				64,//final int hp,
				4000,//final int dust,
				4,//final int candy,
				0.56//final double iv
			)
		);
		when(pokemonFactory.createPokemon(133, 2729, 202, 5000, 4))
			.thenReturn(new Pokemon(
				133,//final int index,
				"Vaporeon",//final String name,
				205,//final int attack,
				177,//final int defense,
				260,//final int stamina,
				2729,//final int cp,
				202,//final int hp,
				5000,//final int dust,
				4,//final int candy,
				1//final double iv
			)	
		);
		/**/
		when(pokemonMetadataProvider.getPokemonMetadata(0))
		.thenReturn(new PokemonMetadata(
			0,//final int index,
			"Bulbasaur",//final String name,
			118,//final int attack,
			118,//final int defense,
			90//final int stamina,
		)
		);
		when(pokemonMetadataProvider.getPokemonMetadata(133))
			.thenReturn(new PokemonMetadata(
				133,//final int index,
				"Vaporeon",//final String name,
				205,//final int attack,
				177,//final int defense,
				260//final int stamina,
				)
		); 
		/**/
	}
	@Before
	public void setUp() throws PokedexException{
		
		
		mockSetUp();
		pokemonFactory = new PokemonFactory(pokemonMetadataProvider);
		bulbizzare = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
		aquali = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
	}

	@Test
	public void testValidPokemon(){
		assertNotNull(bulbizzare);
		assertNotNull(aquali);

		assertEquals(0,bulbizzare.getIndex());
		assertEquals(133,aquali.getIndex());

		if(apiLocale == Locale.ENGLISH){
			assertEquals("Bulbasaur",bulbizzare.getName());
			assertEquals("Vaporeon",aquali.getName());
		}else if(apiLocale == Locale.FRENCH){
			assertEquals("Bulbizarre",bulbizzare.getName());
			assertEquals("Aquali",aquali.getName());
		}else{
			fail("the LOCALE attribute was not EN or FR, code the test for other language");
		}
		
		assertEquals(613,bulbizzare.getCp());
		assertEquals(2729,aquali.getCp());

		assertEquals(4000,bulbizzare.getDust());
		assertEquals(5000,aquali.getDust());

		assertEquals(4,bulbizzare.getCandy());
		assertEquals(4,aquali.getCandy());

		assertEquals(118,bulbizzare.getAttack());
		assertEquals(205,aquali.getAttack());

		assertEquals(118,bulbizzare.getDefense());
		assertEquals(177,aquali.getDefense());

		assertEquals(90,bulbizzare.getStamina());
		assertEquals(260,aquali.getStamina());
		
		assertEquals(0.56,bulbizzare.getIv(),0.01);
		assertEquals(1,aquali.getIv(),0.01);
	}
}
