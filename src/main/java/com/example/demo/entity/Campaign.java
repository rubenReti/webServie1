package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CAMPAIGN")
public class Campaign {


    //@EmbeddedId
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "campaign_id")// updatable=false)
    private int id;
    private String campaignName;
    @ElementCollection
    private List<String> mandatoryFields;


    //@ElementCollection
     //private List<Contact> contacts = new ArrayList<Contact>();

    @OneToMany(targetEntity = Contact.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL},
               mappedBy = "campaign")
    //@OrderBy("name ASC")
    private Set<Contact> contacts = new HashSet<Contact>();;





    public int getId() {
        return id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public List<String> getMandatoryFields() {
        return mandatoryFields;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public void setMandatoryFields(List<String> mandatoryFields) {
        this.mandatoryFields = mandatoryFields;
    }

    public void setContacts( Set<Contact> contacts ) {
        this.contacts = contacts;
    }
}
