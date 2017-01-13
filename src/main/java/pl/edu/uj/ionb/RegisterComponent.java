package pl.edu.uj.ionb;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * Created by mostafil on 13.01.2017.
 */
public class RegisterComponent extends com.vaadin.ui.FormLayout implements View {
    private Label label = new Label("Please register");
    private TextField login = new TextField("Login");
    private PasswordField passwd = new PasswordField("Password");
    private PasswordField passwdRepeat = new PasswordField("Repeat password");
    private Button registerBtn = new Button("Register", this::register);


    public RegisterComponent() {
        this.setMargin(true);
        setSizeUndefined();
        this.passwdRepeat.addValueChangeListener(e -> {
            if(!this.passwd.getValue().equals(this.passwdRepeat.getValue())){
                this.passwdRepeat.setComponentError(new UserError("Password must be repeated exactly"));
            } else {
                this.passwdRepeat.setComponentError(null);
            }
        });
        addComponents(this.label, this.login, this.passwd, this.passwdRepeat, this.registerBtn);
    }

    private void register(Button.ClickEvent event){



    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
