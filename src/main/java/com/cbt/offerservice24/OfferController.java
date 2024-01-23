package com.cbt.offerservice24;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/offer")
public class OfferController
{

    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    ProductofferstatusRepository productofferstatusRepository;
    @Autowired
    ProductOfferViewService productOfferViewService;

    Logger logger = LoggerFactory.getLogger(OfferController.class);



    @PostMapping("save/offer")
    public ResponseEntity<Productoffer> saveOffer(@RequestBody Productoffer offer)
    {
        offer.setId(String.valueOf((int) (Math.random() * 10000)));
        productofferRepository.save(offer);

        Productofferstatus productofferstatus = new Productofferstatus();
        productofferstatus.setId(String.valueOf((int) (Math.random() * 10000)));
        productofferstatus.setOfferid(offer.getId());
        productofferstatus.setStatus("OPEN");

        productofferstatusRepository.save(productofferstatus);

        return ResponseEntity.ok(offer);
    }

    @GetMapping("get/offers/{offer_status}")
    public ResponseEntity<List<ProductOfferView>> getOffers(@PathVariable String offer_status )
    {
        List<Productofferstatus> requestedOffers =  productofferstatusRepository.findByStatusIgnoreCase(offer_status);
        List<ProductOfferView> finalOffers =  requestedOffers.stream().map(productofferstatus -> productOfferViewService.createProductOfferView(productofferstatus.getOfferid())).collect(Collectors.toList());
        return ResponseEntity.ok(finalOffers);
    }


    @PostMapping("update/offer/status/{status}/{offerid}")
    public ResponseEntity<String> updateProductOfferStatus(@PathVariable String status,
                                                           @PathVariable String offerid)
    {
        productofferstatusRepository.updateStatusByOfferid(status,offerid);
        logger.info("offer accepted and status updated");
        return ResponseEntity.ok().body("status updated");
    }



}
