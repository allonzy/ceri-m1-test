package fr.univavignon.pokedex.api;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import fr.univavignon.pokedex.imp.PokemonTrainerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class IPokemonTrainerFactoryTest {

	@Mock
	private IPokedexFactory pokedexFactory;
	@Mock 
	private IPokedex pokedex;
	@Mock
	private IPokemonTrainerFactory pokemonTrainerFactory;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void setUp(){
		mockSetUp();
		impSetUp();
	}
	public void impSetUp(){
		pokemonTrainerFactory = new PokemonTrainerFactory();
	}
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
