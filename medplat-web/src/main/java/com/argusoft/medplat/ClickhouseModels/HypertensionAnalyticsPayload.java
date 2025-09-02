package com.argusoft.medplat.ClickhouseModels;


import java.util.Date;

public class HypertensionAnalyticsPayload {
    private Integer memberId;
    private Date screeningDate;
    private Integer systolicBp;

    // Constructors
    public HypertensionAnalyticsPayload() {}

    public HypertensionAnalyticsPayload(Integer memberId, Date screeningDate, Integer systolicBp) {
        this.memberId = memberId;
        this.screeningDate = screeningDate;
        this.systolicBp = systolicBp;
    }

    // Getters & Setters
    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }

    public Date getScreeningDate() { return screeningDate; }
    public void setScreeningDate(Date screeningDate) { this.screeningDate = screeningDate; }

    public Integer getSystolicBp() { return systolicBp; }
    public void setSystolicBp(Integer systolicBp) { this.systolicBp = systolicBp; }
}