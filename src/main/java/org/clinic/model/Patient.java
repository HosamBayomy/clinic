package org.clinic.model;

public class Patient {

    private Integer id;
    private String name;

    public Patient(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId(){return id;}
    public String getName(){return name;}

    @Override
    public boolean equals(Object anotherObject){
        Patient anotherPatient = (Patient) anotherObject;
        return id.equals(anotherPatient.getId()) && name.equals(anotherPatient.getName());
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[")
                .append("PatientId=")
                .append(id)
                .append(",")
                .append("Name=")
                .append(name)
                .append("]");
        return builder.toString();
    }
}
