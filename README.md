# OOP_Project

This project can be used by both the restaurant 
customers and the restaurant manager. Customers can use the 
application to give their orders. Furthermore, the
restaurant manager can use the application to follow the restaurant's 
expenses and revenues. The manager can also display the employees
or add more employees. An example user interface for this restaurant application is 
presented below.

<img width="645" alt="Screenshot 2020-01-12 at 3 30 33 PM" src="https://user-images.githubusercontent.com/45246131/72250162-01725900-360c-11ea-8679-fbc1b4c57545.png">



### Order Creation :
When customer wants to create an order, as the first thing, the system randomly assigns
a waiter to the customer. A pop-up message will be displayed with the name of the waiter 
who is assigned randomly. After closing the pop-up the following view is displayed 
to the user.

<img width="641" alt="Screenshot 2020-01-12 at 3 32 47 PM" src="https://user-images.githubusercontent.com/45246131/72263939-ecf18900-362a-11ea-9eb0-cbf68496a108.png">




User can select any individual product or menu product from the combobox menu. Whenever
a product is selected, the price of the product is displayed next to the
Price label. For example, in the following screenshots, 6.00 TL is displayed 
for the price of the Pizza and 3.00 TL is displayed for Ice Cream.


<p float="left">
<img width="425" alt="Screenshot 2020-01-12 at 5 08 50 PM" src="https://user-images.githubusercontent.com/45246131/72250361-78a7ed00-360c-11ea-95f4-3b2f38abcd1f.png">
<img width="425" alt="Screenshot 2020-01-12 at 5 09 27 PM" src="https://user-images.githubusercontent.com/45246131/72250410-9aa16f80-360c-11ea-9ee0-b407b97e22fd.png">
</p>


When user clicks on the “Add” button, the selected number of products 
will be added to the order and displayed in the lower panel as shown:

<img width="642" alt="Screenshot 2020-01-12 at 5 11 58 PM" src="https://user-images.githubusercontent.com/45246131/72250452-ab51e580-360c-11ea-84d9-47654e6e402a.png">


When user clicks the “Finalize” button, the order will be created and added to
the orders list of the waiter. At the same time a pop up with the total price of 
the order will be displayed to the user. And then after user clicks “OK” button, the
app will go to its initial state and continue from there.

<img width="642" alt="Screenshot 2020-01-12 at 5 14 12 PM" src="https://user-images.githubusercontent.com/45246131/72250493-c02e7900-360c-11ea-8d20-2ccc3711b4f2.png">


### Restaurant Management :

The manager of the restaurant can also use the same program in order to manage the restaurant.

<img width="644" alt="Screenshot 2020-01-13 at 5 38 07 PM" src="https://user-images.githubusercontent.com/45246131/72264295-8e78da80-362b-11ea-8634-3789c85a8088.png">


There are 3 different types of things a user can do to manage the restaurant:
 
 1. List the currently working employees
 2. Add a new cook or a waiter
 3. Calculate the expenses

There are two types of employees in this restaurant: (1) cook and (2) waiter. When user clicks the “Add Cook” button, the GUI 
will ask for the name and salary of the cook. Similarly, for the “Add Waiter” button. In that case, user only needs to enter the 
name of the waiter. The waiters do not have a base salary but instead get some percentage of the order they got from customers. 
When the user clicks the “Add” button, the cook/waiter is added to the employees. Employee’s ID starts from 1 and incremented 
whenever a new employee is added. A pop up window will be displayed to user after insertion.

<p float="left">
<img width="425" alt="Screenshot 2020-01-13 at 5 42 38 PM" src="https://user-images.githubusercontent.com/45246131/72264807-6178f780-362c-11ea-86d7-4d8c1051a6ef.png">
<img width="425" alt="Screenshot 2020-01-13 at 5 42 56 PM" src="https://user-images.githubusercontent.com/45246131/72264819-69389c00-362c-11ea-91f7-18501494b057.png">
</p>



When user clicks “List Employees” button the all employees (waiter or cook) will be displayed to the user as shown. These employees will be sorted and printed with their employee IDs.

<img width="643" alt="Screenshot 2020-01-13 at 5 48 13 PM" src="https://user-images.githubusercontent.com/45246131/72265097-f4199680-362c-11ea-86f8-cc9023a10e49.png">

Finally, when the “Calculate Expenses” button is clicked, the expenses and the revenue so far will be
calculated and displayed to the user together with the profit.

<img width="645" alt="Screenshot 2020-01-13 at 5 52 00 PM" src="https://user-images.githubusercontent.com/45246131/72265378-773aec80-362d-11ea-8ca8-a288122ef9e2.png">


### Technologies 
* Java Swing

### Author 
Emin Sadikhov   ```emin.sadikhov@ozu.edu.tr```


 
