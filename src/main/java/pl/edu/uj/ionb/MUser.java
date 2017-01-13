package pl.edu.uj.ionb;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mostafil on 08.01.2017.
 */
@Entity
@Table
public class MUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;

    public MUser() {
    }

    public MUser(String username, String password) {
        this.username = username;
        try {
            this.password = MUser.hashPasswd(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean login(){

        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("pl.edu.uj.ionb");
        EntityManager entityManager
                = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String hql = "FROM MUser U WHERE U.username = '" + this.username + "' and U.password = '" + this.password + "'";
        Query query = entityManager.createQuery(hql);
        List<MUser> users = query.getResultList();
        System.out.println("Users size: " + users.size());
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        if(users.size() == 1){
            return true;
        } else{
            return false;
        }
    }

    public void register(){
        System.out.println("Registering...");

        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("pl.edu.uj.io.entity");
        EntityManager entityManager
                = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(this);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("Done");
    }

    public static List<MUser> allUsers(){
        List<MUser> users = new ArrayList<>();
        users.add(new MUser("Jan","Kowalski"));
        users.add(new MUser("Adam","Nowak"));
        return users;
    }

    public static String hashPasswd(String passwd) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(passwd.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof MUser){
            MUser user = (MUser) o;
            if(this.username.equals(user.username) && this.password.equals(user.password)){
                return true;
            }
        }
        return false;
    }
}
