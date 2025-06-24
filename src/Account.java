import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private List<Transaction> transactions = new ArrayList<>();
    private static  DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        transactions.add(new Transaction(LocalDate.now(), amount));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        transactions.add(new Transaction(LocalDate.now(), -amount));
    }

    @Override
    public void printStatement() {
        System.out.println("DATE || AMOUNT || BALANCE");

        List<String> statementLines = new ArrayList<>();
        int balance = 0;

        for (Transaction t : transactions) {
            balance += t.getAmount();
            String line = t.getDate().format(DATE_FORMAT) + " || " + t.getAmount() + " || " + balance;
            statementLines.add(line);
        }

        for (int i = statementLines.size() - 1; i >= 0; i--) {
            System.out.println(statementLines.get(i));
        }
    }

    public void deposit(int amount, String dateStr) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        transactions.add(new Transaction(LocalDate.parse(dateStr, DATE_FORMAT), amount));
    }

    public void withdraw(int amount, String dateStr) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        transactions.add(new Transaction(LocalDate.parse(dateStr, DATE_FORMAT), -amount));
    }
}
