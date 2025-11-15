package com.api.agencia.agencia_cdv.model;

import java.util.concurrent.atomic.AtomicLong;

public class destino {
    private static final AtomicLong counter = new AtomicLong(0);

    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private double mediaAvaliacoes;
    private int totalAvaliacoes; // Número de avaliações recebidas

    // Construtor padrão
    public destino() {
        this.id = counter.incrementAndGet();
        this.mediaAvaliacoes = 0.0;
        this.totalAvaliacoes = 0;
    }

    // Construtor com campos obrigatórios
    public destino(String nome, String localizacao, String descricao) {
        this(); // Chama o construtor padrão para gerar o ID
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public void setMediaAvaliacoes(double mediaAvaliacoes) {
        this.mediaAvaliacoes = mediaAvaliacoes;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(int totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }

    /**
     * Atualiza a média de avaliação com uma nova nota.
     * @param novaNota a nota a ser adicionada (entre 1 e 10)
     */
    public void atualizarAvaliacao(int novaNota) {
        if (novaNota < 1 || novaNota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 10.");
        }

        // Soma total anterior: Média Antiga * Total de Avaliações Antigas
        double somaTotalAntiga = this.mediaAvaliacoes * this.totalAvaliacoes;
        
        // Novo Total de Avaliações
        this.totalAvaliacoes += 1;

        // Nova Soma Total
        double novaSomaTotal = somaTotalAntiga + novaNota;

        // Nova Média (arredondada para 2 casas decimais)
        this.mediaAvaliacoes = Math.round((novaSomaTotal / this.totalAvaliacoes) * 100.0) / 100.0;
    }
}

