package org.apache.struts.crud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.struts.crud.model.Pet;

/**
 * Implement Services needed to edit and save
 * a Pet object's state using in-memory storage.
 */
public class DefaultPetService implements PetService {
    
    private static final Map<Integer, Pet> petDatabase = new HashMap<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    
    // Initialize with some sample data
    static {
        petDatabase.put(1, new Pet(1, "Max", "Dog", 3));
        petDatabase.put(2, new Pet(2, "Bella", "Cat", 2));
        petDatabase.put(3, new Pet(3, "Charlie", "Dog", 5));
        idGenerator.set(4);
    }

    @Override
    public Pet getPet(Integer id) {
        Pet pet = petDatabase.get(id);
        if (pet != null) {
            return (Pet) pet.clone();
        }
        return null;
    }

    @Override
    public Pet[] getAllPets() {
        List<Pet> pets = new ArrayList<>();
        for (Pet pet : petDatabase.values()) {
            pets.add((Pet) pet.clone());
        }
        return pets.toArray(new Pet[0]);
    }

    @Override
    public void updatePet(Pet pet) {
        if (pet.getPetId() != null && petDatabase.containsKey(pet.getPetId())) {
            petDatabase.put(pet.getPetId(), (Pet) pet.clone());
        }
    }

    @Override
    public void insertPet(Pet pet) {
        Integer newId = idGenerator.getAndIncrement();
        pet.setPetId(newId);
        petDatabase.put(newId, (Pet) pet.clone());
    }

    @Override
    public void deletePet(Integer id) {
        petDatabase.remove(id);
    }

    @Override
    public String[] getSpecies() {
        return new String[]{"Dog", "Cat", "Bird", "Fish", "Rabbit", "Hamster", "Guinea Pig", "Reptile"};
    }

    @Override
    public String[] getBreeds() {
        return new String[]{"Golden Retriever", "Labrador", "German Shepherd", "Beagle", "Bulldog", 
                           "Siamese", "Persian", "Maine Coon", "Parakeet", "Canary", "Mixed Breed"};
    }
}
