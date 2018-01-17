package com.koziolpaulina.foodmanager.recipes;

import javax.persistence.Embeddable;

@Embeddable
public class Step {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;

    private Long sequenceNumber;

    private String description;

    public Step() {
    }

    public Step(Long sequenceNumber, String description) {
        this.sequenceNumber = sequenceNumber;
        this.description = description;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (!sequenceNumber.equals(step.sequenceNumber)) return false;
        return description.equals(step.description);
    }

    @Override
    public int hashCode() {
        int result = sequenceNumber.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
