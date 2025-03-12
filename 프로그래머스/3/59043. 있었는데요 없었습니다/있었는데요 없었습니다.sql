-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS I
WHERE DATETIME > (SELECT DATETIME
                  FROM ANIMAL_OUTS O
                  WHERE I.ANIMAL_ID = O.ANIMAL_ID)
ORDER BY DATETIME ASC;