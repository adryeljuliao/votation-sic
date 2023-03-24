package com.votation.api.services;

import com.votation.api.controllers.associates.requests.AssociateRequest;
import com.votation.api.controllers.associates.responses.AssociateResponse;
import com.votation.api.exceptions.NotFoundException;
import com.votation.api.models.associate.Associate;
import com.votation.api.repositories.associate.AssociateRepository;
import com.votation.api.services.associate.AssociateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

public class AssociateServiceImplTest {

    private final String DOCUMENT_NUMBER = "03409441122";
    @InjectMocks
    private AssociateServiceImpl associateService;

    @Mock
    private AssociateRepository associateRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenDocumentNumberNotExistToCreateThenReturnAssociateData() {
        AssociateRequest mockAssociateRequest = AssociateRequest.builder()
                .documentNumber(DOCUMENT_NUMBER)
                .build();

        Associate mockAssociate = Associate.builder()
                .documentNumber(DOCUMENT_NUMBER)
                .createdDate(LocalDateTime.now())
                .id(1L)
                .votes(Collections.emptyList())
                .build();

        Mockito.when(associateRepository.findByDocumentNumber(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(associateRepository.save(Mockito.any(Associate.class))).thenReturn(mockAssociate);

        AssociateResponse associateResponse = associateService.saveAssociate(mockAssociateRequest);

        Assertions.assertNotNull(associateResponse);
        Mockito.verify(associateRepository, Mockito.times(1)).save(Mockito.any(Associate.class));

    }

    @Test
    void givenDocumentNumberExistToCreateThenReturnAssociateData() {
        AssociateRequest mockAssociateRequest = AssociateRequest.builder()
                .documentNumber(DOCUMENT_NUMBER)
                .build();

        Associate mockAssociate = Associate.builder()
                .documentNumber(DOCUMENT_NUMBER)
                .createdDate(LocalDateTime.now())
                .id(1L)
                .votes(Collections.emptyList())
                .build();

        Mockito.when(associateRepository.findByDocumentNumber(Mockito.anyString())).thenReturn(Optional.of(mockAssociate));

        AssociateResponse associateResponse = associateService.saveAssociate(mockAssociateRequest);

        Assertions.assertNotNull(associateResponse);
        Mockito.verify(associateRepository, Mockito.times(0)).save(Mockito.any(Associate.class));
    }

    @Test
    void givenDocumentNumberValidToFindByDocumentNumberThenReturnAssociateData() {
        Associate mockAssociate = Associate.builder()
                .documentNumber(DOCUMENT_NUMBER)
                .createdDate(LocalDateTime.now())
                .id(1L)
                .votes(Collections.emptyList())
                .build();

        Mockito.when(associateRepository.findByDocumentNumber(Mockito.anyString())).thenReturn(Optional.of(mockAssociate));

        AssociateResponse associateResponse = associateService.findAssociateByDocumentNumber(DOCUMENT_NUMBER);

        Assertions.assertNotNull(associateResponse);
        Mockito.verify(associateRepository, Mockito.times(1)).findByDocumentNumber(Mockito.anyString());
    }

    @Test
    void givenIdValidToFindByIdThenReturnAssociateData() {
        Associate mockAssociate = Associate.builder()
                .documentNumber(DOCUMENT_NUMBER)
                .createdDate(LocalDateTime.now())
                .id(1L)
                .votes(Collections.emptyList())
                .build();

        Mockito.when(associateRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockAssociate));

        AssociateResponse associateResponse = associateService.findAssociateResponseById(1L);

        Assertions.assertNotNull(associateResponse);
        Mockito.verify(associateRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void givenDocumentNumberNotValidToFindByDocumentNumberThenReturnNotFoundException() {
        Mockito.when(associateRepository.findByDocumentNumber(Mockito.anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> associateService.findAssociateByDocumentNumber(DOCUMENT_NUMBER));
    }

    @Test
    void givenIdNotValidToFindByIdThenReturnNotFoundException() {
        Mockito.when(associateRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> associateService.findAssociateResponseById(1L));
    }

//    @Test
//    void givenQuestionIdValidToFindByIdThenReturnQuestionData() {
//        Question mockQuestion = Question.builder()
//                .id(1L)
//                .finalDate(LocalDateTime.now().plusMinutes(2))
//                .description("Teste?")
//                .createdDate(LocalDateTime.now())
//                .build();
//
//        Mockito.when(associateRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockQuestion));
//
//        QuestionResponse questionResponse = associateService.findQuestionById(1L);
//
//        Assertions.assertNotNull(questionResponse);
//        Assertions.assertNotNull(mockQuestion);
//        Assertions.assertEquals(mockQuestion.getDescription(), questionResponse.getDescription());
//        Assertions.assertEquals(mockQuestion.getFinalDate(), questionResponse.getFinalDate());
//    }
//
//    @Test
//    void givenQuestionIdNotValidToFindByIdThenReturnNotFoundException() {
//        Mockito.when(associateRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(NotFoundException.class, () -> associateService.findQuestionById(1L));
//
//    }


}
