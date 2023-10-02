package edu.gatech.cs6310;
import java.util.*;

import static edu.gatech.cs6310.AngryBird.attack;
import static edu.gatech.cs6310.AngryBird.probabilityFormula;

public class DeliveryService {

    TreeMap<String, Customer> customers = new TreeMap<>();
    private static TreeMap<String, Pilot> pilots = new TreeMap<>();
    private static TreeMap<String, Store> stores = new TreeMap<>();
    private static TreeMap<String, String> credentials = new TreeMap<>();
    private static String tokenSession = null;
    private static ArrayList<String> storeCommands = new ArrayList<>(Arrays.asList("display_stores", "sell_item", "display_items", "display_pilots", "make_drone", "display_drones", "fly_drone", "display_customers", "display_orders", "transfer_order", "display_efficiency", "stop", "logout"));
    private static ArrayList<String> pilotCommands = new ArrayList<>(Arrays.asList("display_stores", "display_items", "display_pilots", "display_drones", "display_customers", "display_orders", "display_efficiency", "stop", "logout"));
    private static ArrayList<String> customerCommands = new ArrayList<>(Arrays.asList("display_stores", "display_items", "display_pilots", "display_drones", "display_customers", "start_order", "display_orders", "request_item", "purchase_order", "cancel_order", "display_efficiency", "stop", "logout"));
    private static ArrayList<String> adminCommands = new ArrayList<>(Arrays.asList("make_store", "display_stores", "sell_item", "display_items", "make_pilot", "display_pilots", "make_drone", "display_drones", "fly_drone", "make_customer", "display_customers", "start_order", "display_orders", "request_item", "purchase_order", "cancel_order", "transfer_order", "display_efficiency", "angry_birds", "stop", "logoout"));

    public void authenticateUser() {
        createAdmin();
        Scanner obj = new Scanner(System.in);
        System.out.println("Do you wish to login or register? Type \"l\" for login and \"r\" for register: ");
        String input = obj.next();
        if (input.equalsIgnoreCase("l")) {
            login();
        }
        else if (input.equalsIgnoreCase("r")) {
            register();
        }
        else {
            System.out.println("Invalid input. Please put a valid input.");
            authenticateUser();
        }
    }

    public void createAdmin() {
        String username = "admin";
        String password = "adminaccessonly99";
        tokenSession = "admin";
        credentials.put(username, password);

    }
    public void login() {
        Scanner obj = new Scanner(System.in);
        System.out.println("What is your username: ");
        String usernameInput = obj.nextLine();

        System.out.println("What is your password (Type \"f\" for forgot passsword): ");
        String passwordInput = obj.nextLine();

        if (passwordInput.equalsIgnoreCase("f")) {
            forgotPassword();
        }
        else if (!credentials.containsKey(usernameInput) || !credentials.get(usernameInput).equals(passwordInput) ) {
            System.out.println("Invalid credentials. Please login again.");
            login();
        }
        else if (credentials.containsKey(usernameInput)) {
            if (credentials.get(usernameInput).equals(passwordInput)) {
                if (customers.containsKey(usernameInput)){
                    tokenSession = "customer";
                } else if (pilots.containsKey(usernameInput)) {
                    tokenSession = "pilot";
                } else if (stores.containsKey(usernameInput)) {
                    tokenSession = "store";
                }
            }
            System.out.println("Login successful");
            commandLoop();
        }
        else {
            System.out.println("Invalid credentials. Please login again.");
            login();
        }
    }


