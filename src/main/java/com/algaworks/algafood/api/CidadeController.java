package com.algaworks.algafood.api;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CidadeService;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPor(@PathVariable Long id, Cidade cidade) {
        try {
            return ResponseEntity.ok(cidadeService.buscarPor(id));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.salvar(cidade));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> atualizar(@PathVariable Long id, Cidade cidade) {
//        Cidade cidadeAtual = cidadeService
//                .buscarOuFalhar(cidade.getId());
//        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
//        try {
//            cidadeService.salvar(cidadeAtual);
//            return ResponseEntity.ok(cidadeAtual);
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeService
                .buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        try {
            return cidadeService.salvar(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
