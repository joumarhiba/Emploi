package com.emploi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.regex.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String email;
    private String password;
    private String adresse;
    private String telephone;
    private String image;
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Offre> offres;




    public void setTelephone(String telephone) throws Exception {
        boolean regex= Pattern.compile("(\\+212|0)([ \\-_/]*)(\\d[ \\-_/]*){9}").matcher(telephone).matches();
        if(regex){
            this.telephone = telephone;
        }else {
            throw new Exception("Veuillez entrer un num de tel valide !");
        }
    }
}
