package com.sip.ams.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sip.ams.entities.Etudiant;

@Controller
public class EtudiantController {
     
	List<Etudiant> etudiants = new ArrayList<>();
	{
	etudiants.add(new Etudiant(1, "emna", "emna@gmail.com"));
	etudiants.add(new Etudiant(2, "beki", "beki@gmail.com"));
	etudiants.add(new Etudiant(3, "amine", "amine@gmail.com"));
    }
    @RequestMapping("/home") // request pour invoquer (exécutter la méthode ci-dessous)
    public String message(Model model) {
	System.out.println("BIENVENUE AU BOOTCAMP");
	String formation = "FULL STACK";
	String lieu = "sesame";

	model.addAttribute("training", formation);
	model.addAttribute("location", lieu);

	return "info";
    }

    @RequestMapping("/products")
    public ModelAndView listProducts(Model model) {
	ModelAndView mv = new ModelAndView();
	List<String> productsList = new ArrayList<>();
	productsList.add("Voiture");
	productsList.add("Camion");
	productsList.add("Moto ");
	productsList.add("Bus");
	// model.addAttribute("mesProduits",productsList);
	mv.addObject("mesProduits", productsList);
	mv.setViewName("products");
	// ModelAndView plus récent et utilser ; soit retourner une type d'objet , soit
	// string et return le nom de la page html
	return mv;
	// return "products";
    }

    @RequestMapping("/students") // le path ecrit dans url
    public ModelAndView listStudents() {
	ModelAndView mv = new ModelAndView();
	 
	mv.addObject("students", etudiants);
	mv.setViewName("listStudents");// le nom du fichier html
	return mv;

    }

   // @GetMapping("/add")
   // ou bien 
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView addStudentForm() {
	ModelAndView mv = new ModelAndView();
	mv.setViewName("addStudent");
	return mv;
    }
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String addStudent(
	    @RequestParam("id") int id,
	    @RequestParam("nom") String nom, 
	    @RequestParam("email") String email
	    ) 
    {
	Etudiant e=new Etudiant(id,nom,email);
	etudiants.add(e);
  	return "redirect:students";
    }
}
