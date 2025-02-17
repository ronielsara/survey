-- Query 1: Find the Customer(s) with the Most Orders
SELECT customerNumber, COUNT(orderNumber) AS total_orders
FROM orders
GROUP BY customerNumber
HAVING total_orders = (
    SELECT MAX(order_count)
    FROM (
        SELECT customerNumber, COUNT(orderNumber) AS order_count
        FROM orders
        GROUP BY customerNumber
    ) AS order_counts
);

-- Query 2: View all "Germany" customers and their order details
SELECT c.customerNumber, c.customerName, o.orderNumber, o.status, o.orderDate
FROM customers c
JOIN orders o ON c.customerNumber = o.customerNumber
WHERE c.country = 'Germany';

-- Query 3: List all employees and their revenue amount (based on payments table)
SELECT e.employeeNumber, e.firstName, e.lastName, SUM(p.amount) AS total_revenue
FROM employees e
JOIN customers c ON e.employeeNumber = c.salesRepEmployeeNumber
JOIN payments p ON c.customerNumber = p.customerNumber
GROUP BY e.employeeNumber;

-- Query 4: List all products ordered in the last month (assuming today's date is '2005-01-01')
SELECT p.productCode, p.productName, o.orderDate
FROM products p
JOIN orderdetails od ON p.productCode = od.productCode
JOIN orders o ON od.orderNumber = o.orderNumber
WHERE o.orderDate BETWEEN '2004-12-01' AND '2004-12-31';

-- Query 5: Create a new table named employeeDetails
CREATE TABLE employeeDetails (
    bankAccount VARCHAR(50),
    address VARCHAR(255),
    phoneNumber VARCHAR(20),
    personalEmail VARCHAR(100)
);

