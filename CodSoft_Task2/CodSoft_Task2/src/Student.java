import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {

    private int TotalSubjects = 0;
    private final List<Integer> SubjectMarks;
    public Student()
    {
        SubjectMarks = new ArrayList<Integer>();
    }

    public void AssignMarks()
    {
        String answer = "N";
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        do{
            //something
            counter++;
            System.out.printf("Enter a mark for subject %d: ",counter);
            int tempMark = scanner.nextInt();


            if(tempMark >= 0 && tempMark <= 100)
                SubjectMarks.add(tempMark);
            else
            {
                while(tempMark < 0 || tempMark > 100){
                    System.out.print("\nInvalid mark, enter the mark again: ");
                    tempMark = scanner.nextInt();
                }
            }

            System.out.print("\nAdd another? (Y/N) ");
            answer = scanner.next();

        }
        while(answer.equalsIgnoreCase("Y"));
    }

    public void DisplayResults()
    {
        double averagePerc = 0;
        int totalSubjects = SubjectMarks.size();
        int sumMarks = 0, counter = 0;

        System.out.println("\nMark Overview");
        System.out.println("=============\n");
        for(int i : SubjectMarks)
        {
            counter++;
            sumMarks += i;
            System.out.printf("Subject %d: %.0f\n", counter, (double)i);
        }

        averagePerc = (double)sumMarks/totalSubjects;
        System.out.printf("\nAverage percentage: %.2f%%\n", averagePerc);
        System.out.printf("Student Grade: %s\n", GetGrading((int)Math.round(averagePerc)));
    }

    public String GetGrading(int grading){
        String output = "F";
        if(isBetween(grading,81,100))
            output = "A";
            else
        if (isBetween(grading, 74, 80))
            output = "A-";
        else
        if (isBetween(grading, 68, 73))
            output = "B+";
        else
        if (isBetween(grading, 63, 67))
            output = "B";
        else
        if (isBetween(grading, 53, 62))
            output = "B-";
        else
        if (isBetween(grading, 55, 59))
            output = "C+";
        else
        if (isBetween(grading, 50, 54))
            output = "C";
        else
        if (isBetween(grading, 45, 49))
            output = "C-";
        else
        if (isBetween(grading, 40, 44))
            output = "D+";
        else
        if (isBetween(grading, 35, 39))
            output = "D";
        else
        if (isBetween(grading, 30, 34))
            output = "E";
        else
        if (isBetween(grading, 0, 29))
            output = "E";
            return output;
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    @Override
    public String toString(){
        int count = 0;
        StringBuilder output = new StringBuilder();
        for (int temp : SubjectMarks) {
            count++;
            output.append(String.format("Subject %d: %.2f\n", count, (double) temp));
        }
        return output.toString();
    }
}
