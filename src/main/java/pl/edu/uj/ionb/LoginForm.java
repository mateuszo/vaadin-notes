package pl.edu.uj.ionb;

import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * Created by mostafil on 08.01.2017.
 */
public class LoginForm extends com.vaadin.ui.VerticalLayout {
    private TextField login = new TextField("Login");
    private PasswordField passwd = new PasswordField("Password");

    public LoginForm() {
    }
}
