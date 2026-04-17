package com.example.oksanapetitions.controller;

import com.example.oksanapetitions.model.Petition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class PetitionController {
    
    private List<Petition> petitions = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(1);
    
    public PetitionController() {
        // Add some random data at the start
        petitions.add(new Petition(idGenerator.getAndIncrement(), "Save the Local Library", "The local library is facing closure due to budget cuts."));
        petitions.add(new Petition(idGenerator.getAndIncrement(), "More Bike Lanes in City Center", "We need safer cycling infrastructure."));
        petitions.add(new Petition(idGenerator.getAndIncrement(), "Extend School Holidays", "Students need more rest time between terms."));
    }
    
    @GetMapping("/")
    public String home() {
        return "redirect:/petitions/create";
    }
    
    @GetMapping("/petitions/create")
    public String showCreateForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "create-petition";
    }
    
    @PostMapping("/petitions/create")
    public String createPetition(Petition petition) {
        petition.setId(idGenerator.getAndIncrement());
        petitions.add(petition);
        return "redirect:/petitions/all";
    }
    @GetMapping("/petitions/all")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitions);
        return "all-petitions";
    }
    @GetMapping("/petitions/search")
    public String showSearchForm() {
        return "search";
    }
    
    @GetMapping("/petitions/search/results")
    public String searchResults(@RequestParam String keyword, Model model) {
        List<Petition> results = new ArrayList<>();
        for (Petition p : petitions) {
            if (p.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                p.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(p);
            }
        }
        model.addAttribute("results", results);
        model.addAttribute("keyword", keyword);
        return "search-results";
    }
}
