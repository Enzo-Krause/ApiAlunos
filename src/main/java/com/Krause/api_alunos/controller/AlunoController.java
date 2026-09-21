package com.Krause.api_alunos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Krause.api_alunos.dto.AlunoRequest;
import com.Krause.api_alunos.dto.AlunoResposta;
import com.Krause.api_alunos.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public List<AlunoResposta> listarAlunos() {
        return service.listarAlunos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResposta> obterAlunoPorId(
            @PathVariable int id) {

        return ResponseEntity.ok(service.obterAlunoPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlunoResposta> cadastrarAluno(
            @Valid @RequestBody AlunoRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.cadastrarAluno(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResposta> atualizarAluno(
            @Valid @RequestBody AlunoRequest request,
            @PathVariable int id) {

        return ResponseEntity.ok(service.atualizarAluno(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAluno(
            @PathVariable int id) {

        service.excluirAluno(id);

        return ResponseEntity.noContent().build();
    }
}
