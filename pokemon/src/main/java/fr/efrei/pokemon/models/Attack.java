package fr.efrei.pokemon.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private int puissance;

    private int point;

    @OneToMany
    private List<Learn> learn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public List<Learn> getLearn() {
        return learn;
    }

    public void setLearn(List<Learn> learn) {
        this.learn = learn;
    }
}
