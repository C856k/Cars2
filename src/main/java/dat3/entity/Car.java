package dat3.entity;

import dat3.repository.CarsRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car extends AdminDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = true,name = "brand",length = 50)
    String brand;

    @Column(nullable = true,name = "Model",length = 60)
    String model;

    @Column(name = "rental_price_day",nullable = true)
    Double pricePrDay;

@Column(name ="max_discount",nullable = true)
    private int bestDiscount;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
}
