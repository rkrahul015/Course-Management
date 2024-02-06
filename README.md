# Course Management API Documentation

## Technologies Used:
- Java
- Spring Boot 3
- Spring Data JPA
- Maven
- MySQL
- Postman (for API testing)

## Running the Project:

### Prerequisites:
- Java JDK 17 or later
- Maven installed
- MySQL database (course_management_db)

Before running the application, make sure the database `course_management_db` is created in MySQL.

### Step Up and Run:

#### 2.1 MySQL Configuration:

1. **Login to MySQL:**
   Open Terminal or Command Prompt.
   Enter the following command and press enter:
   ```bash
   mysql -u username -p 
   ```

2. **Create a new database:**
   ```sql
   CREATE DATABASE course_management_db;
   ```

3. **Configure application.properties:**
   Update the `application.properties` file (`src/main/resources/application.properties`) of `courseManagement` (Spring Boot file) with your MySQL credentials.

   properties
   # Database Configuration
   Spring.datasource.url = jdbc:mysql://localhost:3306/course_management_db
   Spring.datasource.username = username
   Spring.datasource.password = password
   Spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
   Spring.jpa.hibernate.ddl-auto = update
   Spring.jpa.show-sql = true

   # Server Configuration
   server.port=8000

   # Hibernate Configuration
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
   

#### 2.2 To Run Course Management Application:

1. Move to `courseManagement` directory.
2. Build the project with the following command to generate a `*.jar` file in the `target/` folder:
   ```bash
   mvn clean install
   ```
3. Move to `target/` folder and run the following command to run the application:
   ```bash
   cd target/
   java -jar courseManagement-0.0.1-SNAPSHOT.jar
   ```

Now, the Course Management Application is successfully running on Port: 8000.

## API Endpoints:

### Student Controller APIs:

1. **Create a Student**
   - **Endpoint:** POST `/api/students/`
   - **Description:** Create a new student.
   - **Request Body:**
     ```json
     {
       "firstName": "Rahul",
       "lastName": "Kumar",
       "email": "rahul@gmail.com"
     }
     ```

2. **Fetch a Single Student**
   - **Endpoint:** GET `/api/students/{id}`
   - **Description:** Retrieve details of a single student.
   - **Request Parameters:**
     - `id` (Long): Student ID.

3. **Fetch All Students**
   - **Endpoint:** GET `/api/students/`
   - **Description:** Retrieve details of all students.

4. **Delete a Student**
   - **Endpoint:** DELETE `/api/students/{id}`
   - **Description:** Delete a specific student.
   - **Request Parameters:**
     - `id` (Long): Student ID.

5. **Get Courses for a Student**
   - **Endpoint:** GET `/api/students/{id}/courses`
   - **Description:** Retrieve courses enrolled by a specific student.
   - **Request Parameters:**
     - `id` (Long): Student ID.

### Course Controller APIs:

6. **Create a Course**
   - **Endpoint:** POST `/api/courses/`
   - **Description:** Create a new course.
   - **Request Body:**
     ```json
     {
       "courseName": "Computer Science",
       "credits": 3.0
     }
     ```

7. **Fetch a Single Course**
   - **Endpoint:** GET `/api/courses/{id}`
   - **Description:** Retrieve details of a single course.
   - **Request Parameters:**
     - `id` (Long): Course ID.

8. **Fetch All Courses**
   - **Endpoint:** GET `/api/courses/`
   - **Description:** Retrieve details of all courses.

9. **Delete a Course**
   - **Endpoint:** DELETE `/api/courses/{id}`
   - **Description:** Delete a specific course.
   - **Request Parameters:**
     - `id` (Long): Course ID.

10. **Get Students for a Course**
    - **Endpoint:** GET `/api/courses/{id}/students`
    - **Description:** Retrieve students enrolled in a specific course.
    - **Request Parameters:**
      - `id` (Long): Course ID.

11. **Get the Most Enrolled Course**
    - **Endpoint:** GET `/api/courses/most-enrolled`
    - **Description:** Retrieve the course with the highest enrollment.

12. **Get Highest Marks for a Course**
    - **Endpoint:** GET `/api/courses/highest-marks/{courseId}`
    - **Description:** Retrieve the highest marks scored in a specific course by a student.
    - **Request Parameters:**
      - `courseId` (Long): Course ID.

### Enrollment Controller APIs:

13. **Enroll a Student in a Course**
    - **Endpoint:** POST `/api/enrollments/enroll`
    - **Description:** Enroll a specified student in a course.
    - **Request Body:**
      ```json
      {
        "studentId": 1,
        "courseId": 2,
        "marks": 90
      }
      ```

14. **Unenroll a Student from a Course**
    - **Endpoint:** DELETE `/api/enrollments/unenroll`
    - **Description:** Unenroll a specified student from a course.
    - **Request Parameters:**
      - `studentId` (Long): Student ID.
      - `courseId` (Long): Course ID.

15. **Update Marks for a Student in a Course**
    - **Endpoint:** PUT `/api/enrollments/updateMarks`
    - **Description:** Update marks for a particular student in a specified course.
    - **Request Body:**
      ```json
      {
        "studentId": 1,
        "courseId": 2,
        "marks": 95
      }
      ```
