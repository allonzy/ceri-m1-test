package fr.univavignon.pokedex.imp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class Pokedex implements IPokedex {

	/**required to provide metadata to the pokemon **/
	private IPokemonMetadataProvider pokemonMetadataProvider;
	/** required to create pokemon **/
	private IPokemonFactory pokemonFactory;
	/** stored to store all the pokemon */
	protected HashMap<Integer,Pokemon> pokemons;
	/**
	 * 
	 * @param pokemonMetadataProvider
	 * @param pokemonFactory
	 */
	Pokedex(IPokemonMetadataProvider pokemonMetadataProvider,IPokemonFactory pokemonFactory){
		this.pokemonMetadataProvider = pokemonMetadataProvider;
		this.pokemonFactory = pokemonFactory;
	}
	/**
	 * Decorate {@link #pokemonMetadataProvider}
	 */
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		return pokemonMetadataProvider.getPokemonMetadata(index);
	}
	/**
	 * Decorate {@link #createPokemon}
	 */
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
	}

	@Override
	public int size() {
		return pokemons.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		pokemons.put(pokemon.getIndex(),pokemon);
		return 0;
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		return pokemons.get(id);
	}

	@Override
	public List<Pokemon> getPokemons() {
		return new ArrayList<Pokemon>(pokemons.values());
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		return null;
	}

}
