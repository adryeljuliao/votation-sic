package com.votation.api.mappers;

import com.votation.api.controllers.associates.requests.AssociateRequest;
import com.votation.api.controllers.associates.responses.AssociateResponse;
import com.votation.api.models.associate.Associate;

import java.util.Objects;

public final class AssociateMapper {

    public static Associate makeAssociate(AssociateResponse associateResponse) {
        if (Objects.nonNull(associateResponse)) {
            return Associate.builder()
                    .id(associateResponse.getId())
                    .documentNumber(associateResponse.getDocumentNumber())
                    .build();
        }
        return null;
    }

    public static Associate makeAssociate(AssociateRequest associateRequest) {
        if (Objects.nonNull(associateRequest)) {
            return Associate.builder()
                    .documentNumber(associateRequest.getDocumentNumber())
                    .build();
        }
        return null;
    }

    public static AssociateResponse makeAssociateResponse(Associate associate) {
        if (Objects.nonNull(associate)) {
            return AssociateResponse.builder()
                    .id(associate.getId())
                    .documentNumber(associate.getDocumentNumber())
                    .build();
        }
        return null;
    }

}
