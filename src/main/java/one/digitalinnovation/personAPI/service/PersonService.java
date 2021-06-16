package one.digitalinnovation.personAPI.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personAPI.dto.request.PersonDTO;
import one.digitalinnovation.personAPI.dto.response.MessageResponseDTO;
import one.digitalinnovation.personAPI.entity.Person;
import one.digitalinnovation.personAPI.exception.PersonNotFoundException;
import one.digitalinnovation.personAPI.mapper.PersonMapper;
import one.digitalinnovation.personAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //Indica ao spring que essa é uma classe que vai controlar todas as regras de negocio da aplicação+
@AllArgsConstructor(onConstructor = @__(@Autowired)) // Substitui o construtor padrão
public class PersonService
{
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

//    @Autowired //Construtor padrão
//    public PersonService(PersonRepository personRepository)
//    {
//        this.personRepository = personRepository;
//    }

    public MessageResponseDTO createPerson(PersonDTO personDTO)
    {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with Id ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                        .map(personMapper::toDTO)
                        .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        //Código refatorado
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);

        //Código sem refatoração
//        Optional<Person> optionalPerson = personRepository.findById(id);
//        if (optionalPerson.isEmpty()){
//            throw new PersonNotFoundException(id);
//        }
//          return personMapper.toDTO(optionalPerson.get());
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with Id ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
