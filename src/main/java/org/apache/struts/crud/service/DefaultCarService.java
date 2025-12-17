package org.apache.struts.crud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.struts.crud.model.Car;

/**
 * Implement Services needed to edit and save
 * a Car object's state using in-memory storage.
 */
public class DefaultCarService implements CarService {
    
    private static final Map<Integer, Car> carDatabase = new HashMap<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    
    // Initialize with some sample data
    static {
        carDatabase.put(1, new Car(1, "Accord", "Honda", 2020, "blue"));
        carDatabase.put(2, new Car(2, "Civic", "Honda", 2021, "red"));
        carDatabase.put(3, new Car(3, "Camry", "Toyota", 2019, "white"));
        carDatabase.put(4, new Car(4, "Mustang", "Ford", 2022, "black"));
        carDatabase.put(6, new Car(6, "Corolla", "Toyota", 2020, "gray"));
        carDatabase.put(7, new Car(7, "Altima", "Nissan", 2019, "white"));
        carDatabase.put(8, new Car(8, "Ibiza", "Seat", 2022, "blue"));

        idGenerator.set(9);
    }

    @Override
    public Car getCar(Integer id) {
        Car car = carDatabase.get(id);
        if (car != null) {
            return (Car) car.clone();
        }
        return null;
    }

    @Override
    public Car[] getAllCars() {
        List<Car> cars = new ArrayList<>();
        for (Car car : carDatabase.values()) {
            cars.add((Car) car.clone());
        }
        return cars.toArray(new Car[0]);
    }

    @Override
    public void updateCar(Car car) {
        if (car.getCarId() != null && carDatabase.containsKey(car.getCarId())) {
            carDatabase.put(car.getCarId(), (Car) car.clone());
        }
    }

    @Override
    public void insertCar(Car car) {
        Integer newId = idGenerator.getAndIncrement();
        car.setCarId(newId);
        carDatabase.put(newId, (Car) car.clone());
    }

    @Override
    public void deleteCar(Integer id) {
        carDatabase.remove(id);
    }

    @Override
    public String[] getManufacturers() {
        return new String[]{"Honda", "Toyota", "Ford", "Chevrolet", "BMW", "Mercedes-Benz", "Audi", "Volkswagen"};
    }

    @Override
    public String[] getColors() {
        return new String[]{"Red", "Blue", "White", "Black", "Silver", "Gray", "Green", "Yellow"};
    }
}
