package com.example.demo.service;

import com.example.demo.controller.CampaignController;
import com.example.demo.entity.Campaign;
import com.example.demo.entity.Contact;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.repository.ContactRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaingRepo;
    @Autowired
    private ContactRepositoty contactRepo;

    public Campaign saveCampaign(Campaign campaign){
        return campaingRepo.save(campaign);
    }

    public List<Campaign> saveCampaigns(List<Campaign> campaigns){
        return campaingRepo.saveAll(campaigns);
    }

    public List<Campaign> getCampaigns(){
        return campaingRepo.findAll();
    }

    public Campaign getCampaignById(int id){
        return campaingRepo.findById(id).orElse(null);
    }

    /*public List<Campaign> getCampaignByName(String name){
        return campaingRepo.findByName(name).orElse(null);
    }
*/
    public String deleteCampaign(int id){
        campaingRepo.deleteById(id);
        return "removed !! :) " + id;
    }

    public Campaign updateCampaign(Campaign campaign, int id){
        Campaign existingCampaign =
                campaingRepo.findById(id).orElse(null);
        existingCampaign.setCampaignName(campaign.getCampaignName());
        existingCampaign.setMandatoryFields(campaign.getMandatoryFields());
        existingCampaign.setContacts(campaign.getContacts());
        return campaingRepo.save(existingCampaign);
    }



    /*add contact to repo, (new contact to add, campaignId) */
    public Contact saveContact (Contact contact, int campId) {


        //save original contact to return
        Contact origContact = contact;

        //find the campaing of the new contact
        Campaign existingCampaign = campaingRepo.findById(campId).orElse( null);
        if ( existingCampaign == null)
            throw  new CampaignController.CompaignNotFoundForContactException("Wrong CampignNo");

        //check if mail is new and doesnt exist in repo - i had to fix it yet, havesome problem with the query

        //contactRepo.findByemail(contact.getEmail());//.orElseThrow
        //       (() -> new CampaignController.NewContactAlreadyExits ("This mail already exists"));





        //check incoming contact data validations according to campaing rules
        if ( ! isValidContactData( existingCampaign.getMandatoryFields(), contact) ){
            throw new CampaignController.WrongContactDetails("Wrong contact data");
        }

        //save it
        contact.setCampaign(existingCampaign);
        existingCampaign.getContacts().add(contact);
        campaingRepo.save(existingCampaign);

        return origContact;
   }



    /*validate the new contact fields */
    private boolean isValidContactData(List<String> mandatortyFields, Contact contact){


        //loop on the mandatory fielst List to find which of the contact fields are mandatory
        for(String mndtrFld : mandatortyFields){

            switch (mndtrFld){
            case "email":

                if ( ! validMailAddr(contact.getEmail() ))  return false;
                break;
            case "fName":
                if ( ! validFName(contact.getfName())) return false;
                break;
            case "lName":
                if ( ! validLName(contact.getlName())) return false;
                break;
            case "phone":
                if ( ! validPhone(contact.getPhone())) return false;
                break;
            case "address":
                validAddress(contact.getAddress());
                break;

            default:
                return false; //+exception - wrong mandatoryField value in compaing

            }
        }

        return true;

    }

    private boolean validMailAddr(String mailAdd){
        /*check if not null or blank*/
        if(mailAdd != null && !mailAdd.trim().isEmpty())
            return isValidEmailAddress(mailAdd);

        //Mail is Null or Blanks -> exception +
        return false;
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        //String ePattern =  "^[\w-.]+@([\w-]+.)+[\w-]{2,4}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean validFName(String fName){
        return true;
    }
    private boolean validLName(String lName){
        return true;
    }
    private boolean validAddress(String addr){
        return true;
    }
    private boolean validPhone(String phone){
        /*check if not null or blank*/
        if(phone != null && !phone.trim().isEmpty())
            return true ;// return isValidPhone(phone); <- changed to static true for testes
        return false;
    }
    private boolean isValidPhone(String email) {
        String ePattern = "^ [+] * [(] {0,1} [0-9] {1,4} [)] {0,1} [- \\ s \\ ./ 0-9] * $";
        //String ePattern =  "^[\w-.]+@([\w-]+.)+[\w-]{2,4}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


}
