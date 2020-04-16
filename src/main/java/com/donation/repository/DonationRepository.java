package com.donation.repository;

import com.donation.domain.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DonationRepository extends JpaRepository<Donation,Long> {
    Donation findByDonationName(String donationName);
}
