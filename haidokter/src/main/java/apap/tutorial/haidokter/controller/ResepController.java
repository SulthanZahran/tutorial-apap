package apap.tutorial.haidokter.controller;

import apap.tutorial.haidokter.model.ResepModel;
import apap.tutorial.haidokter.model.ObatModel;
import apap.tutorial.haidokter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class ResepController {
//
//    @Autowired
//    private ResepService resepService;
//
//    // Routing URL yang diinginkan
//    @RequestMapping("/resep/add")
//    public String addResep(
//            @RequestParam(value = "noResep", required = true) String noResep,
//            @RequestParam(value = "namaDokter", required = true) String namaDokter,
//            @RequestParam(value = "namaPasien", required = true) String namaPasien,
//            @RequestParam(value = "catatan", required = true) String catatan,
//            Model model
//    ){
//        ResepModel resep = new ResepModel(noResep, namaDokter, namaPasien, catatan);
//
//        resepService.addResep(resep);
//
//        model.addAttribute("nomorResep", noResep);
//
//        return "add-resep";
//    }
//
    @RequestMapping("/resep/viewall")
    public String liatResep(Model model){
        //Mendapatkan semua ResepModel

        List<ResepModel> listResep = resepService.getResepList();

        //Add variable semua ResepModel ke 'listResep' untuk dirender pada Thymeleaf
        model.addAttribute("listResep", listResep);

        //return View Template
        return "viewall-resep";
    }
//
//    @RequestMapping("/resep/view")
//    public String detailResep(@RequestParam(value = "noResep") String noResep, Model model){
//        //Mendapatkan ResepModel sesuai nomor resep
//
//        ResepModel resep = resepService.getResepByNomorResep(noResep);
//
//        //Add variable ResepModel ke 'resep' untuk dirender pada Thymeleaf
//        model.addAttribute("resep", resep);
//
//        //return View Template yang ingin digunakan
//        return "view-resep";
//    }
//
//    @GetMapping(value = "/resep/view/no-resep/{noResep}")
//    public String detailResepPath(@PathVariable(value = "noResep") String noResep, Model model){
//        ResepModel resep = resepService.getResepByNomorResep(noResep);
//        model.addAttribute("resep", resep);
//        return "view-resep";
//    }
//
//    @GetMapping(value = "/resep/update/no-resep/{noResep}/catatan/{catatan}")
//    public String updateCatatan(@PathVariable(value = "noResep") String noResep,
//                                @PathVariable(value = "catatan") String catatan, Model model){
//
//        resepService.updateResepByNomorResep(noResep,catatan);
//
//        model.addAttribute("nomorResep", noResep);
//        model.addAttribute("catatan", catatan);
//
//        return "updateCatatan-resep";
//    }
//
//    @GetMapping(value = "/resep/delete/no-resep/{noResep}")
//    public String hapusResep(@PathVariable(value = "noResep") String noResep, Model model){
//        int err = 1;
//        err = resepService.deleteResep(noResep);
//
//        model.addAttribute("nomorResep", noResep);
//
//        if(err == 0){return "delete-resep";}
//        else {return "deleteError-resep";}
//
//    }

    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Autowired
    private ObatService obatService;

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @GetMapping("/resep/add")
    public String addResepFormPage(Model model){
        model.addAttribute("resep", new ResepModel());

        return "form-add-resep";
    }

    @PostMapping("/resep/add")
    public String addResepSubmit(
            @ModelAttribute ResepModel resep,
            Model model
    ){
        resepService.addResep(resep);
        model.addAttribute("nomorResep", resep.getNoResep());

        return "add-resep";
    }

    @GetMapping("/resep/change/{noResep}")
    private String changeResepFormPage(
            @PathVariable Long noResep,
            Model model
    ){
        try {
            ResepModel resep = resepService.getResepByNomorResep(noResep);
            model.addAttribute("resep", resep);

            return "form-update-resep";
        }
        catch (Exception e){
            return "error-resep";
        }
    }

    @PostMapping("/resep/change")
    private String changeResepFormSubmit(
            @ModelAttribute ResepModel resep,
            Model model
    ){
        try {
            ResepModel updateResep = resepService.updateResep(resep);
            model.addAttribute("resep", updateResep);
            Long noResep = updateResep.getNoResep();
            model.addAttribute("noResep", noResep);

            return "update-resep";
        }
        catch (Exception e){
            return "error-resep";
        }
    }

    @GetMapping("/resep/view")
    public String viewDetailResep(
            @RequestParam(value = "noResep") Long noResep,
            Model model
    ){
        try {
            ResepModel resep = resepService.getResepByNomorResep(noResep);
            List<ObatModel> listObat = resep.getListObat();

            model.addAttribute("resep", resep);
            model.addAttribute("listObat", listObat);

            return "view-resep";
        }
        catch (Exception e){
            return "error-resep";
        }
    }

    @RequestMapping("/resep/delete/{noResep}")
    public String deleteResep(@PathVariable(value = "noResep") Long noResep, Model model){
        try {
            ResepModel resep = resepService.getResepByNomorResep(noResep);


            model.addAttribute("nomorResep",resep.getNoResep());
            resepService.deleteResep(resep);

            return "delete-resep";
        }
        catch (Exception e){
            return "error-resep";
        }
    }

    @RequestMapping("/resep/tdkdelete/")
    public String tidakDeleteResep(){

        try {
            return "cant-delete-resep";
        }
        catch (Exception e){
            return "error-resep";
        }
    }

}
