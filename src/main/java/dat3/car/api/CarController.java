package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/cars")
@RestController
public class CarController {
    CarService carService;
    private List<Car> cars = new ArrayList<>();

    public CarController(CarService carService){
        this.carService = carService;
    }
    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars(false);
    }
    @GetMapping(path = "/admin/{id}")
    CarResponse getCarByIdAdmin(@PathVariable Integer id) {
        return carService.findById(id,true);
    }
    @GetMapping("/api/cars/{brand}")
    public List<Car> getCarsByAndModel(
            @RequestParam(name = "brand") String brand,
            @RequestParam(name = "model") String model) {
        return cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand) && car.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }
    @PostMapping
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }
    @PutMapping("/{id}")
    CarResponse editCar(@RequestBody CarRequest body, @PathVariable Integer id){
        return carService.editCar(body,id);
    }
    @PatchMapping("/price/{id}/{newPrice}")
    void setPrice(@PathVariable Integer id,@PathVariable double newPrice){
        carService.setPrice(id,newPrice);
    }
    @PatchMapping("/discount/{id}/{newDiscount}")
    void setPrice(@PathVariable Integer id, @PathVariable int newDiscount){
        carService.setDiscount(id,newDiscount);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteCar(@PathVariable Integer id){
        return carService.deleteCar(id);
    }

}
