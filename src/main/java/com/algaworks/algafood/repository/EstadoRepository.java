package com.algaworks.algafood.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    //List<Estado> listar();

}
