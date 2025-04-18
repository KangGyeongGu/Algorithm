

WITH RECURSIVE ECOLI_GENERATION (ID, PARENT_ID, GENERATION) AS (
    SELECT ID, PARENT_ID, 1
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT E.ID, E.PARENT_ID, G.GENERATION+1
    FROM ECOLI_DATA E
        JOIN ECOLI_GENERATION G ON E.PARENT_ID = G.ID
)

SELECT ID

FROM ECOLI_GENERATION

WHERE GENERATION = 3

ORDER BY ID ASC;



