package one.digitalinnovation.personAPI.controller;

import one.digitalinnovation.personAPI.dto.request.PersonDTO;
import one.digitalinnovation.personAPI.dto.response.MessageResponseDTO;
import one.digitalinnovation.personAPI.exception.PersonNotFoundException;
import one.digitalinnovation.personAPI.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/people")
public class PersonController
{
    private PersonService personService;

    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    @PostMapping //Indica que a operação será do tipo POST
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO)
    {
        return personService.createPerson(personDTO);
    }

    @GetMapping //GetMapping indica que a operação será do tipo GET
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}") //Indica que minha requisição GET tem um parametro especifico
                         //Para n dar conflito com o @GetMapping do metodo listAll
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
                            //@PathVariable está indicando que a requisição tem um parametro
        return personService.findById(id);
    }

    @DeleteMapping("/{id}") //Indica que minha requisição ira excluir um usuario atraves do ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deteleById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
