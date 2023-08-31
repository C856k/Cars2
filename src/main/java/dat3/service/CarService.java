package dat3.service;

import dat3.dto.CarRequest;
import dat3.dto.CarResponse;
import dat3.entity.Car;
import dat3.repository.CarsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    CarsRepository carsRepository;

    public CarService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<CarResponse> getCars(boolean includeAll){
        List<Car> cars = carsRepository.findAll();
        return cars.stream().map(car -> new CarResponse(car,includeAll)).collect(Collectors.toList());
    }
    public CarResponse findById(Integer id, boolean includeAll) {
        Car found = carsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        return new CarResponse(found,includeAll);
    }
    public CarResponse addCar(CarRequest body) {
        Car newCar = Car.builder().brand(body.getBrand()).
                model(body.getModel()).pricePrDay(body.getPricePrDay()).
                bestDiscount(body.getBestDiscount()).build();
        return new CarResponse(newCar,true);
    }
    public CarResponse editCar(CarRequest body, Integer id){
        Car carToEdit = carsRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this ID doesnt exist"));
        carToEdit.setBrand(body.getBrand());
        carToEdit.setModel(body.getModel());
        carToEdit.setPricePrDay(body.getPricePrDay());
        carToEdit.setBestDiscount(body.getBestDiscount());
        Car saved = carsRepository.save(carToEdit);
        return new CarResponse(saved,true);
    }

    public void setPrice(Integer id, double newPrice) {
        Car carToEdit = carsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID does not exist"));
        carToEdit.setPricePrDay(newPrice);
        carsRepository.save(carToEdit);
    }

    public void setDiscount(Integer id, int newDiscount) {
        Car carToEdit = carsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID does not exist"));
        carToEdit.setBestDiscount(newDiscount);
        carsRepository.save(carToEdit);
    }

    public ResponseEntity<Boolean> deleteCar(Integer id) {
        if(!carsRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID does not exist");
        }
        try {
            carsRepository.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete car. Most likely because it part of a rental/reservation");
        }
    }
}
