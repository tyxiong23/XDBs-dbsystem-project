CREATE TABLE T1(A INT, B VARCHAR(10));
INSERT INTO T1 VALUES(1,'ABECF'), (2,'BCD'), (3,'CDBD'), (4,'CDE') ;
-- 聚集查询：
SELECT COUNT (A) FROM T1;
SELECT AVG(A) FROM T1;
SELECT MAX(A) FROM T1;
SELECT MIN(A) FROM T1;
SELECT SUM(A) FROM T1;
-- 模糊查询：
SELECT * FROM T1 WHERE B LIKE '%C_';
SELECT * FROM T1 WHERE B LIKE '%B%';
SELECT * FROM T1 WHERE B LIKE '_D_';
-- 嵌套查询：
CREATE TABLE T2(A INT, B INT);
INSERT INTO T2 VALUES(1,2), (2,3), (3,3);
SELECT * FROM T2 WHERE A IN (SELECT B FROM T2);
-- 分组查询：
INSERT INTO T2 VALUES(1,3), (2,4), (1,1), (2,6);
SELECT A, SUM(B) FROM T2 GROUP BY A;
SELECT A, AVG(B) FROM T2 GROUP BY A;
-- 分页查询：
SELECT * FROM T2 LIMIT 1 OFFSET 1;

-- UNIQUE约束：
CREATE TABLE T3 (A INT);
ALTER TABLE T3 ADD UNIQUE A(A);
INSERT INTO T3 VALUES(1);
INSERT INTO T3 VALUES(1); --ERROR

-- 日期：
CREATE TABLE T4(D DATE);
INSERT INTO T4 VALUES('2023-1-7');
SELECT * FROM T4;