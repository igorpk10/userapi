package com.igaopk.userapi.users.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_CELLPHONES")
@Getter
@Setter
@Data
public class CellPhone implements Serializable {

    @Id
    @Column(name = "phone_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cell_phone")
    private String cellphone;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private Long userId;

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
            return objCellphone.getId().equals(this.getId());
        }

        return false;
    }
}
