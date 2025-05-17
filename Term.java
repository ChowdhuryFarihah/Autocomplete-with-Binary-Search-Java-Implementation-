import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Term implements Comparable<Term> {

    // a query string associated with the Term object
    private String query;
    // a weight associated with the Term object
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        // A Term cannot be initialized with a null query or a negative weight
        if (query == null || weight < 0) {
            throw new IllegalArgumentException("Invalid Term Initialization");
        }

        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightComparator();
    }

    private static class ReverseWeightComparator implements Comparator<Term> {
        public int compare(Term a, Term b) {
            if (a.weight > b.weight) {
                return -1;
            }
            else if (a.weight < b.weight) {
                return 1;
            }
            return 0;
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException("Invalid Prefix Order");
        }
        return new PrefixOrderComparator(r);
    }

    private static class PrefixOrderComparator implements Comparator<Term> {
        // length of the prefix (i.e. how many letters are we comparing)
        private int prefixLength;

        // constructor for PrefixOrder
        PrefixOrderComparator(int prefixLength) {
            this.prefixLength = prefixLength;
        }

        public int compare(Term a, Term b) {

            for (int i = 0; i < prefixLength; i++) {
                char charOfA = (char) 0;
                char charOfB = (char) 0;

                if (i < a.query.length()) {
                    charOfA = a.query.charAt(i);
                }
                if (i < b.query.length()) {
                    charOfB = b.query.charAt(i);
                }

                if (charOfA > charOfB) {
                    return 1;
                }
                if (charOfA < charOfB) {
                    return -1;
                }
            }
            return 0;
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);

    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        String result = weight + "\t" + query;
        return result;


    }

    // unit testing (required)
    public static void main(String[] args) {

        Term term0 = new Term("megaaardvark", 1);
        Term term1 = new Term("megallama", 2);
        Term term2 = new Term("megaaardvark", 2);

        // unit testing #1: compareTo() method
        StdOut.println("Unit testing #1 - compareTo() method:");
        // prints "Does term0 come before term1? 1"
        StdOut.println("Does term0 come before term1? "
                               + term0.compareTo(term1));
        // prints "Does term1 come before term0? -1"
        StdOut.println("Does term1 come before term0? "
                               + term1.compareTo(term0));
        // prints "Does term0 and term2 have the same name? 0"
        StdOut.println("Does term0 and term2 have the same name? "
                               + term0.compareTo(term2));

        StdOut.println();

        // Unit testing #2: byReverseWeightOrder()
        StdOut.println("Unit testing #2: byReverseWeightOrder()");
        Comparator<Term> comparator1 = Term.byReverseWeightOrder();
        // prints "Does term0 come before term1? -1"
        StdOut.println("Does term0 come before term1? " +
                               comparator1.compare(term0, term1));
        // prints "Does term1 come before term0? 1"
        StdOut.println("Does term1 come before term0? "
                               + comparator1.compare(term1, term0));
        // prints "Do term1 and term 2 weigh the same? 0"
        StdOut.println("Do term1 and term 2 weigh the same? "
                               + comparator1.compare(term2, term1));

        StdOut.println();

        // Unit testing #3: byPrefixOrder(int 5)
        StdOut.println("Unit testing #3: byPrefixOrder(int 5)");
        Comparator<Term> comparator2 = Term.byPrefixOrder(5);
        // prints "Does term0 come before term1? 1"
        StdOut.println("Does term0 come before term1? "
                               + comparator2.compare(term0, term1));
        // prints "Does term1 come before term0? -1"
        StdOut.println("Does term1 come before term0? "
                               + comparator2.compare(term1, term0));
        // prints "Does term0 and term2 have the same name? 0"
        StdOut.println("Does term0 and term2 have the same name? "
                               + comparator2.compare(term0, term2));

        StdOut.println();

        // Unit testing #4: byPrefixOrder(int 3)
        StdOut.println("Unit testing #3: byPrefixOrder(int 3)");
        Comparator<Term> comparator3 = Term.byPrefixOrder(3);
        // prints "Does term0 come before term1? 0"
        StdOut.println("Does term0 come before term1? "
                               + comparator3.compare(term0, term1));
        // prints "Does term1 come before term0? 0"
        StdOut.println("Does term1 come before term0? "
                               + comparator3.compare(term1, term0));
        // prints "Does term0 and term2 have the same name? 0"
        StdOut.println("Does term0 and term2 have the same name? "
                               + comparator3.compare(term0, term2));


    }

}
