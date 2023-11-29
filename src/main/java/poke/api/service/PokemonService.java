package poke.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import poke.api.model.Pokemon;
import poke.api.model.dto.PokemonRequestDTO;
import poke.api.model.dto.converter.PokemonConverter;
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

    public void adicionar(PokemonRequestDTO pokemonRequestDTO) {
        String nomePokemonAdicionado = pokemonRequestDTO.getNome();
        Pokemon pokemonExistente = buscarPeloNome(nomePokemonAdicionado);


        //Verificar se pokemon ja existe no banco de dados
        if (pokemonExistente != null) {
            //Retornar HTTP Status code 400 com mensagem explicativa
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel adicionar um pokemon repetido. O pokemon "
                    + nomePokemonAdicionado + " ja existe no banco de dados.");
        } else {
            Pokemon pokemonEntity = PokemonConverter.converterParaEntidade(pokemonRequestDTO);
            pokemonRepository.save(pokemonEntity);
        }
    }

    public Pokemon removerPorId(Long id) {
        Pokemon pokemonParaRemover = pokemonRepository.findById(id).get();
        pokemonRepository.delete(pokemonParaRemover);
        return pokemonParaRemover;
    }

    public boolean existePokemon(Long id) {
        return pokemonRepository.existsById(id);
    }

    public Pokemon buscarPorId(Long id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon alterarNomeETipo(Long id, Pokemon pokemon) {
        if (existePokemon(id)) {
            Pokemon pokemonParaAlterar = buscarPorId(id);

            pokemonParaAlterar.setNome(pokemon.getNome());
            pokemonParaAlterar.setTipo(pokemon.getTipo());
            pokemonRepository.save(pokemonParaAlterar);

            return pokemonParaAlterar;
        }
        return null;
    }


    public Pokemon removerPorNome(String nome) {
        Pokemon pokemonRemoverPorNome = pokemonRepository.findByNome(nome);
        if (pokemonRemoverPorNome == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel remover um pokemon inexistente. O pokemon " + nome + " não existe no banco de dados.");
        } else {
            pokemonRepository.delete(pokemonRemoverPorNome);

        }
        return pokemonRemoverPorNome;
    }

    public Long contar(){
        return pokemonRepository.count();
    }
}

