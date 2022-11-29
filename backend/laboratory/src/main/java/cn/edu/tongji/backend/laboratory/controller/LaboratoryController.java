package cn.edu.tongji.backend.laboratory.controller;

import cn.edu.tongji.backend.laboratory.pojo.Feedback;
import cn.edu.tongji.backend.laboratory.pojo.Laboratory;
import cn.edu.tongji.backend.laboratory.service.LaboratoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/laboratory")
public class LaboratoryController {
    @Autowired
    private LaboratoryService laboratoryService;

    @GetMapping("/getCourseLabs")
    public List<Laboratory> getCourseLabs(@RequestParam("c_id") int c_id){
        return laboratoryService.getCourseLabs(c_id);
    }

    @GetMapping("/getLabInfo")
    public Laboratory getLabInfo(@RequestParam("l_id") int l_id){
        return laboratoryService.getLabById(l_id);
    }

    @PostMapping("/postLabInfo")
    public String postLabInfo(@RequestBody Laboratory laboratory){
        System.out.println(laboratory);
        laboratoryService.updateLab(laboratory);
        return null;
    }

    @PostMapping("/postNewLab")
    public int postNewLab(@RequestBody Laboratory laboratory){
        System.out.println(laboratory);
        laboratoryService.addLab(laboratory);
        return laboratory.getL_id();
    }

    @DeleteMapping("/deleteLabById")
    public String deleteLabById(@RequestParam("l_id") int l_id){
        laboratoryService.removeLab(l_id);
        return null;
    }

    @PostMapping("/postFeedback")
    public String postLabFeedback(@RequestBody Feedback feedback){
        laboratoryService.createFeedback(feedback);
        return null;
    }
}
