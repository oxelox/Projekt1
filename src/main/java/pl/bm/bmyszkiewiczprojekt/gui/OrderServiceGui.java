package pl.bm.bmyszkiewiczprojekt.gui;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import pl.bm.bmyszkiewiczprojekt.entity.Order;

import pl.bm.bmyszkiewiczprojekt.entity.Status;
import pl.bm.bmyszkiewiczprojekt.repository.OrderRepository;

import pl.bm.bmyszkiewiczprojekt.service.OrderService;

import java.math.BigDecimal;
import java.util.*;


@Route("order-service")
public class OrderServiceGui extends VerticalLayout {

    private OrderRepository orderRepository;
    private OrderService orderService;


    @Autowired
    public OrderServiceGui(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        List<Order> all = orderRepository.findAll();

        Grid<Order> grid = new Grid<>();
        grid.setItems(all);

        Binder<Order> binder = new Binder<>(Order.class);
        Editor<Order> editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);
        Collection<Button> editButtons = Collections
                .newSetFromMap(new WeakHashMap<>());


        grid.addColumn(order -> order.getAppUser().getUsername()).setHeader("User");
        grid.addColumn(Order::getDescription).setHeader("Zamowienie");
        grid.addColumn(Order::getOrderDate).setHeader("Data zlozenia zamowienia");
        grid.addColumn(Order::getPrice).setHeader("Wartosc");
        grid.addColumn(Order::getStatus).setHeader("Status");
        grid.addColumn(order -> order.getDeliverer().getFirstName()+" "+order.getDeliverer().getLastName()).setHeader("Dostawca");
        Grid.Column<Order> editorColumn = grid.addComponentColumn(order -> {
            Button edit = new Button("Zmiana statusu");
            edit.addClassName("edit");

            edit.addClickListener(e -> {
                editor.editItem(order);

            });
            edit.setEnabled(!editor.isOpen());
            if(order.getStatus().equals(Status.KONIEC)){
                edit.setEnabled(false);
            }
            editButtons.add(edit);
            return edit;
        });
        editor.addOpenListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));
        editor.addCloseListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));
        Button save = new Button("Zmien", e -> editor.save());
        save.addClassName("save");
        Button cancel = new Button("Anuluj", e -> editor.cancel());
        cancel.addClassName("cancel");
        grid.getElement().addEventListener("keyup", event -> editor.cancel())
                .setFilter("event.key === 'Escape' || event.key === 'Esc'");
        Div buttons = new Div(save, cancel);
        editorColumn.setEditorComponent(buttons);
        editor.addSaveListener(
                event -> {
                    orderService.changeStatus(event.getItem().getId());
                    UI.getCurrent().getPage().reload();
                });
        add(grid);
    }
}
