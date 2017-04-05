package fr.univavignon.pokedex.api;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class IPokemonTrainerFactoryTest {

	@Mock
	IPokedexFactory pokedexFactory;
	@Mock 
	IPokedex pokedex;
	@Mock
	IPokemonTrainerFactory pokemonTrainerFactory;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void mockSetUp(){
		when(pokemonTrainerFactory.createTrainer("red", Team.MYSTIC, pokedexFactory))
			.thenReturn(new PokemonTrainer("red",Team.MYSTIC,pokedex));
	}
	
	@Test
	public void testCreateTrainer(){
		PokemonTrainer red = pokemonTrainerFactory.createTrainer("red", Team.MYSTIC, pokedexFactory);
		assertNotNull(red);
		assertEquals(red.getName(),"red");
		assertEquals(red.getTeam(),Team.MYSTIC);
	}
}
