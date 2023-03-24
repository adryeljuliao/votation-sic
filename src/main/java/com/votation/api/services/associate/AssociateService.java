package com.votation.api.services.associate;

import com.votation.api.controllers.associates.requests.AssociateRequest;
import com.votation.api.controllers.associates.responses.AssociateResponse;

public sealed interface AssociateService permits AssociateServiceImpl {

    AssociateResponse saveAssociate(AssociateRequest associate);

    AssociateResponse findAssociateByDocumentNumber(String documentNumber);

    AssociateResponse findAssociateResponseById(Long associateId);

}
