package com.votation.api.models.associate;

import com.votation.api.models.vote.Vote;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "associate")
@Table(name = "associate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Associate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_number", length = 11)
    private String documentNumber;

    @OneToMany(mappedBy = "associate", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Vote> votes;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;
}
