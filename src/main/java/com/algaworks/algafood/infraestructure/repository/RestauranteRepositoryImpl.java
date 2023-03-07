package com.algaworks.algafood.infraestructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.repository.RestauranteRepository;
import com.algaworks.algafood.repository.RestauranteRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.algaworks.algafood.infraestructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infraestructure.repository.spec.RestauranteSpecs.comNomeSemelhante;
import static java.util.Objects.nonNull;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager em;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var jpql = new StringBuilder();
        jpql.append("from Restaurante where 0=0 ");

        var parametros = new HashMap<String, Object>();

        if (nonNull(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", nome);
        }
        if (nonNull(taxaFreteInicial)) {
            jpql.append("and taxa_frete >= :taxaFreteInicial ");
            parametros.put("taxaFreteInicial", taxaFreteInicial);
        }
        if (nonNull(taxaFreteFinal)) {
            jpql.append("and taxa_frete <= :taxaFreteFinal ");
            parametros.put("taxaFreteFinal", taxaFreteFinal);
        }


        TypedQuery<Restaurante> query = em
                .createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
    }

    public List<Restaurante> findAll(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class);

        var predicatesList = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)) {
            predicatesList.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }
        if (nonNull(taxaFreteInicial)) {
            predicatesList.add(builder
                    .greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }
        if (nonNull(taxaFreteFinal)) {
            predicatesList.add(builder
                    .lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }


        criteria.where(predicatesList.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = em
                .createQuery(criteria);


        return query.getResultList();
    }
}
