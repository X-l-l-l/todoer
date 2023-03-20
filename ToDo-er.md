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
![Database](https://www.planttext.com/api/plantuml/svg/VPBTQeD048NlzoccAHI5Xf12IWWYUOZUowfZ7Be_iauQGz9txwungIMnxjNksJFdcnrXsx9qlDTAF9LOaK5e76dfZjad7gCkXYHFay8gwpRF-NhzllJR5o-boQ8_JQ5B5dbwSrVM8UtCpJrOPVZkd3SNornXk8-6YO_GGP90zF4AXveOOqX_0xnthysuDbPX-U8QIv9pXuLrvPyKhb7xnfGa8yGbQz-YQ-EJ67TBPRo1d7PaM0mnfMJCR9NbvBZsQjZ3LKGjIVd3B-cAMhheRRMARwAH6gUqHhRjmKDEwG7kWLzyN-dPblOVUX3ZCOHgsQFUi2qSDKpMJ87uOQXf2ZyQ_TyRZY4KjVm8eh2wKSXuKJXQZiAnupkm41wQX_DvFZz1Zx21sL1-9lgv3c9_qOl30yp45atfVtNu0G00)

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

The project is divided into 3 layers:
- the controller layer, which contains all of the functionalities of the app and routing methods for specific requests
- the service layer, responsible for implementing all the functionalities of the app
- the repository layer, which ties the app and the database, it creates queries for getting specific data from the database
