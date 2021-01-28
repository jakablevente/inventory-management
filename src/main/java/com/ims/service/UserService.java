package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ims.model.User;
import com.ims.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
    private BCryptPasswordEncoder passwordEncoder;
	
	public List<User> listAll(){
		
		return userRepo.findAll();
	}
	
	public Page<User> listAll(int pageNum, String sortField, String sortDir){
		int pageSize = 8;
		
		Pageable pageable = PageRequest.of(pageNum -1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
						: Sort.by(sortField).descending());
		
		return userRepo.findAll(pageable);
	}
	
	public void save (User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
		
		userRepo.save(user);
	}
	
	public User get(int id) {
		
		return userRepo.findById(id).get();
		
	}
	
	public void delete (int id) {
		
		userRepo.deleteById(id);
	}
}
