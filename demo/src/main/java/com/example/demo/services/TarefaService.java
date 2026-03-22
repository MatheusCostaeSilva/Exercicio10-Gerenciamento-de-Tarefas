package com.example.demo.services;

import com.example.demo.models.TarefaModel;
import com.example.demo.repositories.TarefaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    public static final Logger log = LoggerFactory.getLogger(TarefaService.class.getName());

    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaModel criarTarefa(TarefaModel tarefaModel) {
        return tarefaRepository.save(tarefaModel);
    }

    public List<TarefaModel> listarTarefa() {
        return tarefaRepository.findAll();
    }

    public Optional<TarefaModel> buscarId(Long id) {
        return tarefaRepository.findById(id);
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    public TarefaModel atualizarTarefa(Long id, TarefaModel tarefaModel) {
        TarefaModel newTarefa = tarefaRepository.findById(id).get();
        newTarefa.setDescricao(tarefaModel.getDescricao());
        newTarefa.setDataVencimento(tarefaModel.getDataVencimento());
        newTarefa.setConcluida(tarefaModel.getConcluida());
        return tarefaRepository.save(newTarefa);
    }
}
