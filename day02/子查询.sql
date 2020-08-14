-- 11.1 查询选修了“计算机原理”的学生学号和姓名
select s.sno, s.sname
from student s
join sc on s.sno = sc.sno
join course c on sc.cno = c.cno
where cname = '计算机原理';

-- 11.2查询“周星驰”同学选修了的课程名字
select cname
from student s
join sc on s.sno = sc.sno
join course c on sc.cno = c.cno
where s.sname = '周星驰';

-- 13.
select * 
from city 
left join state on city.stateno = state.stateno
order by cityno;

-- 2. 查询哪个部⻔的平均工资是最高的，列出部⻔编码，平均工资。
-- 每个部门的平均工资
select deptno, avg(sal) MaxSal from emp GROUP BY deptno;

-- 获得上面查询结果中的最大值 - 平均工资的最大值 2916.6667
select s.deptno, max(s.MaxSal) from 
(select deptno, avg(sal) MaxSal from emp GROUP BY deptno) s;


-- 哪个部门的平均工资是 2916.6667
select s.deptno, s.MaxSal 
from
	(select deptno, avg(sal) MaxSal from emp GROUP BY deptno) s
where MaxSal = 
	(select max(s.MaxSal) from 
	(select deptno, avg(sal) MaxSal from emp GROUP BY deptno) s);




-- 3. 列出各个部⻔中工资最高的员工的信息: 名字、部⻔号、工资。
-- 每个部门最高工资
select deptno, max(sal) maxSal from emp group by deptno;

-- 
select ename, deptno, sal from emp
where (deptno, sal) in 
	(select deptno, max(sal) maxSal from emp group by deptno);


-- 4. 查询管理者是“KING”的员工姓名(ename)和工资(sal)。
-- king的员工号
select empno from emp where ename = 'KING';

select ename, sal from emp 
where mgr = (select empno from emp where ename = 'KING');


-- 5. 查询部⻔所在地(loc)为“NEWYORK”的部⻔的员工姓名、部⻔名称和岗位名称。
-- NEWYORK部门号
select deptno from dept where loc = 'NEW YORK';

-- select 子句中加子查询，可以取代表连接
select ename, job, 
	(select dname from dept d where d.deptno = e.deptno) 
from emp e
where deptno = (select deptno from dept where loc = 'NEW YORK');


-- 6. 查询工资比公司平均工资高的所有员工的员工号，姓名和工资。
-- 公司的平均工资
select avg(sal) from emp;

select empno, ename, sal from emp 
where sal > (select avg(sal) from emp);


-- 7. 查询姓名中包含字母“u”的员工在相同部⻔的员工的员工号和姓名。
-- 包含字母“u”的员工的部门号
select distinct deptno from emp where ename like '%u%';

select empno, ename from emp 
where deptno in (select distinct deptno from emp where ename like '%u%');

-- 8. 查询哪些员工的薪水比本部⻔的平均薪水低。
-- 每个部门平均工资
select deptno, avg(sal) avgsal from emp GROUP BY deptno;

select ename, sal from emp e 
join (select deptno, avg(sal) avgsal from emp GROUP BY deptno) d
on e.deptno = d.deptno where e.sal < d.avgsal;


-- 9. SALES部⻔有哪些职位?
-- SALES部门号
select deptno from dept where dname = 'SALES';

select distinct job from emp where deptno = (select deptno from dept where dname = 'SALES');

-- 10. 哪些人不是别人的经理?
-- 查经理的编号
select distinct mgr from emp;

select ename from emp where empno not in (select distinct ifnull(mgr,0) from emp);

-- 11. 谁的薪水比FORD高?如果有多个同名，比任何一个叫FORD的人高就行
-- 查FORD薪水
select sal from emp where ename = 'FORD';

-- sal > any (1,2,3,4);
-- sal > all (1,2,3,4);

select ename from emp where sal > any (select sal from emp where ename = 'FORD');

-- 12. 谁和FORD同部⻔?列出除了FORD之外的员工名字
-- FORD部门号
select deptno from emp where ename = 'FORD';

select ename from emp 
where deptno = (select deptno from emp where ename = 'FORD')
and ename <> 'FORD';

-- 13. 哪个部⻔的人数比部⻔20的人数多
-- 部门20的人数
select count(*) from emp WHERE deptno = 20;

-- 每个部门的人数
select count(*) num, deptno from emp 
group by deptno 
having num > (select count(*) from emp WHERE deptno = 20);


-- 14. 列出员工名字和职位，查询员工所在的部⻔平均薪水大于2000元的员工信息
-- 平均薪水大于2000的部门
select deptno from emp group by deptno having avg(sal) > 2000;

select ename, job from emp 
where deptno in (select deptno from emp group by deptno having avg(sal) > 2000);

-- 16.找出EMP中那些工资高于他们所在部⻔普通员工(不包含管理者)平均工资的员工。
-- 查询工资高于部门平均工资的员工，部门划分按照管理者来
-- 查询每个部门的平均工资
select avg(sal) avgsal, mgr from emp group by mgr;






