package com.igaopk.userapi.users.mappers;

import com.igaopk.userapi.users.entitys.CellPhone;
import com.igaopk.userapi.users.entitys.User;

import java.util.List;
import java.util.stream.Collectors;

public class CellPhoneMapper {

    public static List<String> parseCellPhoneToList(List<CellPhone> cellPhones) {
        return cellPhones.stream().map(CellPhone::getCellphone).collect(Collectors.toList());
    }

    public static List<CellPhone> parseCellPhoneToObject(List<String> cellPhones, User user) {
        return cellPhones.stream().map(cellphone -> {
            var cellphoneObject = new CellPhone(cellphone);
            cellphoneObject.setUser(user);
            return cellphoneObject;
        }).collect(Collectors.toList());
    }
}
