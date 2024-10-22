package fr.efrei.pokemon.dto;

import java.util.List;

public class UpdateAttack {

    private String name;

    private List<String> learn;

    private int puissance;

    private int point;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLearn() {
        return learn;
    }

    public void setLearn(List<String> learn) {
        this.learn = learn;
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
}
