package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car{
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

    public void addReservation(Reservation reservation){
        if (reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }


@OneToMany(mappedBy = "car")
List<Reservation> reservations;

@CreationTimestamp
LocalDateTime created;

@UpdateTimestamp
LocalDateTime edited;

@OneToOne
Member member;
@ManyToOne
Reservation reservation;

    public Car(String brand, String model,double pricePrDay,Integer bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;

    }
}
