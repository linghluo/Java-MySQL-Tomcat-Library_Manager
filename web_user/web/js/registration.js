function submitForm() {
    var tag0 = checkPassword();
    if (tag0) {
        var tag = confirm("确认注册吗？");
        if (tag) {
            alert("提交注册信息成功！");
            return true;
        } else {
            alert("提交注册信息失败！");
            return false;
        }
    } else {
        alert("请检查输入信息！");
        return false;
    }
}
function checkPassword() {
    var passwordReg = /^[0-9A-Za-z]{6,12}$/;
    var password = document.getElementById("password").value;
    var tip = document.getElementById("tip");
    if (!passwordReg.test(password)) {
        tip.innerHTML = "密码格式不正确！";
        return false;
    } else {
        tip.innerHTML = "";
        return true;
    }
}