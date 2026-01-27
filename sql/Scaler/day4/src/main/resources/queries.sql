-- Question: Q7
--------------------------------------------------------
-- Problem Statement:

-- In the context of managing data for an Olympic event, you are tasked with sorting a table of countries based on their medal counts.
-- The goal is to determine the ranking of countries by their performance in the Olympics, prioritizing gold, silver, and bronze medals, respectively.

-- Write an SQL query that sorts the olympic table according to the following rules:

-- The country with more gold medals should come first.
-- If there is a tie in the no. of gold medals, the country with more silver medals should come first.
-- If there is a tie in the no. of silver medals, the country with more bronze medals should come first.
-- If there is a tie in the no. of bronze medals as well, then the countries with the tie are sorted in ascending order lexicographically.
-- Table: olympic
-- Schema

-- Table Olympic {
--     country VARCHAR(255),
--     gold INT,
--     silver INT,
--     bronze INT
-- }

select
    country, gold, silver, bronze
from
    Olympic
order by
    gold desc,
    silver desc,
    bronze desc,
    country

-- Question: Q8
--------------------------------------------------------

-- Write a SQL query that retrieves and displays all movie titles in descending order of popularity.
-- In cases where movies have the same level of popularity, prioritize them based on reven
select
    title
from
    movies
order by popularity desc, revenue desc
