window.onload = function (ev) {
    var aChangeImage = document.getElementById("changeImage");
    aChangeImage .onclick = function (ev) {
        var imgKaptchaImage = document.getElementById("kaptchaImage");
        imgKaptchaImage.src = "/kaptcha/getImage";
    }
}