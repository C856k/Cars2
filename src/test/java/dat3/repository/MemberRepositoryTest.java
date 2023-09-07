package dat3.repository;

import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    boolean isInitialized = false;
    @BeforeEach
    void setUp() {
        if (!isInitialized){
            memberRepository.save(new Member("","SoSecure","Nylastraza.crimson@example.com","Nyla","crim","84 Mage quater St","Stormwind","6942"));
            memberRepository.save(new Member("LenosaCrimson","Topsecure","Lenosa.crimson@example.com","Leno","crim","87 old town St","Stormwind","9000"));
            isInitialized = true;

        }
    }
    @Test
    public void deleteAll(){
        memberRepository.deleteAll();
        assertEquals(0,memberRepository.count());
    }

    @Test
    public void testAll(){
        Long count = memberRepository.count();
        assertEquals(2, count);
    }

}