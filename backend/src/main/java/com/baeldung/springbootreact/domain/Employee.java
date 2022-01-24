package com.baeldung.springbootreact.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
    generator ="native")
    @GenericGenerator(name = "native",
    strategy = "native")
    private Long id;
    @Size(min = 2)
    private String name;
    private String contact_details;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Team> team;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> task;

}