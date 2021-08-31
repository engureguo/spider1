package com.engure.mm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

public class BloomFilterTest {

    @Test
    public void mmm() {

        int size = 1000_000;
        //int size = 127;
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),
                size, 0.03);

        for (int i = 0; i < size; i++) {
            bloomFilter.put(i + "");
        }

        //测试在集合中的元素：不存在的可能性
        for (int i = 0; i < size; i++) {
            boolean exist = bloomFilter.mightContain(i + "");
            if (!exist) {
                System.out.println("发现漏网之鱼！！！" + i);//永远不会执行，因为如果判断不在那么一定不在
            }
        }

        //测试不存在集合中的元素：误判为可能存在的可能性
        int mistake = 0;
        for (int i = size; i < size * 2; i++) {
            boolean exist = bloomFilter.mightContain(i + "");
            if (exist) {
                mistake++;
                //System.out.println("哦吼~误判了！！！" + i);
            }
        }

        System.out.println("误判率：" + (mistake * 1.0) / size);// 0.030094
    }

}
