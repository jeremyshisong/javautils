package com.core.nlp.dictionary;

import com.core.nlp.dao.GoodsDao;
import com.core.nlp.entity.Item;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by shisong on 17/4/19.
 */
public class TestTrieDict {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
            "classpath:spring-context.xml");

    @Test
    public void testJieba() {
        TrieDict dict = new TrieDict();
        dict.loadDict("/cate.dict");
        String sentence1 = "坚果（JmGO）J6 家用 全高清 投影机 （DLP芯片 1000ANSI流明 1080P分辨率 左右梯形校正 手机同屏";
        String sentence2 = "宝驹悦 真皮汽车方向盘套 整张牛皮 大众别克日产丰田奥迪本田哈弗斯柯达现代福特宝马车把套 黑色 别克昂科威全新英朗君越君威凯越昂科拉威朗";
        System.out.println(dict.segments(sentence2));
    }

    @Test
    public void testA() {
        TrieDict dict = new TrieDict();
        dict.loadDict("/cate.dict");

        GoodsDao goodsDao = ctx.getBean("goodsDao", GoodsDao.class);
        boolean hasMore = true;
        int start = 0;
        int limit = 1000;
        double rights = 0;
        int records = 0;
        while (hasMore) {
            List<Item> goods = goodsDao.selectAllItems(start, limit);

            start += limit;

            rights += check(dict, goods);
            records += goods.size();

            hasMore = goods.size() > 0;
        }

        double radio = rights / records;

        System.out.println("radio=" + radio);
    }

    private double check(TrieDict dict, List<Item> items) {
        if (items == null || items.size() == 0) {
            return 0;
        }

        double ret = 0;
        int level1 = 0;
        int level2 = 1;
        int level3 = 2;
        for (Item item : items) {
            String sentence = item.getName();
            String cate = item.getCategory();

            List<String> segments = dict.segments(sentence);

            String[] cates;
            try {
                cates = cate.split(",");

                if (cates.length > 0) {
                    if (cates.length > level3 && segments.contains(cates[level3])) {
                        ret += 1;
                    } else if (cates.length > level2 && segments.contains(cates[level2])) {
                        ret += 0.5;
                    } else if (cates.length > level1 && segments.contains(cates[level1])) {
                        ret += 0.2;
                    }
                }
            } catch (Exception e) {
                System.out.println("cates error" + e.getMessage());
            }
        }

        return ret;
    }
}
