package com.example.eudu;
import java.io.Serializable;

@SuppressWarnings("serial")
public class details implements Serializable{
    private String company,rpm,powerRating,volts,amps,encl,inscl,manufactureYear,ambTemp,duty;
    public details(){

    }

    public details(String company, String rpm, String powerRating, String volts, String amps, String encl, String inscl, String manufactureYear, String ambTemp, String duty) {
        this.company = company;
        this.rpm = rpm;
        this.powerRating = powerRating;
        this.volts = volts;
        this.amps = amps;
        this.encl = encl;
        this.inscl = inscl;
        this.manufactureYear = manufactureYear;
        this.ambTemp = ambTemp;
        this.duty = duty;
    }

    public  details(details det)
    {
        this.company = det.getCompany();
        this.rpm = det.getRpm();
        this.powerRating = det.getPowerRating();
        this.volts = det.getVolts();
        this.amps = det.getAmps();
        this.encl = det.getEncl();
        this.inscl = det.getInscl();
        this.manufactureYear = det.getManufactureYear();
        this.ambTemp = det.getAmbTemp();
        this.duty = det.getDuty();
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }

    public String getPowerRating() {
        return powerRating;
    }

    public void setPowerRating(String powerRating) {
        this.powerRating = powerRating;
    }

    public String getVolts() {
        return volts;
    }

    public void setVolts(String volts) {
        this.volts = volts;
    }

    public String getAmps() {
        return amps;
    }

    public void setAmps(String amps) {
        this.amps = amps;
    }

    public String getEncl() {
        return encl;
    }

    public void setEncl(String encl) {
        this.encl = encl;
    }

    public String getInscl() {
        return inscl;
    }

    public void setInscl(String inscl) {
        this.inscl = inscl;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getAmbTemp() {
        return ambTemp;
    }

    public void setAmbTemp(String ambTemp) {
        this.ambTemp = ambTemp;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
