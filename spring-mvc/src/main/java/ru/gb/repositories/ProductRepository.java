package ru.gb.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.gb.Data.Product;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByTitle(String title);
/*    List<Product> findByCostGreaterThan(int min);
    List<Product> findByCostLessThan(int max);
    List<Product> findByCostGreaterThanAndCostLessThan(int min, int max);*/
    Page<Product> findAll(@Nullable Specification<Product> spec, Pageable pageable);
}
