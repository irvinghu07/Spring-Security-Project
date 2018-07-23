package com.imooc.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append('}');
        return sb.toString();
    }

    public interface UserSimpleView{};

    public interface UserDetailView extends UserSimpleView{};

    private Integer id;

    @MyConstraint(message = "来自MyConstraint的消息")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.setId(0);
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserSimpleView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
