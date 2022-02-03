package com.cci.pi.model;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , unique = true, nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "date_time")
    private String date_time;
    @Column(name = "status")
    private String status;
    @ManyToOne
    private User user;
}
