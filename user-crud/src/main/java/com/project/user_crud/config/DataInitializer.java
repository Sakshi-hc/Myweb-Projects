package com.project.user_crud.config;

import com.project.user_crud.entity.User;
import com.project.user_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Only add sample data if no users exist
        if (userRepository.count() == 0) {
            addSampleUsers();
        }
    }
    
    private void addSampleUsers() {
        User user1 = new User("John", "Doe", "john.doe@example.com", "+1-555-0101", "123 Main St, New York, NY");
        User user2 = new User("Jane", "Smith", "jane.smith@example.com", "+1-555-0102", "456 Oak Ave, Los Angeles, CA");
        User user3 = new User("Mike", "Johnson", "mike.johnson@example.com", "+1-555-0103", "789 Pine Rd, Chicago, IL");
        User user4 = new User("Sarah", "Williams", "sarah.williams@example.com", "+1-555-0104", "321 Elm St, Houston, TX");
        User user5 = new User("David", "Brown", "david.brown@example.com", "+1-555-0105", "654 Maple Dr, Phoenix, AZ");
        
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        
        System.out.println("Sample users have been added to the database!");
    }
} 