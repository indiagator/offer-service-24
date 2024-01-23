package com.cbt.offerservice24;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class ProductOfferView {


    String offerid;
    String offername;
    String productname;
    String unit;
    Integer qty;
    Integer amnt;
    String currency;
    String sellername;


}
