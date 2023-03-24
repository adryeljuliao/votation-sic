package com.votation.api.services.associate;

import com.votation.api.controllers.associates.requests.AssociateRequest;
import com.votation.api.controllers.associates.responses.AssociateResponse;
import com.votation.api.exceptions.NotFoundException;
import com.votation.api.helpers.MaskSensitiveData;
import com.votation.api.mappers.AssociateMapper;
import com.votation.api.models.associate.Associate;
import com.votation.api.repositories.associate.AssociateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public non-sealed class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository associateRepository;

    @Override
    public AssociateResponse saveAssociate(AssociateRequest associateResponse) {
        final String maskedDocumentNumber = maskDocumentNumber(associateResponse.getDocumentNumber());
        log.info("m=saveAssociate documentNumber={}", maskedDocumentNumber);

        Associate associatePersisted = associateRepository.findByDocumentNumber(associateResponse.getDocumentNumber()).orElse(null);
        if (Objects.isNull(associatePersisted)) {
            log.info("m=saveAssociate documentNumber={} DONE SAVE", maskedDocumentNumber);
            final Associate associate = Objects.requireNonNull(AssociateMapper.makeAssociate(associateResponse));
            associatePersisted = associateRepository.save(associate);
        }
        return AssociateMapper.makeAssociateResponse(associatePersisted);
    }

    @Override
    public AssociateResponse findAssociateByDocumentNumber(String documentNumber) {
        final String maskedDocumentNumber = maskDocumentNumber(documentNumber);
        log.info("m=findByDocumentNumber documentNumber={}", maskedDocumentNumber);

        var associate = associateRepository.findByDocumentNumber(documentNumber).orElseThrow(() -> {
            log.error("m=findByDocumentNumber documentNumber={} NOT FOUND", maskedDocumentNumber);

            throw new NotFoundException("Document Number Not Found");
        });

        return AssociateMapper.makeAssociateResponse(associate);
    }

    @Override
    public AssociateResponse findAssociateResponseById(Long associateId) {
        log.info("m=findById associateId={}", associateId);

        final Associate associate = associateRepository.findById(associateId).orElseThrow(() -> {
            log.error("m=findById associateId={} NOT FOUND", associateId);

            throw new NotFoundException("Document Number Not Found");
        });

        return AssociateMapper.makeAssociateResponse(associate);
    }

    private String maskDocumentNumber(String documentNumber) {
        return MaskSensitiveData.mask(documentNumber);
    }
}
