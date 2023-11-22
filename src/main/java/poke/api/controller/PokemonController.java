package poke.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poke.api.integration.response.PokemonResponse;
import poke.api.integration.service.PokemonIntegrationService;
import poke.api.model.Pokemon;
import poke.api.service.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    private PokemonService pokemonService;
    private PokemonIntegrationService pokemonIntegrationService;


    //Construtor da Classe
    public PokemonController(PokemonService pokemonService, PokemonIntegrationService pokemonIntegrationService) {
        this.pokemonService = pokemonService;
        this.pokemonIntegrationService = pokemonIntegrationService;

    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> buscarTodosPokemons(){
        return ResponseEntity.ok(this.pokemonService.buscarTodos());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Pokemon> buscarPokemonPeloNome(@PathVariable("nome") String nome){
        Pokemon pokemonBuscado = this.pokemonService.buscarPeloNome(nome);
        return ResponseEntity.ok(pokemonBuscado);
    }

    @GetMapping("/api-externa/{nome}")
    public ResponseEntity<PokemonResponse> buscarPokemonNoServicoExterno(@PathVariable("nome") String nome) {
        PokemonResponse pokemonsBuscadoServicoExterno =
                this.pokemonIntegrationService.buscarPokemonNoServicoExternoPeloNome(nome);
        return ResponseEntity.ok(pokemonsBuscadoServicoExterno);
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
    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Pokemon> removerPokemonPorNome(@PathVariable("nome") String nome) {
       return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> alterarNomeTipo(@PathVariable Long id, @RequestBody Pokemon pokemonAtualizado) {
        Pokemon pokemonAlterado = pokemonService.alterarNomeETipo(id, pokemonAtualizado);
        return ResponseEntity.ok(pokemonAlterado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> buscarPokemonPorId(@PathVariable("id") Long id) {
        Pokemon pokemonBuscado = this.pokemonService.buscarPorId(id);
        return ResponseEntity.ok(pokemonBuscado);
    }


}
