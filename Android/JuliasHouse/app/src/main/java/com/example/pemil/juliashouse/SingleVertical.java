package com.example.pemil.juliashouse;

public class SingleVertical {

    private String header;
    private int image;
    private String startDate, endDate;
    public SingleVertical( ) {

    }

    public SingleVertical(String header, String startDate, String endDate, int image) {
        this.header = header;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getImage() {
        return image;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
