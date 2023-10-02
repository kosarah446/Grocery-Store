package edu.gatech.cs6310;

public class Pilot extends Users {
    private String taxID;
    private String licenseID; // Unique
    private int expLvl;
    private String drone;

    Pilot(String accountID, String firstName, String lastName, String phoneNum,
          String taxID, String licenseID, int expLvl) {
        super(accountID, firstName, lastName, phoneNum);
        this.taxID = taxID;
        this.licenseID = licenseID;
        this.expLvl = expLvl;

        this.drone = null;
    }
    public boolean hasDrone() {
        return this.drone != null;
    }
    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public void displayPilot() {
        String output = String.format("name:%s,phone:%s,taxID:%s,licenseID:%s,experience:%d",
                this.getName(), this.getPhoneNum(), this.taxID, this.licenseID, this.expLvl);
        System.out.println(output);
    }

    public void resetDrone() { this.drone = null; }

    public void gainExp() {
        this.expLvl += 1;
    }

}
