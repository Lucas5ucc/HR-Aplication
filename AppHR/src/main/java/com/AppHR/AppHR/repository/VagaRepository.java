package com.AppHR.AppHR.repository;

import com.AppHR.AppHR.model.Vaga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VagaRepository extends CrudRepository<Vaga, String> {
    Vaga findByCode(long code);

}
