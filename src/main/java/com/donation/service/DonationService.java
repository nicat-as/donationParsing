package com.donation.service;

import com.donation.domain.entity.Donation;

import java.util.List;

public interface DonationService {
    Donation addDonation(Donation donations);
    Donation getDonationByName(String donationName);
}
