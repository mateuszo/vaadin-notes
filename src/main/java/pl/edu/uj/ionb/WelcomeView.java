package pl.edu.uj.ionb;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
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
        setMargin(true);
        setSizeFull();
        this.myUI = myUI;
        this.logoutCallback = logoutCallback;
        welcomeMsg = new Label("Welcome !");
        logoutButton = new Button("Log out", this::logout);


    }

    private void logout(Button.ClickEvent event){
        this.myUI.getSession().setAttribute("MUser", null);
        this.logoutCallback.accept(null);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        MUser user = (MUser) myUI.getSession().getAttribute("MUser");
        welcomeMsg.setValue("Welcome " + user.getUsername() + "!");
        System.out.println("User id: " + user.getId());

//        for(Note note : user.notes){
//            System.out.println(note.getContent());
//        }

//        Note note = new Note(user);
//        user.addNote(note);
        RichTextArea sample = new RichTextArea();
        sample.setValue("The quick brown fox jumps over the lazy dog.");
        sample.setImmediate(true);
//        sample.setSizeFull();
        addComponent(sample);

        sample.addValueChangeListener(e -> {
            Note note = new Note(sample.getValue().toString(), user);
//            note.setContent(sample.getValue().toString());
            note.save();
        });


    }
}