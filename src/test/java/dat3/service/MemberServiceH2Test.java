package dat3.service;

import dat3.car.service.MemberService;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;

    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email1", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        assertEquals(2,memberResponses.size(),"Expects 2 members");
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(time,"Expects dates to be set when true is passed");

    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        assertEquals(2,memberResponses.size(),"Expects 2 members");
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNull(time,"Expects dates not to be set when false is passed");
    }

    @Test
    void testFindByIdFound() {
        MemberResponse res = memberService.findbyId("user1");
        assertEquals("user1",res.getUsername());
    }

    @Test
    void testFindByIdNotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
        ()->memberService.findbyId("WillFail"));
        //assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember savex a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        MemberRequest request = MemberRequest.builder().
                username("user5").
                password("pw5").
                firstName("fn5").
                lastName("ln5").
                build();
        MemberResponse res = memberService.addMember(request);
        assertEquals("user5",res.getUsername());
        assertTrue(memberRepository.existsById("user5"));
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        MemberRequest request = new MemberRequest();
        request.setUsername("user1");
        request.setEmail("email1");
        request.setPassword("pw1");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> memberService.addMember(request));
        assertEquals(HttpStatus.BAD_REQUEST,exception.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        MemberRequest request = new MemberRequest(m1);
        request.setFirstName("New firstName");
        request.setLastName("New lastName");
        memberService.editMember(request,m1.getUsername());

        memberRepository.flush();
        MemberResponse response = memberService.findbyId(m1.getUsername());
        assertEquals("user1",response.getUsername());
        assertEquals("New firstName",response.getFirstName());
        assertEquals("New lastName",response.getLastName());
        assertEquals("email1",response.getEmail());
        assertEquals("street1",response.getStreet());
        assertEquals("zip1",response.getZip());
        assertEquals("city1",response.getCity());
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        MemberRequest request = new MemberRequest();
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> memberService.editMember(request,"I do not exist"));
        //assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        MemberRequest request = new MemberRequest(m1);
        request.setUsername("new Username");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> memberService.editMember(request,"user1"));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        //assertEquals("Unable to change username",exception.getReason());
    }

    @Test
    void testSetRankingForUser() {
        memberService.setRankingForUser("user1",8);
        MemberResponse response = memberService.findbyId("user1");
        assertEquals(8,response.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> memberService.setRankingForUser("user11",3));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMemberByUsername("user1");
        assertFalse(memberRepository.existsById("user1"));

    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> memberService.deleteMemberByUsername("user10"));
        assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());
    }
}
