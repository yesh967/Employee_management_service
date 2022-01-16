package com.baeldung.springbootreact.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "task")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator ="native")
    @GenericGenerator(name = "native",
            strategy = "native")
    private int Id;

    private String taskName;
    private String taskPriority;
    private String taskComplexity;
    private Date Deadline;
    private boolean completed;
    @ManyToOne
    @JsonIgnore
    private Client client;

    public boolean getCompleted() {
        return completed;
    }
}
