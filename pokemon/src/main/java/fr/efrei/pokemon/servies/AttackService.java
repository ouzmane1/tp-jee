package fr.efrei.pokemon.servies;

import fr.efrei.pokemon.dto.CreateAttack;
import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateAttack;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Attack;
import fr.efrei.pokemon.models.Learn;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.repositories.AttackRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttackService {

    private final AttackRepository attackRepository;

    private final LearnService learnService;

    public AttackService(AttackRepository attackRepository,  @Lazy LearnService learnService){
        this.attackRepository = attackRepository;
        this.learnService = learnService;
    }

    public List<Attack> findAll(){
        return attackRepository.findAll();
    }

    public Attack findById(String id){
        return attackRepository.findById(id).orElse(null);
    }

    public void save(CreateAttack attackBody) {
        Attack attack = new Attack();
        attack.setName(attackBody.getName());
        attack.setPoint(attackBody.getPoint());
        attack.setPuissance(attackBody.getPuissance());
        // On récupère la liste des ids des pokemon du body postman
        List<String> learnIds = attackBody.getLearn();
        // On déclare une nouvelle liste de pokemon
        List<Learn> learnAAjouter = new ArrayList<>();
        // pour chaque id de pokemon dans ma liste d'id
        for (String idLearn: learnIds) {
            // je récupere le pokemon avec l'id courant
            Learn learn = learnService.findById(idLearn);
            // si le pokemon existe
            if(learn != null) {
                // je l'ajoute a ma liste de pokemon
                learnAAjouter.add(learn);
            }
        }
        // j'applique la liste de pokemon au trainer que je créé
        attack.setLearn(learnAAjouter);
        // pokemonIds.forEach(id -> pokemonService.findById(id));
        attackRepository.save(attack);
    }

    @Transactional
    public void update(String id, UpdateAttack attackBody) {
        Attack attack = findById(id);
        if (attackBody.getName() != null) {
            attack.setName(attackBody.getName());
        }
        if (attackBody.getPoint() != 0) {
            attack.setPoint(attackBody.getPoint());
        }
        if (attackBody.getPuissance() != 0) {
            attack.setPuissance(attackBody.getPuissance());
        }
        if(attackBody.getLearn() != null && !attackBody.getLearn().isEmpty()) {
            List<Learn> learnList = new ArrayList<>();
            List<String> learnIds = attackBody.getLearn();
            for(String idLearn: learnIds) {
                Learn learn = learnService.findById(idLearn);
                if(learn != null) {
                    learnList.add(learn);
                }
            }
            learnList.addAll(attack.getLearn());
            attack.setLearn(learnList);
        }
        attackRepository.save(attack);
    }

    public void delete(String id) {
        attackRepository.deleteById(id);
    }
}
