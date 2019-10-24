package com.cignex.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 
 * @author ramkumar.sadhu
 *
 */

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExhcangeServiceProxy {

	@GetMapping(path = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
	
}
