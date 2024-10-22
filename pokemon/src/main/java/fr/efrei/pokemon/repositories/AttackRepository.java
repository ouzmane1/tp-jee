package fr.efrei.pokemon.repositories;

import fr.efrei.pokemon.models.Attack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttackRepository extends JpaRepository<Attack, String> {

}
