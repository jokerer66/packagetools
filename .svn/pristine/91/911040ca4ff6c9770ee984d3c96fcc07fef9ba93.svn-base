package process.webpage;

import helper.log.MyLogTest;
import interfaces.webpage.WebpageInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2016/12/30.
 */
public class Webpage implements WebpageInterface {


    MyLogTest log ;
    private static Webpage webpage;

    public static Webpage getInstance() {
        if (webpage == null) {
            synchronized (Webpage.class) {
                if (webpage == null) {
                    webpage = new Webpage();
                }
            }
        }
        return webpage;
    }

    public Webpage() {
        log = MyLogTest.getInstance();
    }

    /**
     *
     * @param jump_type  跳转类型，0表示跳转首页，1表示跳转尾页，2表示向前跳一页，3表示向后跳一页.cur_page 表示当前页码
     * @param cur_page
     * @param nums_one_page  一页的显示数量
     * @param change_page  跳转页面
     * @param all_nums  总数
     * @return
     */
    @Override
    //上一页、下一页的页码计算
    public Map<String,Integer> outputPageNums(int jump_type,int cur_page,int nums_one_page,int change_page,int all_nums){

        Map<String,Integer> page_nums = new HashMap<String, Integer>();

        int limit ;
        int offset ;
        //多余的一页数据数量
        int yushu_nums = all_nums % nums_one_page;
        int page_num ;
        int new_cur_page ;

        if(yushu_nums >=1){
            page_num = all_nums / nums_one_page+1;
        }else {
            page_num = all_nums / nums_one_page;
        }

        //对跳页的页数处理规则
        if(jump_type == 0){
            limit = 0;
            offset = nums_one_page;
            new_cur_page = 1;

        }else if(jump_type == 1){
            limit = (page_num-1)*nums_one_page;
            offset = yushu_nums;
            new_cur_page = page_num;
        }else if(jump_type == 2){

            if(cur_page > 1) {
                limit = (cur_page - 2)*nums_one_page;
                offset = nums_one_page;
                new_cur_page = cur_page - 1;
            }else{
                limit = (cur_page-1)*nums_one_page;
                offset = nums_one_page;
                new_cur_page = cur_page;
            }
        }else if(jump_type == 4 && change_page <= page_num && change_page >= 1){

            limit = (change_page-1)*nums_one_page;
            offset = nums_one_page;
            new_cur_page = change_page;

        } else{

            if(cur_page < page_num) {
                limit = cur_page *nums_one_page;
                offset = nums_one_page;
                new_cur_page = cur_page+1;
            }else{
                limit = (cur_page-1)*nums_one_page;
                offset = nums_one_page;
                new_cur_page = cur_page;
            }
        }

        log.level("debug","all_nums = "+all_nums+"\nall_pages = "+page_num+"\npages_yushu = "+yushu_nums
                +"\njump_type = "+jump_type+"\ncur_page = "+cur_page+"\nlimit = "+limit+"\noffset = "+offset);

        page_nums.put("page_limit", limit);
        page_nums.put("page_offset",offset);
        page_nums.put("page_cur_page",new_cur_page);
        page_nums.put("page_all_nums",all_nums);//总数量
        page_nums.put("page_yushu_nums",yushu_nums);//
        page_nums.put("page_page_num",page_num);//总页数


        return page_nums;
    }
}
