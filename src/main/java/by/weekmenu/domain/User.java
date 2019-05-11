package by.weekmenu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails, Serializable {


    private static final long serialVersionUID = -7053215824343088953L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long userID;

    @Column(name = "USERNAME")
    @NotBlank(message = "must have name")
    @Size(min = 3, max = 20, message ="must be between 3 and 20" )
    private String username;

    @NotBlank(message = "must have password")
    @Size(min = 3, max = 255, message = "must be between 3 and 255")
    @Column(name = "PASSWORD")
    private String password;

    @NotBlank(message = "must have firstname")
    @Size(min = 3, max = 255, message = "must be between 3 and 255")
    @Column(name = "FIRSTNAME")
    private String firstName;

    @NotBlank(message = "must have lastname")
    @Size(min = 3, max = 255, message = "must be between 3 and 255")
    @Column(name = "LASTNAME")
    private String lastName;

    @Valid
    @NotNull(message = "not null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;


    @Valid
    @NotNull(message = "Account must be")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserAddress> userAdress;


    @PositiveOrZero(message = "positive or zero")
    @Column(name = "MOBILEPHONE")
    private Long mobilephone;


    public User(String username,String password,String firstName,String lastName,Role role,Account account, Set<UserAddress> userAdress,Long mobilephone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.account = account;
        this.userAdress = userAdress;
        this.mobilephone = mobilephone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        return roleList;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
