import dao.AreaInfo;
import domain.AreaCode;
import domain.Person;
import service.IdCardGenerator;
import service.PersonInfo;

public class Test {
    public static void main(String[] args) {
        PersonInfo personInfo = new PersonInfo();
        AreaInfo areaInfo = new AreaInfo();
        IdCardGenerator idCardGenerator = new IdCardGenerator();
        //一次生成一个循环生成，此生成方法会使用大量SQL
        for (int i = 0; i < 1000; i++) {
            try {
                //由于循环过快导致SQL查询过多导致数据库连接池等待超时，每次查询大约40毫秒，为避免异常程序中断此处休眠80毫秒
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AreaCode area = areaInfo.getArea();
            Person person = personInfo.getPerson();
            String generate = idCardGenerator.generate(person,area);
            System.out.println(person.toString());
            System.out.println(area.toString());
            System.out.println(generate);
        }
        //一次生成指定数量只用一次SQL
//        List<AreaCode> areas = areaInfo.getAreas(100000);
//        List<Person> persons = randInfo.getPersons(100000);
//        for (int i = 0; i < persons.size(); i++) {
//            AreaCode area = areas.get(i);
//            Person person = persons.get(i);
//            String generate = idCardGenerator.generate(person,area);
//            System.out.println(person.toString());
//            System.out.println(area.toString());
//            System.out.println(generate);
//        }
    }
}
