package br.ufc.deti.ecgweb.domain.security;

import br.ufc.deti.ecgweb.domain.client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;


/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name = "login")
public class Login implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    
    private String login;
    private String password;    
    
    private String uuid;
    
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean enable;
    
    @Column(name = "last_access")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastAccess;
    
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){        
        /*AbstractDigest digest = DigestCreator.FactoryMethod();
        String passwordDigest = digest.getHashValue(password);
        this.password = passwordDigest;*/
        this.password = password;
        
    }    

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Client getClient() {
        return client;
    }
    
    public void updateData() {
        this.uuid = UUID.randomUUID().toString();
        this.lastAccess = LocalDateTime.now();        
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }
}
