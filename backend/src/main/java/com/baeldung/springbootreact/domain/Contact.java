package com.baeldung.springbootreact.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contact")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator ="native")
    @GenericGenerator(name = "native",
            strategy = "native")
    private Long cid;
    private String name;
    private String email;
    private String phoneNumber;
    @ManyToOne
    @JsonIgnore
    private Client client;

}
