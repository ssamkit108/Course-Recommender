## Note
>This is a course assignment for the CSCI3901.Main purpose of this assignment to learn and implement the data structures in java.


# Course-Recommender
This program is mainly useful for course recommendations. Here, we will recommend courses to take at the university based on what other students have taken. User is asked to enter taken courses and the number of recommendations they need. Along with this user will provide support count as well. We will show a list of courses based on past data.


# Description: 
This program is mainly useful for course recommendations. Here, we will recommend courses to take at the university based on what other students have taken. User is asked to enter taken courses and the number of recommendations they need. Along with this user will provide support count as well. We will show a list of courses based on past data. Moreover, we have added more 2 functions to show the pairwise popularity matrix. All these functionalities are wrapped under one single class name **&quot;CourseSelector&quot;**.

# Input: 
The user is required to load the data file of courses taken by other students of a university in the past. Now, after loading data user has 3 functions to use. Which is described further below.

# Methods: 
There are mainly 4 methods:

1. public static Integer  **read** ​ (String filename):

This method will take filename in the string and read student data from a file. It stores that data in **ArrayList\&lt;ArrayList\&lt;String\&gt;\&gt;** called &quot; **course**&quot; and return number of the line present in the file. If there is an empty file or file not found, then it throws an exception message and returns 0.

2. public ArrayList\&lt;String\&gt;  **recommend** (String taken, int support, int recommendations):

This method is mainly used for returning a list of courses that we generated from past data.

In this method, we follow simple steps.

- First, the Program removes data of students who had not taken courses.
- Now, we have data that have a list of students who had taken this courses.
- we use one HashMap to store occurrences of course who has taken along with that.
- We store the course name as key and number of occurrences as value.
- After that, we sort our HashMap in descending order and return a list of recommendations as much the user wants.

If any input error condition occurs or exception generated, then the program returns a null list.

3. Boolean **show** (String courses):

This method will print the 2D matrix of the input list of courses. Convert String of courses to **ArrayList\&lt;String\&gt;** and then we check each pair in the main course list. Moreover, we used a 2D array of Integer named **&quot;flag[int][int]&quot;** to maintain occurrences of that pair. It will return a true value if we are able to make the matrix. If any error generated or something that we couldn&#39;t able to generate matrix then we return &quot;false&quot;.

4. Boolean **showCommonAll** (String filename):

This method will write a 2D matrix for all pairs in data. Here, we start from File class. we use the write () method of **BufferedWriter** class to writes a matrix in the output file. Moreover, we used a 2D array of Integer named **&quot;flag[int][int]&quot;** to maintain occurrences of that pair. It will return &quot;true&quot; value if we can write successfully in a file or we couldn&#39;t able to generate a matrix then the program will return &quot;false&quot;.

# Data Structure Used:

In this program mainly we used ArrayList, **Multidimensional ArrayList** and **LinkedHashMap**. We store all detail of students in double ArrayList. At the time of the recommend () method, we store courses with that occurrence in LinkedHashMap.

- Why we used double ArrayList to store courses is that we store courses taken by one student in single ArrayList\&lt;String\&gt; and we have data of multiple students so add that list into one more **ArrayList\&lt;ArrayList\&lt;String\&gt;\&gt;**. So, it will create a structure like data of one student in one raw. We can access very easily.
- The reason behind using LinkedHashMap is that it doesn&#39;t allow duplicate key value and we also want the same thing. Another main reason is that we can easily sort **LinkedHashMap\&lt;String, Integer\&gt;** in descending order according to value. And LinkedHashMap maintains the insertion order as well.

# Assumptions:

- Each line in the input file will have at most 10 courses.
- There are no spaces included in the course names in the input file or in the parameters provided to your methods.

- File names will contain the full path to the file, including any filename extensions.
- If user call read () method multiple times, then our program will replace the old data with new data.

# Constraints:

- You will not get the expected output without reading the input file. You have to first load the data to use other methods.
- In the showCommonAll method, if there is any content already present in the output file then the 2D matrix will replace that content and write matrix.

# References:

1. Concept of Class and Methods: [https://www.tutorialspoint.com/java/index.htm](https://www.tutorialspoint.com/java/index.htm) [10 Jan 2020]
2. Multidimensional Collections in Java [https://www.geeksforgeeks.org/multidimensional-collections-in-java/](https://www.geeksforgeeks.org/multidimensional-collections-in-java/) [11 Jan 2020]
3. Concept of LinkedHashMap [https://www.geeksforgeeks.org/linkedhashmap-class-java-examples/](https://www.geeksforgeeks.org/linkedhashmap-class-java-examples/) [11 Jan 2020]
4. Sorting LinkedHashMap in Descending Order [https://stackoverflow.com/questions/29860667/how-to-sort-a-linkedhashmap-by-value-in-decreasing-order-in-java-stream](https://stackoverflow.com/questions/29860667/how-to-sort-a-linkedhashmap-by-value-in-decreasing-order-in-java-stream) [12 Jan 2020]
