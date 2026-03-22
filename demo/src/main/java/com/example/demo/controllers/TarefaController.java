package com.example.demo.controllers;

import com.example.demo.models.TarefaModel;
import com.example.demo.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaModel>> listarTarefa(){
        List<TarefaModel> lista = tarefaService.listarTarefa();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public Optional<TarefaModel> buscarId(@PathVariable Long id){
        return tarefaService.buscarId(id);
    }

    @PostMapping
    public ResponseEntity<TarefaModel> criarTarefa(@RequestBody TarefaModel tarefaModel){
        TarefaModel nova = tarefaService.criarTarefa(tarefaModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nova.getId())
                .toUri();

        return ResponseEntity.created(uri).body(nova);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id){
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaModel> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaModel tarefaModel){
        TarefaModel atualizada = tarefaService.atualizarTarefa(id, tarefaModel);
        return ResponseEntity.ok().body(atualizada);
    }
}