    public void forgotPassword() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String usernameInput = obj.next();
        if (credentials.containsKey(usernameInput)) {
            System.out.println("Please enter your new password: ");
            String passwordInput = obj.next();
            credentials.put(usernameInput, passwordInput);
            login();
        }
        else {
            System.out.println("Invalid username. Please try again.\n");
            forgotPassword();
        }

    }

    public void register() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Would you like to register as a drone pilot, customer or store manager? (Please type \"d\" for drone pilot, \"c\" for customer, \"s\" for store manager)");
        String input = obj.nextLine();

        if (input.equalsIgnoreCase("d")) {
            registerDronePilot();
        }
        else if (input.equalsIgnoreCase("c")) {
            registerCustomer();
        }
        else if (input.equalsIgnoreCase("s")) {
            registerStoreManager();
        }
        else {
            System.out.println("Invalid input. Please put a valid input.");
            register();
        }
    }

    public void registerCustomer() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter a username:");
        String usernameInput = obj.nextLine();

        if (customers.containsKey(usernameInput)) {
            System.out.println("Username already exists. Please enter another username.");
            registerCustomer();
        } else {
            System.out.println("Enter a password:");
            String passwordInput = obj.nextLine();

            System.out.println("Enter your first name:");
            String firstNameInput = obj.nextLine();

            System.out.println("Enter your last name:");
            String lastNameInput = obj.nextLine();

            System.out.println("Enter your phone number:");
            String phoneNumberInput = obj.nextLine();

            System.out.println("Enter a credit amount (in integers):");
            String creditInput = obj.nextLine();
            Integer credit = Integer.parseInt(creditInput);

            Customer customer = new Customer(usernameInput, firstNameInput, lastNameInput, phoneNumberInput, 5, credit);
            customers.put(usernameInput, customer);
            credentials.put(usernameInput, passwordInput);
            System.out.print("Registration successful. Please login.\n");
            login();
        }
    }

    public void registerStoreManager() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter a username (this will be your store name):");
        String usernameInput = obj.nextLine();

        if (pilots.containsKey(usernameInput)) {
            System.out.println("Username already exists. Please enter another username.");
            registerStoreManager();
        } else {
            System.out.println("Enter a password:");
            String passwordInput = obj.nextLine();

            System.out.println("Enter the revenue:");
            String rev = obj.nextLine();
            Integer revenue = Integer.parseInt(rev);

            Store store = new Store(usernameInput, revenue);
            stores.put(usernameInput, store);
            credentials.put(usernameInput, passwordInput);
            System.out.print("Registration successful. Please login.\n");
            login();
        }

    }
    public void registerDronePilot() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter a username:");
        String usernameInput = obj.nextLine();

        if (pilots.containsKey(usernameInput)) {
            System.out.println("Username already exists. Please enter another username.");
            registerDronePilot();
        } else {
            System.out.println("Enter a password:");
            String passwordInput = obj.nextLine();

            System.out.println("Enter your first name:");
            String firstNameInput = obj.nextLine();

            System.out.println("Enter your last name:");
            String lastNameInput = obj.nextLine();

            System.out.println("Enter your phone number:");
            String phoneNumberInput = obj.nextLine();

            System.out.println("Enter your tax ID:");
            String taxID = obj.nextLine();

            System.out.println("Enter your license ID:");
            String licenseID = obj.nextLine();

            System.out.println("Enter the number of successful deliveries:");
            String num = obj.nextLine();
            Integer numOfSuccessfulDeliveries = Integer.parseInt(num);

            Pilot dronePilot = new Pilot(firstNameInput, lastNameInput, phoneNumberInput, taxID, usernameInput, licenseID, numOfSuccessfulDeliveries);
            pilots.put(usernameInput, dronePilot);
            credentials.put(usernameInput, passwordInput);
            System.out.print("Registration successful. Please login.\n");
            login();
        }

    }
    public void menu() {
        System.out.println("Enter your commands. Type help for list of commands");
//        System.out.println("make_store,[name]");
//        System.out.println("display_stores");
//        System.out.println("sell_item,[store],[item_name]");
    }

    // DISPLAY THE COMMANDS
    public void displayStoreCommands() {
        System.out.println("The following commands are available:");
        Collections.sort(storeCommands);
        for(String item : storeCommands) {
            System.out.println(item);
        }
    }

    public void displayPilotCommands() {
        System.out.println("The following commands are available:");
        Collections.sort(pilotCommands);
        for(String item : pilotCommands) {
            System.out.println(item);
        }
    }

    public void displayCustomerCommands() {
        System.out.println("The following commands are available:");
        Collections.sort(customerCommands);
        for(String item : customerCommands) {
            System.out.println(item);
        }
    }

    public void displayAdminCommands() {
        System.out.println("The following commands are available:");
        Collections.sort(adminCommands);
        for(String item : adminCommands) {
            System.out.println(item);
        }
    }

    public void commandLoop() {
        Scanner commandLineInput;
        if (tokenSession == null) {
            authenticateUser();
        } else {
            commandLineInput = new Scanner(System.in);
            String wholeInputLine;
            String[] tokens;
            final String DELIMITER = ",";

            PrintStatements log = new PrintStatements();
            Set<String> licenses = new HashSet<>();
            List<AngryBird> angryBirds = new ArrayList<>();
            menu();


            while (true) {
                try {
                    // Determine the next command and echo it to the monitor for testing purposes
                    wholeInputLine = commandLineInput.nextLine();
                    tokens = wholeInputLine.split(DELIMITER);
                    System.out.println("> " + wholeInputLine);
                    if (tokens[0].startsWith("//")) {
                        continue;
                    }

                    if (tokens[0].equals("make_store")) {
                        if (tokenSession.equals("admin")) {
                            // Set clear variables
                            String name = tokens[1];
                            double revenue = Float.parseFloat(tokens[2]);
                            Store grocery = new Store(name, revenue);
                            // Determine whether the store needs to be created
                            if (stores.containsKey(name)) {
                                System.out.println("ERROR:store_identifier_already_exists");
                            } else {
                                stores.put(name, grocery);
                                System.out.println("OK:change_completed");
                            }
                        } else {
                            log.notAuthorized();
                        }
                    } else if (tokens[0].equals("display_stores")) {
                        Set<String> keys = stores.keySet();
                        // Displays all the store names from the keys of the set
                        for (String name : keys) {
                            System.out.printf("name:" + name + ",revenue:%.0f\n", stores.get(name).getRevenue());
                        }
                        System.out.println("OK:display_completed");
//                    System.out.println("store: " + tokens[1]);
                    } else if (tokens[0].equals("sell_item")) {
                        if (tokenSession == "store" || tokenSession.equals("admin")) {
                            // Set clear variables
                            String store = tokens[1];
                            String item_name = tokens[2];
                            int weight = Integer.parseInt(tokens[3]);
                            Store currentStore = stores.get(store);
                            // Determine error conditions
                            if (!stores.containsKey(store)) {
                                log.storeDNE();
                            } else if (currentStore.getCatalog().containsKey(item_name)) {
                                log.itemExists();
                            } else {
                                // Add item to store
                                currentStore.addItem(item_name, weight);
                                log.okayChange();
                            }
                        } else {
                            log.notAuthorized();
                        }
                    } else if (tokens[0].equals("display_items")) {
                        String store_name = tokens[1];
                        if (!stores.containsKey(store_name)) {
                            log.storeDNE();
                        } else {
                            stores.get(store_name).displayItems();
                        }
                    } else if (tokens[0].equals("make_pilot")) {
                        if (tokenSession.equals("admin")) {
                            // Set clear variables
                            String account = tokens[1];
                            String firstName = tokens[2];
                            String lastName = tokens[3];
                            String phone = tokens[4];
                            String taxID = tokens[5];
                            String license = tokens[6];
                            int expLvl = Integer.parseInt(tokens[7]);
                            // Determine error conditions
                            if (pilots.containsKey(account)) {
                                log.pilotExists();
                            } else if (licenses.contains(license)) {
                                log.pilotLicenseExists();
                            } else {
                                // Create pilots and add to the list
                                Pilot individual = new Pilot(account, firstName, lastName, phone, taxID, license, expLvl);
                                pilots.put(account, individual);
                                licenses.add(license);
                                log.okayChange();
                            }
                        } else {
                            log.notAuthorized();
                        }
                    } else if (tokens[0].equals("display_pilots")) {
                        Set<String> keys = pilots.keySet();
                        for (String name : keys) {
                            pilots.get(name).displayPilot();
                        }

                        log.okayDisplay();
                    } else if (tokens[0].equals("make_drone")) {
                        if (tokenSession == "store" || tokenSession.equals("admin")) {
                            // Set clear variables
                            String store = tokens[1];
                            String droneID = tokens[2];
                            int capacity = Integer.parseInt(tokens[3]);
                            int fuel = Integer.parseInt(tokens[4]);
                            // Determine error conditions
                            if (!stores.containsKey(store)) {
                                log.storeDNE();
                            } else if (stores.get(store).getFleet().containsKey(droneID)) {
                                log.droneExists();
                            } else {
                                // Add drone to designated store
                                stores.get(store).addDrone(droneID, new Drone(store, droneID, capacity, fuel));
                                log.okayChange();
                            }
                        } else {
                            log.notAuthorized();
                        }
                    } else if (tokens[0].equals("display_drones")) {
                        String store = tokens[1];
                        if (!stores.containsKey(store)) {
                            log.storeDNE();
                        } else {
                            stores.get(store).displayDrones(pilots);
                        }
                    } else if (tokens[0].equals("fly_drone")) {
                        if (tokenSession == "store" || tokenSession.equals("admin")) {
                            // Set clear variables
                            String store = tokens[1];
                            String drone = tokens[2];
                            String pilot = tokens[3];
                            Store currentStore = stores.get(store);
                            // Determine error conditions
                            if(!stores.containsKey(store)) {
                                log.storeDNE();
                            } else if(!currentStore.getFleet().containsKey(drone)) {
                                log.droneDNE();
                            } else if(!pilots.containsKey(pilot)) {
                                log.pilotDNE();
                            } else {
                                // Distinguish between replacing pilots
                                Pilot newPilot = pilots.get(pilot);
                                Drone newDrone = currentStore.getFleet().get(drone);
                                String pilotName = newPilot.getName();
                                // Reset old drone's pilot when both names are the same with the new
                                if (newPilot.hasDrone()) {
                                    Drone oldDrone = currentStore.getFleet().get(newPilot.getDrone());

                                    if (Objects.equals(oldDrone.getPilotID(), pilot)) {
                                        currentStore.getFleet().get(newPilot.getDrone()).resetPilot();
                                        newPilot.resetDrone();
                                    }
                                }
                                // Assign pilots and drones with each other
                                pilots.get(pilot).setDrone(drone);
                                newDrone.setPilot(pilotName, pilot);
                                log.okayChange();
                            }
                        } else {
                            log.notAuthorized();
                        }
                    } else if (tokens[0].equals("make_customer")) {
                        if (tokenSession.equals("admin")) {
                            // Set clear variables
                            String account = tokens[1];
                            String firstName = tokens[2];
                            String lastName = tokens[3];
                            String phone = tokens[4];
                            int rating = Integer.parseInt(tokens[5]);
                            int credit = Integer.parseInt(tokens[6]);
                            // Determine error conditions
                            if (customers.containsKey(account)) {
                                log.customerExists();
                            } else {
                                // Add customers
                                customers.put(account, new Customer(account, firstName, lastName, phone, rating, credit));
                                log.okayChange();
                            }
                        } else {
                            log.notAuthorized();
                        }
//                    System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
//                    System.out.println(", phone: " + tokens[4] + ", rating: " + tokens[5] + ", credit: " + tokens[6]);

                    } else if (tokens[0].equals("display_customers")) {
                        Set<String> keys = customers.keySet();
                        for (String id : keys) {
                            customers.get(id).display();
                        }
                        log.okayDisplay();
//                    System.out.println("no parameters needed");

                    } else if (tokens[0].equals("start_order")) {
                        if (tokenSession.equals("admin") || tokenSession.equals("customer")) {
                            // Set clear variables
                            String store = tokens[1];
                            String order = tokens[2];
                            String drone = tokens[3];
                            String customer = tokens[4];
                            // Determine error conditions
                            if (!stores.containsKey(store)) {
                                log.storeDNE();
                            } else if (stores.get(store).getOrders().containsKey(order)) {
                                log.orderExists();
                            } else if (!stores.get(store).getFleet().containsKey(drone)) {
                                log.droneDNE();
                            } else if (!customers.containsKey(customer)) {
                                log.customerDNE();
                            } else {
                                Order list = new Order(store, order, drone, customer);
                                // Assign the order to customers, store and drones
                                customers.get(customer).addOrder(order, list);
                                stores.get(store).addOrder(order, list);
                                stores.get(store).getFleet().get(drone).addOrder(order, list);
                                log.okayChange();
                            }
                        } else {
                            log.notAuthorized();
                        }
//                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", drone: " + tokens[3] + ", customer: " + tokens[4]);

                    } else if (tokens[0].equals("display_orders")) {
                        String store = tokens[1];
                        if (!stores.containsKey(store)) {
                            log.storeDNE();
                        } else {
                            stores.get(store).displayOrders();
                        }
//                    System.out.println("store: " + tokens[1]);

                    } else if (tokens[0].equals("request_item")) {
                        if (tokenSession.equals("admin") || tokenSession.equals("customer")) {
                            // Set clear variables
                            String store = tokens[1];
                            String order = tokens[2];
                            String item = tokens[3];
                            int quantity = Integer.parseInt(tokens[4]);
                            int unitPrice = Integer.parseInt(tokens[5]);
                            Store currentStore = stores.get(store);
                            // Determine error conditions
                            if (!stores.containsKey(store)) {
                                log.storeDNE();
                            } else if (!currentStore.getOrders().containsKey(order)) {
                                log.orderDNE();
                            } else if (!currentStore.getCatalog().containsKey(item)) {
                                log.itemDNE();
                            } else if (currentStore.getOrders().get(order).getList().containsKey(item)) {
                                log.itemOrdered();
                            } else {
                                // Set clear variables
                                Order current = stores.get(store).getOrders().get(order);
                                String droneID = current.getDrone();
                                String customerID = current.getCustomer();
                                int weight = currentStore.getCatalog().get(item);
                                // Determine error conditions
                                if (customers.get(customerID).creditCheck(quantity, unitPrice)) {
                                    log.tooExpensive();
                                } else if (currentStore.getFleet().get(current.getDrone()).weightCheck(weight, quantity)) {
                                    log.droneOverweight();
                                } else {
                                    // Add item to the list and set sale to pending
                                    customers.get(customerID).pending(quantity, unitPrice, order);
                                    currentStore.getFleet().get(droneID).deductWeight(weight, quantity);
                                    current.addItem(item, quantity, unitPrice, weight);
                                    log.okayChange();
                                }
                            }
                        } else {
                            log.notAuthorized();
                        }
                    } else if (tokens[0].equals("purchase_order")) {
                        if (tokenSession.equals("admin") || tokenSession.equals("customer")) {
                            // Set clear variables
                            String store = tokens[1];
                            String order = tokens[2];
                            Store currentStore = stores.get(store);
                            // Determine error conditions
                            if (!stores.containsKey(store)) {
                                log.storeDNE();
                            } else if (!currentStore.getOrders().containsKey(order)) {
                                log.orderDNE();
                            } else {
                                //System.out.println("INSIDE PURCHASE ORDER: " + AngryBird.getGlobalProbability());
                                // Set clear variables
                                Order currentOrder = currentStore.getOrders().get(order);
                                String droneID = currentOrder.getDrone();
                                Drone currentDrone = currentStore.getFleet().get(droneID);
                                double totalCost = currentOrder.getTotal();
                                // Determine error conditions
                                if (currentDrone.getPilot() == null) {
                                    log.droneReqPilot();
                                } else if (currentDrone.fuelCheck()) {
                                    log.droneReqFuel();
                                } else {
                                    // Check and calculate for bird damage inflicted at current store location
                                    int numBirdsStore = 0;
                                    for (AngryBird bird : angryBirds) {
//                                        System.out.println(bird + " " + bird.getCurrentLocation());
                                        if (Objects.equals(bird.getCurrentLocation(), store)) {
                                            numBirdsStore += 1;
                                        }
                                    }
                                    // Check for number of birds at this location
                                    // Check for probability of a successful strike. Anything more than 70 is considered a hit
                                    double probability = probabilityFormula(numBirdsStore);
                                    // Randomly assign damage value of 1-10
                                    int dmg = attack(probability);
                                    // If health goes below 7 (dmg of 3 pts), defer order
                                    if(currentDrone.damaged(dmg)) {
                                        // Inform user of drone hit and delay
                                        log.orderDelayedDroneHit();
                                        // Add expense to store revenue for repairs
                                        currentStore.deductDroneHitRevenueLoss(currentOrder);
                                    } else {
                                        // Check and calculate for bird damage inflicted at current customer location
                                        String pilotID = currentDrone.getPilotID();
                                        String customerID = currentOrder.getCustomer();
                                        // Deduct credit
                                        customers.get(customerID).execute(order);
                                        // Add store revenue
                                        currentStore.setRevenue(totalCost);
                                        // Adjust remaining deliveries; reduce by 1
                                        currentStore.getFleet().get(droneID).deliver(order);
                                        // Pilot exp +1
                                        pilots.get(pilotID).gainExp();
                                        // Remove order
                                        currentStore.getOrders().remove(order);
                                    }
                                }
                            }
                        } else {
                            log.notAuthorized();
                        }

                    } else if (tokens[0].equals("cancel_order")) {
                        if (tokenSession.equals("admin") || tokenSession.equals("customer")) {
                            // Set clear variables
                            String store = tokens[1];
                            String order = tokens[2];
                            Store currentStore = stores.get(store);

                            if (!stores.containsKey(store)) {
                                log.storeDNE();
                            } else if (!currentStore.getOrders().containsKey(order)) {
                                log.orderDNE();
                            } else {
                                Order currentOrder = currentStore.getOrders().get(order);
                                String droneID = currentOrder.getDrone();
                                //
                                currentStore.getOrders().remove(order);
                                currentStore.getFleet().get(droneID).cancel(currentOrder);
                                log.okayChange();
                            }
                        } else {
                            log.notAuthorized();
                        }
//                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);
                    } else if (tokens[0].equals("transfer_order")) {
                        if (tokenSession.equals("admin") || tokenSession.equals("store")) {
                            String store = tokens[1];
                            String order = tokens[2];
                            String newDrone = tokens[3];
                            Store currentStore = stores.get(store);
                            // Determine error conditions
                            if (!stores.containsKey(store)) {
                                log.storeDNE();
                            } else if (!currentStore.getOrders().containsKey(order)) {
                                log.orderDNE();
                            } else if (!currentStore.getFleet().containsKey(newDrone)) {
                                log.droneDNE();
                            } else {
                                Order currentOrder = currentStore.getOrders().get(order);
                                String droneID = currentOrder.getDrone();
                                TreeMap<String, Drone> droneFleet = currentStore.getFleet();
                                // Distinct between the two drones for transfer
                                Drone transfer = droneFleet.get(newDrone);
                                Drone old = droneFleet.get(droneID);
                                // Determine error conditions
                                if (Objects.equals(transfer.getDroneID(), old.getDroneID())) {
                                    log.sameDrone();
                                } else if (droneFleet.get(droneID).transferCheck(transfer, order)) {
                                    log.transferWeight();
                                } else {
                                    // Start transfer process
                                    currentStore.initiateTransfer(old, transfer, currentOrder);
                                    log.okayChange();
                                }
                            }
                        } else {
                            log.notAuthorized();
                        }
                    } else if (tokens[0].equals("display_efficiency")) {
                        Set<String> keys = stores.keySet();
                        int overloads = 0;

                        for (String name : keys) {
                            Store currentStore = stores.get(name);
                            int orders = currentStore.getCountOrders();
                            int transfers = currentStore.getTransfers();

                            TreeMap<String, Drone> droneFleet = currentStore.getFleet();
                            Set<String> drones = droneFleet.keySet();
                            for (String drone : drones) {
                                int current = droneFleet.get(drone).getOverload();
                                overloads += current;
                            }
                            System.out.println(String.format("name:%s,purchases:%d,overloads:%d,transfers:%d",
                                    name, orders, overloads, transfers));
                        }
                        System.out.println("OK:display_completed");
                        //  System.out.println("no parameters needed");
                    } else if (tokens[0].equals("help")){
                        if (tokenSession.equals("store")) {
                            displayStoreCommands();
                        }
                        else if (tokenSession.equals("pilot")) {
                            displayPilotCommands();
                        }
                        else if (tokenSession.equals("customer")){
                            displayCustomerCommands();
                        }
                        else if (tokenSession.equals("admin")) {
                            displayAdminCommands();
                        }
                    } else if (tokens[0].equals("angry_birds")) {
                        //System.out.println("number of angry birds created: " + tokens[1] + ", probability of attack: " + tokens[2]);
                        //check that at least 1 customer or store is already in the system
                        int probability = Integer.parseInt(tokens[2]);
                        if(probability > 100 || probability < 0) {
                            System.out.println("ERROR: Please input a probability value between 0-100 for the second parameter");
                        } else if (customers.isEmpty() && stores.isEmpty()) {
                            log.unassignableAngryBirdLocations();
                        } else {
                            AngryBird.setGlobalProbability(probability);
                            //check that user wants to create at least 1 angry bird
                            if (Integer.parseInt(tokens[1]) <= 0) {
                                log.tooFewAngryBirds();
                            } else {
                                //initialize angry bird
                                AngryBird angryBird = null;
                                //add angry bird to list of angry birds with just ID
                                // angry bird class has a currentLocation field that is initially set to null;
                                for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                                    angryBird = new AngryBird(angryBirds.size() + 1);
                                    angryBirds.add(angryBird);
                                }
                                //get list of stores and add it to set of possible locations
                                Set<String> storeKeys = stores.keySet();
                                for (String name : storeKeys) {
                                    if (angryBird != null) {
                                        angryBird.addPossibleLocation(name);
                                    }
                                }
                                //get list of customers and add it to set of possible locations
                                Set<String> customerKeys = customers.keySet();
                                for (AngryBird bird : angryBirds) {
                                    for (String name : storeKeys) {
                                        bird.addPossibleLocation(name);
                                    }
                                    for (String id : customerKeys) {
                                        bird.addPossibleLocation(id);
                                    }
                                }

                                //assign the entire list of angry birds to a random location
                                AngryBird.assignAngryBirdsToRandomLocation(angryBirds);
                                log.okayChange();
                            }
                        }
                    } else if (tokens[0].equals("stop")) {
                        System.out.println("stop acknowledged");
                        break;

                    } else if (tokens[0].equals("logout")) {
                        tokenSession = null;
                        System.out.println("Successfully logged out.");
                        authenticateUser();
                    } else {
                        System.out.println("command " + tokens[0] + " NOT acknowledged");
                    }
            } catch(Exception e){
                e.printStackTrace();
                System.out.println();
            }
        }
            System.out.println("simulation terminated");
            commandLineInput.close();
        }
    }
}