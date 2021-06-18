package pl.bm.bmyszkiewiczprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bm.bmyszkiewiczprojekt.entity.AppUser;
import pl.bm.bmyszkiewiczprojekt.entity.VerificationToken;
import pl.bm.bmyszkiewiczprojekt.repository.AppUserRepository;
import pl.bm.bmyszkiewiczprojekt.repository.VerificationTokenRepository;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@Transactional
public class AppUserService {

    private AppUserRepository appUserRepository;
    private MailSenderService mailSenderService;
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, MailSenderService mailSenderService, VerificationTokenRepository verificationTokenRepository) {
        this.appUserRepository = appUserRepository;
        this.mailSenderService = mailSenderService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public void signup(AppUser appUser, HttpServletRequest httpServletRequest) throws MessagingException {
        appUserRepository.save(appUser);
        String token = UUID.randomUUID().toString();
        System.out.println(appUser);

        VerificationToken verificationToken = new VerificationToken(token, appUser);
        verificationTokenRepository.save(verificationToken);

        String url = "http://"+ httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort()
                + httpServletRequest.getContextPath() + "/verification?token="+token;

        mailSenderService.sendMail(appUser.getEmail(), "Verification Token", url , false);
    }

    public void verify(String token){
        System.out.println(token);
        AppUser appUser = verificationTokenRepository.findByToken(token).getAppUser();
        appUser.setEnabled(true);
        appUserRepository.save(appUser);
        verificationTokenRepository.deleteByToken(token);
    }

}
