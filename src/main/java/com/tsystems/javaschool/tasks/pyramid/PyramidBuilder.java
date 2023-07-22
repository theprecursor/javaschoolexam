package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
    	 for(Integer numbers : inputNumbers) {
    		 if(numbers == null) {
                 throw new CannotBuildPyramidException("Invalid input size. Cannot build a pyramid.");
    		 }
    	 }
    	 int totalElements = inputNumbers.size();
         int pyramidHeight = calculatePyramidHeight(totalElements);

         if (totalElements != pyramidHeight * (pyramidHeight + 1) / 2) {
             throw new CannotBuildPyramidException("Invalid input size. Cannot build a pyramid.");
         }

         int[][] pyramid = new int[pyramidHeight][2 * pyramidHeight - 1];
         Collections.sort(inputNumbers);
         int currentIndex = 0;
         for (int row = 0; row < pyramidHeight; row++) {
             int elementsInRow = row + 1;
             int startColumn = pyramidHeight - row - 1;

             for (int i = 0; i < elementsInRow; i++) {
                 pyramid[row][startColumn + i * 2] = inputNumbers.get(currentIndex);
                 currentIndex++;
             }
         }

         return pyramid;
    }
    
    private int calculatePyramidHeight(int totalElements) {
        int n = 0;
        while (totalElements > 0) {
            n++;
            totalElements -= n;
        }
        return n;
    }
   
}
