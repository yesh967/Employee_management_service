package com.baeldung.springbootreact.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "team")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "native")
    @GenericGenerator(name = "native",
            strategy = "native")
    private Long teamId;
    private String teamName;
    private String project;
    private String location;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Employee employee;

}
