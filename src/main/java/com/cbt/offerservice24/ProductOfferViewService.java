package com.cbt.offerservice24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOfferViewService
{
    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    ProductRepository productRepository;

    public ProductOfferView createProductOfferView(String offerid)
    {
        ProductOfferView offerView = new ProductOfferView();

        Productoffer offer = productofferRepository.findById(offerid).get();
        Product product = productRepository.findById(offer.getHscode()).get();

        offerView.setOfferid(offerid);
        offerView.setOffername(offer.getOffername());
        offerView.setQty(offer.getQty());
        offerView.setProductname(product.getName());
        offerView.setUnit(product.getUnit());
        offerView.setAmnt(offer.getQty()*offer.getUnitprice());
        offerView.setCurrency(offer.getCurrency());
        offerView.setSellername(offer.getSellername());

        return offerView;
    }

}
