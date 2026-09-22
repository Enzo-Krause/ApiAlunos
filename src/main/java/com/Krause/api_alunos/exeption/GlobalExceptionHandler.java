package com.Krause.api_alunos.exeption;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> handleAlunoNaoEncontradoException(AlunoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroResposta(404, ex.getMessage(), Instant.now()));
    }

    @ExceptionHandler(AlunoEmailJaCadastrado.class)
    public ResponseEntity<ErroResposta> handleAlunoEmailJaCadastrado(AlunoEmailJaCadastrado ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroResposta(409, ex.getMessage(), Instant.now()));
    }
}
