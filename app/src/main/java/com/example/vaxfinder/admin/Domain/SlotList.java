package com.example.vaxfinder.admin.Domain;

public class SlotList {
    public String slotDateTime;
    public String loc, vType, Bookingstatus, centerOnPost;
    public String AvailStatus;

    public SlotList(String slotDateTime, String loc, String vType, String bookingstatus, String centerOnPost, String availStatus) {
        this.slotDateTime = slotDateTime;
        this.loc = loc;
        this.vType = vType;
        Bookingstatus = bookingstatus;
        this.centerOnPost = centerOnPost;
        AvailStatus = availStatus;
    }

    public String getSlotDateTime() {
        return slotDateTime;
    }

    public void setSlotDateTime(String slotDateTime) {
        this.slotDateTime = slotDateTime;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public String getBookingstatus() {
        return Bookingstatus;
    }

    public void setBookingstatus(String bookingstatus) {
        Bookingstatus = bookingstatus;
    }

    public String getCenterOnPost() {
        return centerOnPost;
    }

    public void setCenterOnPost(String centerOnPost) {
        this.centerOnPost = centerOnPost;
    }

    public String getAvailStatus() {
        return AvailStatus;
    }

    public void setAvailStatus(String availStatus) {
        AvailStatus = availStatus;
    }

    @Override
    public String toString() {
        return "SlotList{" +
                "slotDateTime='" + slotDateTime + '\'' +
                ", loc='" + loc + '\'' +
                ", vType='" + vType + '\'' +
                ", Bookingstatus='" + Bookingstatus + '\'' +
                ", centerOnPost='" + centerOnPost + '\'' +
                ", AvailStatus='" + AvailStatus + '\'' +
                '}';
    }
}
