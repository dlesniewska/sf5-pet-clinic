package dagimon.spring5course.sf5petclinic.controllers;

import dagimon.spring5course.sf5petclinic.model.Owner;
import dagimon.spring5course.sf5petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    public static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder binder){
        binder.setDisallowedFields("id");
    }

//    //INDEX
//    @GetMapping({"", "/", "/index", "/index.html"})
//    public String list(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

    //FIND
    @GetMapping("/find")
    public String findOwner(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if(owner.getLastName()==null) {
            owner.setLastName("");
        }
        List<Owner> foundOwners = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        if(foundOwners.isEmpty()) { //none -> back to search page
            result.rejectValue("lastName", "notFound", "nie znaleziono");
            return "owners/findOwners";
        } else if(1 == foundOwners.size()) {//1 found -> display its details
            owner = foundOwners.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else { //multiple -> display a list
            model.addAttribute("selections", foundOwners);
            return "owners/ownersList";
        }
    }

    //OWNER DETAILS by ID
    @GetMapping("/{ownerId}")
    public ModelAndView displayOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner", ownerService.findById(Long.valueOf(ownerId)));
        return mav;
    }

    //CREATE
    @GetMapping("/new")
    public String initCreateForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Owner owner, BindingResult binding){
        if(binding.hasErrors()) {
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        }
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    //UPDATE
    @GetMapping("/{ownerId}/edit")
    public String initUpdateForm(@PathVariable String ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(Long.valueOf(ownerId)));
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@Valid Owner owner, @PathVariable String ownerId, BindingResult binding) {
        if(binding.hasErrors()) {
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        }
        owner.setId(Long.valueOf(ownerId)); //must be exclusively set because of binding restriction on id field (not copied)
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }
}
