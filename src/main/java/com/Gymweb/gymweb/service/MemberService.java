package com.Gymweb.gymweb.service;

import com.Gymweb.gymweb.entity.Member;
import com.Gymweb.gymweb.entity.Role;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService extends BaseService<Member> {

    @Autowired
    private MemberRepo memberRepo;




    @Override
    protected void validateUnique(Member entity) throws ValidationException {
        List<Member> memberList = memberRepo.findAll();
        for (Member existingMember : memberList) {
            if (existingMember.getUsername().equalsIgnoreCase(entity.getUsername())) {
                throw new ValidationException("Member " + entity.getEmail() + " already exists.");
            }
        }
    }

    @Override
    public Member add(Member entity) throws ValidationException {
//        if(!validateUnique(entity)){
//            throw new ValidationException("User with email" + entity.getUsername()+ " Already Exist");
//        }
        validateUnique(entity);
        if(entity.getRole() == Role.ADMIN) {
            throw new ValidationException("You can't add a Admin");
        }


        return super.add(entity);
    }


    public Member findByEmail(String email) throws ValidationException {
        return memberRepo.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Member with email " + email + " not found."));
    }

    @Override
    public Member patch(long Id, Member entity) throws ValidationException {

        // Fetch the existing Member (which also includes the User fields)
        Member existingMember = memberRepo.findById(Id)
                .orElseThrow(() -> new ValidationException("Member with ID " + Id + " not found"));

        // First, update the inherited fields from User (i.e., fname, lname, gender, email, etc.)
        super.patch(Id, entity); // Call the base patch method for User fields

        // Now, update the Member-specific fields


        if (entity.getStartDate() != null) {
            existingMember.setStartDate(entity.getStartDate());
        }

        if (entity.getEndDate() != null) {
            existingMember.setEndDate(entity.getEndDate());
        }

        if (entity.getStatus() != null && !entity.getStatus().isBlank()) {
            existingMember.setStatus(entity.getStatus());
        }

        if (entity.getMembership() != null) {
            existingMember.setMembership(entity.getMembership());
        }

        if (entity.getRelaxingAreas() != null && !entity.getRelaxingAreas().isEmpty()) {
            existingMember.setRelaxingAreas(entity.getRelaxingAreas());
        }

        if (entity.getClassNames() != null && !entity.getClassNames().isEmpty()) {
            existingMember.setClassNames(entity.getClassNames());
        }

        // Save the updated member using the generic method
        return super.patch(Id, existingMember); // Save the Member entity
    }


    public void processMemberExpirySchedule(){
        List<Member> expiredMembers = memberRepo.getAllExpiredMembers();

        for(Member member : expiredMembers){
            member.setStatus("expired");
        }
        memberRepo.saveAll(expiredMembers);
    }
}
