package apap.tutorial.haidokter.controller;

import apap.tutorial.haidokter.model.ResepModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import apap.tutorial.haidokter.service.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;

@Controller
public class ResepController {

    @Autowired
    private ResepService resepService;

    // Routing URL yang diinginkan
    @RequestMapping("/resep/add")
    public String addResep(
            @RequestParam(value = "noResep", required = true) String noResep,
            @RequestParam(value = "namaDokter", required = true) String namaDokter,
            @RequestParam(value = "namaPasien", required = true) String namaPasien,
            @RequestParam(value = "catatan", required = true) String catatan,
            Model model
    ){
        ResepModel resep = new ResepModel(noResep, namaDokter, namaPasien, catatan);

        resepService.addResep(resep);

        model.addAttribute("nomorResep", noResep);

        return "add-resep";
    }

    @RequestMapping("/resep/viewall")
    public String liatResep(Model model){
        //Mendapatkan semua ResepModel

        List<ResepModel> listResep = resepService.getResepList();

        //Add variable semua ResepModel ke 'listResep' untuk dirender pada Thymeleaf
        model.addAttribute("listResep", listResep);

        //return View Template
        return "viewall-resep";
    }

    @RequestMapping("/resep/view")
    public String detailResep(@RequestParam(value = "noResep") String noResep, Model model){
        //Mendapatkan ResepModel sesuai nomor resep

        ResepModel resep = resepService.getResepByNomorResep(noResep);

        //Add variable ResepModel ke 'resep' untuk dirender pada Thymeleaf
        model.addAttribute("resep", resep);

        //return View Template yang ingin digunakan
        return "view-resep";
    }

    @GetMapping(value = "/resep/view/no-resep/{noResep}")
    public String detailResepPath(@PathVariable(value = "noResep") String noResep, Model model){
        ResepModel resep = resepService.getResepByNomorResep(noResep);
        model.addAttribute("resep", resep);
        return "view-resep";
    }

    @GetMapping(value = "/resep/update/no-resep/{noResep}/catatan/{catatan}")
    public String updateCatatan(@PathVariable(value = "noResep") String noResep,
                                @PathVariable(value = "catatan") String catatan, Model model){

        resepService.updateResepByNomorResep(noResep,catatan);

        model.addAttribute("nomorResep", noResep);
        model.addAttribute("catatan", catatan);

        return "updateCatatan-resep";
    }

    @GetMapping(value = "/resep/delete/no-resep/{noResep}")
    public String hapusResep(@PathVariable(value = "noResep") String noResep, Model model){
        int err = 1;
        err = resepService.deleteResep(noResep);

        model.addAttribute("nomorResep", noResep);
        
        if(err == 0){return "delete-resep";}
        else {return "deleteError-resep";}
        
    }

    @GetMapping(value = "/resep/delete-all")
    public String hapusSemuaResep(){
        resepService.deleteAllResep();
        return "deleteAll-resep";
    }

}
