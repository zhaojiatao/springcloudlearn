package zjt.learn.service.impl;

/**
 * 功能：
 * 模拟两个比较重的外部方法
 * @Author: zhaojiatao
 * @Date: 2022/5/16 13:54
 * @ClassName: StubbingService
 */
public class StubbingService {

    public int getI(){
        System.out.println("=============getI============");
        return 10;
    }

    public String getS(){
        System.out.println("=============getS============");
        throw new RuntimeException();
    }

}
