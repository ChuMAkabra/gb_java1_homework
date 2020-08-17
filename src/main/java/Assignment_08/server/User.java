package Assignment_08.server;

import java.util.StringJoiner;

public class User {
    private int id;
    private String nick;
    private String login;

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getNick() {
        return nick;
    }

    public User setNick(String nick) {
        this.nick = nick;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getNick() != null ? !getNick().equals(user.getNick()) : user.getNick() != null) return false;
        return getLogin() != null ? getLogin().equals(user.getLogin()) : user.getLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getNick() != null ? getNick().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nick='" + nick + "'")
                .add("login='" + login + "'")
                .toString();
    }
}
