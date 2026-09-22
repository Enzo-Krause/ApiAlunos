package com.Krause.api_alunos.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Krause.api_alunos.dto.AlunoRequest;
import com.Krause.api_alunos.dto.AlunoResposta;
import com.Krause.api_alunos.exeption.AlunoEmailJaCadastrado;
import com.Krause.api_alunos.exeption.AlunoNaoEncontradoException;
import com.Krause.api_alunos.model.Aluno;

@Service
public class AlunoService {

    private final List<Aluno> alunos;
    private int id = 11;

    public AlunoService() {
        alunos = new ArrayList<>();

        alunos.add(new Aluno(1, "Enzo", "enzo@gmail.com", "06042010", LocalDate.of(2010, Month.APRIL, 6), 8.5));
        alunos.add(new Aluno(2, "Yuri", "yuri@gmail.com", "123456", LocalDate.of(2007, 5, 15), 9.5));
        alunos.add(new Aluno(3, "Willian", "Willian@gmail.com", "654321", LocalDate.of(2009, 8, 22), 5.2));
        alunos.add(new Aluno(4, "Carlos", "carlos@gmail.com", "789456", LocalDate.of(2007, 3, 12), 7.8));
        alunos.add(new Aluno(5, "Ana", "ana@gmail.com", "321654", LocalDate.of(2008, 7, 25), 9.0));
        alunos.add(new Aluno(6, "Pedro", "pedro@gmail.com", "987123", LocalDate.of(2009, 11, 8), 6.5));
        alunos.add(new Aluno(7, "Lucas", "lucas@gmail.com", "456789", LocalDate.of(2006, 1, 30), 8.7));
        alunos.add(new Aluno(8, "Julia", "julia@gmail.com", "159753", LocalDate.of(2008, 9, 17), 9.5));
        alunos.add(new Aluno(9, "Sofia", "sofia@gmail.com", "258741", LocalDate.of(2008, 6, 14), 8.9));
        alunos.add(new Aluno(10, "Agata", "agata@gmail.com", "369852", LocalDate.of(2009, 10, 3), 9.3));
    }

    public List<AlunoResposta> listarAlunos() {
        List<AlunoResposta> alunosResposta = new ArrayList<>();

        for (Aluno aluno : alunos) {
            alunosResposta.add(new AlunoResposta(aluno.getId(), aluno.getNome(), aluno.getEmail(),
                    aluno.getDataNascimento(), aluno.getMedia()));
        }

        return alunosResposta;
    }

    public AlunoResposta obterAlunoPorId(int id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                return new AlunoResposta(aluno.getId(), aluno.getNome(), aluno.getEmail(),
                        aluno.getDataNascimento(), aluno.getMedia());
            }
        }

        throw new AlunoNaoEncontradoException("Aluno não encontrado");
    }

    public AlunoResposta cadastrarAluno(AlunoRequest request) {
        for (Aluno aluno : alunos) {
            if (aluno.getEmail().equals(request.getEmail())) {
                throw new AlunoEmailJaCadastrado("Email já existente");
            }
        }

        Aluno novoAluno = new Aluno(id, request.getNome(), request.getEmail(), request.getSenha(),
                request.getDataNascimento(), request.getMedia());

        alunos.add(novoAluno);
        id++;

        return new AlunoResposta(novoAluno.getId(), novoAluno.getNome(), novoAluno.getEmail(),
                novoAluno.getDataNascimento(), novoAluno.getMedia());
    }

    public AlunoResposta atualizarAluno(int id, AlunoRequest request) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                for (Aluno outroAluno : alunos) {
                    if (outroAluno.getId() != id && outroAluno.getEmail().equals(request.getEmail())) {
                        throw new AlunoEmailJaCadastrado("Email já existente");
                    }
                }

                aluno.setNome(request.getNome());
                aluno.setEmail(request.getEmail());
                aluno.setSenha(request.getSenha());
                aluno.setDataNascimento(request.getDataNascimento());
                aluno.setMedia(request.getMedia());

                return new AlunoResposta(aluno.getId(), aluno.getNome(), aluno.getEmail(),
                        aluno.getDataNascimento(), aluno.getMedia());
            }
        }

        throw new AlunoNaoEncontradoException("Aluno não encontrado");
    }

    public void excluirAluno(int id) {
        boolean removido = alunos.removeIf(aluno -> aluno.getId() == id);

        if (!removido) {
            throw new AlunoNaoEncontradoException("Aluno não encontrado");
        }
    }
}
