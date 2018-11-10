package jp.morgan.jp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jp.morgan.jp.Utils.MyDateTimeDeserializer;
import org.joda.time.DateTime;

import java.util.List;

public class SitModel {

    @JsonDeserialize(using= MyDateTimeDeserializer.class)
    public DateTime startDate;

    @JsonDeserialize(using=MyDateTimeDeserializer.class)
    public DateTime endDate;

    public Long patientId;

    public Boolean canceled;

    public List<Long> carrersIds;

    public SitModel() {
    }
}
