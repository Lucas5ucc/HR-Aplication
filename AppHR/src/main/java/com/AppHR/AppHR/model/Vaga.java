package com.AppHR.AppHR.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
@Entity
public class Vaga implements Serializable { // Vaga

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // eu mudei o codigo, se correr mal no futuro olhar aqui primeiro
    private long code;
    @NotEmpty
    private String role;    //nome

    @NotEmpty
    private String description;

    @NotEmpty
    private String data;

    @NotEmpty
    private Integer salary;

    @OneToMany(mappedBy = "vaga",cascade = CascadeType.REMOVE)
    private List<Candidate> candidateList;

    public long getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public String getData() {
        return data;
    }

    public Integer getSalary() {
        return salary;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setCandidateList(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }
}
