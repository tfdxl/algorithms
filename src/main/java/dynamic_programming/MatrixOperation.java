package dynamic_programming;

/**
 * Created by tianfeng on 2017/4/4.
 */
public class MatrixOperation {

    public static Matrix matrixMultiply(Matrix a,Matrix b) {

        if (a.getColumns() != b.getRows()) {
            throw new RuntimeException("矩阵不满足相乘条件");
        } else {

            Matrix matrix = new Matrix();
            matrix.setRows(a.getRows());
            matrix.setColumns(b.getColumns());

            int[][] data = new int[a.getRows()][b.getColumns()];

            for(int i = 0;i<a.getRows();i++){
                for(int j = 0;j<b.getRows();j++){
                    int cell = 0;
                    for(int k = 0;k<a.getColumns();k++){
                        cell += cell+a.getCell(i,k)*b.getCell(k,j);
                    }
                    data[i][j] = cell;
                }
            }
            matrix.setData(data);
            return matrix;
        }
    }

}
