package batman;

class UserSession {
    private Long id;
    private String login;

    UserSession(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    UserSession() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
