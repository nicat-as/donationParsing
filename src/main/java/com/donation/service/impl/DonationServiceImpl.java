package com.donation.service.impl;

import com.donation.domain.entity.Donation;
import com.donation.repository.DonationRepository;
import com.donation.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Transactional
    @Override
    public Donation addDonation(Donation donation) {
        try {
            donation = donationRepository.save(donation);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return donation;
        }
    }

    @Override
    public Donation getDonationByName(String donationName) {
        return donationRepository.findByDonationName(donationName);
    }
}
