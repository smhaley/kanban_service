package com.home.kanban_service.labels;

import com.home.kanban_service.kanban_item.KanbanItem;
import com.home.kanban_service.kanban_item.KanbanItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LabelService {
    private final LabelRepository labelRepository;
    private final KanbanItemRepository kanbanItemRepository;

    @Autowired
    public LabelService(LabelRepository labelRepository, KanbanItemRepository kanbanItemRepository) {

        this.labelRepository = labelRepository;
        this.kanbanItemRepository = kanbanItemRepository;
    }

    public List<Label> getLabels() {
        return labelRepository.findAll();
    }

    public void addNewLabel(Label label){
        Optional<Label> labelOptional = labelRepository.findLabelByLabel(label.getLabel());

        if (labelOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Label already exists.");
        } else {
            labelRepository.save(label);
        }
    }

    public void deleteLabel(Long labelId){
        boolean exists = labelRepository.existsById(labelId);
        if (exists) {
            labelRepository.deleteById(labelId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id does not exist.");
        }
    }

    public void updateLabel(Long labelId, Label label){
        Label oldLabel =  labelRepository.getById(labelId);
        if (oldLabel != null) {
            if (label.getId()==null || label.getId() != labelId){
                label.setId(labelId);
            }
            String newLabel = label.getLabel();
            List<KanbanItem> items = kanbanItemRepository.findByLabel(oldLabel.getLabel());
            for (KanbanItem kanbanItem : items) {
                kanbanItem.setLabel(newLabel);
            }
            labelRepository.save(label);
            kanbanItemRepository.saveAll(items);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Id does not exist.");
        }
    }
}
