package extraCredit1;
import java.io.*;
import java.util.*;

public class GJE {
	public static void main(String[] args) throws Exception{
		double[][] matrix = setMatrix(getFile(args));
	
//		double[][] temp = {{-7, -6, -12, -33},{5,5,7,24},{1,0,4,5}};
//		System.out.println("This is the original matrix");
//		printMatrix(temp);
//		System.out.println("This is in REF:");
//		gaussElim(temp);
//		System.out.println("");
//		printMatrix(temp);
//		gJE(temp);
//		System.out.println("This is in RREF:");
//		printMatrix(temp);
	
		
		System.out.println("This is the original matrix");
		printMatrix(matrix);
		
		
		gaussElim(matrix);
		System.out.println("This is in REF:");
		printMatrix(matrix);
		
		
		gJE(matrix);
		System.out.println("This is in RREF:");
		printMatrix(matrix);
		
		
		
	}
	
	public static void gaussElim(double[][] matrix){
		
		for (int m = 0; m < matrix.length; m++){
			if (m < matrix[0].length){
				for (int n = m; n <= m; n++ ){
//					System.out.println("m is "+m);
//					System.out.println("n is "+n);
//					System.out.println("pivot is "+matrix[m][n]);
					//if pivot is 0
					if (matrix[m][n]==0){
						int tempM = m;
						while(matrix[tempM][n] ==0){
							tempM++;
							if (tempM == matrix.length){
								tempM = -1;
								System.out.println("breaking");
								break;
							}
						}
						if (tempM != -1){
							swap(matrix,m, tempM);
						}
					}
					else{
						for (int tempM = m+1; tempM < matrix.length; tempM++){
							if (matrix[tempM][n]!=0){
								ero3(matrix, tempM, m, -1 * (matrix[tempM][n]/matrix[m][n]));
							}
						}
					}
				}
			}
		}
		
//		//bigM represents current pivot row
//		int bigM = 0;
//		for (int n = 0; n < matrix[0].length; n++){
//			//set pivot to top left
//			double pivot = matrix[bigM][n];
//			
//			for (int m = bigM; m < matrix.length; m++){
//				if (m == bigM){
//					//make sure theres pivot in first row
//					int pivotRow = m;
//					while (pivot==0){
//						pivotRow++;
//						pivot = matrix[pivotRow][n];
//						//zero column
//						if (pivotRow == matrix.length-1 && pivot==0){
//							//break;
//						}
//					}
//					if (pivotRow < matrix.length && matrix[pivotRow][n]!=0){
//						swap(matrix, m, pivotRow);
//					}
//				}
//				
//				else if (matrix[m][n] != 0){
//					ero3(matrix, m, bigM, -1 * (matrix[m][n]/matrix[bigM][n]));
//				}
//			}
////			printMatrix(matrix);
//			bigM++;
//			if(bigM == matrix.length){
//				System.exit(0);
//			}
//		}	
	}
	public static File getFile(String[] args){
		if (args.length < 1){
			System.err.println("File name missing");
			System.exit(0);
		}
		
		File fileName = new File(args[0]);
		
		if (!fileName.canRead()){
			System.err.printf("Cannot read from file %s\n", fileName.getName());
			System.exit(0);
		}
		return fileName;
	}
	
	public static double[][] setMatrix(File file) throws Exception{
		Scanner scn = new Scanner(file);
		double m = scn.nextDouble();
		double n = scn.nextDouble();
		double bigN = scn.nextDouble();
		
		//creates matrix!!
		double[][] matrix = new double[(int)m][(int)n];
		
		for (int i = 0; i < bigN; i++){
			matrix[scn.nextInt()-1][scn.nextInt()-1]=scn.nextDouble();
		}
		scn.close();
		return matrix;
	}
	
	public static void printMatrix(double[][] matrix){
		for (double[] row: matrix){
			for (double x: row){
				System.out.printf("%10.2f",x);
			}
			System.out.println();
		}
	}

	public static void swap(double[][]matrix, int firstRow, int secondRow){
		double[] temp=matrix[firstRow];
		matrix[firstRow] = matrix[secondRow];
		matrix[secondRow]=temp;
		
		System.out.printf("ERO 1: swapping rows %d and %d%n",firstRow+1, secondRow+1);
	}
	public static void multiply(double[][]matrix, int row, double constant){
		for (double x: matrix[row]){
			x = x*constant;
		}
		System.out.printf("ERO 2: multiplying row %d by %f%n", row+1, constant);
	}
	public static void multiply(double[]row, double constant){
		for (int i = 0; i < row.length;i++){
			row[i]*=constant;
		}
	}
	
	public static void ero3(double[][]matrix, int row, int k, double constant){
		//creates new temp array
		double[] temp = new double[matrix[0].length];
		for (int i = 0; i < temp.length;i++){
			temp[i] = matrix[k][i];
		}
		multiply(temp,constant);

		for (int i = 0; i < temp.length; i++){
			matrix[row][i]+=temp[i];
		}
		System.out.printf("ERO 3: adding row %d multiplied by %f to row %d%n", k+1, constant, row+1);
	}
	

	public static void gJE(double[][] matrix){
		gaussElim(matrix);
		int max;
		if (matrix.length > matrix[0].length){
			max = matrix[0].length;
		}
		else{
			max = matrix.length;
		}
		//make all pivot columns 1
		for (int m = 0; m < max; m++){
			
			if (matrix[m][m] != 1 && matrix[m][m] != 0){
				multiply(matrix[m],1/matrix[m][m]);
			}
		}
		
		//gets rid of -0
		for (int m = 0; m < matrix.length; m++){
			for (int n = 0; n < matrix[0].length;n++){
				if (matrix[m][n] == -0.00){
					matrix[m][n] = 0.00;
				}
			}
		}
		
		for (int m = 0; m < matrix.length; m++){
			for (int n = m+1; n < matrix[0].length; n++){
				if (matrix[m][n] != 0){
					ero3(matrix, m, n, -1*matrix[m][n]);
				}
			}
		}
	}

}
