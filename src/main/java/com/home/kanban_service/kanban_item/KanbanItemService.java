package com.home.kanban_service.kanban_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class KanbanItemService {

    private final KanbanItemRepository kanbanItemRepository;

    @Autowired
    public KanbanItemService(KanbanItemRepository kanbanItemRepository) {
        this.kanbanItemRepository = kanbanItemRepository;
    }

    public List<KanbanItem> getKanbanItems(
            List<String> users, List<String> labels, List<String> priorities
    )
    {

        List<KanbanItem> kanbanItems =  kanbanItemRepository.findByItemStatusNot(TableOptions.ARCHIVE);

        if (users == null && labels == null && priorities == null) return kanbanItems;

        List<KanbanItem> filteredKanbanItems = new ArrayList();

        for(KanbanItem item: kanbanItems){

            boolean hasUsers = users == null || users.isEmpty()? true: users.contains(item.getUser());
            boolean hasLabel = labels == null || labels.isEmpty()? true: labels.contains(item.getLabel());
            boolean hasPriority = priorities == null || priorities.isEmpty() ? true: priorities.contains(item.getPriority().toString());

            if (hasUsers && hasLabel && hasPriority) filteredKanbanItems.add(item);
        }

        return filteredKanbanItems;
    }

    public Optional<KanbanItem> getKanbanItem(UUID id)
    {
        return kanbanItemRepository.findById(id);
    }


    public List<KanbanItem> getArchivedKanbanItems()
    {
        return kanbanItemRepository.findByItemStatus(TableOptions.ARCHIVE);
    }

    public KanbanItem addNewKanbanItem(KanbanItem kanbanItem) {
        if (kanbanItem.getId()!=null){
            kanbanItem.setId(null);
        }
        kanbanItem.setUpdateDateTime(LocalDateTime.now());
        kanbanItem.setCreationDateTime(LocalDateTime.now());

        kanbanItemRepository.save(kanbanItem);
        return kanbanItem;
    }

    public void deleteKanbanItem(UUID kanbanItemId){
        boolean exists = kanbanItemRepository.existsById(kanbanItemId);
        if (exists) {
            kanbanItemRepository.deleteById(kanbanItemId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id does not exist.");
        }
    }

    public KanbanItem updateKanbanItem(KanbanItem kanbanItem){
        Optional<KanbanItem> optionalItem = kanbanItemRepository.findById(kanbanItem.getId());
        if (optionalItem.isPresent()){
            KanbanItem itemToUpdate = optionalItem.get();

            kanbanItem.setCreationDateTime(itemToUpdate.getCreationDateTime());

            kanbanItemRepository.save(kanbanItem);
            return kanbanItem;
        } else {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID not found.");
        }

    }

    public void batchUpdateKanbanItems(List<KanbanItem> kanbanItems){

        kanbanItemRepository.saveAll(kanbanItems);
    }
}
