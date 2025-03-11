SELECT
    C.ID,
    C.GENOTYPE,
    P.GENOTYPE AS PARENT_GENOTYPE 
FROM
    ECOLI_DATA AS C 
INNER JOIN
    ECOLI_DATA AS P ON C.PARENT_ID = P.ID
WHERE  1=1 
       AND C.GENOTYPE & P.GENOTYPE = P.GENOTYPE
ORDER BY
    ID