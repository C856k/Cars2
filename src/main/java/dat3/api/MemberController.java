package dat3.api;

import dat3.dto.MemberRequest;
import dat3.dto.MemberResponse;
import dat3.entity.Member;
import dat3.repository.MemberRepository;
import dat3.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Security Admin only
    @GetMapping
    List<MemberResponse> getMembers(){
        return memberService.getMember(false);
    }

    //Security Admin
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return memberService.findbyId(username);
    }

    //Security Anonymous
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    //Security ???
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        return memberService.editMember(body,username);
    }

    //Security ????
    @PatchMapping("/ranking/{username}/{value}")
    ResponseEntity<Boolean> setRankingForUser(@PathVariable String username, @PathVariable int value) {return null;}

    // Security ????
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {}

}
