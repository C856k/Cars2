package dat3.service;

import dat3.dto.CarRequest;
import dat3.dto.CarResponse;
import dat3.entity.Car;
import dat3.repository.CarsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceTest {
    @Autowired
    CarsRepository carsRepository;

    CarService carService;

    boolean dataIsInitialized = false;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        if(dataIsInitialized) return;
        car1 = Car.builder().brand("Volvo").model("V70").pricePrDay(500.0).bestDiscount(10).build();
        car2 = Car.builder().brand("Suzuki").model("Swift").pricePrDay(350.0).bestDiscount(6).build();
        carsRepository.saveAndFlush(car1);
        carsRepository.saveAndFlush(car2);
        carService = new CarService(carsRepository);

        dataIsInitialized = true;
    }

    @Test
    void getCars1() {

        List<CarResponse> responseForAdmin =  carService.getCars(true);
        assertEquals(2, responseForAdmin.size());
        assertNotNull(responseForAdmin.get(0).getCreated());
        assertNotNull(responseForAdmin.get(0).getBestDiscount());

    }

    @Test
    void getCars2() {
        List<CarResponse> responseForAnonymous =  carService.getCars(false);
        assertEquals(2, responseForAnonymous.size());
        assertNull(responseForAnonymous.get(0).getCreated());
        assertNull(responseForAnonymous.get(0).getBestDiscount());
    }

    @Test
    void findCarById() {
        CarResponse response = carService.findById(car1.getId(),true);
        assertEquals(car1.getBrand(), response.getBrand());
        assertEquals(car1.getModel(), response.getModel());
        assertEquals(car1.getPricePrDay(), response.getPricePrDay());
        assertEquals(car1.getBestDiscount(), response.getBestDiscount());
        assertNotNull(response.getCreated());
    }

    @Test
    void findNonExistingCarByIdWillThrow() {
        assertThrows(ResponseStatusException.class,()->carService.findById(123456, true));
    }

    @Test
    void addCar() {
        CarRequest newCar = CarRequest.builder().brand("x").model("y").pricePrDay(10).bestDiscount(20).build();
        CarResponse addedCar = carService.addCar(newCar);
        assertEquals(newCar.getBrand(), addedCar.getBrand());
        assertEquals(newCar.getModel(), addedCar.getModel());
        //assertTrue(addedCar.getId() > car2.getId());  //Verify that id is auto generated
        //assertNotNull(addedCar.getCreated());
    }


    @Test
    void editCar()  {
        //Edit car1 (new model and price)
        CarRequest newCar = CarRequest.builder().brand(car1.getBrand()).model("y").pricePrDay(10).bestDiscount(car1.getBestDiscount()).build();
        CarResponse editedCar = carService.editCar(newCar, car1.getId());

        assertEquals(car1.getBrand(),editedCar.getBrand());
        assertEquals("y",editedCar.getModel());
        assertEquals(10,editedCar.getPricePrDay());
        assertEquals(car1.getBestDiscount(),editedCar.getBestDiscount());

        //This is necessary to test whether dates are updated
        carsRepository.flush();
        Car car = carsRepository.findById(car1.getId()).get();
        assertTrue(car.getEdited().isAfter(car1.getCreated()));
    }

    @Test
    void setPrice() {
        carService.setPrice(car1.getId(),999);
        assertEquals(999,carsRepository.findById(car1.getId()).get().getPricePrDay());
    }

    @Test
    void setDiscount() {
        carService.setDiscount(car1.getId(),12);
        assertEquals(12,carsRepository.findById(car1.getId()).get().getBestDiscount());
    }

    @Test
    void deleteCar() {
        carService.deleteCar(car1.getId());
        assertFalse(carsRepository.existsById(car1.getId()));
    }
    @Test
    void deleteNonExistingCarThrows() {
        assertThrows(ResponseStatusException.class, ()-> carService.deleteCar(1111));
    }
}