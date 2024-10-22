package fr.efrei.pokemon.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Learn {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int niveau;

    @OneToMany
    private List<Pokemon> pokemon;

    @OneToMany
    private List<Attack> attack;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }


    public List<Attack> getAttack() {
        return attack;
    }

    public void setAttack(List<Attack> attack) {
        this.attack = attack;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
