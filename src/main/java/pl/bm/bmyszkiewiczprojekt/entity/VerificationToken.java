package pl.bm.bmyszkiewiczprojekt.entity;

import javax.persistence.*;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne
    private AppUser appUser;

    public VerificationToken() {
    }

    public VerificationToken(String token, AppUser appUser) {
        this.token = token;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public AppUser getAppUser() {
        return appUser;
    }
}
