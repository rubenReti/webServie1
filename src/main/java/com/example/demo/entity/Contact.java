package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
//@Table(name = "contacts")
// @Embeddable
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    private String email;
    private String fName;
    private String lName;
    private String phone;
    private String address;


    //    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ORIG_CMP_ID", referencedColumnName="campaign_id")
    private Campaign campaign;


    public Contact() { }


    public void setId(long id) {
        this.id = id;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public long getId() {
        return id;
    }

    public Campaign getCampaign() {
        return campaign;
    }


    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}






