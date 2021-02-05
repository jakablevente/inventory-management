package com.ims.service;

import java.util.List;

import com.ims.model.Company;
import com.ims.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository compRepo;

    public List<Company> listAll(){
        
        return compRepo.findAll();
    }

    public void save ( Company company){

        compRepo.save(company);
    }

    public void delete (int id){

        compRepo.deleteById(id);
    }

    public Company get( int id ){

        return compRepo.findById(id).get();
    }
    
}
