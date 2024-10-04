package com.example.demo.model;

public class Curso {
    private String nome;
    private boolean concluido;
    private double notaFinal;

    public Curso(String nome) {
        this.nome = nome;
        this.concluido = false;
    }

    public String getNome() {
        return nome;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void concluirCurso(double notaFinal) {
        this.notaFinal = notaFinal;
        if (notaFinal >= 7.0) {
            this.concluido = true;
        }
    }
}

