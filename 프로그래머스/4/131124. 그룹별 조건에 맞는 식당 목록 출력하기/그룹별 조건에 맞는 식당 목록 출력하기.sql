SELECT 
    MP.MEMBER_NAME,
    RE.REVIEW_TEXT,
    DATE_FORMAT(RE.REVIEW_DATE, '%Y-%m-%d')
    
FROM MEMBER_PROFILE MP
    JOIN REST_REVIEW RE USING (MEMBER_ID)
    
WHERE 
    MP.MEMBER_ID IN (
        -- 가장 많은 리뷰를 작성한 회원 ID 찾기
        SELECT MEMBER_ID
        FROM REST_REVIEW
        GROUP BY MEMBER_ID
        HAVING COUNT(*) = (
            -- 최대 리뷰 개수 찾기
            SELECT MAX(REVIEW_COUNT)
            FROM (
                SELECT MEMBER_ID, COUNT(*) AS REVIEW_COUNT
                FROM REST_REVIEW
                GROUP BY MEMBER_ID
            ) AS REVIEW_STATS
        )
    )
ORDER BY 
    RE.REVIEW_DATE ASC,
    RE.REVIEW_TEXT ASC;