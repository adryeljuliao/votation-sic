package com.votation.api.models.associate;

import com.votation.api.models.vote.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "associate")
@Table(name = "associate")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Associate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "document_number")
    private String cpf;

    @OneToMany(mappedBy = "associate")
    private List<Vote> votes;
}
