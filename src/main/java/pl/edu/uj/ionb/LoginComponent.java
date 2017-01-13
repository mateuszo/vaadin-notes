package pl.edu.uj.ionb;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.util.function.Consumer;

public class LoginComponent extends com.vaadin.ui.FormLayout implements View {
    private Label label = new Label("Please log in");
    private TextField login = new TextField("Login");
    private PasswordField passwd = new PasswordField("Password");
    private Button logInbtn = new Button("Log in", this::login);
    private MyUI myUI;
    private Consumer<User> callback;

    public LoginComponent(MyUI myUI, Consumer<User> loginCallback){
        this.setMargin(true);
        this.myUI = myUI;
        this.callback = loginCallback;
        setSizeUndefined();
        addComponents(this.label, this.login, this.passwd, this.logInbtn);

    }

    private void login(Button.ClickEvent event){
        User user = new User(this.login.getValue(), this.passwd.getValue());
        if(user.login()){
            this.myUI.getSession().setAttribute("User", user);
            this.callback.accept(user);
        } else {
            this.label.setValue("Invalid password or login");
            this.label.setComponentError(new UserError("Invalid password or login"));
        }
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
