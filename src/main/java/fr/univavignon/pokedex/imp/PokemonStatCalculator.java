package fr.univavignon.pokedex.imp;
import fr.univavignon.pokedex.api.PokemonMetadata;

class PokemonStatCalculator {
	private PokemonMetadata pokemonMetadata;
	private int hp;
	private int cp;
	PokemonStatCalculator(PokemonMetadata pokemonMetadata,int hp,int cp){
		this.pokemonMetadata = pokemonMetadata;
		this.hp = hp;
		this.cp = cp;
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
	public int generateIV(int attack, int defense){
		return -1;
	}
}
