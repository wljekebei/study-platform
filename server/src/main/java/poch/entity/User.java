package poch.entity;
import jakarta.persistence.*;

@Entity
@Table(name="users")

public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String name;

    @Column (unique = true)
    private String email;
    private String password_hash;

    public User(){}
        public Long getUser_id(){
        return user_id;
        }
        public void setUser_id(Long user_id){
            this.user_id = user_id;
        }

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getEmail(){
            return email;
        }
        public void setEmail(String email){
            this.email = email;
        }
        public String getPassword_hash(){
            return password_hash;
        }
        public void setPassword_hash(String password_hash){
            this.password_hash = password_hash;
        }

}
