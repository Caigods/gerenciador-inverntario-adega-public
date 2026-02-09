package com.caigods.adega.infrastructure.entitys;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name="bebida")
@Entity
public class Bebida {

    @Id                    //NÃO EXISTE TABELA SEM ID. Identificador únicos de cada campo da tabela
    @GeneratedValue (strategy = GenerationType.IDENTITY) //o Id é gerado automaticamente ao chegar no repository
    private Integer id;

    @Column(name = "quantidade_ml")
    private Integer volumeMl;  //Se o campo no banco vier vazio (NULL), o int (primitivo) vai estourar um erro, pois ele não aceita "nada",
                                // apenas números. O Integer aceita null, o que é muito mais seguro para APIs.

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Column(name = "em_envelhecimento")
    private Boolean emEnvelhecimento;

    @Column(name = "nome", nullable = false) // nullable = false impede nomes vazios no banco
    private String nome;

}
