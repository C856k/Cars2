package dat3.config;

import dat3.entity.Car;
import dat3.entity.Member;
import dat3.repository.CarsRepository;
import dat3.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {

    final CarsRepository carsRepository;
    final MemberRepository memberRepository;

    public DeveloperData(CarsRepository carsRepository, MemberRepository memberRepository ){
        this.carsRepository = carsRepository;
        this.memberRepository = memberRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Ford","F-150"));
        cars.add(new Car("Honda","Civic"));
        cars.add(new Car("Toyota","Camry"));
        cars.add(new Car("Chevrolet","Silverado"));
        cars.add(new Car("Nissan","Altima"));
        cars.add(new Car("BMW","3 Series"));
        cars.add(new Car("Porche","911"));
        cars.add(new Car("Audi","E-tron"));
        carsRepository.saveAll(cars);


        List<Member> members = new ArrayList<>();
        members.add(new Member("LenosaCrimson","Topsecure","Lenosa.crimson@example.com","Leno","crim","87 old town St","Stormwind","9000"));
        memberRepository.saveAll(members);
    }
}
