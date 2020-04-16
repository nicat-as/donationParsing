package com.donation.repository;

import com.donation.domain.entity.Amount;
import com.donation.domain.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountRepository extends JpaRepository<Amount, Long> {
    Amount findByDonations_DonationName(String donationName);
}
