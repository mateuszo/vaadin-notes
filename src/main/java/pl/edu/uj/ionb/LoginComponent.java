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
    private Button registerBtn = new Button("Register", this::register);
    private MyUI myUI;
    private Consumer<MUser> callback;

    public LoginComponent(MyUI myUI, Consumer<MUser> loginCallback){
        this.setMargin(true);
        this.myUI = myUI;
        this.callback = loginCallback;
        setSizeUndefined();
        addComponents(this.label, this.login, this.passwd, this.logInbtn, this.registerBtn);

    }

    private void login(Button.ClickEvent event){
        MUser user = new MUser(this.login.getValue(), this.passwd.getValue());
        if(user.login()){
            this.myUI.getSession().setAttribute("MUser", user);
            this.callback.accept(user);
        } else {
            this.label.setValue("Invalid password or login");
            this.label.setComponentError(new UserError("Invalid password or login"));
        }
    }

    private void register(Button.ClickEvent event){
        this.myUI.getCurrent().getNavigator().navigateTo("Register");
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
