package pl.bm.bmyszkiewiczprojekt.gui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bm.bmyszkiewiczprojekt.entity.Pizza;
import pl.bm.bmyszkiewiczprojekt.repository.PizzaRepository;
import pl.bm.bmyszkiewiczprojekt.service.OrderService;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Route("order")
public class OrderGui extends VerticalLayout {

    private PizzaRepository pizzaRepository;
    private OrderService orderService;


    @Autowired
    public OrderGui(PizzaRepository pizzaRepository, OrderService orderService) {
        this.pizzaRepository = pizzaRepository;
        List<Pizza> all = pizzaRepository.findAll();
        List<Pizza> order = new ArrayList<>();


        Div messageDiv = new Div();
        Grid<Pizza> grid = new Grid<>();
        grid.setItems(all);

        grid.addColumn(Pizza::getName).setHeader("Pizza");
        grid.addColumn(Pizza::getDescription).setHeader("Opis");
        grid.addColumn(Pizza::getIngredients).setHeader("Skladniki").setFlexGrow(3);
        grid.addColumn(Pizza::getPrice).setHeader("Cena");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        messageDiv.setText("Zamowienie puste");
        Button button = new Button("Zamow");
        button.setEnabled(false);
        grid.addItemClickListener(event -> {
            BigDecimal price = new BigDecimal("0");
            StringBuilder stringBuilder = new StringBuilder();
            order.add(event.getItem());
            for (int i = 0; i < order.size(); i++) {
                stringBuilder.append(order.get(i).getName());
                stringBuilder.append(", ");
                price = price.add(order.get(i).getPrice());
            }
            if(order.size()>0){
                messageDiv.setText("Twoje zamówienie to: "+stringBuilder.toString()+"Koszt: "+price);
                button.setEnabled(true);
            }

        });
        button.addClickListener(item->{
            Dialog dialog = new Dialog();
            try {
                orderService.order( order,
                        VaadinRequest.getCurrent().getUserPrincipal().getName());
                dialog.add(new Text("Zamowienie złożone."));
                dialog.open();
            } catch (Exception e) {
                dialog.add(new Text("Zamowienie złożone."));
            }
        });
        add(grid, messageDiv, button);
    }
}
