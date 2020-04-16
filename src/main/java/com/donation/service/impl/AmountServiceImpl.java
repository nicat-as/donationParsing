package com.donation.service.impl;

import com.donation.domain.entity.Amount;
import com.donation.repository.AmountRepository;
import com.donation.service.AmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmountServiceImpl implements AmountService {

    @Autowired
    private AmountRepository amountRepository;


    @Override
    public Amount addAmount(Amount amount) {
        try {
            amount = amountRepository.save(amount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            return amount;
        }
    }

    @Override
    public Amount getDonationByDonationName(String donationName) {
        return amountRepository.findByDonations_DonationName(donationName);
    }
}
