 # ToDo-er

## Introduction

ToDo-er is a simple and easy to use task management app for single users, but also for small teams that want to keep their work structured and organised using a set of lists of items/tasks.
The user can create his own lists of tasks but he can also join a team, in which the leader can asign tasks to his teamates, set deadlines, add descriptions, etc.
The built in calendar will help the user better visualise their task deadlines and schedules.

Any user can create a team and invite friends or peers into their team. The difference between a teamate and a team leader is that the leader can assign different tasks to any of the other teamates.

## Objectives
- To create a simple, but powerful app to help the user organise his tasks
- To store the users tasks and organise them
- To boost productivity, not only for individual users, but also for teams
- To create an app that takes away the need to remeber when to do something so the user only has to focus at the task at hand
- To develop a user friendly system that has a nearly flat learning curve

## Benefits
This app can benefit individuals, as well as teams, helping them track their daily tasks, their schedules, their teamates work, etc. It can boost productivity for anybody, removing the problem of remembering **when** and **what** has to be done, so the user can focus on **how** the task has to be done.

## Functionalities
1. **Register**
    - anyone can create an account and start using the app

2. **Login**
    - after creating an account, the user can login into his account on multiple devices

3. **List and task creation**
    - users can create multiple different lists to keep their tasks organised, they can also set deadlines for each of the tasks they add
    - they can tik the tasks they've done, add new ones, delete or even change them

4. **Teams**
    - a user can create a team and be a leader to his friends or peers, giving them tasks

5. **Notifications**
    - notifications will pop up, anouncing the user when a task deadline is close

## Database Design
![Database](https://www.planttext.com/api/plantuml/svg/dLHTRu9047o_Nx6rIID9ZR69ZJ64-2FwJWvOT5FkZXnBrMZ_U-yK4J-mJU7fRdPsPkuMO56nj5ohGZnbc9D6A2qfQRVn9sw7cm3290fJKnWxVqvci_U9MxuuA0h7Xzq8Dk4uYLfsRYpIKhViXYhzSyDiMIoJ0dqzQYRUmWR248OVhx1U4MC0qLd0SMkbkxqnzwzhhZ0ZERAO6fjTgF0AbGjMID927BNg2cqLx4GtBMN1719QacRHo6IICJPvd93bbKFz6auWAac5s_oIDbr9EtoRJeCMr59X7rRAgbexatsuDtU7BxvFxjbave5x4DrhyDtYUzcmIYsLJ4Rt6N6NKVIxMDhoeOc_wpmCkxIcBcEVuahi25n8Tk7ht71kgGrJJgdqdlvzZkTJmWtV96krt02hHvFMbIYeuhWFl8bxqa-DAWjaF28yd0I_M_C31FlzQBI3Wy4v31hnWJXy6mruA7HW4_uIl3Z6lgBpzNKh_EpSApY570Z_B51dxjy4Fm00)

The app is composed of 3 tables: 
- one for users and user related data such as name, date_of_birth, email, username, password
- one for items which has their text and if they are completed or not
- one for the todo lists which has a title/name of the list and a description

## Implementation
There are 4 main packages:
- the user package, which contains the user class, the user_controller, user_service and user repository
- the account package, responsible for authentification functionality like login and registration
- the item package, which contains the item class, the item_controller, item_service and item_repository
- the todolist package, which contains the todolist class, the todolist_controller, todolist_service and todolist_repository
- the group package, containing the model, controller, service and repo
- the notification package, containing the model, controller, service and repo

The project is divided into 3 layers:
- the controller layer, which contains all of the functionalities of the app and routing methods for specific requests
- the service layer, responsible for implementing all the functionalities of the app
- the repository layer, which ties the app and the database, it creates queries for getting specific data from the database

## Functionalities
- add, select, remove or update lists, items, users, groups and group members
- on a new item added the user gets notified with the name of the list it is in and of the new items

## Endpoints

### User endpoints
- POST (register) - adds a new user, if one with the same name or email already exists
- DELETE - deletes a user by id
- PUT - updates a users name or email
- GET - gets all the existing users

### ToDoList endpoints
- POST - adds a new list to a specific user
- GET - gets all the existing lists
- PUT - updates a lists title and description
- DELETE - deletes a list by id

### Items endpoints
- POST - adds a new item to a specific list
- PUT - updates a lists text or state
- GET - gets all the existing items
- DELETE - deletes an item by id

### Group endboints
- POST - adds a new group
- PUT - updates a groups leader
- GET - gets all the existing groups
- DELETE - deletes a group by id

### Member endpoints
- POST - adds a new member to a group
- PUT - updates a member
- GET - gets all the members
- DELETE - deletes a member by id

### Notification endpoints
- POST - adds a new notification to users
- GET - gets all the notifications
- DELETE - deletes a notification by id

## Unit tests
For the unit tests I mocked with Mockito the dependencies of the used services and then tested every method of the said service. The tests validate if the objects are added, removed, updated or retreived correctly.

By mocking the dependencies of the services I can control what the output of the different methodes used is, thus letting us only test the functionality of the service class, indifferent of the input provided. 

I used the when static method to "force" a return value when using a method, the assertTrue or assertEqual methods so that the test would succeed or fail based on if the result is true (for assertTrue) or if the objects provided are equal (for assertEqual), and verify which checks if the method specified was used correctl and with the same parameters.

## Front
For the front part, I used vanilla javascript and jQuery for logic and requests, as well as HTML and CSS for design and placement on the page. I worked modularly, so that each functionality is performed by a function, launched by certain events such as pressing a button, moving the mouse, loading the page, receiving data following a request, etc.

The pages that the user can access are the login and register pages where he can create an account or use an existing one to log in. The home page will follow, where all the todo lists with different items and buttons with which you can add more lists or items are present. With the help of the menu, the user can navigate to other pages such as back to the login page, by logging out, the groups page where groups can be created, add users to groups and add items for teams, and settings where the user can edit his account .
