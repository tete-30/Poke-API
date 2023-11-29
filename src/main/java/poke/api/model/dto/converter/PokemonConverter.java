package poke.api.model.dto.converter;

import poke.api.model.Pokemon;
import poke.api.model.dto.PokemonRequestDTO;

public class PokemonConverter {

    public static Pokemon converterParaEntidade(PokemonRequestDTO pokemonDto) {
        Pokemon pokemonEntity = new Pokemon();

        pokemonEntity.setNome(pokemonDto.getNome());
        pokemonEntity.setTipo(pokemonDto.getTipo());
        pokemonEntity.setAltura(pokemonDto.getAltura());
        pokemonEntity.setPeso(pokemonDto.getPeso());
        pokemonEntity.setLevel(pokemonDto.getLevel());

        return pokemonEntity;
    }
}
