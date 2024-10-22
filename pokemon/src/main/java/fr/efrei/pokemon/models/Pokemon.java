package fr.efrei.pokemon.models;

import fr.efrei.pokemon.contents.Type;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private int level;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany
    private List<Learn> learn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

