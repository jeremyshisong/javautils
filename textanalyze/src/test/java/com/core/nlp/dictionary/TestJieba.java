package com.core.nlp.dictionary;

import com.huaban.analysis.jieba.JiebaSegmenter;
import org.junit.Test;

/**
 * Created by shisong on 17/4/19.
 */
public class TestJieba {
    @Test
    public void testJieba() {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        String[] sentences =
                new String[]{"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "坚果（JmGO）J6 家用 全高清 投影机 （DLP芯片 1000ANSI流明 1080P分辨率 左右梯形校正 手机同屏）", "雷猴回归人间。",
                        "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};

        String sentence = "坚果（JmGO）J6 家用 全高清 投影机 （DLP芯片 1000ANSI流明 1080P分辨率 左右梯形校正 手机同屏";
        System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.SEARCH).toString());
        System.out.println(segmenter.sentenceProcess(sentence));

    }
}
