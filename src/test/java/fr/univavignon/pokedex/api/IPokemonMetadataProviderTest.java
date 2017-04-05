package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokemonMetadataProviderTest {
	
	private PokemonMetadata bulbizzare;
	private PokemonMetadata aquali;

	@Mock
	private IPokemonMetadataProvider pokemonMetadataProvider;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	public void mockSetUp() throws PokedexException{
		
		/*
		when(pokemonMetadataProvider.getPokemonMetadata(0))
			.thenReturn(new PokemonMetadata(
				1,//final int index,
				"Bulbizzare",//final String name,
				126,//final int attack,
				126,//final int defense,
				90//final int stamina,
			)
		);
		when(pokemonMetadataProvider.getPokemonMetadata(133))
			.thenReturn(new PokemonMetadata(
				133,//final int index,
				"Aquali",//final String name,
				186,//final int attack,
				168,//final int defense,
				260//final int stamina,
				)
		);
		/**/
	}
	@Before
	public void setUp() throws PokedexException {
		pokemonMetadataProvider = new PokemonMetadataProvider();
		bulbizzare = pokemonMetadataProvider.getPokemonMetadata(1);
		aquali = pokemonMetadataProvider.getPokemonMetadata(133);
		mockSetUp();
	}
	@Test
	public void testGetPokemonMetadata(){
		
		assertNotNull(bulbizzare);
		assertNotNull(aquali);

		assertEquals(1,bulbizzare.getIndex());
		assertEquals(133,aquali.getIndex());

		assertEquals("Bulbizzare",bulbizzare.getName());
		assertEquals("Aquali",aquali.getName());

		assertEquals(126,bulbizzare.getAttack());
		assertEquals(186,aquali.getAttack());

		assertEquals(126,bulbizzare.getDefense());
		assertEquals(186,aquali.getDefense());

		assertEquals(90,bulbizzare.getStamina());
		assertEquals(260,aquali.getStamina());

	}
}
