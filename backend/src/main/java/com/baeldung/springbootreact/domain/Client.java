package com.baeldung.springbootreact.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "client")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
    generator ="native")
    @GenericGenerator(name = "native",
    strategy = "native")
    private Long id;

    private String name;
    private String contact_details;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Contact> contact;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> task;

}