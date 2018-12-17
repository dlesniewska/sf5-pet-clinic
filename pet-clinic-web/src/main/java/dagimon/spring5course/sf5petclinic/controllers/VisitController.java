package dagimon.spring5course.sf5petclinic.controllers;

import dagimon.spring5course.sf5petclinic.model.Pet;
import dagimon.spring5course.sf5petclinic.model.Visit;
import dagimon.spring5course.sf5petclinic.services.PetService;
import dagimon.spring5course.sf5petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        //allowed fields
        dataBinder.setDisallowedFields("id");

        //date parser
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        Visit visit = new Visit();
        pet.addVisit(visit);
        model.addAttribute("pet", pet);
        return visit;
    }

    @RequestMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewForm(@PathVariable Long petId, Model model) {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewForm(@Valid Visit visit, BindingResult binding) {
        if(binding.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        }
        visitService.save(visit);
        return "redirect:/owners/{ownerId}";
    }

}
