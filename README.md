# Stats

# Postman Output of this project

1.  Only Admin users can do the CRUD operation.
![Noaccess](https://github.com/sharmaakshansh/Stats/assets/121374240/3803a7b3-0f0e-4194-9870-a786c931a99e)

2a.  Creating player

![Create](https://github.com/sharmaakshansh/Stats/assets/121374240/cedf39a7-429a-40c1-b54f-b77143942d33)

2b. Change in Database after creating 1 player

![After Create](https://github.com/sharmaakshansh/Stats/assets/121374240/ac0647cc-43d7-4004-bd77-f3fd8d3f334e)



3a. Delete Player Details


![Delete](https://github.com/sharmaakshansh/Stats/assets/121374240/fcd8422d-575b-4e05-9b33-2507a69381f0)



3b. Change in Database after deleting the only entry/data


![After Delete](https://github.com/sharmaakshansh/Stats/assets/121374240/a97bb4c6-2814-4f4f-94d8-e32d77431f78)



4a.  Update a Players detail
 
 Name is changed from Virat Kohli to Rohit Sharma


![update P](https://github.com/sharmaakshansh/Stats/assets/121374240/8d58c459-3b9f-46b6-a918-672fb74fd940)

4b. Change in database



![Update](https://github.com/sharmaakshansh/Stats/assets/121374240/5d758137-f687-4c95-9796-aa4df9575915)

# 5. Get
Database 

![Beforegetby avg](https://github.com/sharmaakshansh/Stats/assets/121374240/fc4ceef8-f3ad-410b-932f-edf62dafd4f1)


5a. Get Player Details  by Id

![getbyID](https://github.com/sharmaakshansh/Stats/assets/121374240/1f096858-9d9b-4c50-a370-b684b5f9f4d6)

5b. Get Player Details by Country Name

![Get bycountry](https://github.com/sharmaakshansh/Stats/assets/121374240/2cdcffbc-3767-471b-b4cd-75b9c9bd2e91)

5c. Get a List of players in sorted order whose average scores are more than Y. where Y is the number. If two players have the same average, then the older player will get the priority.

In this case Chris Gail and Ishan kishan has same average i.e 100 so they are sorted on the bases of age ,average score i.e is passed is 99

![GetbyAVg(1a)](https://github.com/sharmaakshansh/Stats/assets/121374240/4f5b06ba-6e6a-4fbf-8e84-604580e55736)

Then Virat kohli he has scores 100 and 99 in 2 matches so avg is little less than 100 so he is placed at 3rd position

![GetbyAVg(1b)](https://github.com/sharmaakshansh/Stats/assets/121374240/d8d3825c-3408-4102-b374-9a9c001bcb63)

Here when Average score is passed as 100 , no player has average score more than 100 so empty list ir the result

![getByAverage(2)](https://github.com/sharmaakshansh/Stats/assets/121374240/fe8624b4-8efb-4c5f-a655-36b13d30d8de)

# JUnit test cases

![JUnit test](https://github.com/sharmaakshansh/Stats/assets/121374240/091c71ca-ad88-4272-a821-330b453e9bcf)
