package com.votation.api.controllers.associates.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociateResponse {
    
    private Long id;
    private String documentNumber;

}
