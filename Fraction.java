package Exam_2016.CourseWork1;

import java.math.*;

/**
 * Immutable fractions of whole numbers, also known as rational numbers,
 * with their usual arithmetic operations.
 * 
 * Similar to java.lang.BigInteger, the documentation uses pseudo-code
 * throughout the descriptions of Fraction methods. The pseudo-code
 * expression (i + j) is shorthand for "a Fraction whose value is that
 * of the Fraction plus that of the Fraction j". Other pseudo-code
 * expressions are interpreted similarly. 
 *
 * @see java.math.BigInteger
 *
 * @author George Nyamie
 */
public class Fraction {
    /*instance variables  */
   
    private final BigInteger numerator;
    private final BigInteger denominator;
    
    /**
     * Constructs a Fraction taking the value of its parameter.
     *
     * @param val  non-null; the value the Fraction is supposed to take
     */
    public Fraction(BigInteger val) {
        // This creates a fraction if a BigInteger is passed
        this(val, BigInteger.ONE);
        
    }

    /**
     * Constructs a new Fraction taking the value of its parameter.
     *
     * @param val  the value the Fraction is supposed to take
     */
    public Fraction(long val) {
        // This constructs a new fraction if a long value is passed
        this(val, 1);
    }

    /**
     * Constructs a Fraction corresponding to numerator / denominator.
     * The value is 0 if denominator is 0.
     *
     * @param numerator  non-null; value of the numerator
     * @param denominator  non-null; value of the denominator
     */
    public Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        
    }

    /**
     * Constructs a Fraction corresponding to numerator / denominator.
     * The value is 0 if denominator is 0.
     *
     * @param numerator  the numerator of the Fraction
     * @param denominator  the denominator of the Fraction
     */
    public Fraction(long numerator, long denominator) {
        // accepts a fraction using the long data primitive
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
        
    }

    /**
     * Returns a Fraction whose value is (this + val).
     * (Note that a/b + c/d = (a*d + b*c)/(b*d).)
     * 
     * @param val  non-null; to be added to this
     * @return this + val
     */
    public Fraction add(Fraction val) {
        // Add this value 
        BigInteger axd = this.numerator.multiply(val.denominator);
        BigInteger bxc = this.denominator.multiply(val.numerator);
        BigInteger bxd = this.denominator.multiply(val.denominator);
        return new Fraction(axd.add(bxc), bxd);
    }

    /**
     * Returns the sum of all elements of vals.
     *
     * @param fractions  array of Fractions to be summed up; may be or contain null
     * @return null if vals is or contains null; the sum of all elements of
     *  vals otherwise
     */
    public static Fraction sumAll(Fraction[] fractions) {
        // Create array to hold all fractions
        Fraction sum = fractions[0];
        // Check if array empty
        if (sum == null) {
            return null;
        }
        // add all fractions together using add function
        for (int i = 1; i < fractions.length; i++) {
            if (fractions[i] == null) {
            return null;
            }
            sum = sum.add(fractions[i]);
        }
        return sum;
    }

    /**
     * Returns a Fraction whose value is (this - val).
     *
     * @param val  non-null; to be subtracted from this Fraction
     * @return this - val
     */
    public Fraction subtract(Fraction val) {
        // Subtract fraction
        BigInteger axd = this.numerator.multiply(val.denominator);
        BigInteger bxc = this.denominator.multiply(val.numerator);
        BigInteger bxd = this.denominator.multiply(val.denominator);
        return new Fraction(axd.subtract(bxc), bxd);
    }

    /**
     * Returns a Fraction whose value is (this * val).
     * (Note that a/b * c/d = (a*c)/(b*d).)
     *
     * @param val  non-null; to be multiplied to this Fraction
     * @return this * val
     */
    public Fraction multiply(Fraction val) {
        // Multiply fraction
        BigInteger axc = this.numerator.multiply(val.numerator);
        BigInteger bxd = this.denominator.multiply(val.denominator);
        return new Fraction(axc, bxd);
    }

    /**
     * Returns a Fraction whose value is (this / val). 
     * (Note that (a/b)  (c/d) = (a*d)/(b*c).)
     * 
     * @param val  non-null; value by which this Fraction is to be divided
     * @return this / val
     */
    public Fraction divide(Fraction val) {
        // Divide two fractions
        BigInteger axd = this.numerator.multiply(val.denominator);
        BigInteger bxc = this.denominator.multiply(val.numerator);
        return new Fraction(axd, bxc);
    }

    /**
     * Returns a Fraction whose value is (-this).
     *
     * @return -this
     */
    public Fraction negate() {
        if (this.signum() < 0) {
            return new Fraction(numerator.abs(), denominator.abs());
        }
        return new Fraction(numerator.negate(), denominator);    
    }

    /**
     * Returns the inverse of this Fraction, i.e., the Fraction 1 / this.
     * Note that 1/(a/b) = (b/a)
     * @return 1 / this
     */
    public Fraction invert() {
        // TODO Auto-generated method stub
        return new Fraction(this.denominator, this.numerator);
        
    }

    /**
     * Returns the sign of this Fraction: 1 if its value is positive,
     * 0 if it is zero, -1 if it is negative.
     *
     * @return the sign of this Fraction (1 if its value is positive,
     *  0 if it is zero, -1 if it is negative) 
     */
    public int signum() {
        // Test if value negative
        if (numerator.signum() < 0 || denominator.signum() < 0) {
            return -1;
        }
        // Test if value equal to ero
        if (numerator.signum() == 0 || denominator.signum() == 0) {
            return 0;
        }
        return 1;
    }
    
    /**
     * Returns the absolute value of this Fraction, i.e.,
     * the value of the Fraction itself if it is non-negative,
     * otherwise the negated value.
     * 
     * @return the absolute value of this Fraction
     */
    public Fraction abs() {
        // Call created method signum() and negate() to reduce duplication of code
        if (numerator.signum() < 0){
            return negate();
        }
        return this;
    }

    /**
     * Returns the maximum of this Fraction and val.
     *
     * @param val  non-null; the value with which the maximum is to be computed
     * @return the maximum of this Fraction and val
     */
    public Fraction max(Fraction val) {
        // Compare objects val and this if val gr
        if (compareTo(val) >= 0) {
            return this;
        }
        return val;
    }

    /**
     * Returns the minimum of this Fraction and val.
     *
     * @param val  non-null; the value with which the minimum is to be computed
     * @return the minimum of this Fraction and val
     */
    public Fraction min(Fraction val) {
        if (compareTo(val) >= 0) {
            return val;
        }
        return this;
    }

    /**
     * Returns this Fraction taken to the power of exponent. Here
     * exponent may also be zero or negative. Note that a^0 = 1 and
     * a^b = (1/a)^(-b) if b < 0. 
     * 
     * @param exponent  the exponent to which we want to take this
     * @return this Fraction taken to the power of exponent
     */
    public Fraction pow(int exponent) {
        if(exponent == 0) { // Note that a^0 = 1
            return new Fraction(BigInteger.ONE);
        } else if (exponent == 1) { // Note that a^1 = a
            return this;
        } else if (exponent < 0) {// a^b = (1/a)^(-b) if b < 0.
            return new Fraction (denominator.pow(-exponent),numerator.pow(-exponent));
        } else {// return all other positive cases
            return new Fraction (numerator.pow(exponent), denominator.pow(exponent));
        }
    }

    /**
     * Compares this Fraction with the specified Fraction.
     * 
     * @param val  non-null; value with which this Fraction is to be compared 
     * @return -1, 0 or 1 as this Fraction is numerically less than,
     *         equal to, or greater than val
     * @see java.math.BigInteger#compareTo(BigInteger)
     */
    public int compareTo(Fraction val) {
        if (denominator.equals(val.denominator)){
            return numerator.compareTo(val.numerator);
        }
        else if (signum() != val.signum()){
            return denominator.compareTo(val.denominator);
        }  
        else { 
            return numerator.multiply(denominator).compareTo(val.denominator.multiply(val.numerator));
        } 
    }

    /**
     * Checks if this Fraction and val represent equal values.
     *
     * @param val  potentially null (in this case the method returns false);
     *  the value with which this Fraction is to be compared for equality
     * @return true if this Fraction and other represent the same value;
     *  false otherwise
     */
    public boolean isEqualTo(Fraction val) { 
        if (val==null || !(val instanceof Fraction)){
            return false;
        }
        return   val.denominator.equals(numerator) && numerator.equals(numerator);
    }

    /**
     * Returns a normalised String representation of this Fraction.
     * For example, new Fraction(5,3) and new Fraction(-10,-6) will
     * be represented as "(5 / 3)". The String representation of
     * new Fraction(5,-10) and new Fraction(-12,24) is "(-1 / 2)".
     *
     * In case this Fraction has an integer value, just the String
     * representation of the integer value is returned. For example,
     * new Fraction(-2) has the String representation "-2"; and
     * new Fraction(0), new Fraction(0,3), and new Fraction(4,0)
     * all have the String representation "0".
     *
     * @return a normalised String representation of this Fraction
     */
    public String toString() {
        // TODO Auto-generated method stub
        BigInteger num = numerator;
        BigInteger den = denominator;
        // fix negative fractions
        if (denominator.signum() < 0) {
            den = denominator.abs();
            num = numerator.negate();
        }
        // normalise
        BigInteger gcd = num.gcd(den);
        if(num.mod(den).equals(BigInteger.ZERO)){
            return "" + num.divide(den);
        }
        return "(" + num.divide(gcd)  + " / " + den.divide(gcd) + ")";
    }
}
