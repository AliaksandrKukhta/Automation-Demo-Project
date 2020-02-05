package model;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String e_mail;

    public static class Builder{
        private User newUser;

        public Builder(){
            newUser = new User();
        }

        public Builder withId(int id){
            newUser.id=id;
            return this;
        }

        public Builder withName(String name){
            newUser.name=name;
            return this;
        }

        public Builder withLogin(String login){
            newUser.login=login;
            return this;
        }

        public Builder withPassword(String password){
            newUser.password=password;
            return this;
        }

        public Builder withE_mail(String e_mail){
            newUser.e_mail=e_mail;
            return this;
        }

        public User build(){
            return newUser;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    @Override
    public String toString() {
        return "User: ID=" + this.id + " data.Name=" + this.name + " Login=" + this.login + " Password="
                + this.password + " E_mail=" + this.e_mail;
    }
}
