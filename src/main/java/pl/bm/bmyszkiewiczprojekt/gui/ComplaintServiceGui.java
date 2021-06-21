package pl.bm.bmyszkiewiczprojekt.gui;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bm.bmyszkiewiczprojekt.entity.Complaint;
import pl.bm.bmyszkiewiczprojekt.entity.Order;
import pl.bm.bmyszkiewiczprojekt.entity.Status;
import pl.bm.bmyszkiewiczprojekt.repository.ComplaintRepository;
import pl.bm.bmyszkiewiczprojekt.repository.OrderRepository;
import pl.bm.bmyszkiewiczprojekt.service.ComplaintService;
import pl.bm.bmyszkiewiczprojekt.service.OrderService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;


@Route("complaint-service")
public class ComplaintServiceGui extends VerticalLayout {

    private ComplaintService complaintService;
    private ComplaintRepository complaintRepository;


    @Autowired
    public ComplaintServiceGui(ComplaintService complaintService, ComplaintRepository complaintRepository) {
        this.complaintService = complaintService;
        this.complaintRepository = complaintRepository;
        List<Complaint> all = complaintRepository.findAll();

        Grid<Complaint> grid = new Grid<>();
        grid.setItems(all);

        grid.addColumn(complaint -> complaint.getAppUser().getUsername()).setHeader("User");
        grid.addColumn(Complaint::getDescription).setHeader("Tresc reklamacji");

        add(grid);
    }
}
