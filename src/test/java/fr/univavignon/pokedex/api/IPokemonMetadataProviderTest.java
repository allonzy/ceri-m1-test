package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import fr.univavignon.pokedex.imp.PokemonMetadataProvider;
import java.util.Locale;

public class IPokemonMetadataProviderTest {
	
	private PokemonMetadata bulbizzare;
	private PokemonMetadata aquali;
	private Locale apiLocale = Locale.ENGLISH;
	@Mock
	private IPokemonMetadataProvider pokemonMetadataProvider;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	public void mockSetUp() throws PokedexException{
		
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
		//when(IPokemonMetadataProvider.getLocale()).thenReturn(Locale.ENGLISH);
		/**/
	}

	@Before
	public void setUp() throws PokedexException {
		mockSetUp();
		pokemonMetadataProvider = new PokemonMetadataProvider();
		
		bulbizzare = pokemonMetadataProvider.getPokemonMetadata(0);
		aquali = pokemonMetadataProvider.getPokemonMetadata(133);
	}
	@Test
	public void testGetPokemonMetadata(){

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
		assertEquals(118,bulbizzare.getAttack());
		assertEquals(205,aquali.getAttack());

		assertEquals(118,bulbizzare.getDefense());
		assertEquals(177,aquali.getDefense());

		assertEquals(90,bulbizzare.getStamina());
		assertEquals(260,aquali.getStamina());
	}
}
