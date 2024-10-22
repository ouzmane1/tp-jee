package fr.efrei.pokemon.servies;

import fr.efrei.pokemon.dto.CreateLearn;
import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateLearn;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Attack;
import fr.efrei.pokemon.models.Learn;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.repositories.AttackRepository;
import fr.efrei.pokemon.repositories.LearnRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LearnService {
    private final LearnRepository learnRepository;

    private final PokemonService pokemonService;
    private final AttackService attackService;

    public LearnService(LearnRepository learnRepository, PokemonService pokemonService,  @Lazy AttackService attackService){
        this.learnRepository = learnRepository;
        this.pokemonService = pokemonService;
        this.attackService = attackService;
    }

    public List<Learn> findAll(){
        return learnRepository.findAll();
    }

    public Learn findById(String id){
        return learnRepository.findById(id).orElse(null);
    }

    public void save(CreateLearn learnBody) {
        Learn learn = new Learn();
        learn.setNiveau(learnBody.getNiveau());
        // On récupère la liste des ids des pokemon du body postman
        List<String> pokemonIds = learnBody.getPokemon();
        // On déclare une nouvelle liste de pokemon
        List<Pokemon> pokemonAAjouter = new ArrayList<>();
        // pour chaque id de pokemon dans ma liste d'id
        for (String idPokemon: pokemonIds) {
            // je récupere le pokemon avec l'id courant
            Pokemon pokemon = pokemonService.findById(idPokemon);
            // si le pokemon existe
            if(pokemon != null) {
                // je l'ajoute a ma liste de pokemon
                pokemonAAjouter.add(pokemon);
            }
        }
        // j'applique la liste de pokemon au trainer que je créé
        learn.setPokemon(pokemonAAjouter);
        //Pour les attacks
        List<String> attackIds = learnBody.getAttack();
        List<Attack> attackAAjouter = new ArrayList<>();
        for (String idAttack: attackIds) {
            Attack attack = attackService.findById(idAttack);
            if(attack != null) {
                attackAAjouter.add(attack);
            }
        }
        // j'applique la liste de pokemon au trainer que je créé
        learn.setAttack(attackAAjouter);
        // pokemonIds.forEach(id -> pokemonService.findById(id));
        learnRepository.save(learn);
    }

    @Transactional
    public void update(String id, UpdateLearn learnBody) {
        Learn learn = findById(id);
        if (learnBody.getNiveau() != 0) {
            learn.setNiveau(learnBody.getNiveau());
        }
        if(learnBody.getPokemon() != null && !learnBody.getPokemon().isEmpty()) {
            List<Pokemon> pokemonList = new ArrayList<>();
            List<String> pokemonIds = learnBody.getPokemon();
            for(String idPokemon: pokemonIds) {
                Pokemon pokemon = pokemonService.findById(idPokemon);
                if(pokemon != null) {
                    pokemonList.add(pokemon);
                }
            }
            pokemonList.addAll(learn.getPokemon());
            learn.setPokemon(pokemonList);
        }
        if(learnBody.getAttack() != null && !learnBody.getAttack().isEmpty()) {
            List<Attack> attackList = new ArrayList<>();
            List<String> attackIds = learnBody.getAttack();
            for(String idAttack: attackIds) {
                Attack attack = attackService.findById(idAttack);
                if(attack != null) {
                    attackList.add(attack);
                }
            }
            attackList.addAll(learn.getAttack());
            learn.setAttack(attackList);
        }
        learnRepository.save(learn);
    }

    public void delete(String id) {
        learnRepository.deleteById(id);
    }
}
