package com.votation.api.models.question;


import com.votation.api.models.vote.Vote;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "question")
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_description")
    private String description;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Vote> votes;

    @Column(name = "final_date")
    private LocalDateTime finalDate;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

}
