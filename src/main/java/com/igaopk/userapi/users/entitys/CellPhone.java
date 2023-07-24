package com.igaopk.userapi.users.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "cellphone")
@Table(name = "TB_CELLPHONES")
@Getter
@Setter
@Data
public class CellPhone implements Serializable {

    @Id
    @Column(name = "phone_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cell_phone")
    private String cellphone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" )
    private User user;

    public CellPhone() {

    }

    public CellPhone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof String){
            var phone = (String) obj;
            return this.getCellphone().equals(phone);
        }

        if(obj instanceof CellPhone objCellphone){
            return objCellphone.getId() == this.getId();
        }

        return false;
    }
}
