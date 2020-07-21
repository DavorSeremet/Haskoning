package controllers.DTO;

public class RecommendedCarDto {
    public String make;
    public String model;
    public String version;
    public int yearOfRelease;
    public int fuelConsumption;
    public float annualMaintenanceCost;
    public float totalAnnualCost;

    public RecommendedCarDto(String make, String model, String version, int yearOfRelease, int fuelConsumption, float annualMaintenanceCost, float totalAnnualCost) {
        this.make = make;
        this.model = model;
        this.version = version;
        this.yearOfRelease = yearOfRelease;
        this.fuelConsumption = fuelConsumption;
        this.annualMaintenanceCost = annualMaintenanceCost;
        this.totalAnnualCost = totalAnnualCost;
    }
}
