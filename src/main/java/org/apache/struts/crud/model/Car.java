package org.apache.struts.crud.model;

/**
 * Models a Car.
 *
 * @author generated
 */
public class Car implements Cloneable {
    private Integer carId;
    private String model;
    private String manufacturer;
    private Integer year;
    private String color;
    
    public Car() {
    }
    
    public Car(Integer carId, String model, String manufacturer, Integer year, String color) {
        this.carId = carId;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.color = color;
    }
    
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
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
