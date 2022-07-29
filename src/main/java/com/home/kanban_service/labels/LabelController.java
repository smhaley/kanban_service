package com.home.kanban_service.labels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/label")
public class LabelController {
    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping
    public List<Label> getLabels() {
        return labelService.getLabels();
    }

    @PostMapping
    public void createLabel(@Validated @RequestBody Label label){
        labelService.addNewLabel(label);
    }

    @DeleteMapping(path="{labelId}")
    public void deleteLabel(@PathVariable("labelId") Long labelId) {
        labelService.deleteLabel(labelId);
    }

    @PutMapping(path="{labelId}")
    public void updateLabel(@PathVariable("labelId") Long labelId, @Validated @RequestBody Label label){
        labelService.updateLabel(
                labelId, label
        );
    }
}
