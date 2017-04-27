package fr.univavignon.pokedex.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class Pokedex implements IPokedex,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2028882078020978542L;
	/**required to provide metadata to the pokemon **/
	private IPokemonMetadataProvider pokemonMetadataProvider;
	/** required to create pokemon **/
	private IPokemonFactory pokemonFactory;
	/** stored to store all the pokemon */
	protected List<Pokemon> pokemons;
	/**
	 * 
	 * @param pokemonMetadataProvider
	 * @param pokemonFactory
	 */
	public Pokedex(IPokemonMetadataProvider pokemonMetadataProvider,IPokemonFactory pokemonFactory){
		this.pokemonMetadataProvider = pokemonMetadataProvider;
		this.pokemonFactory = pokemonFactory;
		this.pokemons = new ArrayList<Pokemon>();
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
		pokemons.add(pokemon);
		return pokemons.indexOf(pokemon);
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		try{
			return pokemons.get(id);
		}catch(IndexOutOfBoundsException e1){
			throw new PokedexException("Not a valid pokedex identifier(array index out of bound)");
		}
	}

	@Override
	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		List<Pokemon> pokeList = this.getPokemons();
		Collections.sort(pokeList,order);
		return pokeList;
	}

}
