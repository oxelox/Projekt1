package pl.bm.bmyszkiewiczprojekt.gui;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bm.bmyszkiewiczprojekt.service.AppUserService;


@Route("verification")
public class VerificationGui extends VerticalLayout{

    private AppUserService appUserService;

    @Autowired
    public VerificationGui(AppUserService appUserService) {
        this.appUserService = appUserService;
        String token = VaadinService.getCurrentRequest().getParameter("token");
        Button button = new Button("Zweryfikuj");
        button.addClickListener(buttonClickEvent -> {
            appUserService.verify(token);
            
        });
        add(button);
    }
}
