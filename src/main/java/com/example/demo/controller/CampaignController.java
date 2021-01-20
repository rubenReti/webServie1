package com.example.demo.controller;

import com.example.demo.entity.Campaign;
import com.example.demo.entity.Contact;
import com.example.demo.service.CampaignService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/marketing/ws/partner/campaign")
@RestController
public class CampaignController {

    @Autowired
    private CampaignService service;

    @PostMapping
    public Campaign addCampaign(@NonNull @RequestBody Campaign campaign){
        return service.saveCampaign(campaign);

    }

    @PostMapping("/addCampaigns")
    public List<Campaign> addCampaigns(@RequestBody List<Campaign> campaigns){
        return service.saveCampaigns(campaigns);
    }

    @GetMapping("/AllCampaigns")
    public List<Campaign> findAllCampaigns() {
        return service.getCampaigns();
    }


    @GetMapping(path = "{id}")
    public Campaign findCampaignById(@PathVariable("id") int id){
        return service.getCampaignById(id);
    }
/*
    @GetMapping("/Campaign/{name}")
    public List<Campaign> findCampaignByName(@PathVariable String name){
        return service.getCampaignByName(name);
    }
*/
    @PutMapping(path = "{id}")
    public Campaign updateCampaign(@NonNull @RequestBody Campaign campaign, @PathVariable("id") int id ){
        return service.updateCampaign(campaign, id);
    }


    @DeleteMapping(path = "{id}")
    public String deleteCampaign(@PathVariable("id") int id) {
        return service.deleteCampaign(id);
    }


    @PostMapping("/{CampaignId}/registration")
    public Contact addContact(@NonNull @RequestBody Contact contact,
                              @PathVariable("CampaignId") int campId){
        /*Throw new
                CompaignNotFoundForContactException("Wrong CampignNo");
        return null;*/return service.saveContact(contact, campId);

    }

/*exceptions*/
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class CompaignNotFoundForContactException extends  RuntimeException{
        public     CompaignNotFoundForContactException(String msg) {
            super(msg);
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class WrongContactDetails extends  RuntimeException{
        public     WrongContactDetails(String msg) {
            super(msg);
        }

    }


    @ResponseStatus(HttpStatus.CONFLICT)
    public static class NewContactAlreadyExits extends  RuntimeException{
        public     NewContactAlreadyExits(String msg) {
            super(msg);
        }

    }


}
