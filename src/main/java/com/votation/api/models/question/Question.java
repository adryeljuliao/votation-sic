package com.votation.api.models.question;


import com.votation.api.models.vote.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "question")
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "question")
    private List<Vote> votes;


}
