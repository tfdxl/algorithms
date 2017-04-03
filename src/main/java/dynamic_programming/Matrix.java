package dynamic_programming;

import lombok.Data;

/**
 * Created by tianfeng on 2017/4/4.
 */
@Data
public class Matrix {

    //列
    private int columns;

    //行
    private int rows;

    //data
    private int[][] data;

    public void setCell(int x, int y, int value) {

        if (x < 0 || x > this.rows - 1 || y < 0 || y > this.columns - 1) {
            throw new ArrayIndexOutOfBoundsException("超越边界");
        }
        this.data[x][y] = value;
    }


    public int getCell(int x, int y) {

        if (x < 0 || x > this.rows - 1 || y < 0 || y > this.columns - 1) {
            throw new ArrayIndexOutOfBoundsException("超越边界");
        }
        return this.data[x][y];
    }

}
