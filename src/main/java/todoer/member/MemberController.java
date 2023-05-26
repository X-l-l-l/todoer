//package todoer.member;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import todoer.serviceInterfaces.MemberServiceInterface;
//
//import java.util.List;
//
//
///**
// * Has all the functionalities of a member
// */
//@RestController
//@RequestMapping("member")
//public class MemberController {
//    private final MemberServiceInterface memberService;
//
//    @Autowired
//    public MemberController(MemberServiceInterface memberService) {
//        this.memberService = memberService;
//    }
//
//    /**
//     * @param member the member that needs to be added to the database
//     */
//    @PostMapping
//    public void addMember(@RequestBody Member member)
//    {
//        memberService.addMember(member);
//    }
//
//    /**
//     * @return a list of all members in the database
//     */
//    @GetMapping
//    public List<Member> getMembers() {
//        return memberService.getMembers();
//    }
//
//    /**
//     * @param memberId by which the member in the database, if it exists, is deleted
//     */
//    @DeleteMapping(path = "{memberId}")
//    public void deleteMember(@PathVariable("memberId") Long memberId) {
//        memberService.deleteMember(memberId);
//    }
//
//    /**
//     * @param memberId the id of the member that is updated
//     * @param leader the new state of the user, member or leader
//     */
//    @PutMapping(path = "{memberId}")
//    public void updateMember(
//            @PathVariable("memberId") Long memberId,
//            @RequestParam(required = false) Boolean leader
//    )
//    {
//        memberService.updateMember(memberId, leader);
//    }
//}
