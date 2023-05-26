//package todoer;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import todoer.group.Group;
//import todoer.member.Member;
//import todoer.member.MemberRepository;
//import todoer.member.MemberService;
//import todoer.serviceInterfaces.MemberServiceInterface;
//import todoer.user.User;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class MemberTests {
//    @Mock
//    private MemberRepository memberRepository;
//
//    List<Member> mockMembers = Arrays.asList(new Member(1L, new User(1L),new Group(1L),true),new Member(2L, new User(2L),new Group(2L),true));
//
//    @Test
//    public void addMemberTest() {
//        MemberServiceInterface toDoListService = new MemberService(memberRepository);
//        Member toDoList = mockMembers.get(0);
//
//        when(memberRepository.save(any(Member.class))).thenReturn(new Member());
//        Boolean result = toDoListService.addMember(toDoList);
//
//        assertTrue(result);
//        verify(memberRepository).save(toDoList);
//    }
//    @Test
//    public void getMemberTest() {
//        MemberServiceInterface toDoListService = new MemberService(memberRepository);
//        when(memberRepository.findAll()).thenReturn(mockMembers);
//
//        List<Member> toDoLists = toDoListService.getMembers();
//        assertEquals(toDoLists, mockMembers);
//        verify(memberRepository).findAll();
//    }
//
//    @Test
//    public void deleteMemberTest() {
//        MemberServiceInterface toDoListService = new MemberService(memberRepository);
//        Member member = mockMembers.get(0);
//
//        when(memberRepository.existsById(member.getUser().getId())).thenReturn(true);
//        ResponseEntity<String> result = toDoListService.deleteMember(member.getUser().getId());
//
//        assertEquals(result.getStatusCode(), HttpStatus.OK);
//        verify(memberRepository).existsById(member.getUser().getId());
//    }
//
//    @Test
//    public void updateMemberTest() {
//        MemberServiceInterface toDoListService = new MemberService(memberRepository);
//        Member member = mockMembers.get(0);
//
//        Member memberafterupdate = new Member(1L, new User(1L),new Group(1L),false);
//
//        when(memberRepository.findById(member.getUser().getId())).thenReturn(Optional.of(member));
//
//        Member memberUpdated = toDoListService.updateMember(member.getUser().getId(), false);
//
//        assertEquals(memberUpdated, memberafterupdate);
//
//        verify(memberRepository).findById(member.getUser().getId());
//    }
//}
