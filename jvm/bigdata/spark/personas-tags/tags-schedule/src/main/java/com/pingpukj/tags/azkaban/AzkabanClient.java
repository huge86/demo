package com.pingpukj.tags.azkaban;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pingpukj.tags.utils.SslUtils;


/**
 * @DESC:
 */
public class AzkabanClient {
    String azkabanUrl = "https://10.106.100.236:28443";
    String sessionId = "e4b7b4e2-b8a7-4d63-bd1b-71f6920e21d9";

    public static void main(String[] args) {
        AzkabanClient azkabanClient = new AzkabanClient();
        // azkabanClient.login("azkaban", "azkaban");

        // azkabanClient.createProject("tets2", "test2");

        azkabanClient.uploadProjectZip("G:\\Project\\HZProject\\data\\customerlabel\\customerlabel-job\\azkabaJob_202105241106.zip", "test");


    }

    /**
     * {"session.id" : "dcc83b44-ed09-40a1-a8dc-1d5a6c4dc92a",  "status" : "success"}
     *
     * @param username
     * @param password
     * @return
     */
    public Boolean login(String username, String password) {
        String params = "action=login&username=" + username + "&password=" + password;

        String res = SslUtils.sendPost(azkabanUrl, params);
        System.out.println(res);
        JSONObject parse = JSONObject.parseObject(res);
        if ("success".equals(parse.get("status"))) {
            sessionId = parse.getString("session.id");
            return true;
        } else {
            return false;
        }
    }

    /**
     * {"path":"manager?project=tets2","action":"redirect","status":"success"}
     *
     * @param name
     * @param desc
     * @return
     */
    public Boolean createProject(String name, String desc) {
        String params = "action=create&name=" + name + "&description=" + desc + "&session.id=" + sessionId;
        String res = SslUtils.sendPost(azkabanUrl + "/manager", params);
        System.out.println(res);

        JSONObject parse = JSONObject.parseObject(res);
        if ("success".equals(parse.get("status"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param filePath
     * @param projectName
     * @return
     */
    public Boolean uploadProjectZip(String filePath, String projectName) {
        login("azkaban", "azkaban");
        String params = "file=" + filePath + "&project=" + projectName + "&session.id=" + sessionId + "&ajax=upload";
        String res = SslUtils.sendPost(azkabanUrl + "/manager", params);
        System.out.println(res);

        JSONObject parse = JSONObject.parseObject(res);
        if ("success".equals(parse.get("status"))) {
            return true;
        } else {
            return false;
        }
    }

}
