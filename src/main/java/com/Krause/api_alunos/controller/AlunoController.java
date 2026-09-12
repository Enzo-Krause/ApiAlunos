package com.Krause.api_alunos.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Krause.api_alunos.dto.AlunoResposta;
import com.Krause.api_alunos.service.AlunoService;

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

	@RequestMapping("/{id}")
	public AlunoResposta obterAlunoPorId(@PathVariable int id) {
		return service.obterAlunoPorId(id);
	}

}
