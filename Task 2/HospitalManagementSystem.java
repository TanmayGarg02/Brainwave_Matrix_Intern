import java.text.SimpleDateFormat;
import java.util.*;

class Patient {
    String patientId;
    String name;
    String address;
    String phoneNumber;
    String email;
    List<Appointment> appointments;

    public Patient(String patientId, String name, String address, String phoneNumber, String email) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}

class Appointment {
    String appointmentId;
    Patient patient;
    Date date;
    String doctor;

    public Appointment(String appointmentId, Patient patient, Date date, String doctor) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.date = date;
        this.doctor = doctor;
    }
}

class EHR {
    Patient patient;
    String medicalHistory;
    String prescriptions;
    String diagnosis;

    public EHR(Patient patient, String medicalHistory, String prescriptions, String diagnosis) {
        this.patient = patient;
        this.medicalHistory = medicalHistory;
        this.prescriptions = prescriptions;
        this.diagnosis = diagnosis;
    }
}

class Bill {
    String billId;
    Patient patient;
    double amount;
    Date issueDate;
    String status;

    public Bill(String billId, Patient patient, double amount, Date issueDate, String status) {
        this.billId = billId;
        this.patient = patient;
        this.amount = amount;
        this.issueDate = issueDate;
        this.status = status;
    }

    public void markAsPaid() {
        this.status = "Paid";
    }
}

class Inventory {
    String itemId;
    String itemName;
    int quantity;
    double price;

    public Inventory(String itemId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public boolean isLowStock() {
        return quantity < 10;
    }

    public void reduceStock(int amount) {
        this.quantity -= amount;
    }
}

class Staff {
    String staffId;
    String name;
    String role;
    double salary;
    String shift;

    public Staff(String staffId, String name, String role, double salary, String shift) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.shift = shift;
    }

    public void changeShift(String newShift) {
        this.shift = newShift;
    }
}

class HospitalManagementSystem {
    Map<String, Patient> patients = new HashMap<>();
    Map<String, Appointment> appointments = new HashMap<>();
    Map<String, EHR> ehrRecords = new HashMap<>();
    Map<String, Bill> bills = new HashMap<>();
    Map<String, Inventory> inventoryItems = new HashMap<>();
    Map<String, Staff> staffMembers = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public void registerPatient() {
        System.out.println("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.println("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Address: ");
        String address = scanner.nextLine();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();

        Patient patient = new Patient(patientId, name, address, phoneNumber, email);
        patients.put(patientId, patient);
        System.out.println("Patient registered successfully!");
    }

    public void scheduleAppointment() {
        System.out.println("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine();
        System.out.println("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = patients.get(patientId);
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }
        System.out.println("Enter Appointment Date (dd/MM/yyyy): ");
        String dateString = scanner.nextLine();
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
            System.out.println("Enter Doctor's Name: ");
            String doctor = scanner.nextLine();

            Appointment appointment = new Appointment(appointmentId, patient, date, doctor);
            appointments.put(appointmentId, appointment);
            patient.addAppointment(appointment);
            System.out.println("Appointment scheduled successfully!");
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }

    public void generateBill() {
        System.out.println("Enter Bill ID: ");
        String billId = scanner.nextLine();
        System.out.println("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = patients.get(patientId);
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }
        System.out.println("Enter Bill Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        System.out.println("Enter Bill Status (Paid/Unpaid): ");
        String status = scanner.nextLine();

        Bill bill = new Bill(billId, patient, amount, new Date(), status);
        bills.put(billId, bill);
        System.out.println("Bill generated successfully!");
    }

    public void updateInventory() {
        System.out.println("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.println("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.println("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.println("Enter Price per Item: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Inventory inventory = new Inventory(itemId, itemName, quantity, price);
        inventoryItems.put(itemId, inventory);
        System.out.println("Inventory updated successfully!");
    }

    public void addStaff() {
        System.out.println("Enter Staff ID: ");
        String staffId = scanner.nextLine();
        System.out.println("Enter Staff Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Role: ");
        String role = scanner.nextLine();
        System.out.println("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Shift: ");
        String shift = scanner.nextLine();

        Staff staff = new Staff(staffId, name, role, salary, shift);
        staffMembers.put(staffId, staff);
        System.out.println("Staff added successfully!");
    }

    public void displayPatientDetails(String patientId) {
        Patient patient = patients.get(patientId);
        if (patient != null) {
            System.out.println("Patient ID: " + patient.patientId);
            System.out.println("Name: " + patient.name);
            System.out.println("Address: " + patient.address);
            System.out.println("Phone Number: " + patient.phoneNumber);
            System.out.println("Email: " + patient.email);
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Hospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Generate Bill");
            System.out.println("4. Update Inventory");
            System.out.println("5. Add Staff");
            System.out.println("6. Display Patient Details");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    generateBill();
                    break;
                case 4:
                    updateInventory();
                    break;
                case 5:
                    addStaff();
                    break;
                case 6:
                    System.out.println("Enter Patient ID: ");
                    String patientId = scanner.nextLine();
                    displayPatientDetails(patientId);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        HospitalManagementSystem system = new HospitalManagementSystem();
        system.displayMenu();
    }
}
