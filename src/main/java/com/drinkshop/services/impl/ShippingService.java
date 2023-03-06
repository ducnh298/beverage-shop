package com.drinkshop.services.impl;

import com.drinkshop.dto.OrderForCalculatingShippingCost;
import com.drinkshop.model.Order;
import com.drinkshop.model.OrderedDrink;
import com.drinkshop.model.OrderedDrinkOption;
import com.drinkshop.services.IShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShippingService implements IShippingService {

    @Value("${ghn.token}")
    private String ghnToken;
    @Value("${ghn.shop.id}")
    private int ghnShopId;
    @Value("${ghn.district.id}")
    private int ghnDistrictId;
    @Value("${ghn.service.type.id}")
    private int ghnServiceTypeId;
    @Value("${drink.height}")
    private int drinkHeight;
    @Value("${drink.length}")
    private int drinkLength;
    @Value("${drink.weight}")
    private int drinkWeight;
    @Value("${drink.width}")
    private int drinkWidth;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final WebClient webClient;

    public ShippingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://dev-online-gateway.ghn.vn/shiip/public-api")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Map<String, Object> getProvince() {
        return webClient.get().uri("/master-data/province")
                .header("token", ghnToken)
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public Map<String, Object> getDistrict(int provinceId) {
        Map<String, Integer> bodyMap = new HashMap<>();
        bodyMap.put("province_id", provinceId);
        return webClient.post().uri("/master-data/district")
                .header("token", ghnToken)
                .body(BodyInserters.fromValue(bodyMap))
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public Map<String, Object> getWard(int districtId) {
        Map<String, Integer> bodyMap = new HashMap<>();
        bodyMap.put("district_id", districtId);
        return webClient.post().uri("/master-data/ward")
                .header("token", ghnToken)
                .body(BodyInserters.fromValue(bodyMap))
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public Map<String, Object> getService(int fromDistrict, int toDistrict) {
        Map<String, Integer> bodyMap = new HashMap<>();
        bodyMap.put("shop_id", ghnShopId);
        bodyMap.put("from_district", fromDistrict);
        bodyMap.put("to_district", toDistrict);
        return webClient.post().uri("/v2/shipping-order/available-services")
                .header("token", ghnToken)
                .body(BodyInserters.fromValue(bodyMap))
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public Map<String, Object> calculateCost(OrderForCalculatingShippingCost order) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("from_district_id", ghnDistrictId);
        if (order.getServiceId() != null && !order.getServiceId().equals(""))
            bodyMap.put("service_id", order.getServiceId());
        else
            bodyMap.put("service_type_id", ghnServiceTypeId);
        bodyMap.put("to_district_id", order.getDistrictId());
        bodyMap.put("to_ward_code", order.getWardCode());
        bodyMap.put("height", drinkHeight);
        bodyMap.put("length", drinkLength * order.getNumberOfDrinks() % 2 == 0 ? order.getNumberOfDrinks() / 2 : (order.getNumberOfDrinks() / 2 + 1));
        bodyMap.put("weight", drinkWeight * order.getNumberOfDrinks());
        bodyMap.put("width", drinkWidth * (order.getNumberOfDrinks() % 2 + 1));
        bodyMap.put("insurance_value", order.getTotalDrinksCost());

        return webClient.post().uri("/v2/shipping-order/fee")
                .header("token", ghnToken)
                .body(BodyInserters.fromValue(bodyMap))
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public Map<String, Object> createOrder(Order order) {
        Map<String, Object> bodyMap = new HashMap<>();

        int numberOfDrinks = order.getDrinkList().stream()
                .map(drink -> drink.getQuantity())
                .reduce((x, y) -> x + y).orElse(0);

        List<Map<String, Object>> items = new ArrayList<>();
        for (OrderedDrink orderedDrink : order.getDrinkList()) {
            Map<String, Object> item = new HashMap<>();
            StringBuilder itemName = new StringBuilder(orderedDrink.getDrink().getName());

            BigDecimal itemPrice = new BigDecimal(0);
            itemPrice.add(orderedDrink.getDrink().getPrice()
                    .multiply(BigDecimal.valueOf((orderedDrink.getDrinkSize().getValue() * 0.3) + 1))
                    .multiply(BigDecimal.valueOf(orderedDrink.getQuantity())));

            for (OrderedDrinkOption orderedDrinkOption : orderedDrink.getOrderedDrinkOptionList()) {
                itemName.append(" + " + orderedDrinkOption.getDrinkOption().getName());
                itemPrice.add(orderedDrinkOption.getDrinkOption().getPrice().multiply(BigDecimal.valueOf(orderedDrinkOption.getQuantity())));
            }
            item.put("name", itemName.toString());
            item.put("code", orderedDrink.getDrink().getName());
            item.put("quantity", orderedDrink.getQuantity());
            item.put("price", itemPrice);
            item.put("length", drinkLength);
            item.put("width", drinkWidth);
            item.put("height", drinkHeight);

            items.add(item);
        }

        if (order.getOrderExtraData().getServiceId() != null && !order.getOrderExtraData().getServiceId().equals(""))
            bodyMap.put("service_id", order.getOrderExtraData().getServiceId());
        else
            bodyMap.put("service_type_id", ghnServiceTypeId);
        bodyMap.put("to_name", order.getCustomer().getFullName());

        bodyMap.put("to_phone", order.getCustomer().getPhone());
        bodyMap.put("to_address", order.getOrderExtraData().getAddressLine());
        bodyMap.put("to_ward_name", order.getOrderExtraData().getWard());
        bodyMap.put("to_district_name", order.getOrderExtraData().getDistrict());
        bodyMap.put("to_province_name", order.getOrderExtraData().getProvince());
        bodyMap.put("height", drinkHeight);
        bodyMap.put("length", drinkLength * numberOfDrinks % 2 == 0 ? numberOfDrinks / 2 : (numberOfDrinks / 2 + 1));
        bodyMap.put("weight", drinkWeight * numberOfDrinks);
        bodyMap.put("width", drinkWidth * (numberOfDrinks % 2 + 1));
        bodyMap.put("insurance_value", order.getTotal().intValue());
        bodyMap.put("cod_amount", order.getPaid() ? 0 : order.getTotal().add(order.getOrderExtraData().getShippingCost()).intValue());
        bodyMap.put("payment_type_id", 2);
        bodyMap.put("required_note", "CHOXEMHANGKHONGTHU");
        bodyMap.put("name", "Đơn hàng đồ uống");
        bodyMap.put("quantity", numberOfDrinks);
        bodyMap.put("Items", items);

        Map<String, Object> result = webClient.post().uri("/v2/shipping-order/create")
                .header("token", ghnToken)
                .header("shopId", ghnShopId + "")
                .body(BodyInserters.fromValue(bodyMap))
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();

        logger.info(result.toString());

        if (result.get("code").toString().equalsIgnoreCase("200"))
            return result;
        else return null;
    }
}
