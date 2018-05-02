package com.zikovam.rest;

/**
 * <p>Individual entity class</p>
 */
public class Individual {
    private int id;
    private String name;
    private String snils; //IDK how to translate it properly
    private String dateBirth; //I use String as the easiest way

    public Individual (int id, String name, String snils, String dateBirth) {
        this.setId(id);
        this.setName(name);
        this.setSnils(snils);
        this.setDateBirth(dateBirth);
    }

    public int getId () {
        return id;
    }

    private void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSnils () {
        return snils;
    }

    public void setSnils (String snils) {
        this.snils = snils;
    }

    public String getDateBirth () {
        return dateBirth;
    }

    public void setDateBirth (String dateBirth) {
        this.dateBirth = dateBirth;
    }

        //override for toString() method for more informative output
    @Override
    public String toString () {
        return "Individual{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", Birth date='" + dateBirth + '\'' +
                ", SNILS='" + snils + '\'' +
                '}';
    }
}
