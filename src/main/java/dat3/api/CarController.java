package dat3.api;

import dat3.dto.CarRequest;
import dat3.dto.CarResponse;
import dat3.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/cars")
@RestController
public class CarController {
    CarService carService;

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
