package fr.efrei.pokemon.servies;

import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.repositories.PokemonRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    public final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    // Le chemin
    // Base de données -> Entity -> Repository -> Service -> Controller
    public List<Pokemon> findAll(){
        // comme select* from pokemon
        return pokemonRepository.findAll();
    }

    public Pokemon findById(String id){
        //comme select* from pokemon where id= :id
        return pokemonRepository.findById(id).orElse(null);
    }

    // Controller -> Service -> Repository -> Entité -> BDD
    public void save(Pokemon pokemon){
        //comme insert into pokemon values(:nom, :level, :type)
        pokemonRepository.save(pokemon);
    }

    public void delete(String id){
        pokemonRepository.deleteById(id);
    }

    public void update(String id, Pokemon pokemonBody){
        Pokemon pokemonModify = findById(id);
        pokemonModify.setLevel(pokemonBody.getLevel());
        pokemonModify.setName(pokemonBody.getName());
        pokemonModify.setType(pokemonBody.getType());

        // insertion apres modification
        pokemonRepository.save(pokemonModify);
    }

    public void partialUpdate(String id, Pokemon pokemonBody){
        Pokemon pokemonModify = findById(id);
        if (pokemonBody.getType() != null){
            pokemonModify.setType(pokemonBody.getType());
        }
        if (pokemonBody.getName() != null){
            pokemonModify.setName(pokemonBody.getName());
        }
        if (pokemonBody.getLevel() != 0){
            pokemonModify.setLevel(pokemonBody.getLevel());
        }

        pokemonRepository.save(pokemonModify);
    }
}
