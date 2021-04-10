import org.junit.*;

public class Mytest {

    private jsq j;

    @BeforeClass
    public static void BC(){
        System.out.println("beforeclass");
    }
    @Before
    public void setup(){
        System.out.println("before");
        j=new jsq();

    }
    @After
    public void teardown(){
        System.out.println("after");
    }
    @AfterClass
    public static void ac(){
        System.out.println("AfterClass");
    }
    @Test
    public void add(){
        System.out.println("add测试");
       Assert.assertEquals(3,j.add(1,2));
    }
    @Test
    public void del(){
        System.out.println("del");
        Assert.assertEquals(1,j.del(2,1));
    }



}
