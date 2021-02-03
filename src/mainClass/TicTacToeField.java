package mainClass;

//import java.util.Arrays;

public class TicTacToeField {
    private final int fieldSize;
    private char[][] cells;

    public TicTacToeField(int fieldSize){
        this.fieldSize = fieldSize;
        this.cells = new char[this.fieldSize][this.fieldSize];
        for (int i = 0; i < this.fieldSize; i++){
            for (int j = 0; j < this.fieldSize; j++){
                this.cells[i][j] = 'n';
            }
        }
    }

    public char[][] getCells() {
        return cells;
    }

    public void setCell(char symbol, int x, int y){
        if (symbol == 'x' || symbol == 'o'){
            switch (this.cells[y][x]) {
                //case 'o', 'x' -> System.out.println("Cell is already occupied");
                case 'n' -> {
                    this.cells[y][x] = symbol;
                    //    System.out.println("Cell " + x + " " + y + " set to " + symbol);
                }
                //default -> System.out.println("Unknown symbol");
            }
        }
    }

    public void removeCell(int x, int y){
        if (this.cells[y][x] == 'x' || this.cells[y][x] == 'o') {
            this.cells[y][x] = 'n';
            //System.out.println("Cell " + x + " " + y + " has just been cleaned");
        }
        //else System.out.println("Cell " + x + " " + y + " is already empty");
    }

    public int findLongest(char symbol){
        if (symbol != 'x' && symbol != 'o') {
        //    System.out.println("Unknown symbol");
            return -1;
        } else{
            int maxCountSymbol = 0;
            int[][] cellsCount = new int[this.fieldSize][this.fieldSize];

            if (this.cells[0][0] == symbol) cellsCount[0][0] = 1;
            for (int i = 1; i < this.fieldSize; i++) if (this.cells[i][0] == symbol) cellsCount[i][0] = cellsCount[i-1][0] + 1;
            for (int i = 1; i < this.fieldSize; i++) if (this.cells[0][i] == symbol) cellsCount[0][i] = cellsCount[0][i-1] + 1;

            /*Подряд может идти либо по вертикали, либо по горизонтали, либо по вертикали
            * Проверяем для каждой клетки, с какой из этих возможных сторон идет
            * наибольшая цепочка*/

            for (int i = 1; i < this.fieldSize; i++){
                for (int j = 1; j < this.fieldSize; j++){
                    if (this.cells[i][j] == symbol) {
                        int up;
                        if (cellsCount[i - 1][j] == 1) up = 2;
                        else if (i > 1 && cellsCount[i - 1][j] == cellsCount[i - 2][j] + 1)
                            up = cellsCount[i - 1][j] + 1;
                        else up = 1;

                        int left;
                        if (cellsCount[i][j - 1] == 1) left = 2;
                        else if (j > 1 && cellsCount[i][j - 1] == cellsCount[i][j - 2] + 1)
                            left = cellsCount[i][j - 1] + 1;
                        else left = 1;

                        int dia;
                        if (cellsCount[i - 1][j - 1] == 1) dia = 2;
                        else if (i > 1 && j > 1 && cellsCount[i - 1][j - 1] == cellsCount[i - 2][j - 2] + 1)
                            dia = cellsCount[i - 1][j - 1] + 1;
                        else dia = 1;

                        cellsCount[i][j] = Math.max(Math.max(up, left), dia);
                        maxCountSymbol = Math.max(maxCountSymbol, cellsCount[i][j]);
                    }
                }
            //    System.out.println(Arrays.toString(cellsCount[i]));
            }
            return maxCountSymbol;
        }
    }
}
