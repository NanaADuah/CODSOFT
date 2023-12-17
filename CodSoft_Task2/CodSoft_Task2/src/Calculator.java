public class Calculator {

    public static void main(String [] args)
    {
        System.out.println("Student Mark Calculator");
        System.out.println("=======================");
        Student student = new Student();
        student.AssignMarks();

        student.DisplayResults();
    }
}
