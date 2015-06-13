package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Member;
import ke.co.turbosoft.compass.entity.Principal;

import java.util.List;

/**
 * Created by ktonym on 1/2/15.
 */
public interface PrincipalService {
    List<Principal> findAll();
    List<Member> listMembers(Principal principal);
    void addMember(Member member);
    void removeMember(Member member);
    void save(Principal principal);
}
