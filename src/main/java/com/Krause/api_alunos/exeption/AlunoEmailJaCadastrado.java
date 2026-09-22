package com.Krause.api_alunos.exeption;

public class AlunoEmailJaCadastrado extends RuntimeException {

    public AlunoEmailJaCadastrado(String mensagemDeEmail) {
        super(mensagemDeEmail);
    }
}
