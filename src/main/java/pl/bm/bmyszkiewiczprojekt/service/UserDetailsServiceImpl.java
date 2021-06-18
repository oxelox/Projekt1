package pl.bm.bmyszkiewiczprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bm.bmyszkiewiczprojekt.entity.AppUser;
import pl.bm.bmyszkiewiczprojekt.repository.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser byUsername = appUserRepository.findByUsername(username);
        return byUsername;
    }
}
