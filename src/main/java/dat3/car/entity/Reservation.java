package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//Lombok above
@Entity
@NoArgsConstructor
public class Reservation extends AdminDetails{
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDate rentalDate;

    @ManyToOne
    Member member;
    @ManyToOne
    Car car;

    public Reservation(LocalDate rentalDate, Member member, Car car) {
        this.rentalDate = rentalDate;
        this.member = member;
        this.car = car;
        car.addReservation(this);
        member.addReservation(this);
    }
}
