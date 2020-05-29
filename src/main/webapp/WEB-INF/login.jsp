<%@ page pageEncoding="utf-8" %>
<html>
    <head>
        <style>
            #loginBox {
                width: 350px;
                background-color: #cfc;
                border: 2px solid #ccc;
                border-radius: 10px;
                position: absolute;
                left: 50%;
                margin-left: -175px;
                padding-bottom: 20px;
                top: 100px;
            }

            #loginBox tr {
                height: 40px;
            }

            input {
                width: 200px;
                height: 30px;
            }

            button {
                width: 80px;
                height: 30px;
            }
        </style>
    </head>
    <body>
        <div id="loginBox">
            <h2 align="center">登录</h2>
            <form action="login.action">
                <table align="center">
                    <tr>
                        <td>用户名称:</td>
                        <td><input name="uname" type="text"></td>
                    </tr>
                    <tr>
                        <td>用户密码:</td>
                        <td><input name="upass" type="password"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <button>提交</button>
                        </td>
                    </tr>


                </table>
            </form>
        </div>

    </body>
</html>
