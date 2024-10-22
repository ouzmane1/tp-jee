package fr.efrei.pokemon.repositories;

import fr.efrei.pokemon.models.Learn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnRepository extends JpaRepository<Learn, String> {
}
