package com.home.kanban_service.kanban_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;;
import java.util.UUID;

@Repository
public interface KanbanItemRepository extends JpaRepository<KanbanItem, UUID> {

    @Query
    List<KanbanItem> findByItemStatusNot(TableOptions itemStatus);

    @Query
    List<KanbanItem> findByItemStatus(TableOptions itemStatus);

    @Query
    List<KanbanItem> findByLabel(String label);

    @Query
    List<KanbanItem> findByUser(String user);

}
