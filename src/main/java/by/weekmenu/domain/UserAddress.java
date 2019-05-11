package by.weekmenu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "USER_ADDRESS")
@Getter
@Setter
@NoArgsConstructor
public class UserAddress implements Serializable {


    private static final long serialVersionUID = -1190916645592974938L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DISTRICT")
    @Size(max = 20, message = "must be between 3 and 20")
    private String district;


    @Size(max = 20, message = "must be between 3 and 20")
    @Column(name = "REGION")
    private String region;

    @NotBlank(message = "must have city")
    @Size(max = 20, message = "must be between 3 and 20")
    @Column(name = "CITY")
    private String city;

    @Column(name = "HOUSE")
    @NotBlank(message = "must have house")
    private String house;

    @Column(name = "PORCH")
    private String porch;

    @PositiveOrZero(message = "positive or zero")
    @Column(name = "FLOOR")
    private Integer floor;


    @Column(name = "FLAT")
    private String flat;


    @Column(name = "COMMENT")
    @Size(min = 0, max = 1000, message = "more than 1000")
    private String comment;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ADDRESS_ID")
    private User user;



    public UserAddress(String district,String region,String city,String house, String porch,  Integer floor, String flat, String comment,  User user) {
        this.district = district;
        this.region = region;
        this.city = city;
        this.house = house;
        this.porch = porch;
        this.floor = floor;
        this.flat = flat;
        this.comment = comment;
        this.user = user;
    }
}


