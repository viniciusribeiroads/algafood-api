package com.algaworks.algafood.infraestructure.repository;

import com.algaworks.algafood.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T,ID> extends SimpleJpaRepository<T,ID>
        implements CustomJpaRepository<T,ID> {

    private EntityManager em;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T,?> entityInformation, EntityManager em) {
        super(entityInformation, em);

        this.em = em;
    }


    @Override
    public Optional<T> buscarPrimeiro() {
        var jpql = "from " + getDomainClass().getName();

        T entity = em.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();

        return Optional.ofNullable(entity);
    }
}
