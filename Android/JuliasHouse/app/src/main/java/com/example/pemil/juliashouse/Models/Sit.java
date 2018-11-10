package com.example.pemil.juliashouse.Models;

import java.util.Date;
import java.util.List;

public class Sit {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Long patientId;
    private List<Carrer> carrerList;

    public Sit() {

    }

    public Sit(Long id, Date startDate, Date endDate, Long patientId, List<Carrer> carrerList) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patientId = patientId;
        this.carrerList = carrerList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public List<Carrer> getCarrerList() {
        return carrerList;
    }

    public void setCarrerList(List<Carrer> carrerList) {
        this.carrerList = carrerList;
    }
}
