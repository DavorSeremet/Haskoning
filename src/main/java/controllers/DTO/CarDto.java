package controllers.DTO;

public class CarDto {
    public String make;
    public String model;
    public String version;
    public int yearOfRelease;
    public float price;
    public int fuelConsumption;
    public float annualMaintenanceCost;

    public CarDto(){
        //Constructor for mapper
    }

    public CarDto(String make, String model, String version, int yearOfRelease, float price, int fuelConsumption, float annualMaintanenceCost) {
        this.make = make;
        this.model = model;
        this.version = version;
        this.yearOfRelease = yearOfRelease;
        this.price = price;
        this.fuelConsumption = fuelConsumption;
        this.annualMaintenanceCost = annualMaintanenceCost;
    }
}
