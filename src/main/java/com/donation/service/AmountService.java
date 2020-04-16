package com.donation.service;

import com.donation.domain.entity.Amount;

import java.util.List;

public interface AmountService {
    Amount addAmount(Amount amount);
    Amount getDonationByDonationName(String donationName);
}
