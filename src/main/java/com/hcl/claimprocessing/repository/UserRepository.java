package com.hcl.claimprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.claimprocessing.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
