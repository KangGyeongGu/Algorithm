-- 코드를 입력하세요
SELECT
    A.AUTHOR_ID,
    A.AUTHOR_NAME,
    B.CATEGORY,
    SUM(S.SALES * B.PRICE) AS TOTAL_SALES
FROM 
    BOOK B
        JOIN AUTHOR A ON A.AUTHOR_ID = B.AUTHOR_ID 
        JOIN BOOK_SALES S ON S.BOOK_ID = B.BOOK_ID
WHERE
    SALES_DATE LIKE '2022-01%'
GROUP BY
    A.AUTHOR_ID, B.CATEGORY
ORDER BY
    A.AUTHOR_ID ASC,
    B.CATEGORY DESC;
   
   
