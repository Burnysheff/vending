package edu.paper.vending.service;

import edu.paper.vending.dto.ProductForm;
import edu.paper.vending.model.*;
import edu.paper.vending.repo.*;
import edu.paper.vending.repo.Vending;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VendingService {
    ProductRepository productRepository;
    BucketRepository bucketRepository;
    ProductCardRepository productCardRepository;
    OperationRepository operationRepository;
    UserRepository userRepository;

    public VendingService(ProductCardRepository productCardRepository, OperationRepository operationRepository, ProductRepository productRepository, UserRepository userRepository, BucketRepository bucketRepository) {
        this.productCardRepository = productCardRepository;
        this.operationRepository = operationRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.bucketRepository = bucketRepository;
    }

    public List<Product> getAllGuides() {
        System.out.println("\n\nGuide displayed!\n\n");
        return StreamSupport.stream(operationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Product> searchByTheme(List<String> theme) {
        List<Product> guides = new ArrayList<>();
        List<Product> allGuides = this.getAllGuides();

        for (String name : theme) {
            for (Product guide : allGuides) {
                for (Product themer : productCardRepository.findAllByTitle(name)) {
                    if (themer.getTheme().contains(themer)) {
                        guides.add(guide);
                    }
                }
            }
        }
        return guides;
    }

    public List<Product> searchByTheme(String theme) {
        List<Product> guides = new ArrayList<>();
        List<Product> allGuides = this.getAllGuides();

        for (Product guide : allGuides) {
            for (ProductCard themer : productCardRepository.findAllByTitle(theme)) {
                if (themer.getTheme().contains(themer)) {
                    guides.add(guide);
                }
            }
        }
        return guides;
    }

    public Product searchById(Long id) {
        if (operationRepository.findById(id).isPresent()) {
            return operationRepository.findById(id).get();
        }
        return null;
    }

    public boolean check(Long id, List<String> preview, String text) {
        Product guide = searchById(id);

        if (preview.size() == guide.getPreview().size()) {
            for (Preview name : guide.getPreview()) {
                if (!preview.contains(name.getImage())) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return guide.getText().equals(text);
    }

    public boolean deleteGuide(Long id) {
        Product guide = searchById(id);

        if (guide == null) {
            return false;
        } else {
            System.out.println("\n\nGuide deletedR!\n\n");
            operationRepository.delete(guide);
        }

        List<Vending> comments = bucketRepository.findCommentsByGuide(guide);
        bucketRepository.deleteAll(comments);

        return true;
    }

    public void save(ProductForm form) {
        Product guide = new Product();

        guide.setTitle(form.getTitle());
        guide.setText(form.getText());
        guide.setLink(form.getLink());

        Set<Operation> prev = new HashSet<>();
        for (String str : form.getPreview()) {
            Operation preview = new Preview();
            preview.setImage(str);

            productRepository.save(preview);
            prev.add(preview);
        }
        guide.setPreview(prev);

        Set<ProductCard> theme = new HashSet<>();
        for (String str : form.getTheme()) {
            ProductCard themer = new Theme();
            themer.setTitle(str);

            productCardRepository.save(themer);

            theme.add(themer);
        }
        guide.setTheme(theme);

        User user = userRepository.findByUsername(form.getUser().getUsername());

        if (user == null) {
            user = new User();

            user.setUsername(form.getUser().getUsername());
            userRepository.save(user);
        }

        guide.setUser(user);

        System.out.println("\n\nGuide created!\n\n");
        operationRepository.save(guide);

        // sendEmail(form.getTheme(), guide.getId(), form.getTitle());
    }
}
