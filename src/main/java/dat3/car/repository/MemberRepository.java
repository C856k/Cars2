package dat3.car.repository;

import dat3.car.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {

    @Query("SELECT DISTINCT m from Member m join fetch m.reservations")
    List<Member> findAllMembersWithReservations();
}
