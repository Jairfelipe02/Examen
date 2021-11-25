
package net.learnbyproject.model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String emailOrMobile;
    private String password;
    private String birthday;
    private String sex;
    private String avatar;

    public User(int id, String firstName, String lastName, String emailOrMobile, 
            String password, String birthday, String sex, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailOrMobile = emailOrMobile;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailOrMobile() {
        return emailOrMobile;
    }

    public void setEmailOrMobile(String emailOrMobile) {
        this.emailOrMobile = emailOrMobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    
}
