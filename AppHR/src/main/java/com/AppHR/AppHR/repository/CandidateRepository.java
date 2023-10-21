package com.AppHR.AppHR.repository;

import com.AppHR.AppHR.model.Candidate;
import com.AppHR.AppHR.model.Vaga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate, String> {
    Iterable<Candidate>findByVaga(Vaga vaga);
    Candidate findById(long id);
    Candidate findByRg(String rg);
    List<Candidate>findByNameCandidate(String candidateName);
}
