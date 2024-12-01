package com.example.gyakorlat.hulladekSzallitas;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Lakig {
    public Lakig() {}
    public Lakig(Date igeny, Integer szolgid, Integer mennyiseg) {
        this.igeny = igeny;
        this.setSzolgid(szolgid);
        this.mennyiseg = mennyiseg;
    }

    public Long getAzon() {
        return azon;
    }

    public void setAzon(Long azon) {
        this.azon = azon;
    }

    public Date getIgeny() {
        return igeny;
    }

    public void setIgeny(Date igeny) {
        this.igeny = igeny;
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

    public Integer getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(Integer mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long azon;

    private Date igeny;
    @ManyToOne
    @JoinColumn(name = "szolgid")
    private Szolgaltatas szolgaltatas;
    private Integer mennyiseg;
}
