package by.weekmenu.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
public class Account implements Serializable {


    private static final long serialVersionUID = -4532981139615355119L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;


    @Column(name = "FAMILY_MEMBERS")
    @PositiveOrZero(message = "must have positive or zero value")
    private Integer familyMembers;


    @Column(name = "FAMILY_NAME", unique = true)
    @NotBlank(message =  "Account must have name")
    @Size(min = 3, max = 20, message = "must be between 3 and 20")
    private String name;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> userList;


    public Account(Integer familyMembers,String name) {
        this.familyMembers = familyMembers;
        this.name = name;
    }

    public Account() {
    }


}
