package dagimon.spring5course.sf5petclinic.controllers;

import dagimon.spring5course.sf5petclinic.model.Owner;
import dagimon.spring5course.sf5petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

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

}
