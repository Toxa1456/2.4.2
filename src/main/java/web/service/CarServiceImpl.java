package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService<Car> {

    private static final List<Car> cars = new ArrayList<>();


    static {
        cars.add(new Car("Porsche", "911", 1234));
        cars.add(new Car("Lada", "Granta", 124578));
        cars.add(new Car("Mercedes", "GLS", 3576));
        cars.add(new Car("Toyota", "Camry", 45632));
        cars.add(new Car("Hyundai", "Solaris", 346853));
    }




    @Override
    public List<Car> getListOfCars(byte count) {
        if (count == 0) {
            return cars;
        }

        return cars.stream().limit(count).collect(Collectors.toList());
    }
}
