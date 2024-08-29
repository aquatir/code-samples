package example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * /**
 * * Create a payment validator in Java to ensure that payments adhere to specific business rules before processing.
 * * The validator should enforce rules related to user balances, transaction limits, duplicates, and suspicious activities.
 * * ===== Validation Rules =====
 * <p>
 * * - Sufficient Funds: The sender must have enough balance to cover the transaction amount. Reject payments if the balance
 * *  is insufficient.
 * * - Transaction Amount Limits: Payments must be between AED 1 and AED 10,000.
 * * - Daily Transaction Limit: Each user (senderId) can only make up to 5 transactions per day.
 * Reject payments exceeding this limit.
 * * - Duplicate Transactions: Reject transactions that are identical to one already processed within the last 5 minutes.
 * * - Suspicious Transactions: Flag transactions above AED 5,000 for review but allow them to proceed.
 * <p>
 * * ===== Implementation =====
 * * Implement a system to validate payments according to the above rules.
 * * Keep track of user balances, transaction histories, and any necessary data for suspicious activities.
 */
public class PaymentsValidator {

    private static final BigDecimal MIN_PAYMENT_AMOUNT = BigDecimal.ONE;
    private static final BigDecimal MAX_PAYMENT_AMOUNT = BigDecimal.valueOf(10_000);
    private static final BigDecimal SUSPICIOUS_AMOUNT_TRIGGER = BigDecimal.valueOf(5_000);
    private static final int MAX_NUMBER_OF_TRANSACTIONS_LIMIT = 5;
    // payment id => Payment
    private HashMap<UUID, Payment> payments = new HashMap<>();

    // BalanceId  => Balance
    private HashMap<UUID, Balance> balances = new HashMap<UUID, Balance>();

    private HashMap<UUID, List<Payment>> userPayments = new HashMap<>();

    // UserId => one balance (todo: probably multiple balances, payment happen from a particular balance (account)
    private HashMap<UUID, Balance> userBalances = new HashMap<>();


    record Payment(
        UUID paymentId,
        UUID senderId, // userId
        BigDecimal amount,
        LocalDateTime paymentDate
    ) {
        public boolean isSimilar(Payment anotherPayment) {
            // depends on how "similarity is defined
            return false; // TODO
        }
    }

    record Balance(
        UUID balanceId,
        BigDecimal amount
    ) {
    }

    record ValidationResult(
        boolean isRejected, // todo: reason, maybe enum?
        String rejectReason,
        boolean isFlagged // todo: reason, maybe enum?
    ) {
    }

    // todo: not boolean? maybe enum
    public ValidationResult validatePayment(Payment payment) {

        // todo: sync access

        /*
         * - Sufficient Funds: The sender must have enough balance to cover the transaction amount. Reject payments if the balance
         */
        var userBalance = userBalances.get(payment.senderId);
        if (userBalance.amount.compareTo(payment.amount) < 1) {
            return new ValidationResult(true, "balance not sufficient", false);
        }

        /*
         * - Transaction Amount Limits: Payments must be between AED 1 and AED 10,000.
         */
        if (!strictBiggerThan(payment.amount, MIN_PAYMENT_AMOUNT) || strictBiggerThan(payment.amount, MAX_PAYMENT_AMOUNT)) {
            return new ValidationResult(true, "outside pamynet amount. Amount: " + payment.amount, false);
        }

        /*
         * Daily Transaction Limit: Each user (senderId) can only make up to 5 transactions per day.
         */

        var now = LocalDateTime.now();
        var paymentsToday = userPayments.get(payment.senderId)
            .stream().filter(it -> it.paymentDate.toLocalDate().equals(now.toLocalDate()))
            .toList();
        if (paymentsToday.size() > MAX_NUMBER_OF_TRANSACTIONS_LIMIT) {
            return new ValidationResult(true, "over max limit of transactions", false);
        }

        /*
         * Reject transactions that are identical to one already processed within the last 5 minutes.
         */
        var nowMinus5 = now.minusMinutes(5);

        /**
         *
         * |----------------------------|
         * ^ now() - 5 minutes         ^ now()
         */
        // todo: test
        var paymentsInLast5Minutes = paymentsToday.stream()
            .filter(it -> it.paymentDate.isAfter(nowMinus5)) // 5 minutes ago max
            .filter(it -> it.isSimilar(payment))
            .findAny();
        if (paymentsInLast5Minutes.isPresent()) {
            return new ValidationResult(true, "similar payment detected", false);
        }

        /*
         * Suspicious Transactions: Flag transactions above AED 5,000 for review but allow them to proceed.
         */
        if (strictBiggerThan(payment.amount, SUSPICIOUS_AMOUNT_TRIGGER)) {
            return new ValidationResult(false, null, true);
        }

        return new ValidationResult(false, null, false);
    }

    // TODO: unit test
    private boolean strictBiggerThan(BigDecimal left, BigDecimal right) {
        var compareResult = left.compareTo(right);
        return compareResult > 0;
    }
}
