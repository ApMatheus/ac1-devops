package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;


public class PlataformaCursos {
    private List<Aluno> alunos;

    public PlataformaCursos() {
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public boolean autenticarAluno(String nome, String senha) {
    	Aluno aluno = buscarAluno(nome);
        if (aluno != null && aluno.autenticar(senha)) {
            System.out.println("Login bem-sucedido!");
            return true;
        }
        System.out.println("Falha no login. Verifique seu nome ou senha.");
        return false;
    }

    public void adicionarCursoParaAluno(String nomeAluno, String nomeCurso) {
        Aluno aluno = buscarAluno(nomeAluno);
        if (aluno != null) {
            Curso curso = new Curso(nomeCurso);
            aluno.adicionarCurso(curso);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void alunoConcluiCurso(String nomeAluno, String nomeCurso, double notaFinal) {
        Aluno aluno = buscarAluno(nomeAluno);
        if (aluno != null) {
            aluno.concluirCurso(nomeCurso, notaFinal);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private Aluno buscarAluno(String nome) {
    	return this.alunos.stream()
                .filter(aluno -> aluno.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}
