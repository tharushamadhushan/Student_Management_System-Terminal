import java.util.*;
class Main{
    public static String[] idArray=new String[0];
    public static String[] nameArray=new String[0];
    public static int[] prfMarksArray=new int[0];
    public static int[] dbmMarksArray=new int[0];

    public static void main(String args []){
        printHome();
    }
    public static void printHome(){
        Scanner input=new Scanner(System.in);
        for (int i = 0; i < 150; i++){
            System.out.print("-");
        }
        System.out.printf("\n%-10s%80s%61s","|","WELCOME TO GDSE MARKS MANAGEMENT SYSTEM","|\n");
        for (int i = 0; i < 150; i++){
            System.out.print("-");
        }
        System.out.printf("\n\n%-10s%80s","[1] Add New Student","[2] Add New Student With Marks\n");
        System.out.printf("\n%-10s%82s","[3] Add Marks","[4] Update Student Details\n");
        System.out.printf("\n%-10s%71s","[5] Update Marks","[6] Delete Student\n");
        System.out.printf("\n%-10s%67s","[7] Print Student Details","[8] Print Student Ranks\n");
        System.out.printf("\n%-10s%74s","[9] Best in Programming Fundamentals","[10] Best in Database Management System\n\n\n");
        System.out.print("Enter an option to continue > ");
        int option=input.nextInt();
        switch(option){
            case 1 :clearScreen();
                addNewStudent();break;
            case 2 :clearScreen();
                addNewStudentWithMarks();break;
            case 3 :clearScreen();
                addMarks();break;
            case 4 :clearScreen();
                updateStudentDetails();break;
            case 5 :clearScreen();
                updateMarks();break;
            case 6 :clearScreen();
                deleteStudent();break;
            case 7 :clearScreen();
                printStudentDetails();break;
            case 8 :clearScreen();
                printStudentRanks();break;
            case 9 :clearScreen();
                bestInPrf();break;
            case 10 :clearScreen();
                bestInDbm();break;

        }
    }
    public static void clearScreen(){
        try{
            final String os=System.getProperty("os.name");
            if(os.contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else{
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }catch(final Exception e){
            e.printStackTrace();
        }
    }
    public static int findSearch(String id){
        for (int i = 0; i < idArray.length; i++){
            if(idArray[i].equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }
    public static boolean isValidStudentId(String id){
        if(id.length()==4 && id.charAt(0)=='S'){
            String sNum=id.substring(1,4);
            char charArray[]=sNum.toCharArray();
            for (int i = 0; i < charArray.length; i++){
                if(!Character.isDigit(charArray[i])){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static void extendArrays(){
        String[] tempId=new String[idArray.length+1];
        String[] tempName=new String[nameArray.length+1];
        int[] tempPrfMarks=new int[prfMarksArray.length+1];
        int[] tempDbmMarks=new int[dbmMarksArray.length+1];
        for (int i = 0; i < idArray.length; i++){
            tempId[i]=idArray[i];
            tempName[i]=nameArray[i];
            tempPrfMarks[i]=prfMarksArray[i];
            tempDbmMarks[i]=dbmMarksArray[i];
        }
        idArray=tempId;
        nameArray=tempName;
        prfMarksArray=tempPrfMarks;
        dbmMarksArray=tempDbmMarks;
    }
    public static boolean validMarks(int marks){
        if(marks<0||marks>100){
            return false;
        }
        return true;
    }
    public static int[] rankArrays(){
        int[] total=new int[idArray.length];
        for (int i = 0; i < idArray.length; i++){
            total[i]=prfMarksArray[i]+dbmMarksArray[i];
        }
        for (int i = 0; i < idArray.length-1; i++){
            for (int j= 0; j <idArray.length-1; j++){
                if(total[j]<total[j+1]){
                    int tempTotal=total[j];
                    total[j]=total[j+1];
                    total[j+1]=tempTotal;

                    String tempId=idArray[j];
                    idArray[j]=idArray[j+1];
                    idArray[j+1]=tempId;

                    String tempName=nameArray[j];
                    nameArray[j]=nameArray[j+1];
                    nameArray[j+1]=tempName;

                    int tempPrfMarks=prfMarksArray[j];
                    prfMarksArray[j]=prfMarksArray[j+1];
                    prfMarksArray[j+1]=tempPrfMarks;

                    int tempDbmMarks=dbmMarksArray[j];
                    dbmMarksArray[j]=dbmMarksArray[j+1];
                    dbmMarksArray[j+1]=tempDbmMarks;

                }
            }
        }
        return total;
    }


    //1---------------------------------------------------------------------------------------------------------------------
    public static void addNewStudent(){
        Scanner input=new Scanner(System.in);
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","ADD NEW STUDENT","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            L2:do{
                System.out.print("\n\nInput Student Id : ");
                String id=input.nextLine();
                if(!isValidStudentId(id)){
                    System.out.println("invalid");
                    continue L2;
                }
                if(findSearch(id)!=-1){
                    System.out.println("Already added");
                    continue L2;
                }
                System.out.print("Input Student Name : ");
                String name=input.nextLine();
                extendArrays();
                idArray[idArray.length-1]=id;
                nameArray[nameArray.length-1]=name;
                prfMarksArray[prfMarksArray.length-1]=-1;
                dbmMarksArray[dbmMarksArray.length-1]=-1;

                System.out.print("\n\nStudent has been added successfully.");

                L3:do{
                    System.out.print("Do you want to add a new student (Y/N) : ");
                    String option=input.nextLine();
                    if(option.equals("Y")){
                        clearScreen();
                        continue L1;
                    }else if(option.equals("N")){
                        clearScreen();
                        printHome();
                        break L1;
                    }else{
                        System.out.println("Wrong input");
                        continue L3;
                    }
                }while(true);
            }while(true);
        }while(true);
    }
    //2-----------------------------------------------------------------------------------------------------------------------
    public static void addNewStudentWithMarks(){
        Scanner input=new Scanner(System.in);
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","ADD NEW STUDENT WITH MARKS","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            L2:do{
                System.out.print("\n\nInput Student Id : ");
                String id=input.next();
                if(!isValidStudentId(id)){
                    System.out.println("invalid");
                    continue L2;
                }
                if(findSearch(id)!=-1){
                    System.out.println("Already added");
                    continue L2;
                }
                System.out.print("Input Student Name : ");
                String name=input.next();
                System.out.print("\n");
                L3:do{
                    System.out.print("Programming Fundamentals Marks : ");
                    int prfMarks=input.nextInt();
                    if(!validMarks(prfMarks)){
                        System.out.println("Invalid");
                        continue L3;
                    }
                    L4:do{
                        System.out.print("Database Management System Marks : ");
                        int dbmMarks=input.nextInt();
                        if(!validMarks(dbmMarks)){
                            System.out.println("Invalid");
                            continue L4;
                        }

                        extendArrays();
                        idArray[idArray.length-1]=id;
                        nameArray[nameArray.length-1]=name;
                        prfMarksArray[prfMarksArray.length-1]=prfMarks;
                        dbmMarksArray[dbmMarksArray.length-1]=dbmMarks;
                        System.out.print("\n\nStudent has been added successfully.");

                        L5:do{
                            System.out.print("Do you want to add a new student (Y/N) : ");
                            String option=input.next();
                            if(option.equals("Y")){
                                clearScreen();
                                continue L1;
                            }else if(option.equals("N")){
                                clearScreen();
                                printHome();
                                break L1;
                            }else{
                                System.out.print("Wrong Input");
                                continue L5;
                            }
                        }while(true);
                    }while(true);
                }while(true);
            }while(true);
        }while(true);
    }
    //3-----------------------------------------------------------------------------------------------------------------------
    public static void addMarks(){
        Scanner input=new Scanner(System.in);
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","ADD MARKS","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            L2:do{
                System.out.print("\n\nInput Student Id : ");
                String id=input.next();
                int index=findSearch(id);

                if(!isValidStudentId(id)){
                    System.out.println("invalid");
                    continue L2;
                }
                if(prfMarksArray[index]!=-1){
                    System.out.println("Stundent name : "+nameArray[index]);
                    System.out.println("This student'S marks have been already added.");
                    System.out.println("If you want to update the marks,please use [4] update marks option.");
                }else if(prfMarksArray[index]==-1){
                    System.out.println("Stundent name : "+nameArray[index]);
                    System.out.println();
                    L3:do{
                        System.out.print("Programming Fundamentals Marks : ");
                        int prfMarks=input.nextInt();
                        if(!validMarks(prfMarks)){
                            System.out.println("Invalid");
                            continue L3;
                        }
                        L4:do{
                            System.out.print("Database Management System Marks : ");
                            int dbmMarks=input.nextInt();
                            if(!validMarks(dbmMarks)){
                                System.out.println("Invalid");
                                continue L4;
                            }

                            extendArrays();

                            prfMarksArray[index]=prfMarks;
                            dbmMarksArray[index]=dbmMarks;
                            System.out.print("\n\nMarks has been added successfully.");
                        }while(false);
                    }while(false);
                }

                L5:do{
                    System.out.print("Do you want to add marks for another student? (Y/N) : ");
                    String option=input.next();
                    if(option.equals("Y")){
                        clearScreen();
                        continue L1;
                    }else if(option.equals("N")){
                        clearScreen();
                        printHome();
                        break L1;
                    }else{
                        System.out.print("Wrong Input");
                        continue L5;
                    }
                }while(true);
            }while(true);
        }while(true);
    }
    //4--------------------------------------------------------------------------------------------------------------------------------
    public static void updateStudentDetails(){
        Scanner input=new Scanner(System.in);
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","UPDATE STUDENT DETAILS","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            L2:do{
                System.out.print("\n\nInput Student Id : ");
                String id=input.next();
                int index=findSearch(id);

                if(!isValidStudentId(id)){
                    System.out.println("invalid");
                    continue L2;
                }
                System.out.println("Stundent name : "+nameArray[index]);
                System.out.println();
                do{
                    System.out.print("Enter new student name : ");
                    String newName=input.next();
                    extendArrays();
                    nameArray[index]=newName;
                    System.out.print("\n\nStudent has been update successfully.");
                    L5:do{
                        System.out.print("Do you want to update for another student details? (Y/N) : ");
                        String option=input.next();
                        if(option.equals("Y")){
                            clearScreen();
                            continue L1;
                        }else if(option.equals("N")){
                            clearScreen();
                            printHome();
                            break L1;
                        }else{
                            System.out.print("Wrong Input");
                            continue L5;
                        }
                    }while(true);
                }while(true);
            }while(true);
        }while(true);
    }
    //5-----------------------------------------------------------------------------------------------------------------------------------
    public static void updateMarks(){
        Scanner input=new Scanner(System.in);
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","UPDATE STUDENT DETAILS","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            L2:do{
                System.out.print("\n\nInput Student Id : ");
                String id=input.next();
                int index=findSearch(id);

                if(!isValidStudentId(id)){
                    System.out.println("invalid");
                    continue L2;
                }
                if(prfMarksArray[index]==-1||dbmMarksArray[index]==-1){
                    System.out.println("Stundent name : "+nameArray[index]);
                    System.out.println("\nThis student's marks yet to be added.");
                }else{
                    System.out.println("Stundent name : "+nameArray[index]);
                    System.out.println();
                    System.out.println("Programming Fundamental Marks : "+prfMarksArray[index]);
                    System.out.println("Database Management System Marks : "+dbmMarksArray[index]);
                    System.out.println();
                    do{
                        System.out.print("Enter new Programming Fundamental Marks : ");
                        int newPrfMarks=input.nextInt();
                        System.out.print("Enter new Database Management System Marks : ");
                        int newDbmMarks=input.nextInt();

                        extendArrays();
                        prfMarksArray[index]=newPrfMarks;
                        dbmMarksArray[index]=newDbmMarks;
                        System.out.print("\n\nMarks have been updated successfully.");
                    }while(false);
                }
                L5:do{
                    System.out.print("Do you want to update marks for another student? (Y/N) : ");
                    String option=input.next();
                    if(option.equals("Y")){
                        clearScreen();
                        continue L1;
                    }else if(option.equals("N")){
                        clearScreen();
                        printHome();
                        break L1;
                    }else{
                        System.out.print("Wrong Input");
                        continue L5;
                    }
                }while(true);
            }while(true);
        }while(true);
    }
    //6-----------------------------------------------------------------------------------------------------------------------------------
    public static void deleteStudent(){
        Scanner input=new Scanner(System.in);
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","DELETE STUDENT","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            L2:do{
                System.out.print("\nInput student ID : ");
                String id=input.nextLine();
                int index=findSearch(id);
                if(index==-1){
                    System.out.print("Invalid Student ID.");
                    L3:do{
                        System.out.print("Do you want to search again? (Y/N) : ");
                        String option=input.nextLine();
                        if(option.equalsIgnoreCase("Y")){
                            continue L2;
                        }else if(option.equalsIgnoreCase("N")){
                            printHome();
                            break L1;
                        }else{
                            System.out.println("Invalid option...");
                            continue L3;
                        }
                    }while(true);
                }
                for(int i=index; i<idArray.length-1; i++){
                    idArray[i]=idArray[i+1];
                }
                String[] tempIdArray=new String[idArray.length-1];
                for(int i=0; i<tempIdArray.length; i++){
                    tempIdArray[i]=idArray[i];
                }
                idArray=tempIdArray;
                System.out.println("Student has been deleted successfully.");
                L4:do{
                    System.out.print("Do you want to delete another student? (Y/N) : ");
                    String option=input.nextLine();
                    if(option.equalsIgnoreCase("Y")){
                        continue L1;
                    }else if(option.equalsIgnoreCase("N")){
                        clearScreen();
                        printHome();
                        break L1;
                    }else{
                        System.out.println("Invalid option...");
                        continue L4;
                    }
                }while(true);
            }while(true);
        }while(true);
    }
    //7---------------------------------------------------------------------------------------------------------------------------------
    public static void printStudentDetails(){
        Scanner input=new Scanner(System.in);
        int[] total=rankArrays();
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","PRINT STUDENT DETAILS","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            L2:do{
                System.out.print("\n\nInput Student Id : ");
                String id=input.next();
                int index=findSearch(id);

                if(!isValidStudentId(id)){
                    System.out.println("invalid");
                    continue L2;
                }
                if(prfMarksArray[index]==-1||dbmMarksArray[index]==-1){
                    System.out.println("Stundent name : "+nameArray[index]);
                    System.out.println("\nThis student's marks yet to be added.");
                }
                System.out.println("Stundent name : "+nameArray[index]);
                System.out.println();
                System.out.printf("%-5s%20s%25d","Programming Fundamental Marks","|",+prfMarksArray[index]);
                System.out.printf("\n%-5s%17s%25d","Database Management System Marks","|",+dbmMarksArray[index]);
                System.out.printf("\n%-5s%44s%25d","Total","|",+total[index]);
                double avg=(double)total[index]/2;
                System.out.printf("\n%-5s%42s%25.2f","Average","|",+avg);
                System.out.printf("\n%-5s%44s%25d","Rank","|",(index+1));
                System.out.println();

                L3:do{
                    System.out.print("\nDo you want to search another student details? (Y/N) : ");
                    String option=input.next();
                    if(option.equals("Y")){
                        clearScreen();
                        continue L1;
                    }else if(option.equals("N")){
                        clearScreen();
                        printHome();
                        break L1;
                    }else{
                        System.out.print("Wrong Input");
                        continue L3;
                    }
                }while(true);
            }while(true);
        }while(true);
    }
    //8----------------------------------------------------------------------------------------------------------------------------------
    public static void printStudentRanks(){
        Scanner input=new Scanner(System.in);
        int[] total=rankArrays();
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","PRINT STUDENT DETAILS","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            double[]avg=new double[total.length];
            for (int i = 0; i <total.length ; i++){
                avg[i]=(double)total[i]/2;
            }
            System.out.print("\nRank   ID      Name          Total Marks   Avg. marks\n");
            System.out.print("----  ------  -------------   -----------  ------------\n");
            for (int i = 0; i <idArray.length ; i++){
                if (prfMarksArray[i]==-1){
                    continue;

                }else{
                    System.out.printf("\n%d%9s%11s%15d%15.2f\n",(i+1),idArray[i],nameArray[i],total[i],avg[i]);

                }
            }
            L2:do{
                System.out.print("\nDo you want to Exit? (Y/N) : ");
                String option=input.next();
                if(option.equals("Y")){
                    clearScreen();
                    continue L1;
                }else if(option.equals("N")){
                    clearScreen();
                    printHome();
                    break L1;
                }else{
                    System.out.print("Wrong Input");
                    continue L2;
                }
            }while(true);
        }while(true);
    }
    //9-------------------------------------------------------------------------------------------------------------------
    public static void bestInPrf(){
        Scanner input = new Scanner(System.in);
        int[] total=rankArrays();
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","BEST IN PROGRAMMING FUNDAMENTAL","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            for (int i = 0; i <idArray.length-1 ; i++){
                for (int j = 0; j < idArray.length-1; j++){
                    if (prfMarksArray[j]<prfMarksArray[j+1]){
                        int tempPrf=prfMarksArray[j];
                        prfMarksArray[j]=prfMarksArray[j+1];
                        prfMarksArray[j+1]=tempPrf;

                        String tempId=idArray[j];
                        idArray[j]=idArray[j+1];
                        idArray[j+1]=tempId;

                        String tempName=nameArray[j];
                        nameArray[j]=nameArray[j+1];
                        nameArray[j+1]=tempName;

                        int tempDbms=dbmMarksArray[j];
                        dbmMarksArray[j]=dbmMarksArray[j+1];
                        dbmMarksArray[j+1]=tempDbms;

                    }
                }
            }
            System.out.printf("\nID            Name            PRF MARKS      DBMS MARKS    \n");
            System.out.printf("----  --------------        -------------   -------------   \n");
            for (int i = 0; i <prfMarksArray.length ; i++){
                if (prfMarksArray[i]==-1){
                    continue;

                }else{

                    System.out.printf("%s%10s%22d%15d\n",idArray[i],nameArray[i],prfMarksArray[i],dbmMarksArray[i]);

                }
            }
            L2:do{
                System.out.print("\nDo you want to Exit? (Y/N) : ");
                String option=input.next();
                if(option.equals("Y")){
                    clearScreen();
                    continue L1;
                }else if(option.equals("N")){
                    clearScreen();
                    printHome();
                    break L1;
                }else{
                    System.out.print("Wrong Input");
                    continue L2;
                }
            }while(true);

        }while(true);
    }
    //10-----------------------------------------------------------------------------------------------------------
    public static void bestInDbm(){
        Scanner input = new Scanner(System.in);
        int[] total=rankArrays();
        L1:do{
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            System.out.printf("\n%-10s%72s%69s","|","BEST IN DATABASE MANAGEMENT SYSTEM","|\n");
            for (int i = 0; i < 150; i++){
                System.out.print("-");
            }
            for (int i = 0; i <idArray.length-1 ; i++){
                for (int j = 0; j < idArray.length-1; j++){
                    if (dbmMarksArray[j]<dbmMarksArray[j+1]){
                        int tempDbms=dbmMarksArray[j];
                        dbmMarksArray[j]=dbmMarksArray[j+1];
                        dbmMarksArray[j+1]=tempDbms;

                        String tempId=idArray[j];
                        idArray[j]=idArray[j+1];
                        idArray[j+1]=tempId;

                        String tempName=nameArray[j];
                        nameArray[j]=nameArray[j+1];
                        nameArray[j+1]=tempName;

                        int tempPrf=prfMarksArray[j];
                        prfMarksArray[j]=prfMarksArray[j+1];
                        prfMarksArray[j+1]=tempPrf;

                    }
                }
            }
            System.out.printf("\nID            Name            PRF MARKS      DBMS MARKS    \n");
            System.out.printf("----  --------------        -------------   -------------   \n");
            for (int i = 0; i <dbmMarksArray.length ; i++){
                if (dbmMarksArray[i]==-1){
                    continue;

                }else{

                    System.out.printf("%s%10s%22d%15d\n",idArray[i],nameArray[i],dbmMarksArray[i],prfMarksArray[i]);

                }
            }
            L2:do{
                System.out.print("\nDo you want to Exit? (Y/N) : ");
                String option=input.next();
                if(option.equals("Y")){
                    clearScreen();
                    continue L1;
                }else if(option.equals("N")){
                    clearScreen();
                    printHome();
                    break L1;
                }else{
                    System.out.print("Wrong Input");
                    continue L2;
                }
            }while(true);

        }while(true);
    }
}



