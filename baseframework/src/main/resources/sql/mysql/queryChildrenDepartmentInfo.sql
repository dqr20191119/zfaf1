CREATE DEFINER=`root`@`localhost` FUNCTION `queryChildrenDepartmentInfo`(departmentId INT) RETURNS varchar(4000) CHARSET latin1
BEGIN

DECLARE sTemp VARCHAR (4000);


DECLARE sTempChd VARCHAR (4000);


SET sTemp = '$';


SET sTempChd = cast(departmentId AS CHAR);


WHILE sTempChd IS NOT NULL DO

SET sTemp = CONCAT(sTemp, ',', sTempChd);

SELECT
	group_concat(b.id) INTO sTempChd
FROM
	t_fw_department b
WHERE
	FIND_IN_SET(parent_Id, sTempChd) > 0;


END
WHILE;

RETURN sTemp;


END