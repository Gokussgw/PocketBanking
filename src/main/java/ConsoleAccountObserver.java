public class ConsoleAccountObserver implements AccountObserver {
    @Override
    public void update(Account account) {
        System.out.println("Account updated: " + account);
    }
}

