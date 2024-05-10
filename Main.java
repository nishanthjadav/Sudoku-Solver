public class Main
{
    static final int N = 9;
    static void printGrid(int[][] grid)
    {
        for (int i = 0; i < N; i++)
        {
            if (i % 3 == 0)
            {
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ ");
            }
            for (int j = 0; j < N; j++)
            {
                if (j % 3 == 0 && j != 0)
                {
                    System.out.print(" | " + grid[i][j] + " ");
                }
                else
                {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
            if (i == N - 1)
            {
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ ");
            }
        }
    }
    static boolean isNumInRow(int[][] grid, int num, int row) // helper
    {
        for (int i = 0; i < N; i++)
        {
            if (grid[row][i] == num)
            {
                return true;
            }
        }
        return false;
    }
    static boolean isNumInCol(int[][] grid, int num, int col) // helper
    {
        for (int i = 0; i < N; i++)
        {
            if (grid[i][col] == num)
            {
                return true;
            }
        }
        return false;
    }
    static boolean isNumIn3x3(int[][] grid, int num, int row, int col) // helper
    {
        int localBoxRow = row - row % 3; int localBoxCol = col - col % 3; // to get top left index of current 3x3

        for (int i = localBoxRow; i < localBoxRow + 3; i++) // iterate through 3 rows in local box
        {
            for (int j = localBoxCol; j < localBoxCol + 3; j++) // iterate through 3 columns in local box
            {
                    if (grid[i][j] == num)
                    {
                        return true;
                    }
            }
        }
        return false;
    }
    static boolean isValidPlacement(int[][] grid, int num, int row, int col) // main checking function
    {
        return !isNumInRow(grid, num, row) &&
                !isNumInCol(grid, num, col) &&
                !isNumIn3x3(grid, num, row, col);
    }

    static boolean solve(int[][] grid)
    {
        for (int row = 0; row < N; row++)
        {
            for (int col = 0; col < N; col++)
            {
                if (grid[row][col] == 0)
                {
                    for (int numToTry = 1; numToTry <= N; numToTry++) // trying every num 1-9 to see if valid
                    {
                        if(isValidPlacement(grid, numToTry, row, col))
                        {
                            grid[row][col] = numToTry;

                            if(solve(grid))
                            {
                                return true;
                            }
                            else {
                                grid[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args)
    {
        int[][] grid = {
                {0, 0, 9, 0, 4, 0, 0, 0, 6},
                {6, 3, 0, 0, 2, 0, 7, 0, 0},
                {0, 0, 8, 0, 0, 3, 0, 0, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 0},
                {4, 1, 0, 0, 0, 2, 0, 0, 8},
                {0, 0, 0, 0, 7, 0, 0, 5, 0},
                {0, 0, 0, 4, 0, 0, 8, 0, 0},
                {2, 6, 0, 0, 0, 1, 0, 0, 4},
                {9, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        if (solve(grid))
        {
            System.out.println("solved successfully");
            printGrid(grid);
        }
        else{
            System.out.println("impossible board");
        }
    }




}
