package com.example.gyakorlat.hulladekSzallitas;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Naptar {
    public Naptar() {}
    public Naptar(Date datum, Integer szolgid) {
        this.datum = datum;
        this.setSzolgid(szolgid);
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getSzolgid() {
        if (szolgaltatas != null) {
            return szolgaltatas.getId();
        }
        return 0;
    }

    public void setSzolgid(int szolgid) {
        if (this.szolgaltatas == null) {
            this.szolgaltatas = new Szolgaltatas();
        }
        this.szolgaltatas.setId(szolgid);
    }

    public Szolgaltatas getSzolgaltatas() {
        return szolgaltatas;
    }

    public void setSzolgaltatas(Szolgaltatas szolgaltatas) {
        this.szolgaltatas = szolgaltatas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long azon;

    private Date datum;
    @ManyToOne
    @JoinColumn(name = "szolgid")
    private Szolgaltatas szolgaltatas;

}
