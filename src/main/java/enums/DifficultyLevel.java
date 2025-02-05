package enums;

public enum DifficultyLevel {
    EASY(10.0),
    MEDIUM(25.0),
    HARD(50.0);

    private final double amount;

    DifficultyLevel(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}