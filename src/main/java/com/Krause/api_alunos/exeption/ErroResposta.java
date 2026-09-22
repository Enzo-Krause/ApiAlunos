package com.Krause.api_alunos.exeption;

import java.time.Instant;

public record ErroResposta(int status, String mensagem, Instant timestamp) {
}
