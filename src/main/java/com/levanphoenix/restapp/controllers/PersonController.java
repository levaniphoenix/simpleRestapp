package com.levanphoenix.restapp.controllers;

import com.github.javafaker.Faker;
import com.levanphoenix.restapp.DAL.PersonRepository;
import com.levanphoenix.restapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.Long.parseLong;


@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    PersonRepository personRepo;

    @RequestMapping("/get")
    public Person getPerson(@RequestParam String id){
        return personRepo.findById(parseLong(id)).orElseThrow();
    }

    @RequestMapping("/add")
    public Person addPerson(){
        Person p = new Person();
        Faker faker = new Faker();

        p.setFirstName(faker.name().firstName());
        p.setLastName(faker.name().lastName());
        return personRepo.save(p);
    }

    @RequestMapping("/update")
    public Person editPersonName(@RequestParam String name, @RequestParam String id){
        Person p = personRepo.findById(parseLong(id)).orElseThrow();
        p.setFirstName(name);
        return personRepo.save(p);
    }

    //@DeleteMapping("/delete")
    @RequestMapping("/delete")
    public String deletePerson(@RequestParam String id){
        try {
            personRepo.deleteById(parseLong(id));
        }catch (Exception e){
            return e.toString();
        }
        return "successfully deleted person with id " + id;
    }

}
