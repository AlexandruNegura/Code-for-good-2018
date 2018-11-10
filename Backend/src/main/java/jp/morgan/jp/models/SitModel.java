package jp.morgan.jp.models;

import java.util.Date;
import java.util.List;

public class SitModel {

    public Date startDate;

    public Date endDate;

    public Long patientId;

    public Boolean canceled;

    public List<Long> carrersIds;

    public SitModel() {
    }
}
