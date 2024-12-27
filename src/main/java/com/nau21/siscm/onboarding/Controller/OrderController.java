package com.nau21.siscm.onboarding.Controller;

import com.nau21.siscm.onboarding.DTO.*;
import com.nau21.siscm.onboarding.Model.*;
import com.nau21.siscm.onboarding.Service.OrderService;
import com.nau21.siscm.onboarding.Service.CustomerService;
import com.nau21.siscm.onboarding.Service.ItemsService;
import com.nau21.siscm.onboarding.Service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ItemsService itemsService;
    private final ShippingAddressService shippingAddressService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService, ItemsService itemsService, ShippingAddressService shippingAddressService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.itemsService = itemsService;
        this.shippingAddressService = shippingAddressService;
    }




    @GetMapping("/all-orders")
    public ResponseEntity<List<SimpleOrderDTO>> getAllOrdersSimplified() {
        List<Order> orders = orderService.getAllOrders();
        List<SimpleOrderDTO> simpleOrderDTOs = orders.stream()
                .map(order -> {
                    SimpleOrderDTO dto = new SimpleOrderDTO();
                    dto.setReference("UUID-" + order.getId()); // Lógica de referência
                    dto.setItems(order.getItems().size()); // Total de itens na ordem
                    dto.setCustomerName(order.getCustomer().getCustomerName()); // Nome do cliente
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(simpleOrderDTOs, HttpStatus.OK);
    }


    @GetMapping("/order/{reference}")
    @Transactional
    public ResponseEntity<OrderDetailsDTO> getOrderDetailsByReference(@PathVariable String reference) {
        Order order = orderService.getOrderById(Integer.parseInt(reference));
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Criar o DTO de resposta
        OrderDetailsDTO orderDetails = new OrderDetailsDTO();
        orderDetails.setReference("UUID-" + order.getId()); // Exemplo de UUID
        orderDetails.setCustomerName(order.getCustomer().getCustomerName());

        // Endereço formatado
        ShippingAddress address = order.getShippingAddress();
        orderDetails.setShippingAddress(String.format("%s, %s %s - %s",
                address.getStreet(),
                address.getZipCode(),
                address.getCity(),
                address.getCountryCode()));

        // Mapear os itens
        List<ItemDetailsDTO> itemDetails = order.getItems().stream().map(item -> {
            ItemDetailsDTO itemDTO = new ItemDetailsDTO();
            itemDTO.setName(item.getName());
            itemDTO.setDescription(item.getDescription());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setCurrency(item.getCurrency());
            itemDTO.setFormattedPrice(String.format("%.2f %s", item.getPrice(), item.getCurrency())); // Apenas para exibição
            itemDTO.setQuantity(item.getQuantity());


/*            List<ItemOptionDTO> options = item.getOptions().stream().map(option -> {
                ItemOptionDTO optionDTO = new ItemOptionDTO();
                optionDTO.setOptionName(option.getName().toString());
                optionDTO.setOptionValue(option.getValue().toString());
                return optionDTO;
            }).collect(Collectors.toList()); */

            List<ItemOptionDTO> options;
            if (item.getOptions() != null) {
                options = item.getOptions().stream().map(option -> {
                    ItemOptionDTO optionDTO = new ItemOptionDTO();
                    optionDTO.setOptionName(option.getName().toString());
                    optionDTO.setOptionValue(option.getValue().toString());
                    return optionDTO;
                }).collect(Collectors.toList());
            } else {
                options = new ArrayList<>();
            }



            itemDTO.setOptions(options);
            return itemDTO;
        }).collect(Collectors.toList());

        orderDetails.setItems(itemDetails);

        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }



    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        Order existingOrder = orderService.getOrderById(orderId);
        if (existingOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PostMapping("/register")
    public ResponseEntity<String> registerOrder(@RequestBody RegisterOrderDTO registerOrderDTO) {
        try {

            // Criar o cliente
            Customer customer = new Customer();
            customer.setCustomerName(registerOrderDTO.getCustomerName());
            String reference = java.util.UUID.randomUUID().toString();
            customer.setReference(reference);
            customerService.createCustomer(customer);

            // Criar o shiipingAddress
            ShippingAddress shippingAddress = new ShippingAddress();
            ShippingAddressDTO addressDTO = registerOrderDTO.getShippingAddress();
            shippingAddress.setStreet(addressDTO.getStreet());
            shippingAddress.setCity(addressDTO.getCity());
            shippingAddress.setCountryCode(addressDTO.getCountryCode());
            shippingAddress.setZipCode(addressDTO.getZipCode());
            shippingAddressService.saveShippingAddress(shippingAddress);

            // Criar os itens e opções
            List<Items> itemsList = registerOrderDTO.getItems().stream().map(itemDetailsDTO -> {
                Items item = new Items();
                item.setName(itemDetailsDTO.getName());
                item.setDescription(itemDetailsDTO.getDescription());
                item.setPrice(itemDetailsDTO.getPrice());
                item.setCurrency(itemDetailsDTO.getCurrency());
                System.out.println("Atribuindo quantity: " + itemDetailsDTO.getQuantity());
                item.setQuantity(itemDetailsDTO.getQuantity());
                itemsService.createItem(item);

                // Adicionar opções de itens
                if (itemDetailsDTO.getOptions() != null) {
                    itemDetailsDTO.getOptions().forEach(option -> {
                        ItemOptions itemOption = new ItemOptions();
                        itemOption.setName(optionName.valueOf(option.getOptionName().toUpperCase()));
                        itemOption.setValue(optionValue.valueOf(option.getOptionValue().toUpperCase()));
                        itemOption.setItems(item);
                        itemsService.createItemOption(itemOption);
                    });
                }


                return item;
            }).collect(Collectors.toList());

            // Criar a order
            Order order = new Order();
            order.setCustomer(customer);
            order.setShippingAddress(shippingAddress);
            order.setItems(itemsList);
            orderService.createOrder(order);

            return new ResponseEntity<>("Order registered successfully with reference: " + reference, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while registering order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
