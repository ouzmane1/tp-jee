package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.dto.CreateLearn;
import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateLearn;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Attack;
import fr.efrei.pokemon.models.Learn;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.servies.AttackService;
import fr.efrei.pokemon.servies.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learns")
public class LearnController {

    private final LearnService learnService;

    @Autowired
    public LearnController(LearnService learnService) {
        this.learnService = learnService;
    }

    @GetMapping
    public ResponseEntity<List<Learn>> findAll() {
        return new ResponseEntity<>(learnService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Learn> findById(@PathVariable String id) {
        Learn learn = learnService.findById(id);
        if (learn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(learn, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateLearn learn) {
        learnService.save(learn);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateLearn learnBody) {
        Learn learn = learnService.findById(id);
        if (learn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        learnService.update(id, learnBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Learn learn = learnService.findById(id);
        if (learn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        learnService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
