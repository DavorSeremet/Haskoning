package controllers;

import controllers.DTO.CarDto;
import controllers.DTO.RecommendedCarDto;
import service.ICarService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class CarController {
    @Inject
    private ICarService carService;

    @GET
    @Path("search_cars")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCars() {
        List<CarDto> carDtos = carService.getAllCars();

        return Response.ok(carDtos).build();
    }

    @GET
    @Path("search_cars/make/{make}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCarsByMake(@PathParam("make") String make) {
        List<CarDto> carDtos = carService.getCarsByMake(make);

        return Response.ok(carDtos).build();
    }

    @GET
    @Path("search_cars/year/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCarsByYear(@PathParam("year") int year) {
        List<CarDto> carDtos = carService.getCarsByYear(year);

        return Response.ok(carDtos).build();
    }

    @GET
    @Path("search_cars/make/{make}/year/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCarsByYear(@PathParam("make") String make, @PathParam("year") int year) {
        List<CarDto> carDtos = carService.getCars(make, year);

        return Response.ok(carDtos).build();
    }

    @POST
    @Path("add_car")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCar(CarDto dto){
        carService.addCar(dto);
    }

    @GET
    @Path("recommend_cars/{kmsPerMonth}/{fuelPrice}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCarsByYear(@PathParam("kmsPerMonth") int kmsPerMont, @PathParam("fuelPrice") float fuelPrice) {
        List<RecommendedCarDto> recommendedCarDtos = carService.getRecommendedCars(kmsPerMont, fuelPrice);

        return Response.ok(recommendedCarDtos).build();
    }
}
