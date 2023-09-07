package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarsRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class ReservationService {
    CarsRepository carsRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;

    public ReservationService(CarsRepository carsRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carsRepository = carsRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse reserveCar(ReservationRequest body){
        if (body.getDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date in past not allowed");
        }
        Member member = memberRepository.findById(body.getUserName()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Car car = carsRepository.findById(body.getCarId()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (reservationRepository.existsByCar_IdAndRentalDate(body.getCarId(), body.getDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This car is reserved on this date");
        }
        Reservation res = reservationRepository.save(new Reservation(body.getDate(),member,car));
        return new ReservationResponse(res);


    }

}
