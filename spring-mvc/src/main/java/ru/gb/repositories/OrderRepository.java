package ru.gb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.Data.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
