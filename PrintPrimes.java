public class PrintPrimes {
	  int numberOfPrimes;
	  int numberOfRows;
	  int numberOfColumns;
	  int WW;
	  int ORDMAX;
	  int listOfPrimes[];

	  public PrintPrimes(int numberOfPrimes, int numberOfRows, int numberOfColumns, int WW, int ORDMAX) {
	    this.numberOfPrimes = numberOfPrimes;
	    this.numberOfRows  = numberOfRows;
	    this.numberOfColumns  = numberOfColumns;
	    this.WW  = WW;
	    this.ORDMAX = ORDMAX;
	    this.listOfPrimes = new int[numberOfPrimes + 1];
	  }


	  public static void main(String[] args) {
	      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
	      printPrimes.calculatePrimes();
	      printPrimes.printPrimes();
	  }

	  public void calculatePrimes() {
	      /* Two is the only even prime. All other prime numbers are odd.
	       * To simplify the code, we simply add 2 as a prime number, and
	       * delegate the task of finding all odd prime numbers to a helper
	       * function.
	       */
	      listOfPrimes[1] = 2;
	      calculateOddPrimes();
	  }

	  private void calculateOddPrimes() {
	      boolean JPRIME;
	      int N;
	      int MULT[] = new int[ORDMAX + 1];

	      int startPoint = 1;							
	      int ORD = 2;
	      int SQUARE = 9;

	      for(int primesFoundSoFar = 1; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
	        do {
	          startPoint = startPoint + 2;
	          if (startPoint == SQUARE) {
	            ORD = ORD + 1;
	            SQUARE = listOfPrimes[ORD] * listOfPrimes[ORD];
	            MULT[ORD - 1] = startPoint;
	          }
	          
	          N = 2;
	          JPRIME = true;
	          while (N < ORD && JPRIME) {
	            while (MULT[N] < startPoint)
	              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
	            if (MULT[N] == startPoint)
	              JPRIME = false;
	            N = N + 1;
	          }
	        } while (!JPRIME);
	        
	        listOfPrimes[primesFoundSoFar] = startPoint;
	      }
	    }

	    public void printPrimes() {
	        int PAGENUMBER = 1;
	        int PAGEOFFSET = 1;
	        while (PAGEOFFSET <= numberOfPrimes) {
	          System.out.println("The First " + numberOfPrimes +
	                               " Prime Numbers --- Page " + PAGENUMBER);
	          System.out.println("");
	          for (int ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + numberOfRows; ROWOFFSET++){
	            for (int C = 0; C < numberOfColumns;C++)
	              if (ROWOFFSET + C * numberOfRows <= numberOfPrimes)
	                System.out.format("%10d", listOfPrimes[ROWOFFSET + C * numberOfRows]);
	            System.out.println("");
	          }
	          System.out.println("\f");
	          PAGENUMBER = PAGENUMBER + 1;
	          PAGEOFFSET = PAGEOFFSET + numberOfRows * numberOfColumns;
	        }
	    }
	}