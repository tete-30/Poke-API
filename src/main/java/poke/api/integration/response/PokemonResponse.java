package poke.api.integration.response;

import lombok.Data;

import java.util.List;
import java.util.Locale;

@Data
public class PokemonResponse {

    private int id;

    private String name;

    //Altura
    private int height;

    //Peso
    private int weight;

    private List<MovesResponse> moves;

    private SpritesResponse sprites;


}
