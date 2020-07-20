import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collections;
import java.io.FileNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Comparator;


public class CourseSelector {

    public static ArrayList<ArrayList<String>> course = new ArrayList<>(); //declare variable to store data of input file

    //read() method is for reading the data from input file
    public static Integer read(String filename) {
        try {

            // Check if input is valid
            if(filename.equalsIgnoreCase("null") || filename.equalsIgnoreCase("empty") || filename.isEmpty()){
                return 0;
            }
            else {
                String readfile = filename,st;
                File file = new File(readfile);
                BufferedReader reader = new BufferedReader(new FileReader(file)); //  Using BufferedReader to read the file
                int count = 0;  //declare variable for counting number of line

                //Below loop will fetch data line by line from input file and store in course variable
                while ((st = reader.readLine()) != null) {
                    st=st.toUpperCase();
                    if(st.trim().length()!=0){
                        ArrayList<String> result = new ArrayList<>(Arrays.asList(st.split("\\s+")));
                        course.add(result);
                        count++;
                    }
                }

                //close the reader
                reader.close();

                return count;
            }
        } catch (FileNotFoundException e) {  // Handle the exception if file is not found.
            return 0;
        }
        catch (Exception e){
            return 0;
        }
    }

    //recommend() method is for suggesting courses and return list of courses
    public ArrayList<String> recommend(String taken, int support, int recommendations) {

        ArrayList<String> reco_list = new ArrayList<>(); //declare list for recommendation list
        HashMap<String, Integer> CourseHash = new HashMap<String, Integer>();
        LinkedHashMap<String, Integer> sorted_hash = new LinkedHashMap<>();
        String inp;

        //Here we are converting each and every course name to uppercase

        try {

            // Check if input is valid
            if (taken.equalsIgnoreCase("empty") || taken.isEmpty() || taken.equalsIgnoreCase("null") || taken.equalsIgnoreCase(null)) {
                //System.out.println("Please Enter valid taken string.");
                return reco_list;
            } else if (support < 0) {
                //System.out.println("Please Enter valid Support count.");
                return reco_list;
            } else if (recommendations < 0) {
                //System.out.println("Please Enter valid Recommendation count.");
                return reco_list;
            }
            else if(course.isEmpty()){
                //System.out.println("\nSorry..Please Load the Data.\n");
                return reco_list;
            }

            else {

                taken=taken.toUpperCase();

                //declared list for taken courses
                ArrayList<String> taken_list = new ArrayList<String>(Arrays.asList(taken.split("\\s+")));
                //declared list for storing filtered courses according to support count
                ArrayList<ArrayList<String>> filter_course = new ArrayList<ArrayList<String>>();
                int count = 0;

                //add all courses to filtered courses
                for (int row = 0; row < course.size(); row++) {
                    if (course.get(row).containsAll(taken_list)) {
                        filter_course.add((ArrayList<String>) course.get(row).clone());
                    }
                }

                //System.out.println(filter_course);
                //remove taken courses from filtered courses
                for (int row = 0; row < filter_course.size(); row++) {
                    filter_course.get(row).removeAll(taken_list);
                }

                //This block of code create hashmap where we store course name with it's occurrences
                for (int i = 0; i < filter_course.size(); i++) {
                    for (int j = 0; j < filter_course.get(i).size(); j++) {
                        inp = filter_course.get(i).get(j);
                        // check whether a value exists or not
                        if (CourseHash.containsKey(inp)) {
                            CourseHash.put(inp, CourseHash.get(inp) + 1);
                        } else {
                            CourseHash.put(inp, 1);
                        }
                    }
                }

                //System.out.println(CourseHash);


                //referred from: [4] https://stackoverflow.com/questions/29860667/how-to-sort-a-linkedhashmap-by-value-in-decreasing-order-in-java-stream
                //this next 3 line of code will sort Hashmap in descending order
                CourseHash.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEachOrdered(x -> sorted_hash.put(x.getKey(), x.getValue()));
                sorted_hash.entrySet().removeIf(entry -> entry.getValue() < support);


                //System.out.println("Reversed hashmap is:" + sorted_hash);

                //two different list for course name and frequency
                Set<String> keySet = sorted_hash.keySet();
                ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
                Collection<Integer> values = sorted_hash.values();
                ArrayList<Integer> listOfValues = new ArrayList<Integer>(values);

                //System.out.println(listOfKeys);
                //System.out.println(listOfValues);

                if(listOfKeys.size() <= 0) {

                    //System.out.println("There are no courses available.");

                } else {
                    if(listOfKeys.size()<=recommendations){
                        for(int i=0;i<listOfKeys.size();i++){
                            reco_list.add(listOfKeys.get(i));
                        }
                    }
                    else {

                        //This block of code will generate list according to support count
                        for (int i = 0; i < recommendations; i++) {
                            if (i == recommendations - 1) {
                                reco_list.add(listOfKeys.get(i));
                                //System.out.println(listOfKeys.get(i));
                                while (i < (listOfKeys.size() - 1) && listOfValues.get(i).equals(listOfValues.get(i + 1))) {
                                    reco_list.add(listOfKeys.get(i + 1));

                                    //System.out.println(listOfKeys.get(i + 1));

                                    i = i + 1;
                                }
                            } else {
                                reco_list.add(listOfKeys.get(i));

                                //System.out.println(listOfKeys.get(i));
                            }
                        }
                    }
                }
                return reco_list ;
            }
        }
        catch (NullPointerException e){
            return reco_list;
        }
        catch (Exception e){
            //e.printStackTrace();
            return reco_list;
        }
    }

