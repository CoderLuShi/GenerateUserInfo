package dao;

import domain.AreaCode;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AreaInfo {
    /**
     * 返回一个地址信息，返回值为AreaCode对象
     */
    public AreaCode getArea() {
        BaseDao baseDao = new BaseDao();
        String sql = "SELECT\r\n" +
                "	province.`name` AS province,\r\n" +
                "	city.`name` AS city,\r\n" +
                "	country.`name` AS country,\r\n" +
                "	town.`name` AS town,\r\n" +
                "	village.`name` AS village, \r\n" +
                "	country.`code` AS countryCode \r\n" +
                "   FROM\r\n" +
                "	area_code province\r\n" +
                "	JOIN area_code city ON province.`code` = city.pcode\r\n" +
                "	JOIN area_code country ON city.`code` = country.pcode\r\n" +
                "	JOIN area_code town ON country.`code` = town.pcode\r\n" +
                "	JOIN village village ON town.`code` = village.pcode \r\n" +
                "	where village.code=(select code from village where id=?)";
        //where village.code=(select code from village where id=?)
        //通过village表的id(便于随机查询出一个村庄)查询出village的code，然后查询出村庄所在的省/市/县/镇，查询较快（大约0.02S,
        // 避免了使用ORDER BY RAND()查询时间较长的问题，每次生成一个选它就对了！）
        Object object = baseDao.query(sql, new BeanHandler(AreaCode.class), new Random().nextInt(633980));
        if (object != null && object instanceof AreaCode) {
            AreaCode area = (AreaCode) object;
            return area;
        } else {
            System.out.println("查询结果为空" + object);
            return null;
        }

    }

    /**
     * 根据查询参数生成指定数量的地址信息，返回值为AreaCode类型的List集合
     */
    public List<AreaCode> getAreas(int num) {
        BaseDao baseDao = new BaseDao();
        String sql = "SELECT\r\n" +
                "	province.`name` AS province,\r\n" +
                "	city.`name` AS city,\r\n" +
                "	country.`name` AS country,\r\n" +
                "	town.`name` AS town,\r\n" +
                "	village.`name` AS village, \r\n" +
                "	country.`code` AS countryCode \r\n" +
                "   FROM\r\n" +
                "	area_code province\r\n" +
                "	JOIN area_code city ON province.`code` = city.pcode\r\n" +
                "	JOIN area_code country ON city.`code` = country.pcode\r\n" +
                "	JOIN area_code town ON country.`code` = town.pcode\r\n" +
                "	JOIN village village ON town.`code` = village.pcode \r\n" +
                "	ORDER BY RAND() LIMIT ?";
                //使用ORDER BY RAND() LIMIT ? 查询时间较长2S左右，非批量生成建议不要使用
        Object object = baseDao.query(sql, new BeanListHandler(AreaCode.class), num);
        if (object != null && object instanceof List) {
            ArrayList<AreaCode> areas = (ArrayList<AreaCode>) object;
            return areas;
        } else {
            System.out.println("查询结果为空" + object);
            return null;
        }
    }
}