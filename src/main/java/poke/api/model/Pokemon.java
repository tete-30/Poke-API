package poke.api.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table
@Data
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private String tipo;

    @Column
    private double altura;

    @Column
    private double peso;

    @Column
    private int level;

}
