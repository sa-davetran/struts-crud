package org.apache.struts.crud.model;

/**
 * Models a Pet.
 */
public class Pet implements Cloneable {
    private Integer petId;
    private String name;
    private String species;
    private Integer age;
    
    public Pet() {
    }
    
    public Pet(Integer petId, String name, String species, Integer age) {
        this.petId = petId;
        this.name = name;
        this.species = species;
        this.age = age;
    }
    
    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
