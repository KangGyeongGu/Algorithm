-- 코드를 작성해주세요
SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO I
WHERE NOT EXISTS (SELECT PARENT_ITEM_ID 
                  FROM ITEM_TREE T
                  WHERE I.ITEM_ID = T.PARENT_ITEM_ID
                 )
ORDER BY ITEM_ID DESC;
