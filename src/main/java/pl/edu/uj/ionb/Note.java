package pl.edu.uj.ionb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;

/**
 * Created by mostafil on 13.01.2017.
 */
@Entity
@Table
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="content", length = 32000)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name = "MUSER_ID")
    private MUser user;

    public Note() {
    }

    public MUser getUser() {
        return user;
    }

    public void setUser(MUser user) {
        this.user = user;
    }

    public Note(MUser user) {
        this.user = user;
    }

    public Note(String content, MUser user) {
        this.content = content;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void save(){
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("pl.edu.uj.ionb");
        EntityManager entityManager
                = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(this);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();




    }


}
