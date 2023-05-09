package fraction;

/**
 * Immutable Fraction : numerator/denominator.
 *
 * Fraction parts are constant after construction. Immutable also means thread
 * safety. Invariants: Fraction is always normalized (greatest common divisor of
 * denominator and numerator is 1).
 *
 * Denominator is greater or equal to 1.
 *
 * @author Pieter van den Hombergh
 */
//TODO Declare fraction To be Comparable.
public class Fraction implements Comparable<Fraction> {

    //TODO implement all fields
    
    
    /**
     * Create a Fraction.
     *
     * @param numerator numerator
     * @param denominator denominator
     */
    private int numerator;
    private int denominator;
    
    public Fraction( int numerator, int denominator ) {
        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator cannot be equal to zero!");
        } else if (denominator < 0) {
            numerator *= (-1);
            denominator *= (-1);
        }
        
        int gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }
    
    public Fraction(int numerator) {
        this(numerator, 1);
    }
    
    /**
     * Compute Greatest Common Divisor. Used to normalize fractions.
     *
     * @param a first number
     * @param b second number, gt 0
     * @return greatest common divisor.
     */
    static int gcd( int a, int b ) {
        // make sure params to computation are positive.
        if ( a < 0 ) {
            a = -a;
        }
        while ( b != 0 ) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }


    /**
     * Get numerator.
     *
     * @return the numerator.
     */
    int getNumerator() {
        //TODO implement getNumerator
        return this.numerator;
    }

    /**
     * Get denominator.
     *
     * @return the denominator.
     */
    int getDenominator() {
        //TODO implement getDenominator
        return this.denominator;
    }
    
    /**
     * Multiply with Fraction.
     *
     * @param other Fraction
     * @return new Multiplied Fraction
     */
    public Fraction times( Fraction other ) {
        //TODO implement times
        return times(other.getNumerator(), other.getDenominator());
    }
    
    public Fraction times(int otherNumerator, int otherDenominator) {
        //TODO implement times
        return new Fraction(this.numerator * otherNumerator, this.denominator * otherDenominator);
    }
    
    public Fraction times(int numerator) {
        return this.times(new Fraction(numerator));
    }
    
    public Fraction plus(int otherNumerator, int otherDenominator) {
        int newNumerator = (this.numerator * otherDenominator) + (this.denominator * otherNumerator);
        int newDenominator = (this.denominator * otherDenominator);

        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction plus(Fraction other) {
        return this.plus(other.getNumerator(), other.getDenominator());
    }

    public Fraction plus(int numerator) {
        Fraction frac = new Fraction(numerator);
        return this.plus(frac);
    }
    
    public Fraction minus(Fraction other) {
        return this.plus((-1) * other.getNumerator(), other.getDenominator());
    }

    public Fraction minus(int numerator) {
        return this.minus(new Fraction(numerator));
    }
    
    public Fraction divideBy(Fraction other) {
        return this.times(other.getDenominator(), other.getNumerator());
    }
    
    public Fraction divideBy(int numerator) {
        return this.divideBy(new Fraction(numerator));
    }

    public Fraction inverse() {
        return new Fraction(this.denominator, this.numerator);
    }
    
    public Fraction negate() {
        return new Fraction((-1) * this.numerator, this.denominator);
    }

    @Override
    public int compareTo(Fraction fraction) {
        var first = (float) this.numerator / this.denominator;
        var second = (float) fraction.getNumerator() / fraction.getDenominator();

        if (first == second) {
            return 0;
        } else if (first > second) {
            return 1;
        } else {
            return -1;
        }
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Fraction fraction = (Fraction) object;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.numerator;
        hash = 79 * hash + this.denominator;
        return hash;
    }

    @Override
    public String toString() {
        if (this.denominator == 1) {
            return this.numerator + "";
        }

        int result = this.numerator / this.denominator;

        if (Math.abs(this.numerator) > Math.abs(this.denominator)) {
            if (this.numerator < 0 && this.denominator > 0) {
                return "-(" + Math.abs(result) + "+(" + Math.abs(this.numerator % this.denominator) + "/" + Math.abs(this.denominator) + "))";
            }
            return "(" + result + "+(" + this.numerator % this.denominator + "/" + this.denominator + "))";
        }
        if (this.numerator < 0 && this.denominator > 0) {
            return "-(" + Math.abs(this.numerator) + "/" + Math.abs(this.denominator) + ")";
        }
        return "(" + this.numerator + "/" + this.denominator + ")";
    }
}

