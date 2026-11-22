package ma.lafnoune.billingservice.web;

import ma.lafnoune.billingservice.entities.Bill;
import ma.lafnoune.billingservice.feign.CustomerServiceRestClient;
import ma.lafnoune.billingservice.feign.InventoryServiceRestClient;
import ma.lafnoune.billingservice.model.Customer;
import ma.lafnoune.billingservice.repository.BillRepository;
import ma.lafnoune.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerServiceRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(
                    inventoryServiceRestClient.getProduct(productItem.getProductId()));
        });
        return bill;
    }

}
