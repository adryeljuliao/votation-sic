package com.votation.api.models.vote;

import com.votation.api.models.associate.Associate;
import com.votation.api.models.question.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "vote")
@Table(name = "vote")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private Boolean statusVote;
    @ManyToOne
    @JoinColumn(name="associate_id", nullable=false)
    private Associate associate;

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

}
