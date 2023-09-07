package dat3.repository;

import dat3.car.entity.Car;
import dat3.car.repository.CarsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CarsRepositoryTest {
    @Autowired
    CarsRepository carsRepository;
    boolean isInitialized = false;
    @BeforeEach
    void setUp() {
        if (!isInitialized){
            carsRepository.save(new Car("Ford","M1",67,8));
            carsRepository.save(new Car("C2","M2",90,5));
            isInitialized = true;

        }

    }
    @Test
    public void testAll(){
        Long count = carsRepository.count();
        assertEquals(2,count);
    }
    @Test
    public void deleteAll(){
        carsRepository.deleteAll();
        assertEquals(0,carsRepository.count());
    }

}