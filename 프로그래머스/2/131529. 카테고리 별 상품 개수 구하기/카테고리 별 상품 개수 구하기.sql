-- 코드를 입력하세요
SELECT substring(PRODUCT_CODE, 1, 2) as CATEGORY, count(PRODUCT_CODE)as PRODUCTS
FROM PRODUCT
GROUP BY substring(PRODUCT_CODE, 1, 2)
ORDER BY PRODUCT_CODE;