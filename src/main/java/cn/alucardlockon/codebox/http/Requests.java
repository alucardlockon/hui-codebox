package cn.alucardlockon.codebox.http;

public class Requests {

    public enum HttpMethods {
        Get("get"),
        Post("post"),
        Put("put"),
        Delete("delete");

        private final String method;

        HttpMethods(String method) {
            this.method = method;
        }

        String getValue() {
            return this.method;
        }
    }

}
