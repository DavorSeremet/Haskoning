package service;

import controllers.DTO.CarDto;
import controllers.DTO.RecommendedCarDto;

import java.util.List;

public interface ICarService {
    List<CarDto> getAllCars();

    List<CarDto> getCarsByMake(String make);

    List<CarDto> getCarsByYear(int year);

    List<CarDto> getCars(String make, int year);

    void addCar(CarDto dto);

    List<RecommendedCarDto> getRecommendedCars(int kmsPerMonth, float fuelPrice);
}
