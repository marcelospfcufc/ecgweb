package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class AbstractAuthenticationRequestDTO {
    private String login;
    private String key;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }    

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
