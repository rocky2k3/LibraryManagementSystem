import java.util.Scanner;
class Book{
    String Title;
    String AuthorName;
    String Category;
    int Quantity;
    public Book(String Title,String AuthorName,String Category, int Quantity){
        this.Title=Title;
        this.AuthorName=AuthorName;
        this.Category=Category;
        this.Quantity=Quantity;
    }
}
class Student{
    String Name;
    int ID;
    String MobileNo;
    Book[] borrowed=new Book[10];
    int numofborrowed=0;
    public Student(int ID, String Name, String MobileNo){
        this.ID=ID;
        this.Name=Name;
        this.MobileNo=MobileNo;
    }
}
public class Main {
    static int MAX_Books=100;
    static int MAX_Students=100;
    static int numofstudents=0;
    static int numofBooks=0;
    static Student[] studentDatabase=new Student[MAX_Students];
    static Book[] bookinventory=new Book[MAX_Books];
    public static int login() {
        Scanner sc = new Scanner(System.in);
        String username, password;
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter username: ");
            username = sc.nextLine();
            if (username.equals("LibrarianMaster")) {
                for (int j = 0; j < 3; j++) {
                    System.out.print("Enter password: ");
                    password = sc.nextLine();

                    if (password.equals("LibMaster#2023!")) {
                        System.out.println("Authentication successful. ");
                        return 1;
                    } else if (j == 2) {
                        System.out.println("Maximum attempts reached. Exiting the code.");
                        return 0;
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                }
            } else if (i == 2) {
                System.out.println("Maximum attempts reached. Exiting the code.");
                return 0;
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }
        return 0; 
    }
    public static void AddBook(){
        Scanner sc=new Scanner(System.in);
        if(numofBooks<MAX_Books){
            System.out.print("Enter the title of Book: ");
            String title=sc.nextLine();
            int existing=FindBookIndex(title);
            if(existing!=-1){
                FindBook(title, "Title");
                System.out.print("Enter additional Quantity: ");
                int quantity=sc.nextInt();
                bookinventory[existing].Quantity+=quantity;
            }
            else{
                System.out.print("Enter the author name of Book: ");
                String authorname=sc.nextLine();
                System.out.print("Enter the category of Book: ");
                String category=sc.nextLine();
                System.out.print("Enter the quantity of Book: ");
                int quantity=sc.nextInt();
                sc.nextLine();
                bookinventory[numofBooks++]=new Book(title,authorname,category,quantity);
                System.out.println("Book Added Successfully.");
            }
            
        }
        else{
            System.out.println("Cannot add more books. Maximum Limit Reached.");
        }
    }
    public static int FindBookIndex(String title){
        for(int i=0;i<numofBooks;i++){
            if(bookinventory[i].Title.toLowerCase().contains(title.toLowerCase())){
                return i;
            }
        }
        return -1;
    }
    public static void ViewBook(){
        if (numofBooks == 0) {
            System.out.println("The book inventory is empty.");
        } else {
            System.out.printf("%-5s %-20s %-20s %-20s %-10s%n", "Sl.","Title", "Author", "Category", "Quantity");
            System.out.println("----------------------------------------------------------------------------------");
            for (int i = 0; i < numofBooks; i++) {
                System.out.printf("%-5s %-20s %-20s %-20s %-10d%n",i+1+".",bookinventory[i].Title,bookinventory[i].AuthorName,bookinventory[i].Category,bookinventory[i].Quantity);
            }
        }
        System.out.println();
    }
    public static void SearchBook(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Search for a Book\n1. Search By Title\n2. Search by Author Name\n3. Search by Category");
        System.out.print("Enter your choice: ");
        int ch=sc.nextInt();
        sc.nextLine();
        switch(ch){
            case 1:
                System.out.print("Enter title of Book: ");
                String title=sc.nextLine();
                FindBook(title, "Title");
                break;
            case 2:
                System.out.print("Enter the name of author: ");
                String authorname=sc.nextLine();
                FindBook(authorname, "Author Name");
                break;
            case 3:
                System.out.print("Enter the category: ");
                String category=sc.nextLine();
                FindBook(category, "Category");
                break;
            default:
                System.out.println("Invalid Choice!");
        }
    }
    public static void FindBook(String query,String querytype){
        int found=0;
        for(int i=0;i<numofBooks;i++){
            int flag=0;
            switch(querytype){
                case "Title":
                    if(bookinventory[i].Title.toLowerCase().contains(query.toLowerCase())){
                        flag=1;
                    }
                    break;
                case "Author Name":
                     if(bookinventory[i].AuthorName.toLowerCase().contains(query.toLowerCase())){
                        flag=1;
                    }
                    break;
                case "Category":
                     if(bookinventory[i].Category.toLowerCase().contains(query.toLowerCase())){
                        flag=1;
                    }
                    break;
            }
            if(flag==1){
                if (found == 0) {
                    System.out.printf("%-5s %-20s %-20s %-20s %-10s%n", "Sl.", "Title", "Author", "Category", "Quantity");
                    System.out.println("----------------------------------------------------------------------------------");
                }
                System.out.printf("%-5s %-20s %-20s %-20s %-10d%n",i+1+".",bookinventory[i].Title,bookinventory[i].AuthorName,bookinventory[i].Category,bookinventory[i].Quantity);
                found=1;
            }
        }
        if(found==0){
            System.out.println("No Books Found with "+querytype+" "+query);
        }
    }
    public static void DeleteBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the title of the book you want to delete: ");
        String title = sc.nextLine();
        int bookIndex = FindBookIndex(title);
        if (bookIndex != -1) {
            Book delete = bookinventory[bookIndex];
            for (int i = bookIndex; i < numofBooks - 1; i++) {
                bookinventory[i] = bookinventory[i + 1];
            }
            bookinventory[numofBooks - 1] = null;
            numofBooks--;
            System.out.println("Book Deleted Successfully:");
            System.out.printf("Title: %-20s Author: %-20s Category: %-20s%n",
                    delete.Title, delete.AuthorName, delete.Category);
        } else {
            System.out.println("Book not found.");
        }
    }
    public static void AddStudent(){
        Scanner sc=new Scanner(System.in);
        if(numofstudents<MAX_Students){
            System.out.print("Enter your Student ID: ");
            int ID=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter your name: ");
            String Name=sc.nextLine();
            System.out.print("Enter your Mobile Number: ");
            String MobileNo=sc.nextLine();
            studentDatabase[numofstudents++]=new Student(ID, Name, MobileNo);
            System.out.println("Student added successfully:");
            System.out.printf("%-15s %-20s %-15s%n", "Student ID", "Name", "Mobile Number");
            System.out.println("------------------------------------------------------");
            System.out.printf("%-15s %-20s %-15s%n", ID, Name, MobileNo);
        }
        else{
            System.out.println("Cannot create more student accounts. Maximum limit reached.");
        }
    }
    public static void ViewStudent(){
        if (numofstudents == 0) {
            System.out.println("No students in the database.");
        } else {
            System.out.printf("%-15s %-20s %-15s%n", "Student ID", "Name", "Mobile Number");
            System.out.println("------------------------------------------------------");
            for (int i = 0; i < numofstudents; i++) {
                System.out.printf("%-15s %-20s %-15s%n",
                        studentDatabase[i].ID,
                        studentDatabase[i].Name,
                        studentDatabase[i].MobileNo);
            }
        }
        System.out.println();
    }
    public static void BorrowedBooks(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Student's ID: ");
        int ID=sc.nextInt();
        int existing=FindStudentIndex(ID);
        if(existing!=-1){
            Student student=studentDatabase[existing];
            if(student.numofborrowed>0){
                for(int i=0;i<student.numofborrowed;i++){
                    Book borrowed = student.borrowed[i];
                    System.out.printf("Title: %-20s Author: %-20s Category: %-20s%n",borrowed.Title, borrowed.AuthorName, borrowed.Category);
                }
            }
            else{
                System.out.println("The student has not borrowed any book.");
            }
        }
        else{
            System.out.println("Student not Found.");
        }
    }
    public static int FindStudentIndex(int ID){
        for(int i=0;i<numofstudents;i++){
            if(studentDatabase[i].ID==ID){
                return i;
            }
        }
        return -1;
    }
    public static void CheckOut(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Student's Id: ");
        int ID=sc.nextInt();
        sc.nextLine();
        int existing=FindStudentIndex(ID);
        if(existing!=-1){
            Student student = studentDatabase[existing];
            System.out.print("Enter book title: ");
            String title=sc.nextLine();
            int bookindex=FindBookIndex(title);
            if(bookindex!=-1){
                Book book=bookinventory[bookindex];
                if(book.Quantity>0){
                    book.Quantity--;
                    student.borrowed[student.numofborrowed++]=book;
                    System.out.println("Book Lent Successfully.");
                    System.out.printf("Title: %-20s Author: %-20s Category: %-20s%n",book.Title, book.AuthorName, book.Category);
                }
                else{
                    System.out.println("The book is out of Stock.");
                }
            }
            else{
                System.out.println("Book not Found.");
            }
        }
        else{
            System.out.println("Student not found.");
        }
    }
    public static void CheckIn(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Student's ID: ");
        int ID=sc.nextInt();
        sc.nextLine();
        int existing=FindStudentIndex(ID);
        if(existing!=-1){
            Student student=studentDatabase[existing];
            if(student.numofborrowed>0){
                System.out.println("Borrowed Books: ");
                for(int i=0;i<student.numofborrowed;i++){
                    Book borrowed=student.borrowed[i];
                    System.out.printf("%d. Title: %-20s Author: %-20s Category: %-20s%n",i + 1, borrowed.Title, borrowed.AuthorName, borrowed.Category);
                }
                System.out.print("Enter Book Title: ");
                String title=sc.nextLine();
                int bookindex=findBookInStudent(student, title);
                if(bookindex!=-1){
                    Book book=student.borrowed[bookindex];
                    book.Quantity++;
                    student.borrowed[bookindex]=null;
                    student.numofborrowed--;
                    System.out.println("Book Checked In Successfully.");
                    System.out.printf("Title: %-20s Author: %-20s Category: %-20s%n",book.Title, book.AuthorName, book.Category);
                }
                else{
                    System.out.println("Book not found in the borrowed books.");
                }

            }
            else{
                System.out.println("The student has not borrowed any book.");
            }
        }
        else{
            System.out.println("Student not found.");
        }
    }
    public static int findBookInStudent(Student student, String bookTitle) {
        for (int i = 0; i < student.numofborrowed; i++) {
            if (student.borrowed[i].Title.equals(bookTitle)) {
                return i;
            }
        }
        return -1; // Book not found
    }
    public static void DeleteStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID of the student you want to delete: ");
        int studentID = sc.nextInt();
        int studentIndex = FindStudentIndex(studentID);
        if (studentIndex != -1) {
            Student deleteStudent = studentDatabase[studentIndex];
            for (int i = studentIndex; i < numofstudents - 1; i++) {
                studentDatabase[i] = studentDatabase[i + 1];
            }
            studentDatabase[numofstudents - 1] = null;
            numofstudents--;
            System.out.println("Student Deleted Successfully:");
            System.out.printf("ID: %-15s Name: %-20s Mobile Number: %-15s%n",
                    deleteStudent.ID, deleteStudent.Name, deleteStudent.MobileNo);
        } else {
            System.out.println("Student not found.");
        }
    }
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to the Library Management System");
        int result = login();
        if (result == 0) {
            System.out.println("Authentication failed. Exiting...");
            return;
        }
        int choice;
        do{
            System.out.println("*****************************************");
            System.out.println("\t0. Exit\n\t1. Add a Book\n\t2. Delete a Book\n\t3. View Book Inventory\n\t4. Check In a Book\n\t5. Check Out a Book\n\t6. Search for a Book\n\t7. Add a Student\n\t8. Delete a Student\n\t9. View Student DataBase\n\t10. Student's Borrowed Book");
            System.out.println("*****************************************");
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 0:
                    System.out.println("Exiting...");
                    break;
                case 1:
                    AddBook();
                    break;
                case 2:
                    DeleteBook();
                    break;
                case 3:
                    ViewBook();
                    break;
                case 4:
                    CheckIn();
                    break;
                case 5:
                    CheckOut();
                    break;
                case 6:
                    SearchBook();
                    break;
                case 7:
                    AddStudent();
                    break;
                case 8:
                    DeleteStudent();
                    break;
                case 9:
                    ViewStudent();
                    break;
                case 10:
                    BorrowedBooks();
                    break;
                default:
                    System.out.println("Invalid Choice! Try Again.");
            }
        }while(choice!=0);
    }
}