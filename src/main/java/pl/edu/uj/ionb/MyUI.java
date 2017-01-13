package pl.edu.uj.ionb;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    Navigator navigator;
    Panel contentArea;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        contentArea = new Panel("Content");

        layout.addComponent(contentArea);
        navigator = new Navigator(UI.getCurrent(), contentArea);

        WelcomeView lv = new WelcomeView(this, user -> navigator.navigateTo("LoginComponent"));
        LoginComponent lc = new LoginComponent(this, user -> navigator.navigateTo("WelcomeView"));
        RegisterComponent rc = new RegisterComponent(this);

        navigator.addView("WelcomeView", lv);
        navigator.addView("LoginComponent", lc);
        navigator.addView("Register", rc);


        setContent(contentArea);

        MUser user = (MUser) this.getSession().getAttribute("MUser");
        if(user == null){
            navigator.navigateTo("LoginComponent");
        } else {
            navigator.navigateTo("WelcomeView");
        }
    }




    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
