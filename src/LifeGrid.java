public class LifeGrid
{
    private boolean[][] grid;

    LifeGrid(int w, int h) {
        grid = new boolean[h+2][w+2];
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for(int r = 1; r < grid.length-1; r++){
            for(int c = 1; c < grid.length - 1; c++){
                out.append("[").append(grid[r][c] ? "X" : " ").append("]");
            }
            out.append("\n");
        }
        return out.toString();
    }

    int rows(){
        return grid.length - 2;
    }

    int cols(){
        return grid[0].length - 2;
    }

    void change(int row, int col) {
        grid[row+1][col+1] = !grid[row+1][col+1];
    }

    void next() {
        boolean[][] tempGrid = new boolean[rows()+2][cols()+2];

        for(int row = 1; row <= rows(); row++){
            for(int col = 1; col <= cols(); col++){
                // 2 cases
                // count neighbors
                int neighbors = numNeighbors(row,col);

                if(grid[row][col]){
                    // 1 - alive
                    tempGrid[row][col] = (neighbors == 2 || neighbors == 3);
                } else {
                    // 2 - dead
                    tempGrid[row][col] = (neighbors == 3);
                }
            }
        }
        grid = tempGrid;
    }

    private int numNeighbors(int row, int col){
        int count = 0;
        count += grid[row - 1][col - 1]?1:0;
        count += grid[row - 1][col]?1:0;
        count += grid[row - 1][col + 1]?1:0;
        count += grid[row][col - 1]?1:0;
        count += grid[row][col + 1]?1:0;
        count += grid[row + 1][col - 1]?1:0;
        count += grid[row + 1][col]?1:0;
        count += grid[row + 1][col + 1]?1:0;
        return count;
    }

    boolean get(int row, int col){
        return grid[row+1][col+1];
    }
}
