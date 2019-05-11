package by.weekmenu.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_MENU")
public class UserMenu implements Serializable{


    private static final long serialVersionUID = -356731286677781913L;

    public UserMenu() {
    }

    @Embeddable
    public static class Id implements Serializable {


        @Column(name = "user_id")
        private User user;

        @Column(name = "MENU_ID")
        /// still some time for a while (String)
        private String menu;
    }

    @EmbeddedId
    private Id id = new Id();
}
