package service;

import controllers.DTO.CarDto;
import controllers.DTO.RecommendedCarDto;
import persistence.DAO.ICarDAO;

import javax.inject.Inject;
import java.util.List;

public class CarService implements ICarService {
    @Inject
    private ICarDAO carDAO;

    @Override
    public List<CarDto> getAllCars(){
        return carDAO.getAllCars();
    }

    @Override
    public List<CarDto> getCarsByMake(String make){
        return carDAO.getCarsByMake(make);
    }

    @Override
    public List<CarDto> getCarsByYear(int year){
        return carDAO.getCarsByYear(year);
    }

    @Override
    public List<CarDto> getCars(String make, int year){
        return carDAO.getCars(make, year);
    }

    @Override
    public void addCar(CarDto dto){
        carDAO.addCar(dto);
    }

    @Override
    public List<RecommendedCarDto> getRecommendedCars(int kmsPerMonth, float fuelPrice){
        return carDAO.getRecommendedCars(kmsPerMonth, fuelPrice);
    }
}
