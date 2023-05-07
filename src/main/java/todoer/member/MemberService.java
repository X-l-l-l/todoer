package todoer.member;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import todoer.serviceInterfaces.MemberServiceInterface;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of all the methodology of a member
 */
@Service
public class MemberService implements MemberServiceInterface {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * @return a list of members from the database
     */
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    /**
     * @param member that needs to be added to the database
     * @return
     */
    public Boolean addMember(Member member) {
        memberRepository.save(member);
        return true;
    }

    /**
     * @param memberId id of the member to be updated
     * @param leader   modify if the user is a member or a leader
     * @return
     */
    @Transactional
    public Member updateMember(Long memberId, Boolean leader) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("member with id " + memberId + " does not exist"));
        if (leader != null && !Objects.equals(member.getLeader(), leader)) {
            member.setLeader(leader);
        }
        return member;
    }


    /**
     * @param memberId id of the member to be deleted
     * @return
     */
    public ResponseEntity<String> deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalStateException(
                    "member with id " + memberId + " does not exist");
        }
        memberRepository.deleteById(memberId);
        return new ResponseEntity<>("Member deleted", HttpStatus.OK);
    }
}
