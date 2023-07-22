package com.tsystems.javaschool.tasks.spreadsheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spreadsheet {

    /**
     * Process table cells
     *
     * @param inputData unprocessed table cells
     */
	
	//There is a nested loop that checks the value at 
	//each position in the given table. It replaces the 
	//value in the table with the number at that position. 
	//If there is a formula involving the numbers from the 
	//table positions, the corresponding operation is performed 
	//accordingly
	
    private Map<String, Integer> cellValues = new HashMap<>();
	
    public List<String> process(List<String> input) {
    	List<String> result = new ArrayList<>();

        for (String rowData : input) {
        	
            String[] cellData = rowData.split(" ");
            String[] outputRowData = new String[cellData.length];

            for (int i = 0; i < cellData.length; i++) {
                String cell = cellData[i];
                if (cell.startsWith("=")) {
                    outputRowData[i] = String.valueOf(evaluateFormula(cell.substring(1)));
                } else {
                	cell = cell.replaceAll("'", "");
                    outputRowData[i] = cell;
                    try {
                        int value = Integer.parseInt(cell);
                        cellValues.put(getCellReference(i, result.size()), value);
                    } catch (NumberFormatException ignored) {
                    }
                }
            }

            result.add(String.join(" ", outputRowData));
        }

        return result;

    }
    
    private int evaluateFormula(String formula) {
        String[] formulaParts = formula.split("\\+");
        int result = 0;
        for (String part : formulaParts) {
            part = part.trim();
            if (part.matches("[A-Z]\\d+")) {
                result += cellValues.getOrDefault(part, 0);
            } else {
                try {
                    result += Integer.parseInt(part);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return result;
    }

    private String getCellReference(int column, int row) {
        return String.format("%c%d", 'A' + column, row + 1);
    }
    
    
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}
