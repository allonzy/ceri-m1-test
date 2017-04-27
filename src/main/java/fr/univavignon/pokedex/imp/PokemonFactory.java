package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonFactory implements IPokemonFactory {
	private IPokemonMetadataProvider pokemonMetadataProvider;
	public PokemonFactory(IPokemonMetadataProvider pokemonMetadataProvider){
		this.pokemonMetadataProvider = pokemonMetadataProvider;
	}
	public PokemonFactory(){
		this.pokemonMetadataProvider = new PokemonMetadataProvider();
	}
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		try{
			PokemonMetadata pokemonMetadata = getMetadata(index);
			PokemonStatCalculator pokemonStatCalculator = new PokemonStatCalculator(pokemonMetadata,hp,cp);
			int attack = pokemonStatCalculator.generateAttack();
			int defense = pokemonStatCalculator.generateDefense();
			int stamina = pokemonStatCalculator.generateStamina();
			return new Pokemon(
					index,												// index Pokemon index.
					pokemonMetadata.getName(),							// name Pokemon name.
					attack,												// attack Attack level.
					defense,											// defense Defense level.
					stamina, 											// stamina Stamina level.
					cp,													// cp Pokemon cp.
					hp,													// hp Pokemon hp.
					dust,												// dust Required dust for upgrading this pokemon.
					candy,												// candy Required candy for upgrading this pokemon.
					pokemonStatCalculator.generateIV(stamina,attack,defense)	// iv IV perfection percentage.
					);
		}catch(PokedexException e){
			return null;
		}

	}
	private PokemonMetadata getMetadata(int index) throws PokedexException{
		return pokemonMetadataProvider.getPokemonMetadata(index);
	}
}
