package mainClass;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TicTacToeFieldTest {
    int fieldSize = 9;
    TicTacToeField field = new TicTacToeField(fieldSize);
    char[][] expected;

    @Before
    public void setUp() throws Exception{
        expected = new char[9][9];
        for (int i = 0; i < fieldSize; i++) for (int j = 0; j < fieldSize; j++) expected[i][j] = 'n';
    }

    @Test
    public void setCell() {
        field.setCell('x', 0, 0);
        expected[0][0] = 'x';
        assertEquals(field.getCells(), expected);

        field.setCell('o', 0, 0);
        assertEquals(field.getCells(), expected);

        field.setCell('p', 2, 2);
        assertEquals(field.getCells(), expected);
    }

    @Test
    public void removeCell() {
        field.removeCell(0, 0);
        assertEquals(field.getCells(), expected);

        field.setCell('x', 0, 0);
        field.removeCell(0, 0);
        assertEquals(field.getCells(), expected);

        field.setCell('x', 0, 0);
        field.setCell('o', 1, 1);
        field.removeCell(1, 1);
        expected[0][0] = 'x';
        assertEquals(field.getCells(), expected);
    }

    @Test
    public void findLongest() {
        for (int i = 0; i < fieldSize - 1; i++) {
            field.setCell('x', i, i);
        }
        for (int i = 0; i < 5; i++){
            field.setCell('x', 0, i);
        }
        assertEquals(field.findLongest('x'), fieldSize - 1);
    }
}