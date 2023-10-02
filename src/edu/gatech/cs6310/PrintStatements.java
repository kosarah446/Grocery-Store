package edu.gatech.cs6310;

public class PrintStatements {
    public void pilotExists() {
        System.out.println("ERROR:pilot_identifier_already_exists");
    }
    public void pilotDNE() {
        System.out.println("ERROR:pilot_identifier_does_not_exist");
    }
    public void pilotLicenseExists() {
        System.out.println("ERROR:pilot_license_already_exists");
    }
    public void storeDNE() {
        System.out.println("ERROR:store_identifier_does_not_exist");
    }
    public void storeExists() {
        System.out.println("ERROR:store_identifier_already_exists");
    }
    public void orderDNE() {
        System.out.println("ERROR:order_identifier_does_not_exist");
    }
    public void orderExists() {
        System.out.println("ERROR:order_identifier_already_exists");
    }
    public void itemDNE() {
        System.out.println("ERROR:item_identifier_does_not_exist");
    }
    public void itemExists() {
        System.out.println("ERROR:item_identifier_already_exists");
    }
    public void itemOrdered() {
        System.out.println("ERROR:item_already_ordered");
    }
    public void droneExists() {
        System.out.println("ERROR:drone_identifier_already_exists");
    }
    public void droneDNE() {
        System.out.println("ERROR:drone_identifier_does_not_exist");
    }
    public void droneOverweight() {
        System.out.println("ERROR:drone_cant_carry_new_item");
    }
    public void droneReqPilot() {
        System.out.println("ERROR:drone_needs_pilot");
    }
    public void droneReqFuel() {
        System.out.println("ERROR:drone_needs_fuel");
    }
    public void customerExists() {
        System.out.println("ERROR:customer_identifier_already_exists");
    }
    public void customerDNE() {
        System.out.println("ERROR:customer_identifier_does_not_exist");
    }
    public void tooExpensive() {
        System.out.println("ERROR:customer_cant_afford_new_item");
    }
    public void tooHeavy() {
        System.out.println("ERROR:customer_cant_carry_new_item");
    }
    public void transferWeight() {
        System.out.println("ERROR:new_drone_does_not_have_enough_capacity");
    }
    public void unassignableAngryBirdLocations() {
        System.out.println("ERROR:locations_do_not_exist_for_angry_bird");
    }
    public void tooFewAngryBirds() {
        System.out.println("ERROR:angry_birds_created_must_be_greater_than_zero");
    }
    public void okayDisplay() {
        System.out.println("OK:display_completed");
    }
    public void okayChange() {
        System.out.println("OK:change_completed");
    }
    public void sameDrone() { System.out.println("OK:new_drone_is_current_drone_no_change");}
    public void notAuthorized() { System.out.println("Not authorized. Please try another command.");}
    //TODO: use this print statement when a drone gets hit by bird
    public void orderDelayedDroneHit() { System.out.println("WARNING:drone_damaged_order_delayed_by_one_hour");
    }
}
