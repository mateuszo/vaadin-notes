package pl.edu.uj.ionb;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.function.Consumer;

/**
 * Created by mostafil on 10.01.2017.
 */
public class WelcomeView extends VerticalLayout implements View {
    public static final String NAME = "";

    Label welcomeMsg;
    Button logoutButton;
    MyUI myUI;
    Consumer logoutCallback;

    public WelcomeView(MyUI myUI, Consumer logoutCallback) {
        setSizeFull();
        this.myUI = myUI;
        this.logoutCallback = logoutCallback;
        welcomeMsg = new Label("Welcome !");
        logoutButton = new Button("Log out", this::logout);

        addComponent(welcomeMsg);
        addComponent(logoutButton);

    }

    private void logout(Button.ClickEvent event){
        this.myUI.getSession().setAttribute("User", null);
        this.logoutCallback.accept(null);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        User user = (User) myUI.getSession().getAttribute("User");
        welcomeMsg.setValue("Welcome " + user.getLogin() + "!");

    }
}