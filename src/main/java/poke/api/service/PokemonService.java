package poke.api.service;

import org.springframework.stereotype.Service;
import poke.api.model.Pokemon;
import poke.api.repository.PokemonRepository;

import java.util.List;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> buscarTodos() {
        return pokemonRepository.findAll();
    }

    public Pokemon buscarPeloNome(String nome) {
        return pokemonRepository.findByNome(nome);
    }

    public void adicionar(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    public Pokemon removerPorId(Long id) {
        Pokemon pokemonParaRemover = pokemonRepository.findById(id).get();
        pokemonRepository.delete(pokemonParaRemover);
        return pokemonParaRemover;
    }

}
