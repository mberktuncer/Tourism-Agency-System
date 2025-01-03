# Tourism Agency Management System

This project aims to digitalize hotel management, room reservations, and pricing processes, providing a user-friendly desktop application. 
Users can search for hotel rooms based on features, view pricing information, and easily make reservations.

## Technologies Used
- **Java**: For the core application logic and development.
- **Swing**: To build the graphical user interface (GUI).
- **PostgreSQL**: As the database management system for storing and managing data.
- **Docker**: To containerize and manage the PostgreSQL database environment.
- **DBeaver**: As a database management tool for development and testing.
- **GitHub**: For version control and project collaboration.

---

## Table of Contents

1. [Project Purpose](#project-purpose)
2. [Features](#features)
3. [Database Design](#database-design)
4. [Installation](#installation)
5. [Usage](#usage)

---

## Project Purpose

This system was developed to optimize hotel and reservation processes.  
Key problems it addresses:
- Enabling hotel managers to easily manage inventory and pricing.
- Allowing users to search for rooms based on city, date, and hotel information.
- Streamlining the reservation process for both managers and customers.

---

## Features

The system includes the following key features:
- **Hotel Management:** Manage hotel details, facilities, and room inventory.
- **Room Search:** Dynamic search functionality based on city, hotel name, check-in, and check-out dates.
- **Pricing:** Flexible pricing for adults and children based on seasonal variations.
- **Reservation Management:** View, update, and delete reservations with real-time stock updates.
- **Dynamic Data Handling:** User-friendly interface for adding and editing hotels, rooms, and prices.

---

## Database Design

The system's database includes comprehensive structures to manage hotels, rooms, reservations, and pricing.
- **ER Diagram:**

![er-diagram.png](src%2Fimages%2Fer-diagram.png)
---

## Installation

To set up and run the application, follow these steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/username/repository.git
2. Import the project into your preferred IDE (e.g., IntelliJ, Eclipse).
3. Set up the database by executing the provided SQL scripts.
4. Configure the database connection in the project settings.
5. Build and run the application.

## Usage

- **Login Screen**

This is the login screen of the application. Users can log in using a username and password. If either of these 
credentials is incorrect, the application displays an error message and prevents login. 
There are two user roles in the system: admin and staff.

![Login-Screen.png](src%2Fimages%2FLogin-Screen.png)

- **Admin Screen**

This screen is accessible only to users with the admin role. It allows administrators to list, add, edit, and delete users. 
New staff accounts can only be created through this interface. The screen also supports filtering users by their roles. 

![Admin-Screen.png](src%2Fimages%2FAdmin-Screen.png)

- **Staff Screen**

This screen is used by agency staff and includes tabs for Hotel Management, Room Management, Reservation, and Reservation List.

- *Hotel Tab*

This tab is dedicated to listing, adding, editing, and deleting hotels in the system. Users can search for hotels by name. 
Various features related to each hotel are displayed in the table.

![Staff-Screen-Hotel-Tab.png](src%2Fimages%2FStaff-Screen-Hotel-Tab.png)

When adding a new hotel, the user can click the Add New Hotel button, which opens a new window. 
After filling out the necessary information in the form, the hotel is added to the database.

![Staff-Screen-Add-New-Hotel-Window.png](src%2Fimages%2FStaff-Screen-Add-New-Hotel-Window.png)

- *Room Management Tab*

This tab is used for listing, adding, updating, and deleting rooms. Each room is linked to its respective hotel through the Hotel ID.

![Staff-Screen-Room-Management-Tab.png](src%2Fimages%2FStaff-Screen-Room-Management-Tab.png)

To add a new room, click the Add New Room button. In the new window, room details are entered, and pricing is defined according to the relevant season.

![Staff-Screen-Add-New-Room-Window.png](src%2Fimages%2FStaff-Screen-Add-New-Room-Window.png)

- *Reservation Tab*

This tab allows searching for available rooms based on customer preferences, such as date range, city, and hotel. 

![Staff-Screen-Resrvation-Tab.png](src%2Fimages%2FStaff-Screen-Resrvation-Tab.png)

If a room is available for the desired date range, the Make Reservation button is used. In the new window, customer 
information is collected, and the total price is calculated based on the number of guests staying in the room. 
The reservation is then finalized.

![Staff-Screen-Make-Reservation-Window.png](src%2Fimages%2FStaff-Screen-Make-Reservation-Window.png)

- *Reservation List Tab*

This tab lists all existing reservations in the system. Cancelled reservations can be deleted.

![Staff-Screen-Reservation-List-Tab.png](src%2Fimages%2FStaff-Screen-Reservation-List-Tab.png)