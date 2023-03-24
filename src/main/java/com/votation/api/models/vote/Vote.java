package com.votation.api.models.vote;

import com.votation.api.models.associate.Associate;
import com.votation.api.models.question.Question;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity(name = "vote")
@Table(name = "vote")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_vote")
    private Boolean statusVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="associate_id", nullable=false)
    private Associate associate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

}
