public class DiscreteProbabilityFunctionChecker {

    public static boolean isProbabilityFunction(double[] probabilities) {
        double sum = 0.0;

        // Check non-negativity and calculate the sum of probabilities
        for (double prob : probabilities) {
            if (prob < 0) {
                return false; // Probability cannot be negative
            }
            sum += prob;
        }

        // Check if the sum is approximately equal to 1 (considering rounding errors)
        return Math.abs(sum - 1.0) < 1e-10;
    }

    public static void main(String[] args) {
        // Example: probabilities for a six-sided die
        double[] dieProbabilities = {1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6};

        if (isProbabilityFunction(dieProbabilities)) {
            System.out.println("It is a valid probability function.");
        } else {
            System.out.println("It is not a valid probability function.");
        }
    }
}

