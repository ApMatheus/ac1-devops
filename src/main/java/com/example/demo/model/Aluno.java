package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String senha;
    private List<Curso> cursos;
    private boolean isPremium;
    private int moedas;

    public Aluno(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.cursos = new ArrayList<>();
        this.isPremium = false;
        this.moedas = 0;
    }

    public String getNome() {
        return nome;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public int getMoedas() {
        return moedas;
    }

    public void adicionarCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public boolean autenticar(String senha) {
    	return this.senha.equals(senha);
    }

    public void concluirCurso(String nomeCurso, double notaFinal) {
        Curso curso = buscarCurso(nomeCurso);
        if (curso != null) {
            curso.concluirCurso(notaFinal);
            if (curso.isConcluido()) {
                verificarPremium();
            }
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    private Curso buscarCurso(String nomeCurso) {
    	return this.cursos.stream()
                .filter(curso -> curso.getNome().equalsIgnoreCase(nomeCurso))
                .findFirst()
                .orElse(null);
    }

    private void verificarPremium() {
        long cursosConcluidos = cursos.stream().filter(Curso::isConcluido).count();
        if (cursosConcluidos >= 12 && !this.isPremium) {
            this.isPremium = true;
            this.moedas += 3;
            System.out.println("Aluno " + this.nome + " agora é Premium e recebeu 3 moedas!");
        }
    }
}

