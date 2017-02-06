package interfaces.webpage;

import java.util.Map;

/**
 * Created by apple on 2016/12/30.
 */
public interface WebpageInterface {
    //上一页、下一页的页码计算
    public Map<String,Integer> outputPageNums(int jump_type, int cur_page, int nums_one_page, int change_page, int all_nums);
}
