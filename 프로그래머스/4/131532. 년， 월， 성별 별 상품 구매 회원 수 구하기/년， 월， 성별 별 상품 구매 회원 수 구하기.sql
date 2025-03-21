-- 코드를 입력하세요
SELECT
    YEAR(SALES_DATE),
    MONTH(SALES_DATE),
    GENDER,
    COUNT(DISTINCT S.USER_ID) AS USERS
    
FROM 
    USER_INFO I
        JOIN ONLINE_SALE S USING (USER_ID)

WHERE 
    GENDER IS NOT NULL

GROUP BY
    YEAR(SALES_DATE),
    MONTH(SALES_DATE),
    GENDER

ORDER BY
    YEAR(SALES_DATE),
    MONTH(SALES_DATE),
    GENDER