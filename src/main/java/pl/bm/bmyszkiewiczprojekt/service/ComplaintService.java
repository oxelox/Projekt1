package pl.bm.bmyszkiewiczprojekt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bm.bmyszkiewiczprojekt.entity.AppUser;
import pl.bm.bmyszkiewiczprojekt.entity.Complaint;
import pl.bm.bmyszkiewiczprojekt.repository.AppUserRepository;
import pl.bm.bmyszkiewiczprojekt.repository.ComplaintRepository;

@Service
public class ComplaintService {

    private ComplaintRepository complaintRepository;
    private AppUserRepository appUserRepository;


    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository, AppUserRepository appUserRepository) {
        this.complaintRepository = complaintRepository;
        this.appUserRepository = appUserRepository;
    }


    public void sendComplaint(String value, String name) {
        AppUser byUsername = appUserRepository.findByUsername(name);
        Complaint complaint = new Complaint(value, byUsername);
        complaintRepository.save(complaint);
    }
}
