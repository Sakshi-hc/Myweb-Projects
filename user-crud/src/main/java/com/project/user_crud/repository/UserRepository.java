package com.project.user_crud.repository;

import com.project.user_crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by email
    Optional<User> findByEmail(String email);
    
    // Find users by first name
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
    
    // Find users by last name
    List<User> findByLastNameContainingIgnoreCase(String lastName);
    
    // Find users by first name or last name
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> findByFirstNameOrLastNameContainingIgnoreCase(@Param("searchTerm") String searchTerm);
    
    // Check if email exists
    boolean existsByEmail(String email);
    
    // Find users by phone number
    List<User> findByPhoneContaining(String phone);
} 