package apap.tutorial.haidokter.controller;

import apap.tutorial.haidokter.model.*;
import apap.tutorial.haidokter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ObatController {
    @Qualifier("resepServiceImpl")
    @Autowired
    ResepService resepService;

    @Autowired
    ObatService obatService;

    @GetMapping("/obat/add/{noResep}")
    private String addObatFormPage(@PathVariable Long noResep, Model model){
        ObatModel obat =new ObatModel();
        ResepModel resep = resepService.getResepByNomorResep(noResep);
        obat.setResepModel(resep);
        model.addAttribute("obat", obat);

        return "form-add-obat";
    }

    @GetMapping("/obat/add-multiple/{noResep}")
    private String addMultipleObatFormPage(@PathVariable Long noResep, Model model){
        ObatModel obat =new ObatModel();
        List<ObatModel> listObat = new ArrayList<ObatModel>();
        ResepModel resep = resepService.getResepByNomorResep(noResep);
        obat.setResepModel(resep);
        model.addAttribute("obat", obat);
        model.addAttribute("resep", resep);

        return "form-add-multiple-obat";
    }

    @PostMapping("/obat/add")
    private String addObatSubmit(
            @ModelAttribute ObatModel obat, Model model
    ){
        obatService.addObat(obat);
        model.addAttribute("nama", obat.getNama());

        return "add-obat";
    }

    @PostMapping(value = "/obat/add/{noResep}", params = {"submit"})
    private String addObatSubmit(@ModelAttribute ResepModel resep, Model model){
        for(ObatModel obat: resep.getListObat()){
            obat.setResepModel(resep);
            obatService.addObat(obat);
        }
        model.addAttribute("listObat", resep.getListObat());
        return "add-obat";
    }

    @GetMapping("/obat/change/{id}")
    private String changeObatFormPage(
            @PathVariable Long id,
            Model model
    ){
        ObatModel obat = obatService.getObatById(id);
        model.addAttribute("obat", obat);

        return "form-update-obat";
    }

    @PostMapping("/obat/change")
    private String changeObatFormSubmit(
            @ModelAttribute ObatModel obat,
            Model model
    ){
        ObatModel updateObat = obatService.updateObat(obat);
        model.addAttribute("obat", updateObat);

        return "update-obat";
    }

//    @RequestMapping("/obat/delete/{id}")
//    public String deleteObat(@PathVariable Long id, Model model){
//        ObatModel obat = obatService.getObatById(id);
//
//
//        model.addAttribute("id",obat.getId());
//        obatService.deleteObat(obat);
//
//        return "delete-obat";
//    }

    @PostMapping(value = "/obat/delete")
    public String deleteMenuFormSubmit(@ModelAttribute ResepModel resep, Model model){
        model.addAttribute("obatCount", resep.getListObat().size());
        for(ObatModel obat : resep.getListObat()){
            obatService.deleteObatByID(obat.getId());
        }
        return "delete-obat";
    }

//    @PostMapping(value = "/obat/add/{noResep}", params = {"addRow"})
//    private String addRow(@ModelAttribute ResepModel resep, Model model){
//    }

//    @PostMapping(value = "/obat/add/{noResep}", params = {"deleteRow"})
//    private String deleteRow(@ModelAttribute ResepModel resep, final HttpServletRequest req, Model model){
//
//    }

}
