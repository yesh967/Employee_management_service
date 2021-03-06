package com.baeldung.springbootreact.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "employee")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
    generator ="native")
    @GenericGenerator(name = "native",
    strategy = "native")
    private Long id;
    @Size(min = 2)
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @ToString.Exclude
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Team> team;
    @ToString.Exclude
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> task;

}