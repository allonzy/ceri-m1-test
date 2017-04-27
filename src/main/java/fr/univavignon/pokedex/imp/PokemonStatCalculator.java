package fr.univavignon.pokedex.imp;
import fr.univavignon.pokedex.api.PokemonMetadata;

class PokemonStatCalculator {
	private PokemonMetadata pokemonMetadata;
	PokemonStatCalculator(PokemonMetadata pokemonMetadata,int hp,int cp){
		this.pokemonMetadata = pokemonMetadata;
	}
	public int generateAttack(){
		return pokemonMetadata.getAttack();
	}
	public int generateDefense(){
		return pokemonMetadata.getDefense();
	}
	public int generateStamina(){
		return pokemonMetadata.getStamina();
	}
	public double generateIV(int stamina,int attack, int defense){
		pokemonMetadata.getName();
		return -1;
	}
}
