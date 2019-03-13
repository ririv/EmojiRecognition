package com.riri.emojirecognition.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


//用户实体类
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message="用户名不能为空")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //@Email(message="邮箱格式错误")
    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Role> roles;


//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        //将用户角色作为权限
//        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
//        Collection<Role> roles = this.getRoles();
//        for(Role role : roles){
//            auths.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return auths;
//    }


//    @NotNull
//    @Column(name = "enabled")
//    private Boolean enabled;

    public User() {
    }

    public User(@NotNull(message = "用户名不能为空") String username, String password, String email, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

//    @Override
//    public String toString() {
//        return "User [id=" + id + ", username=" + username + ", email=" + email + ", role=" + role + "]";
//    }

}
