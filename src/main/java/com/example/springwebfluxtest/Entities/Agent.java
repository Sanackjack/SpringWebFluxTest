package com.example.springwebfluxtest.Entities;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("agent")
public class Agent {
    @Id
    @Column("id")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    //@Gen
    private String id;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;

}
