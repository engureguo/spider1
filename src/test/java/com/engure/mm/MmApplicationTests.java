package com.engure.mm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

//@SpringBootTest
class MmApplicationTests {

    @Test
    void contextLoads() {

        String url = "https://img.mm618.com/photos/240631/09a01.jpg";
        int start = url.indexOf("photos");
        String[] imgInfo = url.substring(start).split("/");
        System.out.println(url + " ---> " + Arrays.toString(imgInfo));

    }

    @Test
    public void t1() throws Exception {

        //Class<BaiduBaike> clazz = BaiduBaike.class;
        //BaiduBaike baike = clazz.newInstance();
        //BaiduBaike.main(null);
        //GithubRepo.main(null);
        //GithubRepoApi.main(null);
        //OschinaBlog.main(null);
        //MonitorExample.main(null);


        System.out.println(Integer.MAX_VALUE);
        // 0X7fff-fff

    }

}
