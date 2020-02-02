package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController{

    @RequestMapping(value = "")
    public String search(Model model) {

        //used to display the form in search.html
        model.addAttribute("columns", columnChoices);

        return "search";
    }
    // DONE #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value = "results")
    public String displaySearchResults(@RequestParam String searchTerm, @RequestParam String searchType, Model model) {
        //searchTerm, searchType
        List<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm ==""){
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        //used in (list-jobs :: listingResults) in search.html
        model.addAttribute("jobs", jobs);

        //used to display the form in search.html
        model.addAttribute("columns", columnChoices);

        //used to display the form with previous radio button in search.html
        //Bonus Missions : 1
        model.addAttribute("searchType", searchType);

        return "search";
        //return "redirect:/search/results";
    }
}
