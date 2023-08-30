package dat3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.entity.Car;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    int id;
    String brand;
    String model;
    double pricePrDay;
    Integer bestDiscount;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    public CarResponse(Car car, boolean includeAll){
        this.id = car.getId();
        this.model = car.getModel();
        this.brand = car.getBrand();
        this.pricePrDay = car.getPricePrDay();
        if (includeAll){
            this.bestDiscount = car.getBestDiscount();
            this.created = car.getCreated();
            this.edited = car.getLastEdited();
        }
    }
}

