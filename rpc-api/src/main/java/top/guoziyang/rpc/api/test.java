package top.guoziyang.rpc.api;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: rnzhiw
 * @Description:
 */
public class test {
    public static void main(String[] args) throws UnknownHostException {
        // 使用getLocalHost方法为InetAddress创建对象；
        InetAddress add=InetAddress.getLocalHost();//获得本机的InetAddress对象
        System.out.println(add.getHostAddress());//返回本机IP地址 192.168.56.1
        System.out.println(add.getHostName());//输出计算机名 LAPTOP-5HGMAG7H
        //根据域名得到InetAddress对象
        add=InetAddress.getByName("www.baidu.com");
        System.out.println(add.getHostAddress());//返回百度服务器的IP地址 110.242.68.3
        System.out.println(add.getHostName());//输出www.baidu.com；
        //根据ip得到InetAddress对象；
        add=InetAddress.getByName("111.13.100.91");
        System.out.println(add.getHostAddress()); //111.13.100.91
        System.out.println(add.getHostName());//如果ip地址存在，并且DNS给你解析就会输出 111.13.100.91
        //www.baidu.com，不给你解析就会返回这个IP本身；

        InetSocketAddress add1=new InetSocketAddress("192.168.56.1",9999);
        System.out.println(add1.getHostName()); //LAPTOP-5HGMAG7H
        System.out.println(add1.getPort()); //9999
        InetAddress addr=add1.getAddress();//获得端口的ip；
        System.out.println(addr.getHostAddress());//返回ip； 192.168.56.1
        System.out.println(addr.getHostName());//输出端口名； LAPTOP-5HGMAG7H

    }
}
