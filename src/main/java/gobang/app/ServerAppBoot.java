package gobang.app;

import com.google.gson.Gson;
import gobang.pojo.dto.R;
import gobang.pojo.entity.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerAppBoot {

    public static void main(String[] args) {
        log.info("{}服务器启动成功", "gobang小水管本地only");

        R r = new R(Function.START, "服务器启动成功");
        String json = new Gson().toJson(r); // 将R对象转换为JSON格式
        System.out.println(json);
    }

}
