package com.zikovam.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private IndividualsMockedData individualsMockedData = IndividualsMockedData.getInstance();

    @GetMapping ("/individuals")
    public List<Individual> index() {
        return individualsMockedData.fetchIndividuals();
    }

    @GetMapping ("/individuals/{snils}")
    public Individual show(@PathVariable String snils){
        return individualsMockedData.getIndividualBySnils(snils);
    }

    @PostMapping ("/individuals")
    public Individual create (@RequestBody ObjectNode json){
        String name = json.get("name").asText();
        String snils = json.get("snils").asText();
        String dateBirth = json.get("dateBirth").asText();
        return individualsMockedData.createIndividual(name, snils, dateBirth);
    }

    /**
     * Updates one entity from the list
     * It search by SNILS number and change entry if exist
     *
     * @param json - data for searching and changing
     * @return - updated individual
     */
    @PutMapping ("/individuals")
    public Individual update(@RequestBody ObjectNode json){
        String name = json.get("name").asText();
        String snils = json.get("snils").asText();
        String dateBirth = json.get("dateBirth").asText();
        return individualsMockedData.updateIndividual(name, snils, dateBirth);
    }

    @GetMapping ("/individuals/status")
    public String status(){
        return "{\"Entries number\": " + individualsMockedData.getEntriesNum() +
                ",\n\"Changes number\":" + individualsMockedData.getChangeChecker() + "}";
    }
}
