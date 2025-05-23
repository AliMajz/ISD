package com.Gymweb.gymweb.service;

import com.Gymweb.gymweb.dto.MemberDto;
import com.Gymweb.gymweb.entity.*;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.repository.MemberRepo;
import com.Gymweb.gymweb.repository.ScheduleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService extends BaseService<Member> {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ScheduleRepo scheduleRepo;



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

    public Member subscibeMembership(String email, MemberDto dto) throws ValidationException {

        // Fetch the existing Member (which also includes the User fields)
        Member existingMember = memberRepo.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Member with ID " + email + " not found"));

        if(dto.getMembership() != null)
        {
            existingMember.setMembership(dto.getMembership());
            existingMember.setStartDate(LocalDate.now());
            existingMember.setEndDate(setMembershipEnddate(LocalDate.now(), dto.getMembership() ));
            existingMember.setStatus("Active");
        }

        if (dto.getPt() != null) {
            existingMember.setPt(dto.getPt());
        }

        if (dto.getRelaxingAreas() != null && !dto.getRelaxingAreas().isEmpty()) {
            existingMember.setRelaxingAreas(dto.getRelaxingAreas());
        }

        if(dto.getScheduleIds() != null && !dto.getScheduleIds().isEmpty())
        {
            // Assuming you have a method to fetch schedules by their IDs
            List<Schedule> schedules = scheduleRepo.findAllById(dto.getScheduleIds());
            existingMember.setSchedules(schedules);
        }


        // Save the updated member using the generic method
        return super.update(existingMember.getId(), existingMember); // Save the Member entity
    }


    public void processMemberExpirySchedule(){
        List<Member> expiredMembers = memberRepo.getAllExpiredMembers();

        for(Member member : expiredMembers){
            member.setStatus("expired");
        }
        memberRepo.saveAll(expiredMembers);
    }

    @Override
    public List<Member> fetchList() {
        return super.fetchList();
    }

    private LocalDate setMembershipEnddate(LocalDate startDate, Membership membershipType) {
        LocalDate endDate = null;
        switch (membershipType)
        {
            case ONE_MONTH:
                endDate = startDate.plusMonths(1);
                break;
            case THREE_MONTHS:
                endDate = startDate.plusMonths(3);
                break;
            case SIX_MONTHS:
                endDate = startDate.plusMonths(6);
                break;
            case NINE_MONTHS:
                endDate = startDate.plusMonths(9);
            default:
                throw new IllegalArgumentException("Invalid membership type: " + membershipType);
        }
        return endDate;
    }
}
