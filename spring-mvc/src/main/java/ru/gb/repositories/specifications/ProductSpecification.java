package ru.gb.repositories.specifications;


import org.springframework.data.jpa.domain.Specification;
import ru.gb.Data.Product;

public class ProductSpecification {
    public static Specification<Product> costGreaterThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("cost"), cost);
    }

    public static Specification<Product> costLessThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("cost"), cost);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%s%%", titlePart));
    }
}
