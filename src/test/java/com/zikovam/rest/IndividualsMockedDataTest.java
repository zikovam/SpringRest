package com.zikovam.rest;

import static org.junit.Assert.*;

public class IndividualsMockedDataTest {

    private IndividualsMockedData individualsMockedData = IndividualsMockedData.getInstance();

    @org.junit.Test
    public void fetchIndividuals () {
        assertEquals(individualsMockedData.getEntriesNum(), 3);
    }

    @org.junit.Test
    public void getIndividualBySnils () {
    }

    @org.junit.Test
    public void createIndividual () {
    }

    @org.junit.Test
    public void updateIndividual () {
    }
}
