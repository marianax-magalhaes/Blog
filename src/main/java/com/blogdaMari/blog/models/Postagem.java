package com.blogdaMari.blog.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="tb_postagem")
public class Postagem {

    // definindo a PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //indicando que o campo nao pode ser vazio e o seu tamanho
    // se quisesse ter nomeado a coluna: @Column(name = "post_titulo"), por exemplo
    @NotNull
    @Size(min = 5, max=100)
    private String titulo;

    @NotNull
    @Size(min = 5, max=500)
    private String texto;

    // conforme doc do prof, para informar ao banco que é data/hora
    @Temporal(TemporalType.TIMESTAMP)
    // com a notacao mais "moderna" LocalDateTime nao rodou, deu erro
    private Date data = new java.sql.Date(System.currentTimeMillis());


    // metodos get e set
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDate() {
        return data;
    }
    public void setDate(Date data) {
        this.data = data;
    }

}
