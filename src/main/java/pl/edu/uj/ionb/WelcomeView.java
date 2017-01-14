package pl.edu.uj.ionb;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.function.Consumer;

/**
 * Created by mostafil on 10.01.2017.
 */
public class WelcomeView extends VerticalLayout implements View {
    public static final String NAME = "";

    Label welcomeMsg;
    Button logoutButton;
    Button newNoteButton;
    MyUI myUI;
    Consumer logoutCallback;


    public WelcomeView(MyUI myUI, Consumer logoutCallback) {
        setMargin(true);
        setSizeFull();
        this.myUI = myUI;
        this.logoutCallback = logoutCallback;
        welcomeMsg = new Label("Welcome !");
        logoutButton = new Button("Log out", this::logout);
        newNoteButton = new Button("Add note");
        addComponents(welcomeMsg, logoutButton);
        setSpacing(true);


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


        for(Note note : user.getAllNotes()){
            RichTextArea noteEditor = new RichTextArea();
            noteEditor.setValue(note.getContent());
            noteEditor.setImmediate(true);
            noteEditor.addValueChangeListener(e -> {
                note.setContent(noteEditor.getValue().toString());
                note.save();
                Notification.show("Note saved",
                        String.valueOf(note.getId()),
                        Notification.Type.HUMANIZED_MESSAGE);
            });
            addComponent(noteEditor);
        }

        newNoteButton.addClickListener( e -> {
            Note newNote = new Note(user);
            RichTextArea noteEditor = new RichTextArea();
            noteEditor.addValueChangeListener( a -> {
                newNote.setContent(noteEditor.getValue().toString());
                newNote.save();
                Notification.show("Note saved",
                        String.valueOf(newNote.getId()),
                        Notification.Type.HUMANIZED_MESSAGE);
            });
            //add new noteEditor before new note button
            addComponent(noteEditor,getComponentCount()-1);

        });

        addComponent(newNoteButton);



    }
}