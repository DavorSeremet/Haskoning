USE [CarDealership]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_AddCarToStore] 
	@Make VARCHAR(25),
	@Model VARCHAR(30),
	@Version VARCHAR(20),
	@YearOfRelease INT,
	@Price FLOAT,
	@FuelConsumption INT,
	@AnnualMaintenanceCost FLOAT
AS
BEGIN
	DECLARE @CarId INT;

	SET NOCOUNT ON;

		IF NOT EXISTS(SELECT 1 FROM Car WHERE Make = @Make
					AND Model = @Model
					AND Version = @Version)
		BEGIN
			INSERT INTO Car VALUES(@Make, @Model, @Version)
		END

	INSERT INTO CarInStore VALUES ((SELECT CarId FROM Car WHERE Make = @Make
										AND Model = @Model
										AND Version = @Version), 
									@YearOfRelease, 
									@Price, 
									@FuelConsumption,
									@AnnualMaintenanceCost)
END
GO


