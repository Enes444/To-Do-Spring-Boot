package de.htwberlin.webtech.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping("/")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Optional<Person> existingPersonOptional = personRepository.findById(id);

        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setAge(updatedPerson.getAge());


            return personRepository.save(existingPerson);
        } else {
            throw new IllegalArgumentException("Person not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}