package persistence.DAO;

import controllers.DTO.CarDto;
import controllers.DTO.RecommendedCarDto;

import java.util.List;

public interface ICarDAO {
    List<CarDto> getAllCars();

    List<CarDto> getCars(String make, int year);

    List<CarDto> getCarsByMake(String make);

    List<CarDto> getCarsByYear(int year);

    void addCar(CarDto dto);

    List<RecommendedCarDto> getRecommendedCars(int kmsPerMonth, float fuelPrice);
}
