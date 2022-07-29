package com.home.kanban_service.kanban_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class KanbanItemController {

    private final KanbanItemService kanbanItemService;

    // autowired instantiates the KanbanItemService
    // i.e. new KanbanItemService()
    @Autowired
    public KanbanItemController(KanbanItemService kanbanItemService) {
        this.kanbanItemService = kanbanItemService;
    }


    @GetMapping(value = "/api/v1/kanbanItems")
    public List<KanbanItem> getKanbanItems(
            @RequestParam(required = false) List<String> users,
            @RequestParam(required = false) List<String> labels,
            @RequestParam(required = false) List<String> priorities
    ){
        return kanbanItemService.getKanbanItems(users, labels, priorities);
    }

    @GetMapping(value = "/api/v1/archivedKanbanItems")
    public List<KanbanItem> getArchivedKanbanItems(){return kanbanItemService.getArchivedKanbanItems();}

    @GetMapping(value = "/api/v1/kanbanItem/{kanbanItemId}")
    public Optional<KanbanItem> getKanbanItem(@PathVariable("kanbanItemId") UUID kanbanItemId){return kanbanItemService.getKanbanItem(kanbanItemId);}


    @PostMapping(value = "/api/v1/kanbanItem")
    public KanbanItem createNewKanbanItem(@Validated @RequestBody KanbanItem kanbanItem){
        KanbanItem addedItem = kanbanItemService.addNewKanbanItem(kanbanItem);
        return addedItem;
    }


    @DeleteMapping(value = "/api/v1/kanbanItem/{kanbanItemId}")
    public void deleteKanbanItem(@PathVariable("kanbanItemId") UUID kanbanItemId){
            kanbanItemService.deleteKanbanItem(kanbanItemId);

    }


    @PutMapping(value = "/api/v1/kanbanItem")
    public KanbanItem updateKanbanItem(@RequestBody KanbanItem kanbanItem){
       KanbanItem updatedItem =  kanbanItemService.updateKanbanItem(kanbanItem);
       return updatedItem;
    }


    @PutMapping(value = "/api/v1/kanbanItems")
    public void batchUpdateKanbanItems(@Validated @RequestBody List<KanbanItem> kanbanItems){

        kanbanItemService.batchUpdateKanbanItems(kanbanItems);
    }

}
