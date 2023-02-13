package com.project.generalservices.dao;

import com.project.generalservices.helper.DBHelper;

public class ServicoSolicitado {

    private int id;
    private long usuario_cliente_id;
    private  long usuario_fornecedor_id;
    private String nome_servico;
    private String nome_cliente;
    private String endereco;

    public ServicoSolicitado(int id, long usuario_cliente_id, long usuario_fornecedor_id, String nome_servico, String nome_cliente, String endereco) {
        this.id = id;
        this.usuario_cliente_id = usuario_cliente_id;
        this.usuario_fornecedor_id = usuario_fornecedor_id;
        this.nome_servico = nome_servico;
        this.nome_cliente = nome_cliente;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUsuario_cliente_id() {
        return usuario_cliente_id;
    }

    public void setUsuario_cliente_id(long usuario_cliente_id) {
        this.usuario_cliente_id = usuario_cliente_id;
    }

    public long getUsuario_fornecedor_id() {
        return usuario_fornecedor_id;
    }

    public void setUsuario_fornecedor_id(long usuario_fornecedor_id) {
        this.usuario_fornecedor_id = usuario_fornecedor_id;
    }

    public String getNome_servico() {
        return nome_servico;
    }

    public void setNome_servico(String nome_servico) {
        this.nome_servico = nome_servico;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
