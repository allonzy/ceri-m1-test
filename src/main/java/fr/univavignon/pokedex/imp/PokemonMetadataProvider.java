package fr.univavignon.pokedex.imp;
import java.io.IOException;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fr.univavignon.pokedex.api.*;
public class PokemonMetadataProvider implements IPokemonMetadataProvider{

	/**
	 * this URL is a link to a JSON resource containing all the data of pokemon in pokemon GO
	 * if changed check {@link #create} for naming change, 
	 * and all the code for data type change
	 */
	private static final String RESSOURCE_URL = "https://raw.githubusercontent.com/PokemonGoF/PokemonGo-Bot/master/data/pokemon.json";
	/**
	 * The language of the API
	 */
	private static final Locale LOCALE = Locale.ENGLISH;
	public static Locale getLocale(){
		return LOCALE;
	}

	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		try {
			JsonObject object = getResource(index);
			return create(object);
		} catch (IOException e) {
			//LOG the error
			throw new PokedexException("ressource not available");
		}
	}
	/**
	 * change for mapping with new value if {@link #RESSOURCE_URL} change
	 * @param oldObject
	 * @return
	 */
	private PokemonMetadata create(JsonObject object){
		int index = Integer.parseInt(object.get("Number").getAsString());
		String name = object.get("Name").getAsString();
		int attack = object.get("BaseAttack").getAsInt();
		int defense = object.get("BaseDefense").getAsInt();
		int stamina = object.get("BaseStamina").getAsInt();
		PokemonMetadata pokemonMetadata = new PokemonMetadata(index,name,attack,defense,stamina);
		return pokemonMetadata;
	}
	private JsonObject getResource(int index) throws IOException{
		JsonArray resource = JsonResourceProvider.getJsonFrom(RESSOURCE_URL).getAsJsonArray();
		return resource.get(index-1).getAsJsonObject();
	}
}
