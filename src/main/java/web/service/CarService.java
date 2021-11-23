package web.service;


import java.util.List;

public interface CarService <E> {

    List<E> getListOfCars(byte count);

}
