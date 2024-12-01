package com.example.eloadas.hulladekSzallitas;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Szolgaltatas {
    public Szolgaltatas() {}
    public Szolgaltatas(Integer id, String tipus, String jelentes) {
        this.id = id;
        this.tipus = tipus;
        this.jelentes = jelentes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getJelentes() {
        return jelentes;
    }

    public void setJelentes(String jelentes) {
        this.jelentes = jelentes;
    }

    @Id
    private Integer id;

    private String tipus;
    private String jelentes;

}
