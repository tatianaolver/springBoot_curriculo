package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Curriculo;
import com.example.demo.repository.curriculoRepository;

import java.util.List;

@RestController
@RequestMapping("/curriculos")
public class CurriculoController {

    @Autowired
    private curriculoRepository curriculoRepository;

    @GetMapping(value = "/listar")
    public List<Curriculo> getAllCurriculos() {
        return curriculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Curriculo getCurriculoById(@PathVariable Long id) {
        return curriculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currículo não encontrado com o ID: " + id));
    }

    @PostMapping
    public Curriculo createCurriculo(@RequestBody Curriculo Curriculo) {
        return curriculoRepository.save(Curriculo);
    }

    @PutMapping("/{id}")
    public Curriculo updateCurriculo(@PathVariable Long id, @RequestBody Curriculo updatedCurriculo) {
        Curriculo existingCurriculo = curriculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currículo não encontrado com o ID: " + id));

        existingCurriculo.setNome(updatedCurriculo.getNome());
        existingCurriculo.setSobrenome(updatedCurriculo.getSobrenome());
        existingCurriculo.setEndereco(updatedCurriculo.getEndereco());
        existingCurriculo.setTelefone(updatedCurriculo.getTelefone());
        existingCurriculo.setEmail(updatedCurriculo.getEmail());
        // Atualize outros campos conforme necessário

        return curriculoRepository.save(existingCurriculo);
    }

    @DeleteMapping("/{id}")
    public void deleteCurriculo(@PathVariable Long id) {
        curriculoRepository.deleteById(id);
    }
}
