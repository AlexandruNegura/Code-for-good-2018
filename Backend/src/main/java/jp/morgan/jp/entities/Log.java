package jp.morgan.jp.entities;

import jp.morgan.jp.Constants;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
public class Log extends IdEntity {

    @OneToOne
    private Carrer carrer;

    @OneToOne
    private Sit sit;

    @Enumerated(EnumType.STRING)
    private Constants.CarerAction carerAction;

    public Log() {
    }

    public Log(Carrer carrer, Sit sit, Constants.CarerAction carerAction) {
        this.carrer = carrer;
        this.sit = sit;
        this.carerAction = carerAction;
    }

    public Carrer getCarrer() {
        return carrer;
    }

    public void setCarrer(Carrer carrer) {
        this.carrer = carrer;
    }

    public Sit getSit() {
        return sit;
    }

    public void setSit(Sit sit) {
        this.sit = sit;
    }

    public Constants.CarerAction getCarerAction() {
        return carerAction;
    }

    public void setCarerAction(Constants.CarerAction carerAction) {
        this.carerAction = carerAction;
    }
}
