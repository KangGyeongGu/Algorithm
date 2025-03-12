SELECT 
    FI.ID,
    FNI.FISH_NAME,
    FI.LENGTH
    
FROM FISH_INFO FI
    JOIN FISH_NAME_INFO FNI ON FI.FISH_TYPE = FNI.FISH_TYPE
    
WHERE 
    FI.LENGTH = (
        SELECT MAX(FI2.LENGTH)
        FROM FISH_INFO FI2
        WHERE FI.FISH_TYPE = FI2.FISH_TYPE
        )
        
ORDER BY ID