package com.algaworks.algafood.api;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.repository.CozinhaRepository;
import com.algaworks.algafood.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.algaworks.algafood.infraestructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infraestructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restaurante;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }

    @GetMapping("/restaurante/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restaurante.findByTaxaFreteBetween(taxaInicial,taxaFinal);
    }

    @GetMapping("/restaurante/com-frete-gratis")
    public List<Restaurante> restauranteComFreteGratis(String nome) {

        return restaurante.findComFreteGratis(nome);
    }

    @GetMapping("/restaurante/por-nome-e-idcozinha")
    public List<Restaurante> buscarPorNomeEIdCozinha(String nome, Long idCozinha) {
        return restaurante.consultarPorNome(nome,idCozinha);
    }

    @GetMapping("/primeiro-restaurante")
    public Restaurante buscarPrimeiroRestaurante(){
        return restaurante.buscarPrimeiro().get();
    }
}
