package com.example.eric.banheirobomv1;

/**
 * Created by eric on 21/07/2015.
 */
public class Usuario {

    private int id;
    private String email;
    private String nome;
    private String senha;

    public Usuario(){


    }

    public Usuario(int id, String email, String nome, String senha) {
        super();
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String alias) {
        this.nome = alias;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return  nome;
    }
}
