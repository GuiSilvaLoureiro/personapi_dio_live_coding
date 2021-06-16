package one.digitalinnovation.personAPI.util;

import one.digitalinnovation.personAPI.dto.request.PersonDTO;
import one.digitalinnovation.personAPI.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {

    private static final String FIRST_NAME = "Guilherme";
    private static final String LAST_NAME = "Loureiro";
    private static final String CPF_NUMBER = "474.941.228-77";
    private static final Long PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(2021, 06, 16);

    public static PersonDTO createFakeDTO(){
        return PersonDTO.builder()
                        .firstName(FIRST_NAME)
                        .lastName(LAST_NAME)
                        .cpf(CPF_NUMBER)
                        .birthDate("04-04-2010")
                        .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                        .build();
    }

    public static Person createFakeEntity(){
        return Person.builder()
                    .id(PERSON_ID)
                    .firstName(FIRST_NAME)
                    .lastName(LAST_NAME)
                    .cpf(CPF_NUMBER)
                    .birthDate(BIRTH_DATE)
                    .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                    .build();
    }
}