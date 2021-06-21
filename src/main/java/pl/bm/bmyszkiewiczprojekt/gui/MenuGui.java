package pl.bm.bmyszkiewiczprojekt.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("")
public class MenuGui extends VerticalLayout {
    public MenuGui() {
        Button buttonLogin = new Button("Logowanie");
        buttonLogin.addClickListener(e-> UI.getCurrent().navigate("login"));
        Button buttonRegister = new Button("Rejestracja");
        buttonRegister.addClickListener(e-> UI.getCurrent().navigate("signup"));
        Button buttonOrder = new Button("Order");
        buttonOrder.addClickListener(e-> UI.getCurrent().navigate("order"));
        Button buttonOrderService = new Button("OrderService");
        buttonOrderService.addClickListener(e-> UI.getCurrent().navigate("order-service"));
        Button buttonComplaint = new Button("Complaint");
        buttonComplaint.addClickListener(e-> UI.getCurrent().navigate("complaint"));
        Button buttonComplaintService = new Button("ComplaintService");
        buttonComplaintService.addClickListener(e-> UI.getCurrent().navigate("complaint-service"));
        Button buttonLogout = new Button("Logout");
        buttonLogout.addClickListener(e-> UI.getCurrent().navigate("logout"));
        add(buttonLogin, buttonRegister, buttonOrder, buttonOrderService,
                buttonComplaint, buttonComplaintService, buttonLogout);

    }
}
