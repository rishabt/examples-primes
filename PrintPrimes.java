public class PrintPrimes {
	  int numberOfPrimes;
	  int numberOfRows;
	  int numberOfColumns;
	  int WW;
	  int ORDMAX;
	  int listOfPrimes[];

	  public PrintPrimes(int numberOfPrimes, int numberOfRows, int numberOfColumns, int WW, int ORDMAX) {	// ---> Constructor
	    this.numberOfPrimes = numberOfPrimes;
	    this.numberOfRows  = numberOfRows;
	    this.numberOfColumns  = numberOfColumns;
	    this.WW  = WW;
	    this.ORDMAX = ORDMAX;
	    this.listOfPrimes = new int[numberOfPrimes + 1];
	  }


	  public static void main(String[] args) {
	      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);		//Calls the constructor
	      
	      printPrimes.calculatePrimes();										//Primes calculated and stored in the array 'listOfPrimes'
	      printPrimes.printPrimes();											//The list of primes printed on the screen in a pre-assigned format
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
	  
	  /* Calculates all the odd prime numbers, since 2 is the only even prime number, rest are all odd. The counter starts with 3.
	   * In the process when we find an odd prime number, all it's odd multiples are stored in an array (primeMult). As the process 
	   * continues, the composite numbers in the array are compared with the odd numbers as the counter increases, if they are equal
	   * the obtained number is not prime, and we move on to a new number, and the process restarts.
	   */

	  private void calculateOddPrimes() {
	      boolean JPRIME;												//Some variables initialized for further use
	      int index;
	      int primeMult[] = new int[ORDMAX + 1];						//Odd multiples of primes stored in this array

	      int startPoint = 1;											//Startpoint set to 1	
	      int ORD = 2;
	      int primeSquare = 9;											
	      
	      int increment = 2;

	      for(int primesFoundSoFar = 1; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
	        do {
	        	startPoint = startPoint + increment;					//As the process flows, the startpoint incremented by 2
	        	
	        	if (startPoint == primeSquare) {
		            ORD = ORD + 1;
		            primeSquare = computeSquare(listOfPrimes[ORD]);
		            primeMult[ORD - 1] = startPoint;
	        	}
	          
	        	index = 2;
	        	JPRIME = true;
	          
	        	while (index < ORD && JPRIME) {
	        		
	        		while (primeMult[index] < startPoint){
	        			primeMult[index] = primeMult[index] + listOfPrimes[index] + listOfPrimes[index];
	        		}
	        		
	        		if (primeMult[index] == startPoint){
	        			JPRIME = false;
	        		}
	        		
	        		index = index + 1;
	        	}
	        	
	        } while (!JPRIME);
	        
	        //After the do-while loop we obtain the prime number, and we add it to the listOfPrimes array
	        listOfPrimes[primesFoundSoFar] = startPoint;
	      }
	    }

	  
	    /* The following method prints the primes calculated in the previous method on the screen in a pre-assigned format, which can be changed
	     * by changing a few values when the constructor is initialized. 
	     */
	    public void printPrimes() {
	        int PAGENUMBER = 1;											//Page number as displayed
	        int PAGEOFFSET = 1;											//Page offset	
	        
	        while (PAGEOFFSET <= numberOfPrimes) {
	        	
	        	System.out.println("The First " + numberOfPrimes +
	                               " Prime Numbers --- Page " + PAGENUMBER);
	        	System.out.println("");
	        	
	        	setFormat(PAGEOFFSET);									//Helper method called to set and print the format 
	        	
	        	System.out.println("\f");
	        	PAGENUMBER = PAGENUMBER + 1;
	        	PAGEOFFSET = PAGEOFFSET + numberOfRows * numberOfColumns;
	        }
	    }
	    
	    public int computeSquare(int number){							//Helper method to compute teh square of a number
	    	int result = number * number;
	    	
	    	return result;
	    }
	    
	    /* The following method takes as input the pageoffset and sets the format of the numbers to be printed and prints them out 
	     * on the screen
	     */
	    
	    public void setFormat(int PAGEOFFSET){										
	    	for (int ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + numberOfRows; ROWOFFSET++){
        		
	            for (int C = 0; C < numberOfColumns;C++){
	            	if (ROWOFFSET + C * numberOfRows <= numberOfPrimes){
	            		System.out.format("%10d", listOfPrimes[ROWOFFSET + C * numberOfRows]);
	            	}
	            }
	            
	            System.out.println("");
        	}
	    }
	    
	}

