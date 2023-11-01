package poke.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poke.api.model.Pokemon;
import poke.api.service.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    private PokemonService pokemonService;

    //Construtor da Classe
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> buscarTodosPokemons(){
        return ResponseEntity.ok(this.pokemonService.buscarTodos());
    }

    @GetMapping({"/nome"})
    public ResponseEntity<Pokemon> buscarPokemonPeloNome(String nome){
        Pokemon pokemonBuscado = this.pokemonService.buscarPeloNome(nome);
        return ResponseEntity.ok(pokemonBuscado);
    }

    @PostMapping
    public ResponseEntity<Void> adicionarPokemon (@RequestBody Pokemon pokemon) {
        pokemonService.adicionar(pokemon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> removerPokemonPorId(@PathVariable("id") Long id) {
        Pokemon pokemonRemovido = pokemonService.removerPorId(id);
        return ResponseEntity.ok(pokemonRemovido);
    }

}
