package com.sip.ams.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sip.ams.entities.Etudiant;

@Controller

@RequestMapping("/etudiant")
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
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStudentForm() {
	ModelAndView mv = new ModelAndView();
	mv.setViewName("addStudent");
	return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(@RequestParam("id") int id, @RequestParam("nom") String nom,
	    @RequestParam("email") String email) {
	Etudiant e = new Etudiant(id, nom, email);
	etudiants.add(e);
	return "redirect:students";
    }

    @GetMapping("/delete/{ide}")
    public String suppression(@PathVariable("ide") int id) // id = ide
    {
	System.out.println("id = " + id);
	// on va supprimer ici
	Etudiant e = null;
	e = recherche(etudiants, id);
	etudiants.remove(e);
	// remove ne sera pas effectué lor le parcourt de la liste au mme temps et la
	// rechercehe de thread car les deux thread marche au mme temps
	return "redirect:../students";
    }

    private Etudiant recherche(List<Etudiant> le, int index) {
	Etudiant temp = null;
	for (Etudiant e : le) {
	    if (e.getId() == index) {
		temp = e;
		return e;
	    }
	}
	return temp;
    }
    
    /*
     * @GetMapping("/update/{ide}") public ModelAndView
     * getUpdateForm(@PathVariable("ide")int id) // id = ide {
     * System.out.println("id = "+id);
     * 
     * Etudiant e = null; e = recherche(etudiants, id);
     * 
     * ModelAndView mv = new ModelAndView(); mv.addObject("etudiant", e);
     * mv.setViewName("updateStudent"); return mv; }
     * 
     * @PostMapping("/update") public String updateEtudiant(Etudiant etudiant) // id
     * = ide { System.out.println("ok"); int index = rechercheIndex(etudiants,
     * etudiant); etudiants.set(index, etudiant); return "redirect:students"; }
     * 
     * private int rechercheIndex(List<Etudiant> le, Etudiant e) { int compteur =
     * -1; for(Etudiant temp : le) { compteur++; if(temp.getId()==e.getId()) {
     * return compteur; }
     * 
     * } return compteur; }
     * 
     * 
     * 
     */
    @GetMapping("/update/{ide}")
	public ModelAndView getUpdateForm(@PathVariable("ide")int id) // id = ide
	{
		System.out.println("id = "+id);
		// on va supprimer ici
		Etudiant e = null;
		e =  recherche(etudiants, id);
		 
		ModelAndView mv = new ModelAndView();
		mv.addObject("etudiant", e);
		mv.setViewName("updateStudent");
		return mv;
	}
	
	
	@PostMapping("/update")
	public String updateEtudiant(Etudiant etudiant) // id = ide
	{
		 
		int index = rechercheIndex(etudiants, etudiant);
		etudiants.set(index, etudiant);
		return "redirect:students";
	}
	
	
	 
	
	private int rechercheIndex(List<Etudiant> le, Etudiant e)
	{
		int compteur = -1;
		for(Etudiant temp : le)
		{
			compteur++;
			if(temp.getId()==e.getId())
			{
				return compteur;
			}
			
		}
		return compteur;
	}
}
