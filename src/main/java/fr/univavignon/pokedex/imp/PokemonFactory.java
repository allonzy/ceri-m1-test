package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonFactory implements IPokemonFactory {

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		try{
			PokemonMetadata pokemonMetadata = getMetadata(index);
			PokemonStatCalculator pokemonStatCalculator = new PokemonStatCalculator(pokemonMetadata,hp,cp);
			int attack = pokemonStatCalculator.generateAttack();
			int defense = pokemonStatCalculator.generateDefense();
			return new Pokemon(
					index,												// index Pokemon index.
					pokemonMetadata.getName(),							// name Pokemon name.
					attack,												// attack Attack level.
					defense,											// defense Defense level.
					pokemonStatCalculator.generateStamina(), 			// stamina Stamina level.
					cp,													// cp Pokemon cp.
					hp,													// hp Pokemon hp.
					dust,												// dust Required dust for upgrading this pokemon.
					candy,												// candy Required candy for upgrading this pokemon.
					pokemonStatCalculator.generateIV(attack,defense)	// iv IV perfection percentage.
					);
		}catch(PokedexException e){
			//LOG the error
			return null;
		}

	}
	private static PokemonMetadata getMetadata(int index) throws PokedexException{
		IPokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
		return pokemonMetadataProvider.getPokemonMetadata(index);
	}
}
