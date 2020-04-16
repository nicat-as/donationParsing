package com.donation.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(onConstructor = {})
@ToString
public class ParserDto implements Serializable {

    private static final long serialVersionUID = 8676764981442491968L;
    @NonNull
    private int type;

    @NonNull
    private String donationName;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private String currency;
}
