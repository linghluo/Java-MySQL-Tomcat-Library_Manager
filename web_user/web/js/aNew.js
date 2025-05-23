function submitForm() {
    var tag0 = checkPassword();
    if (tag0) {
        var tag = confirm("确认提交吗？");
        if (tag) {
            alert("提交信息成功！");
            return true;
        } else {
            alert("提交信息失败！");
            return false;
        }
    } else {
        alert("请检查输入信息！");
        return false;
    }
}
function checkPassword1() {
    var passwordReg = /^[0-9A-Za-z]{6,12}$/;
    var password = document.getElementById("password1").value;
    var tip = document.getElementById("tip1");
    if (!passwordReg.test(password)) {
        tip.innerHTML = "密码格式不正确！";
        return false;
    } else {
        tip.innerHTML = "";
        return true;
    }
}
function checkPassword2() {
    var passwordReg = /^[0-9A-Za-z]{6,12}$/;
    var password = document.getElementById("password2").value;
    var tip = document.getElementById("tip2");
    if (!passwordReg.test(password)) {
        tip.innerHTML = "密码格式不正确！";
        return false;
    } else {
        tip.innerHTML = "";
        return true;
    }
}
function checkPassword3() {
    var passwordReg = /^[0-9A-Za-z]{6,12}$/;
    var password = document.getElementById("password3").value;
    var tip = document.getElementById("tip3");
    if (!passwordReg.test(password)) {
        tip.innerHTML = "密码格式不正确！";
        return false;
    } else {
        tip.innerHTML = "";
        return true;
    }
}