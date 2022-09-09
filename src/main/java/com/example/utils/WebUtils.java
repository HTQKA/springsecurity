package com.example.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springsecurity
 * @ClassName WebUtis
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 15:19
 * @Version 1.0
 **/
public class WebUtils {

    public static String renderString(HttpServletResponse response, String str) {
        try { response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
