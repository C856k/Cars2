package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarsRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {

    final CarsRepository carsRepository;
    final MemberRepository memberRepository;
    final ReservationRepository reservationRepository;

    public DeveloperData(CarsRepository carsRepository, MemberRepository memberRepository, ReservationRepository reservationRepository ){
        this.carsRepository = carsRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Ford","F-150",24,5));
        cars.add(new Car("Honda","Civic",45,8));
        cars.add(new Car("Toyota","Camry",45,1));
        cars.add(new Car("Chevrolet","Silverado",43,7));
        cars.add(new Car("Nissan","Altima",23,1));
        cars.add(new Car("BMW","3 Series",23,5));
        cars.add(new Car("Porche","911",32,2));
        cars.add(new Car("Audi","E-tron",82,32));
        carsRepository.saveAll(cars);





        List<Member> members = new ArrayList<>();
        Member m2 = new Member("LenosaCrimson","Topsecure","Lenosa.crimson@example.com","Leno","crim","87 old town St","Stormwind","9000");
        memberRepository.saveAll(members);

        Car car1 = new Car("VW", "Golf",750,25);
        Member m1 = new Member("Jan","test12","a@b.dk","Jan","Jensen","Lyngbyvej 1","Lyngby","2800");
        memberRepository.save(m1);
        carsRepository.save(car1);

        LocalDate date1 = LocalDate.of(2023,12,12);
        LocalDate date2 = date1.plusDays(1);
        Reservation r1 = new Reservation(date1,m1,car1);

        Reservation r2 = new Reservation(date2,m1,car1);
        Reservation r3 = new Reservation(date2,m2,car1);

        reservationRepository.save(r2);
        reservationRepository.save(r1);

        System.out.println("xxxx --->"+m1.getReservations().size());
        System.out.println("xxxx --->"+car1.getReservations().size());
        System.out.println("xxxx ---->"+m2.getReservations().size());

        //setupUserWithRoleUsers();

    }
    @Autowired
    UserWithRolesRepository userWithRolesRepository;

    final String passwordUsedByAll = "test12";

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }

}
