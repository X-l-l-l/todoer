package todoer.member;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of all the methodology of a member
 */
@Service
public class MemberService {
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
     */
    public void addMember(Member member) {
        memberRepository.save(member);
    }

    /**
     * @param memberId    id of the member to be updated
     * @param leader      modify if the user is a member or a leader
     */
    @Transactional
    public void updateMember(Long memberId, Boolean leader) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("member with id " + memberId + " does not exist"));
        if (leader != null && !Objects.equals(member.getLeader(), leader)) {
            member.setLeader(leader);
        }
    }


    /**
     * @param memberId id of the member to be deleted
     */
    public void deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalStateException(
                    "member with id " + memberId + " does not exist");
        }
        memberRepository.deleteById(memberId);
    }
}
