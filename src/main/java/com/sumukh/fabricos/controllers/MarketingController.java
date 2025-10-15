package com.sumukh.fabricos.controllers;

import com.sumukh.fabricos.Dtos.CommunicateInfoDto;
import com.sumukh.fabricos.Entities.Customer;
import com.sumukh.fabricos.Repositories.CustomerRepository;
import com.sumukh.fabricos.Repositories.TemplateRepository;
import com.sumukh.fabricos.enums.Channel;
import com.sumukh.fabricos.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sumukh.fabricos.utils.TemplateUtils.replaceNamePlaceholder;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MarketingController {
    
    private final EmailService emailService;
    private final CustomerRepository customerRepository;
    private final TemplateRepository templateRepository;
    
    public MarketingController(EmailService emailService, CustomerRepository customerRepository, TemplateRepository templateRepository) {
        this.emailService = emailService;
        this.customerRepository = customerRepository;
        this.templateRepository = templateRepository;
    }
    
    @PostMapping("/marketing/communicate")
    public ResponseEntity<Map<String,Object>> sendCommunication(@RequestBody CommunicateInfoDto request){
        Channel channel = Channel.valueOf(request.getChannel());
        Long templateId = Long.valueOf(request.getTemplateId());
        List<Long> selectedCustomers = request.getSelectedCustomers().stream().map(Long::valueOf).toList();
        
        String templateSubject = templateRepository.findById(templateId).orElseThrow().getSubject();
        List<String> customersNotFound = new ArrayList<>();
        
        if(channel == Channel.EMAIL){
            for(Long customerId : selectedCustomers){
                if(customerRepository.findById(customerId).isPresent()){
                    Customer customer = customerRepository.findById(customerId).get();
                    emailService.sendEmail(customer.getEmail(), replaceNamePlaceholder(templateSubject,customer.getName()));
                } else {
                    customersNotFound.add(String.valueOf(customerId));
                }
            }
        } else {
            // send sms
        }
        
        Map<String, Object> res = new HashMap<>();
        res.put("customersNotFound", customersNotFound);
        res.put("customersNotified", String.valueOf(selectedCustomers.size() - customersNotFound.size()));
        
        return ResponseEntity.ok(res);
    }
}
