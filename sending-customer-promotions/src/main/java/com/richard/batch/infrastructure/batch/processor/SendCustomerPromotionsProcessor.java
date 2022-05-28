package com.richard.batch.infrastructure.batch.processor;

import com.richard.batch.domain.InterestProductCustomer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;

@Component
public class SendCustomerPromotionsProcessor implements ItemProcessor<InterestProductCustomer, SimpleMailMessage> {

    @Override
    public SimpleMailMessage process(InterestProductCustomer interestProductCustomer) throws Exception {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("xpto@no-reply.com");
        email.setTo(interestProductCustomer.getClient().getEmail());
        email.setSubject("Promoção Imperdivel!!!");
        email.setText(genareteText(interestProductCustomer));
        return email;
    }
    
    private String genareteText(InterestProductCustomer interestProductCustomer) {
        StringBuilder writer = new StringBuilder();
        writer.append(String.format("Olá, %s˜\n\n", interestProductCustomer.getClient().getName()));
        writer.append("Essa promoção pode ser do seu interesse:\n\n");
        writer.append(String.format("%s - %s\n\n", interestProductCustomer.getProduct().getName(),
                                                   interestProductCustomer.getProduct().getDescription()));
        writer.append(String.format("Por apenas: %s!",
                NumberFormat.getCurrencyInstance().format(interestProductCustomer.getProduct().getPrice())));
        return writer.toString();
    }

}
