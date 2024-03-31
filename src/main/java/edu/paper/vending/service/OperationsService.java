package edu.paper.vending.service;

import edu.paper.vending.dto.LoginForm;
import edu.paper.vending.dto.ProductForm;
import edu.paper.vending.model.Operation;
import edu.paper.vending.model.Product;
import edu.paper.vending.model.ProductCard;
import edu.paper.vending.model.User;
import edu.paper.vending.repo.BucketRepository;
import edu.paper.vending.repo.OperationRepository;
import edu.paper.vending.repo.UserRepository;
import edu.paper.vending.repo.Vending;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OperationsService {

	BucketRepository bucketRepository;
	OperationRepository operationRepository;
	UserRepository userRepository;
	Vending vending;

	public OperationsService(BucketRepository bucketRepository, OperationRepository operationRepository, UserRepository userRepository, Vending vending) {
		this.bucketRepository = bucketRepository;
		this.operationRepository = operationRepository;
		this.userRepository = userRepository;
		this.vending = vending;
	}

	public boolean checkUser(LoginForm form) {
		User user = userRepository.findByEmail(form.getEmail());

		if (user == null) {
			return false;
		}
		return bCryptPasswordEncoder.matches(form.getPassword(), user.getPassword());
	}

	public boolean checkNames(String name) {
		/**for (User user : userRepository.findAll()) {
		 if (user.getUsername().equals(name)) {
		 return false;
		 }
		 }*/
		return true;
	}

	public String nameByEmail(String email) {
		return userRepository.findByEmail(email).getUsername();
	}

	public User getByName(String name) {
		return userRepository.findByUsername(name);
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
