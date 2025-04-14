package com.investscreener.investscreener.repositories;

import com.investscreener.investscreener.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
