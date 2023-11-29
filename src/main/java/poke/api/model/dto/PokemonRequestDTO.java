package poke.api.model.dto;

import lombok.Data;

@Data
public class PokemonRequestDTO {

    private String nome;
    private String tipo;
    private double altura;
    private double peso;
    private int level;




}
