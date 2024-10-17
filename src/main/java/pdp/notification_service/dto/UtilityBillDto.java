package pdp.notification_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UtilityBillDto(Long Id, LocalDateTime date, BigDecimal Amount, boolean isPaid,
                             SubscriptionProviderDto subscriptionProvider, CustomerDto customer) {
}
