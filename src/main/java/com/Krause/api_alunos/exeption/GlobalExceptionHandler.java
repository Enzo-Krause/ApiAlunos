package com.Krause.api_alunos.exeption;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<ErroResposta> AlunoEmailJaCadastrado(AlunoEmailJaCadastrado ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroResposta(409, ex.getMessage(), Instant.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroAtributoResposta> handleValidacaoRequest(MethodArgumentNotValidException ex) {
        List<ErroAtributo> erroAtributo = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> new ErroAtributo(erro.getField(), erro.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroAtributoResposta(400, "Erro de verificaçao", Instant.now(), erroAtributo));
    }
}
