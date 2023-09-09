package dat3.car.entity;


import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")

public class Member extends UserWithRoles {

    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column()
    private String zip;

    private int ranking;
    private boolean approved;

    @OneToMany(mappedBy = "member")
    List<Reservation> reservations;

        public void addReservation(Reservation reservation){
            if (reservations == null){
                reservations = new ArrayList<>();
            }
            reservations.add(reservation);

        }





    @OneToOne
    Car car;

    @ManyToOne
    Reservation reservation;


    public Member(String user, String password, String email, String firstName,
                  String lastName, String street, String city, String zip) {
        super(user,password,email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;

    }




}
