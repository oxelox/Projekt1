package pl.bm.bmyszkiewiczprojekt.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bm.bmyszkiewiczprojekt.service.ComplaintService;

@Route("complaint")
public class ComplaintGui extends VerticalLayout {

    private ComplaintService complaintService;

    @Autowired
    public ComplaintGui(ComplaintService complaintService) {
        this.complaintService = complaintService;
        Label label = new Label("Skladanie reklamacji");
        TextArea textArea = new TextArea("Opis");
        textArea.setPlaceholder("Wpisz tresc reklamacji...");
        Button button = new Button("Zloz reklamacje");
        button.addClickListener(e -> {
            complaintService.sendComplaint(textArea.getValue(), VaadinRequest.getCurrent().getUserPrincipal().getName());
        });
        add(label, textArea, button);
    }
}
