package pl.bm.bmyszkiewiczprojekt.gui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.bm.bmyszkiewiczprojekt.entity.AppUser;
import pl.bm.bmyszkiewiczprojekt.entity.AppUserDetails;
import pl.bm.bmyszkiewiczprojekt.service.AppUserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;


@Route("signup")
public class RegisterGui extends VerticalLayout {

    private AppUserService appUserService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterGui(AppUserService appUserService, PasswordEncoder passwordEncoder, HttpServletRequest httpServletRequest) {
        this.appUserService = appUserService;

        TextField loginField = new TextField("Login", "Login");
        PasswordField passwordField = new PasswordField("Haslo", "Haslo");
        EmailField emailField = new EmailField("Email", "Email");
        emailField.setErrorMessage("Format adresu mail jest niepoprawny");

        HorizontalLayout userDetailsLayout = new HorizontalLayout();
        TextField cityField = new TextField("Miasto", "Miasto");
        TextField streetField = new TextField("Ulica", "Ulica");
        IntegerField numberField = new IntegerField("Numer mieszkania", "Numer");
        userDetailsLayout.add(cityField, streetField, numberField);

        Button button = new Button("Zarejestruj");

        button.addClickListener(buttonClickEvent -> {
            AppUser appUser = new AppUser(loginField.getValue(), passwordEncoder.encode(passwordField.getValue()),
                    emailField.getValue(), false, "ROLE_USER");
            AppUserDetails appUserDetails = new AppUserDetails(cityField.getValue(), streetField.getValue(),
                    numberField.getValue());
            appUser.setAppUserDetails(appUserDetails);
            appUserDetails.setAppUser(appUser);
            Dialog dialog = new Dialog();
            try {
                appUserService.signup(appUser, httpServletRequest);
                dialog.add(new Text("Rejestracja udana na twój adres został wysłany link aktywacyjny."));
                dialog.open();
            } catch (MessagingException e) {
                dialog.add(new Text("Rejestracja niepomyślna spróbuj ponownie."));
                dialog.open();
            }
        });
        add(loginField, passwordField, emailField, userDetailsLayout, button);
    }
}
