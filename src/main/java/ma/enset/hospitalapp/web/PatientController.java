package ma.enset.hospitalapp.web;

import jakarta.validation.Valid;
import ma.enset.hospitalapp.entities.Patient;
import ma.enset.hospitalapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw
                        ){
        Page<Patient> pagePatients = patientRepository.findByNomContains(kw, PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/deletePatient")
    public String deletePatient(Long id , String keyword , int page) {
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
    @GetMapping("/formPatient")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }

    @PostMapping(path = "/savePatient")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id, String keyword , int page){
        Patient patient =patientRepository.findById(id).get();
        if(patient==null) throw new RuntimeException("Patient Introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }
}
