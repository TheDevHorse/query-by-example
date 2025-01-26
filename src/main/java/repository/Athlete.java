package repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Athlete {

    @Id
    private String athleteId;
    private int age;
    private String name;
    private Address address;

    public Athlete(String athleteId, int age, String name, Address address) {
        this.athleteId = athleteId;
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "athleteId='" + athleteId + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
