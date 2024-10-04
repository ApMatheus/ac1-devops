package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Aluno;
import com.example.demo.model.Curso;
import com.example.demo.model.PlataformaCursos;

import static org.junit.jupiter.api.Assertions.*;


class PlataformaCursosTest {

    private PlataformaCursos plataforma;

    @BeforeEach
    void setUp() {
        plataforma = new PlataformaCursos();
    }

    @Test
    void alunoDeveSeTornarPremiumAoConcluir12CursosAposAutenticacao() {
    	//cenario
        Aluno aluno = new Aluno("João", "senha123");
        
        //execucao
        plataforma.adicionarAluno(aluno);
        try {
        boolean logado = plataforma.autenticarAluno("João", "senha123");
        assertTrue(logado);
        }catch(UnsupportedOperationException e) {
        	fail();
        }
        for (int i = 1; i <= 12; i++) {
            plataforma.adicionarCursoParaAluno("João", "Curso " + i);
            plataforma.alunoConcluiCurso("João", "Curso " + i, 7.5);
        }
        //resultados
        assertTrue(aluno.isPremium());
        assertEquals(3, aluno.getMoedas());
    }

    @Test
    void loginDeveSerNecessarioParaAdicionarCursoEConcluir() {
    	//cenario
        Aluno aluno = new Aluno("Carlos", "senhaSegura");
        
        //execucao
        plataforma.adicionarAluno(aluno);
        try {
            boolean logado = plataforma.autenticarAluno("Carlos", "senhaSegura");
            assertTrue(logado);
        }catch(UnsupportedOperationException e) {
        	fail();
        }

        plataforma.adicionarCursoParaAluno("Carlos", "Curso 1");
        plataforma.alunoConcluiCurso("Carlos", "Curso 1", 7.0);
        
        //resultados
        assertEquals(1, aluno.getCursos().stream().filter(Curso::isConcluido).count());
    }

    @Test
    void loginDeveFalharComSenhaIncorreta() {
    	
    	//cenario
        Aluno aluno = new Aluno("Ana", "senha1234");
        
        //execucao
        plataforma.adicionarAluno(aluno);
        try {
        boolean logado = plataforma.autenticarAluno("Ana", "senha1234");
        assertTrue(logado);
        }catch(UnsupportedOperationException e) {
        	fail();
        }
        plataforma.adicionarCursoParaAluno("Ana", "Curso 1");
        plataforma.alunoConcluiCurso("Ana", "Curso 1", 9.0);
        
        //resultados
        assertEquals(1, aluno.getCursos().stream().filter(Curso::isConcluido).count());
        assertEquals(false,aluno.isPremium());
        assertEquals(0, aluno.getMoedas());
    }
}
