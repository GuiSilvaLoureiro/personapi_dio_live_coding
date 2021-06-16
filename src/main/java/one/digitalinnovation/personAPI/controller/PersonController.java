package one.digitalinnovation.personAPI.controller;

import one.digitalinnovation.personAPI.dto.request.PersonDTO;
import one.digitalinnovation.personAPI.dto.response.MessageResponseDTO;
import one.digitalinnovation.personAPI.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/people")
public class PersonController
{
    private PersonService personService;

    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO)
    {
        return personService.createPerson(personDTO);
    }
}
