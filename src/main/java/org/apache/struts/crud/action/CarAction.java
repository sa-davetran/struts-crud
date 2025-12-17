package org.apache.struts.crud.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.crud.model.Car;
import org.apache.struts.crud.service.CarService;
import org.apache.struts.crud.service.DefaultCarService;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.Preparable;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Acts as a controller to handle actions related to editing a Car.
 */
public class CarAction extends ActionSupport implements Preparable {

    private static final Logger LOG = LogManager.getLogger(CarAction.class.getName());
    private CarService carService = new DefaultCarService();
    private Car car;
    private Car[] cars;
    private String[] manufacturers;
    private String[] colors;

    @Override
    public void prepare() throws Exception {
        manufacturers = carService.getManufacturers();
        colors = carService.getColors();
        LOG.info("Prepared support data for Car entity.");
    }

    /**
     * Prepare method specifically for the input action (editing an existing car).
     * This is called by the Preparable interceptor before the input action executes.
     */
    public void prepareInput() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String carIdParam = request.getParameter("car.carId");
        LOG.info("Preparing input data for Car with ID: " + carIdParam);

        if (carIdParam != null && !carIdParam.isEmpty()) {
            try {
                car = carService.getCar(Integer.valueOf(carIdParam));
                LOG.info("Preparing actual data for Car: " + car);
            } catch (NumberFormatException e) {
                LOG.warn("Invalid carId parameter: " + carIdParam);
            }
        }
    }

    /**
     * Prepare method specifically for the save action.
     * This ensures proper car loading when updating.
     */
    public void prepareSave() throws Exception {
        if (car != null && car.getCarId() != null) {
            Car existingCar = carService.getCar(car.getCarId());
            if (existingCar != null) {
                LOG.info("Preparing to save existing Car: " + existingCar);
            }
        }
    }

    /**
     * Prepare method specifically for the delete action.
     * This is called by the Preparable interceptor before the delete action executes.
     */
    public void prepareDelete() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String carIdParam = request.getParameter("car.carId");
        LOG.info("Preparing delete for Car with ID: " + carIdParam);

        if (carIdParam != null && !carIdParam.isEmpty()) {
            try {
                car = carService.getCar(Integer.valueOf(carIdParam));
                LOG.info("Loaded Car for deletion: " + car);
            } catch (NumberFormatException e) {
                LOG.warn("Invalid carId parameter: " + carIdParam);
            }
        }
    }

    /**
     * Get all cars for display in the view.
     */
    public String list() {
        cars = carService.getAllCars();
        LOG.info("Listing cars");
        return SUCCESS;
    }

    /**
     * Display the form to edit an existing car or add a new car.
     * The car data will be loaded by the prepareInput() method if carId is provided.
     */
    public String input() {
        LOG.info("Editing Car with ID: " + (car != null ? car.getCarId() : "null"));
        return INPUT;
    }

    /**
     * Save the state of the Car object instance field.
     */
    public String save() {
        if (car.getCarId() == null) {
            carService.insertCar(car);
            LOG.info("Created new Car: " + car);
        } else {
            carService.updateCar(car);
            LOG.info("Updated Car: " + car);
        }
        return SUCCESS;
    }

    /**
     * Delete from Car identified by the car
     * instance field's carId value.
     */
    public String delete() {
        carService.deleteCar(car.getCarId());
        LOG.info("Deleted Car: " + car);
        return SUCCESS;
    }

    public Car[] getCars() {
        return cars;
    }

    @StrutsParameter(depth = 2)
    public Car getCar() {
        return car;
    }

    @StrutsParameter(depth = 2)
    public void setCar(Car car) {
        this.car = car;
    }

    public String[] getManufacturers() {
        return manufacturers;
    }

    public String[] getColors() {
        return colors;
    }
}
