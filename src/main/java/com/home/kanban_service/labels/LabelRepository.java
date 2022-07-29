package com.home.kanban_service.labels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Long> {

    @Query
    Optional<Label> findLabelByLabel(String label);
}
