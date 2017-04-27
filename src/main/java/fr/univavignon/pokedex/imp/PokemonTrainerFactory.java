package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory{
	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
		IPokedex pokedex = pokedexFactory.createPokedex(new PokemonMetadataProvider(),new PokemonFactory());
		return new PokemonTrainer(name, team,pokedex);
	}
}
