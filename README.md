# JoesTable #

### Welcome!
JoesTable is a web application that uses the Yelp API to allow users to search for restaurants, add them to their favorites, and schedule reservations. I incorporated a MySQL database for managing user data and utilized the Google Maps JavaScript API for enhanced functionality.

### [*Video Demo*](https://drive.google.com/file/d/1XXOo4iwsc3NZHsGc9SCGoblmNQzPA3PS/view?usp=share_link)

## How It's Organized
### Frontend
#### HTML  
*Path:* `/wntrinh_CSCI201_Assignment4/src/main/webapp/pages`
1. home.html
2. login.html
3. favorites.html
4. reservations.html

#### CSS  
*Path:* `/wntrinh_CSCI201_Assignment4/src/main/webapp/styles`
1. home.css
2. login.css
3. favorites.css
4. reservations.css

#### JavaScript  
*Path:* `/wntrinh_CSCI201_Assignment4/src/main/webapp/scripts`  
*These files use JSON/AJAX to exchange data between frontend and backend.*
1. home.js
2. login.js
3. reservations.js
4. favorites.js

### Backend
*Path:* `/wntrinh_CSCI201_Assignment4/src/main/java/com/wntrinh/assignment4`

1. **`/JDBCConnector/JDBCConnector.java` Class**
   - JDBCConnector was used for connecting to the database and performing operations like registering users, updating reservations, and managing favorites in the MySQL database
   - Created functions for the following:
        1. Inserting new users
        2. Logging in users
        3. Adding and removing favorites
        4. Adding and retrieving reservations

2. **`/servlets` folder**
   - Contains a variety of servlets. Servlet classes would be extending HttpServlet and would require implementing doPost and/or doGet
   - Each servlet called function(s) from the JDBCConnector class. Served as a facilitator between the frontend and backend

3. **`/YelpAPI/YelpAPI.java` Class**
   - Contains all of the code to make a Yelp search and parse the results

4. **Remaining Classes/Folders**
   - Helper classes for the files listed above

### Database

I had used 4 different tables (See `create.sql`):

1. **Users Table**
   - Data: user_id, username, password, email

2. **Restaurants Table**
   - Data: restaurant_id, name, address, phone, etc.

3. **Favorites Table**
   - Data: user_id, restaurant_id

4. **Reservations Table**
   - Data: user_id, restaurant_id, date, time

I used primary keys, foreign keys, and auto-increment ID to connect the tables.

## Where to Start
### Files
I've included all of the necesary files to set up the project on your computer.

`create.sql` contains the SQL scripts that you need to run to initialize the database on MySQLWorkbench. If needed, the script can be used to clear the database.

The `wntrinh_CSCI201_Assignment4` contains all of the source code to run the project. Import the root folder into Eclipse.

### 1. Download Prerequisite Software
To run this project, you need the following software, if you don't already have it:
1. **Eclipse and Java**
2. **Apache Tomcat**
3. **MySQLWorkbench**

Detailed Instructions:
1. **Eclipse and Java**
    - First, go to [Oracle's website](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)  
    - Follow these [instructions](https://drive.google.com/file/d/1xY0-Z2JT5bFnYH3TKrszN_dVyEerzcd5/view?usp=share_link)
    - Click on **Import existing projects** and  
    add `wntrinh_CSCI201_Assignment4` into Eclipse
2. Follow these [instructions **Apache Tomcat**](https://drive.google.com/file/d/1Qs9pA3fTu_dE-V4iQIC9I6NMxb5TOueU/view?usp=share_link)  
    - To run the web app, just navigate to  
    `/wntrinh_CSCI201_Assignment4/src/main/webapp/pages/home.html`  
    Right click on home.html and click **Run as...** and choose **Tomcat**
3. Follow these [instructions for **MySQLWorkbench**](https://drive.google.com/file/d/1-n8eYSOoo3LMX_hRUv7fxwZ_uR_R_R5w/view?usp=share_link)
    - After you click on **Create new schema**  
    Paste in the script from `create.sql`
    - Make sure that your **MySQL server is live before running JoesTable**

### 2. Update API Keys

#### 1. Yelp API
- **Navigate to the file YelpAPI.java**
  - **Path:** `/wntrinh_CSCI201_Assignment4/src/main/java/com/wntrinh/assignment4/YelpAPI/YelpAPI.java`
- Follow the instructions for **STEP #1** on line 18:
  - To get the API key, go to the [Yelp website](https://www.yelp.com/login?return_url=https%3A%2F%2Fwww.yelp.com%2Fseeyousoon%3Ffsid%3D5VnJEHtFy72kywB6_Wlmqg)
  - Click on **New to Yelp? Sign up** and follow the instructions
  - Once logged in, navigate to the [developer dashboard](https://www.yelp.com/developers/v3/manage_app)
    1. Go to Create App
    2. In the create new app form, enter information about your app, then agree to Yelp API Terms of Use and Display Requirements. Then click the Submit button.
    3. You will now have an API Key. Use this to complete **STEP #1**

#### 2. Google Maps API
- **Navigate to the folder `pages`**
  - **Path:** `/wntrinh_CSCI201_Assignment4/src/main/webapp/pages`
- In here, you should find four .html files. You need to update three of them, these being:
  - `home.html` *(instructions on line 95)*
  - `favorites.html` *(instructions on line 108)*
  - `reservations.html` *(instructions on line 104)*
- How to get new API keys to finish **STEP #2**:
  - To get the API key, you first need to set up a [Google Cloud project](https://developers.google.com/maps/documentation/javascript/cloud-setup)
    - Follow the instructions under **Create a project**
  - With your new project, you need to [Create API keys](https://developers.google.com/maps/documentation/javascript/get-api-key)
    - Follow the instructions under **Create API keys** for your new project
    - After that, go to **APIs & Services** for your project and enable **Maps JavaScript API**
  - You will now have an API Key. Use this to complete **STEP #2** of the three .html files.

Please message me if you would like to talk, share your thoughts, or have questions about setting up the project locally on your computer.  

Thank you for taking the time to explore my project!

Contact: [wntrinh@usc.edu](mailto:wntrinh@usc.edu)
