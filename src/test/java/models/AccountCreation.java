package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountCreation {
    private String gender;
    private String first_name;
    private String last_name;
    private String password;
    private int day;
    private int month;
    private String year;
    private String address;
    private String city;
    private String state;
    private String zip_code;
    private String country;
    private String additional_information;
    private String mobile_phone;
    private String address_alias;
}