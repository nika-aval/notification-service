package pdp.notification_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import pdp.notification_service.dto.UtilityBillDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NotificationConsumerService {

    public static final String CUSTOMER_NOTIFICATION = "customer_notification";
    public static final String CUSTOMER_NOTIFICATION_GROUP = "customer_notification_group";

    @KafkaListener(topics = CUSTOMER_NOTIFICATION, groupId = CUSTOMER_NOTIFICATION_GROUP)
    public void cancelSubscription(Message<UtilityBillDto> utilityBill) {

        String subscriptionProvider = utilityBill.getPayload().getSubscriptionProvider().name();
        String subscription = utilityBill.getPayload().getSubscriptionProvider().description();
        LocalDateTime date = utilityBill.getPayload().getDate();
        BigDecimal amount = utilityBill.getPayload().getAmount();
        String customer = utilityBill.getPayload().getCustomer().firstName() + " " + utilityBill.getPayload().getCustomer().lastName();
        String email = utilityBill.getPayload().getCustomer().email();

        System.out.println(String.format("Received a bill for subscription={%s} from provider={%s} for {%s} EUR. " +
                                        "Sending email to {%s - %s}. " + "Please find the bill attached below:",
                                        subscription, subscriptionProvider, amount, customer, email));

        System.out.println("|-----------NEW_BILL_ISSUED!-----------|");
        System.out.println("|Date: " + date.format(DateTimeFormatter.ISO_DATE) + " " + date.format(DateTimeFormatter.ISO_TIME));
        System.out.println("|customer: " + customer);
        System.out.println("|Utility Provider: " + subscriptionProvider);
        System.out.println("|Service price: " + amount);
        System.out.println("|--------------------------------------|");
        System.out.println();
    }

}