    //This method will print pairwise 2D matrix for given input
    public boolean showCommon(String courses) {

        try {
            // Check if input is valid
            if (courses.equalsIgnoreCase("null") || courses.equalsIgnoreCase("empty") || courses.trim().length()==0 ) {
                //System.out.println("Enter valid list of courses.");
                return false;
            }
            else if(course.isEmpty()){
                //System.out.println("\nSorry..Data is not available.Please Load the Data.\n");
                return false;
            }
            else {
                courses=courses.toUpperCase();
                int freq;
                Set<String> course_set1 = new HashSet<>(Arrays.asList(courses.split("\\s+"))); //store course string to set
                List<String> course_set = new ArrayList<>(course_set1); //convert set to list
                Collections.sort(course_set);  //sort course list in ascending order

                //System.out.println(course_set);

                //declared variable flag is used to store occurrences of pair
                int[][] flag = new int[course_set.size()][course_set.size()];

                //This block of code will scan each pair and compare in course variable
                for (int i = 0; i < course_set.size(); i++) {
                    for (int j = 0; j < course_set.size(); j++) {
                        //freq = 0;
                        if (i == j) {
                            flag[i][j] = 0;
                        } else {
                            for (int row = 0; row < course.size(); row++) {
                                if ((course.get(row).contains(course_set.get(i))) && (course.get(row).contains(course_set.get(j)))) {
                                    flag[i][j]++;
                                }
                            }
                        }
                    }
                }

                //This block of code will print 2D matrix on screen
                for (int i = 0; i < course_set.size(); i++) {
                    System.out.println();
                    System.out.print(course_set.get(i) + "\t");
                    for (int j = 0; j < course_set.size(); j++) {
                        System.out.print("\t" + flag[i][j]);
                    }
                }
                System.out.println();
                return true;
            }
        }catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    //This method will write 2D matrix to filename in argument
    public boolean showCommonAll(String filename){

        try {
            // Check if input is valid
            if(filename.equalsIgnoreCase("null") || filename.equalsIgnoreCase("empty") || filename.isEmpty()){
                //System.out.println("Please Enter valid file name.");
                return false;
            }
            else if(course.isEmpty()){
                //System.out.println("\nSorry..Data is not available.Please Load the Data.\n");
                return false;
            }
            else {
                String readfile = filename;
                int freq;
                File file = new File(readfile); //create new object of File class
                ArrayList<ArrayList<String>> course_all = new ArrayList<ArrayList<String>>();

                //set of courses read from file
                Set<String> course_set1 = new HashSet<>();

                //Using BufferWriter to write to the file
                BufferedWriter writer=new BufferedWriter(new FileWriter(file));


                //Here clone method is use to create deep copy of element
                for (int row = 0; row < course.size(); row++) {
                    course_all.add((ArrayList<String>) course.get(row).clone());
                }

                for (int i = 0; i < course_all.size(); i++) {
                    for (int j = 0; j < course_all.get(i).size(); j++) {
                        course_set1.add(course_all.get(i).get(j));
                    }
                }

                List<String> course_set = new ArrayList<>(course_set1);  //This convert set of course to set
                Collections.sort(course_set);

                int[][] flag = new int[course_set.size()][course_set.size()];
                //System.out.println("course set is:" + course_set);


                //Below block of code will check each pair and generate
                //2D matrix in flag array

                for (int i = 0; i < course_set.size(); i++) {
                    for (int j = 0; j < course_set.size(); j++) {
                        //freq = 0;
                        if (i == j) {
                            flag[i][j] = 0;
                        } else {
                            for (int row = 0; row < course_all.size(); row++) {
                                if ((course_all.get(row).contains(course_set.get(i))) && (course_all.get(row).contains(course_set.get(j)))) {
                                    flag[i][j]++;
                                }
                            }
                        }
                    }
                }

                //This block of code will write data to output file
                for (int i = 0; i < course_set.size(); i++) {
                    writer.write(course_set.get(i)+"\t");
                    for (int j = 0; j < course_set.size(); j++) {
                        //System.out.print("\t" + flag[i][j]);
                        writer.write(flag[i][j]+"\t");
                    }
                    writer.write("\n");
                }

                //flush the writer
                writer.flush();

                return true;
            }
        } catch (Exception e) {  // Handle the exception if file is not found.
            return false;
        }
    }
}
