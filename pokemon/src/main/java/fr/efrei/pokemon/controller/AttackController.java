package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.dto.CreateAttack;
import fr.efrei.pokemon.dto.UpdateAttack;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Attack;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.servies.AttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attacks")
public class AttackController {

    private final AttackService attackService;

    @Autowired
    public AttackController(AttackService attackService) {
        this.attackService = attackService;
    }

    @GetMapping
    public ResponseEntity<List<Attack>> findAll() {
        return new ResponseEntity<>(attackService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attack> findById(@PathVariable String id) {
        Attack attack = attackService.findById(id);
        if (attack == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(attack, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateAttack attack){
        attackService.save(attack);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateAttack attackBody) {
        Attack attack = attackService.findById(id);
        if (attack == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        attackService.update(id, attackBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Attack attack = attackService.findById(id);
        if (attack == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        attackService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
