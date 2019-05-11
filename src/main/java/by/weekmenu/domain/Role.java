package by.weekmenu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "ROLE")
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority, Serializable {


    private static final long serialVersionUID = 4448878283161219539L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @NotBlank(message = "role must have name")
    @Column(name = "NAME")
    private String name;


    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> userList;

    public Role(String name) {
        this.name = name;
    }

    public Role( String name, Set< User> userList) {
        this.name = name;
        this.userList = userList;
    }





    @Override
    public String getAuthority() {
        return name;
    }
}
