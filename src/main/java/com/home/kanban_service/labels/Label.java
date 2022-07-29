package com.home.kanban_service.labels;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class Label {

    @Id
    @SequenceGenerator(
            name = "labelItem_sequence",
            sequenceName = "labelItem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "labelItem_sequence"
    )
    private Long id;

    @NotNull(message="Label is required")
    private String label;

    public Label() {
    }

    public Label(String label) {
        this.label = label;
    }

    public Label(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
