--Question: Q1
--------------------------------

-- Display the titles of the movies that are released (i.e., release_year) after 2014 and have an average vote rating (i.e.,vote_average) greater than 7.

-- Result:

-- Return the column 'original_title'.
-- Return the result ordered by original_title in ascending order.
select
    original_title
from
    movies
where
    release_year > 2014 and vote_average > 7
order by
    original_title

-- Question: Q2
--------------------------------

-- A country is big if:

-- a) it has an area of at least three million (i.e., 3000000 km2), or
-- b) it has a population of at least twenty-five million (i.e., 25000000).

-- Write a query to report the name, population, and area of the big countries in world table.

-- Result:

-- Return the result table ordered by name column in ascending order.
select
    `name`,
    population,
    area
from
    world
where
    area >= 3000000 or population >= 25000000
order by `name`

-- Question: Q3
--------------------------------

-- Write a query to find the orderNumber and requiredDate of the orders which were ordered in 2003 December and the status is "Shipped".

-- Result:

-- 1. Use the orderDate column to check for the orders that are ordered in 2003 December.
-- 2. Return the result ordered by orderNumber in ascending order.
select
    orderNumber,
    requiredDate
from
    orders
where
    year(orderDate) = 2003 and month(orderDate) = 12 and status = "Shipped"
order by
    orderNumber

-- Question: Q4
--------------------------------

-- Write a query to return a list of all employees with selected last names: King, Taylor, and Grant from the employee's table.

-- Result:

-- 1. Return all the fields.
-- 2. Return the result ordered by employee_id in ascending order.
select
    *
from
    employees
where
    lower(last_name) IN ("king", "taylor", "grant")
order by
    employee_id

-- Question: Q5
--------------------------------

-- Write a query to list down all the movies along with their details that have keywords like 'sport' or 'sequel' or 'suspense'.

-- Result:

-- 1. Return the columns 'original_title', 'director', 'genres', 'cast', 'budget', 'revenue', 'runtime', and 'vote_average'.
-- 2. Return the columns ordered by original_title in ascending order.
select
    original_title,
    director,
    genres,
    cast,
    budget,
    revenue,
    runtime,
    vote_average
from
    movies
where
    lower(genres) like '%sport%'
    or lower(genres) like '%sequel%'
    or lower(genres) like '%suspense%'
order by
    original_title