package org.apache.struts.crud.service;

import org.apache.struts.crud.model.Car;

/**
 * Define methods a CarService must implement
 * to provide services related to a Car class.
 */
public interface CarService {

    Car getCar(Integer id);
    
    Car[] getAllCars();

    void updateCar(Car car);
    
    void insertCar(Car car);
    
    void deleteCar(Integer id);
    
    String[] getManufacturers();
    
    String[] getColors();
}
