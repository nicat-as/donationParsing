package com.donation.service;

import com.donation.domain.dto.ParserDto;
import com.donation.domain.entity.Donation;
import com.donation.domain.entity.Type;

import java.util.List;

public interface ParserService {
    List<Type> parseType();
    List<ParserDto> parseAll();
}
