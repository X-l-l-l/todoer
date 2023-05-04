package todoer.serviceInterfaces;

import jakarta.transaction.Transactional;
import todoer.member.Member;

import java.util.List;
import java.util.Objects;

public interface MemberServiceInterface {
    public List<Member> getMembers();

    public void addMember(Member member);
    public void updateMember(Long memberId, Boolean leader);
    public void deleteMember(Long memberId);
}
