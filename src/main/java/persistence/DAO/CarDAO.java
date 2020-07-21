package persistence.DAO;

import controllers.DTO.CarDto;
import controllers.DTO.RecommendedCarDto;
import persistence.IConnectionFactory;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements ICarDAO {
    @Inject
    private IConnectionFactory connectionFactory;

    @Override
    public List<CarDto> getAllCars(){
        String sqlQuery = "SELECT * FROM CarInStore cis INNER JOIN Car c ON cis.CarId = c.CarId";

        return getCars(sqlQuery);
    }

    @Override
    public List<CarDto> getCars(String make, int year){
        String sqlQuery = "SELECT * FROM CarInStore cis INNER JOIN Car c ON cis.CarId = c.CarId WHERE c.Make = '" + make + "' AND cis.YearOfRelease = " + year;

        return getCars(sqlQuery);
    }

    @Override
    public List<CarDto> getCarsByMake(String make){
        String sqlQuery = "SELECT * FROM CarInStore cis INNER JOIN Car c ON cis.CarId = c.CarId WHERE c.Make = '" + make + "'";

        return getCars(sqlQuery);
    }

    @Override
    public List<CarDto> getCarsByYear(int year){
        String sqlQuery = "SELECT * FROM CarInStore cis INNER JOIN Car c ON cis.CarId = c.CarId WHERE cis.YearOfRelease = " + year;

        return getCars(sqlQuery);
    }

    private List<CarDto> getCars(String sqlQuery){
        List<CarDto> carDtos = new ArrayList<>();
        try(
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                carDtos.add(new CarDto(resultSet.getString("Make"), resultSet.getString("Model"), resultSet.getString("Version"),
                        resultSet.getInt("YearOfRelease"), resultSet.getFloat("Price"), resultSet.getInt("FuelConsumption"), resultSet.getFloat("AnnualMaintenanceCost")));
            }
        }catch (SQLException e){
            throw new RuntimeException("message", e);
        }

        return carDtos;
    }

    @Override
    public void addCar(CarDto dto) {
        String sqlQuery = "EXEC sp_AddCarToStore @Make = ?, @Model = ?,  @Version = ?, @YearOfRelease = ?, @Price = ?, @FuelConsumption = ?, @AnnualMaintenanceCost = ?";

        try(
                Connection connection = connectionFactory.getConnection();
                CallableStatement callableStatement = connection.prepareCall(sqlQuery);
        ){
            callableStatement.setString(1, dto.make);
            callableStatement.setString(2, dto.model);
            callableStatement.setString(3, dto.version);
            callableStatement.setInt(4, dto.yearOfRelease);
            callableStatement.setFloat(5, dto.price);
            callableStatement.setInt(6, dto.fuelConsumption);
            callableStatement.setFloat(7, dto.annualMaintenanceCost);
            callableStatement.execute();
        } catch (SQLException e){
            throw new RuntimeException("message", e);
        }
    }

    @Override
    public List<RecommendedCarDto> getRecommendedCars(int kmsPerMonth, float fuelPrice){
        String sqlQuery = "SELECT c.Make, c.Model, c.Version, cis.YearOfRelease, cis.FuelConsumption, cis.AnnualMaintenanceCost, (? * 12 / FuelConsumption * ? + AnnualMaintenanceCost) AS TotalAnnualCost FROM CarInStore cis INNER JOIN car c ON cis.CarId = c.CarId ORDER BY TotalAnnualCost ASC";

        List<RecommendedCarDto> recommendedCarDtos = new ArrayList<>();
        try(
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ){
            preparedStatement.setInt(1, kmsPerMonth);
            preparedStatement.setFloat(2, fuelPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                recommendedCarDtos.add(new RecommendedCarDto(resultSet.getString("Make"), resultSet.getString("Model"), resultSet.getString("Version"),
                        resultSet.getInt("YearOfRelease"), resultSet.getInt("FuelConsumption"), resultSet.getFloat("AnnualMaintenanceCost"),
                        resultSet.getFloat("TotalAnnualCost")));
            }
        }catch (SQLException e){
            throw new RuntimeException("message", e);
        }

        return recommendedCarDtos;

    }
}
