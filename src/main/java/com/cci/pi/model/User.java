package com.cci.pi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , unique = true, nullable = false)
    private long id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "user_name")
    private String username;
    @Column(name = "last_name")
    private String last_name;
    @OneToMany(mappedBy = "user" , cascade =  CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Task> task;
}
