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
	
	//There is a first loop that takes care of inspecting
	//the array, performing a split to separate its elements. 
	//After splitting, it handles different scenarios, applying
	//conditionals where, if it starts with an equal sign, it 
	//extracts the number from the specified position. If there 
	//is no equal sign, it checks the positions one by one. 
	//If no equal sign is found, it leaves the number unchanged. 
	//Then, depending on the specified positions after the equal sign, 
	//the corresponding scenario is executed.
	
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
