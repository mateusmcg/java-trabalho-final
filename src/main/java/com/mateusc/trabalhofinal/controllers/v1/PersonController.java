package com.mateusc.trabalhofinal.controllers.v1;

import java.util.List;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Person;
import com.mateusc.trabalhofinal.models.Stock;
import com.mateusc.trabalhofinal.services.interfaces.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/person")
@Api(value = "PersonController", description = "Pessoas físicas que vendem/compram ações")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public ResponseEntity<List<Person>> getAll() {
        List<Person> people = this.personService.getAll();
        logger.info("People count: " + people.size());
        return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable UUID id) {
        logger.info("Person Id to GET: " + id.toString());
        Person person = this.personService.findById(id);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @PostMapping("")
    public HttpStatus create(@RequestBody Person person) {
        this.personService.create(person);
        return HttpStatus.OK;
    }

    @PostMapping("/{id}/stock/{stockId}/buy")
    public HttpStatus buy(@PathVariable UUID id, @PathVariable UUID stockId) {
        logger.info("Person Id: " + id.toString());
        logger.info("Stock Id: " + stockId.toString());
        return HttpStatus.OK;
    }

    @PostMapping("/{id}/stock/{stockId}/sell")
    public HttpStatus sell(@PathVariable UUID id, @PathVariable UUID stockId) {
        logger.info("Person Id: " + id.toString());
        logger.info("Stock Id: " + stockId.toString());
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public HttpStatus update(@PathVariable UUID id, @RequestBody Person updatedPerson) {
        logger.info("Person Id to UPDATE: " + id.toString());
        this.personService.update(id, updatedPerson);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        logger.info("Person Id to DELETE: " + id.toString());
        this.personService.delete(id);
        return HttpStatus.OK;
    }
}