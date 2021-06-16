package one.digitalinnovation.personAPI.service;

import one.digitalinnovation.personAPI.dto.request.PersonDTO;
import one.digitalinnovation.personAPI.dto.response.MessageResponseDTO;
import one.digitalinnovation.personAPI.entity.Person;
import one.digitalinnovation.personAPI.repository.PersonRepository;
import one.digitalinnovation.personAPI.util.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personAPI.util.PersonUtils.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSendMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccesMessage = createExpectedMessageResponse(expectedSavedPerson.getId());

        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSuccesMessage, succesMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with Id " + id)
                .build();
    }
}
