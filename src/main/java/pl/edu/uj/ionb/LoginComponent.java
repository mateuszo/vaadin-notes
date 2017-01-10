package pl.edu.uj.ionb;


import com.vaadin.ui.*;

public class LoginComponent extends com.vaadin.ui.FormLayout {
    private Label label = new Label("Please log in");
    private TextField login = new TextField("Login");
    private PasswordField passwd = new PasswordField("Password");
    private Button logInbtn = new Button("Log in", this::login);
    private Button logOutBtn = new Button("Log out", this::logout);
    private MyUI myUI;

    public LoginComponent(MyUI myUI){
        this.myUI = myUI;
        setSizeUndefined();

        addComponents(this.label);

        User user = (User) this.myUI.getSession().getAttribute("User");
        if(user == null){
            addComponents(this.login, this.passwd, this.logInbtn);
        } else{
            this.label.setValue("Logged in as: " + user.getLogin());
            addComponent(this.logOutBtn);
        }
    }

    private void login(Button.ClickEvent event){
        User user = new User(this.login.getValue(), this.passwd.getValue());
        if(user.login()){
            this.myUI.getSession().setAttribute("User", user);
            this.label.setValue("Logged in as: " + user.getLogin());
            removeComponent(this.login);
            removeComponent(this.passwd);
            removeComponent(this.logInbtn);
            addComponent(this.logOutBtn);
        } else {
            this.label.setValue("Unable to log in");
        }
    }

    private void logout(Button.ClickEvent event){
        this.myUI.getSession().setAttribute("User", null);
        label.setValue("Please log in");
        removeComponent(this.logOutBtn);
        addComponents(this.login, this.passwd, this.logInbtn);
    }

}