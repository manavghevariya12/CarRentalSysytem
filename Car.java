import java.util.*;
class Car
{
    int carId;
    String brand;
    String model;
    boolean isAvailable;

    Car(int carId,String brand, String model)
    {
    this.carId = carId;
    this.brand = brand;
    this.model = model;
    this.isAvailable = true;
    }
    void displayCar()
    {
        System.out.println(carId+"."+brand+" "+model+"-"+(isAvailable ? "Available":"Rented"));
    }
}

class Customer
{
    int customerId;
    String name;

    Customer(int customerId,String name)
    {
        this.customerId = customerId;
        this.name = name;
    }

    void displayCustomer()
    {
        System.out.println("Customer ID : " +customerId);
        System.out.println("Customer Name : "+name);
    }
}

class Rental
{
    Car rentedCar;       //object of class Car 
    Customer customer;   //object of class Customer
    int rentalDays;

    Rental(Car rentedCar,Customer customer,int rentalDays)
    {
        this.rentalDays = rentalDays;
        this.customer = customer;
        this.rentalDays = rentalDays;
        this.rentedCar.isAvailable = false;   // calling isAvailable boolean variable from object of car class 
    }

    void displayRentedDetails()
    {
        System.out.println("-----Rental Details-----");

        customer.displayCustomer();  //calling displayCustomer method using Customer class's object customer

        System.out.println("Car rented : "+rentedCar.brand+" "+rentedCar.model);  //caliing car brand and model from class Car
        
        System.out.println("Rental Days : "+rentalDays);

        System.out.println("----------------------");
    }

    void returnCar()
    {
        rentedCar.isAvailable = true;
        System.out.println("Car"+rentedCar.brand+" "+rentedCar.model+" has been returned successfully!!");
    }
}

class CarRentalSystem
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        Car cars[] = new Car[4];
        cars[0] = new Car(1,"Toyota","Corolla");
        cars[1] = new Car(2,"Honda","City");
        cars[2] = new Car(3,"Ford","Mustang");
        cars[3] = new Car(4,"Mecedese","G-Wagon");
        
        Rental currentRental = null;

        while (true) 
        {
            System.out.println(".... CAR RENTAL SYSTEM ....");

            System.out.println("1.View Cars");

            System.out.println("2.Rent a car");

            System.out.println("3.Return a car");

            System.out.println("4.Exit");

            System.out.println("Enter choice :");

            int choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                System.out.println("Availabe Cars");
                for(Car car:cars)
                {
                    car.displayCar();
                }
                break;

                case 2:
                System.out.println("Enter Customer Id : ");
                int customerId = sc.nextInt();

                sc.nextLine();   //for evaluting flushing buffer

                System.out.println("Enter Customer Name :");
                String name = sc.nextLine();

                Customer customer = new Customer(customerId, name);

                System.out.println("Select a Car");

                for(Car car:cars)
                {
                    if(car.isAvailable)
                    {
                        car.displayCar();
                    }
                }

                System.out.println("Enter Car Id: ");
                int carId = sc.nextInt();

                Car selectedCar = null;

                for(Car car:cars)
                {
                    if(car.carId == carId && car.isAvailable)
                    {
                        selectedCar = car;
                        break;
                    }
                }
                if(selectedCar == null)
                {
                    System.out.println("Invalid selection or car is not found");
                    break;
                }

                System.out.println("Enter Rental Days");
                int days = sc.nextInt();

                currentRental = new Rental(selectedCar, customer, days);

                currentRental.displayRentedDetails();
                break;

                case 3:
                if(currentRental == null)
                {
                    System.out.println("NO  active rental found");
                }

                else
                {
                    currentRental.returnCar();
                    currentRental = null;
                    break;
                }

                case 4:
                System.out.println("Exitning......");
                System.out.println("Thank you for visiting CAR RENTAL SYSTM!!!");

                sc.close();
                return;

                default:
                System.out.println("Inavalid choice!!. Please enter valid choice or Try again");
            }
        }
    }
}
