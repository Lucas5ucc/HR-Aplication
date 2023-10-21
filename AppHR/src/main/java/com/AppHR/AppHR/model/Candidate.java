package com.AppHR.AppHR.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Candidate {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String rg;
    @NotEmpty
    private String nameCandidate;

    @NotEmpty
    private String email;

    @ManyToOne
    private Vaga vaga;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNameCandidate() {
        return nameCandidate;
    }

    public void setNameCandidate(String nameCandidate) {
        this.nameCandidate = nameCandidate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
