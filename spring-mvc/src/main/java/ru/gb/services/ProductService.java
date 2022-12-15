package ru.gb.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.Data.Product;
import ru.gb.exceptions.ResourceNotFoundException;
import ru.gb.repositories.ProductRepository;
import ru.gb.repositories.specifications.ProductSpecification;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Transactional
    public Page<Product> find(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.costGreaterThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.costLessThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecification.titleLike(partTitle));
        }
        return productRepo.findAll(spec, PageRequest.of(page - 1, 5));
    }
    @Transactional
    public List<Product> getProductList() {
        return (List<Product>) productRepo.findAll();
    }
    @Transactional
    public Product getItemByID(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден в базе, id: " + id ));
    }
    @Transactional
    public List<Product> getAllByID(Long[] arrID) {
        return (List<Product>) productRepo.findAllById(List.of(arrID));
    }
    @Transactional
    public void addItem(String title, int cost) {
        productRepo.save(new Product(title, cost));
    }
    @Transactional
    public void saveProduct(Product product) {
        productRepo.save(product);
    }
    @Transactional
    public void removeItem(Long id) {
        productRepo.deleteById(id);
    }
}
