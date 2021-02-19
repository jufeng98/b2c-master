/**
 * Created by yu on 2018/5/11.
 */
$(document).ready(function () {
    (function (inputUserId, inputPwdPH, inputPwd) {
        var holderTxtUserId = inputUserId.attr("data-placeholder");
        var holderTxtPwd = inputPwdPH.attr("data-placeholder");
        inputUserId.focus(function () {
            if ($(this).val().indexOf(holderTxtUserId) != -1) {
                $(this).val("");
            }
        });
        inputPwdPH.focus(function () {
            if ($(this).val().indexOf(holderTxtPwd) != -1) {
                $(this).val("");
            }
            inputPwdPH.addClass("hide");
            inputPwd.removeClass("hide");
            inputPwd.focus();
        });
        inputPwd.blur(function () {
            if ($(this).val() == "") {
                inputPwdPH.removeClass("hide");
                inputPwd.addClass("hide");
                return;
            }
        });
        inputUserId.blur(function () {
            if ($(this).val() == "") {
                $(this).val(holderTxtUserId);
            }
        });
        inputPwdPH.blur(function () {
            if ($(this).val() == "") {
                $(this).val(holderTxtPwd);
            }
        });
        $("form#memberLogin").on("submit", function (e) {
            e.preventDefault();
            var inputList = Javamaster.Validation.validateForm($(this));
            var error = false;
            inputList.each(function (index, input) {
                if (input.hasError) {
                    error = true;
                    input.parent().addClass("error");
                    input.next().removeClass("hide-box");
                    return;
                }
                input.parent().removeClass("error");
                input.next().addClass("hide-box");
            });
            if (error) {
                return;
            }
            var obj = Javamaster.formUtils.getSubmitObjs($("form#memberLogin"));
            obj.jsonObj["loginType"] = 1;
            obj.jsonObj["memberType"] = 1;
            Javamaster.AjaxUtils.postJSON("/login/login", obj.jsonObj
                , function (result) {
                    var resJson = JSON.parse(result.responseText);
                    if (!resJson.success) {
                       alert(resJson.errorMsg);
                        return;
                    }
                    window.location.href = resJson.data;

                }, false);
        });
    })($("input#userId"), $("input#passWordPH"), $("input#passWord"));

    (function (tabTitle, divLogin, divGetCode) {
        tabTitle.click(function (event) {
            $(event.target).addClass("current");
            if (event.target == $(this).find(":last")[0]) {
                $(this).find(":first").removeClass("current");
                divLogin.find("div#dynamicPswBox").removeClass("hide");
                divLogin.find("div#memberBox").addClass("hide");
            } else {
                $(this).find(":last").removeClass("current");
                divLogin.find("div#dynamicPswBox").addClass("hide");
                divLogin.find("div#memberBox").removeClass("hide");
            }

            var inputDynamic = $("input#eId");
            var inputCode = $("input#vcode");
            var holderTxtDynamic = inputDynamic.attr("data-placeholder");
            var holderTxtCode = inputCode.attr("data-placeholder");
            inputDynamic.focus(function () {
                if ($(this).val().indexOf(holderTxtDynamic) != -1) {
                    $(this).val("");
                }
            });
            inputCode.focus(function () {
                if ($(this).val().indexOf(holderTxtCode) != -1) {
                    $(this).val("");
                }
            });
            inputDynamic.blur(function () {
                if ($(this).val() === "") {
                    $(this).val(holderTxtDynamic);
                }
            });
            inputDynamic.on("keyup", function () {
                if ($(this).val() === "") {
                    divGetCode.addClass("disabled");
                } else {
                    divGetCode.removeClass("disabled");
                }
            });
            divGetCode.click(function () {
                if ($(this).hasClass("disabled")) {
                    return;
                }
                if (divGetCode.find(">span:first").hasClass("hidden")) {
                    return;
                }
                var input = Javamaster.Validation.validateInput(inputDynamic);
                var divErr = $("div#dynamicPswBox").find(".help-txt:first");
                var divInput = $("div#dynamicPswBox").find(".lg-unit:first");
                if (input.hasError !== undefined) {
                    divErr.text(divErr.attr("data-errmsg"));
                    divInput.addClass("error");
                    return;
                }
                divErr.text("");
                divInput.removeClass("error");
                divGetCode.addClass("disabled");
                divGetCode.find("img.loading-img").removeClass("hide-box");
                var reqJsonObj = {
                    mobile: inputDynamic.val(),
                    debugMsgType: "2"
                };
                Javamaster.AjaxUtils.postJSON("/login/smsMessage/EUserVerifyCode", reqJsonObj
                    , function (result) {
                        divGetCode.removeClass("disabled");
                        divGetCode.find("img.loading-img").addClass("hide-box");
                        var resJsonObj = JSON.parse(result.responseText);
                        $("div#alert_msg_box").removeClass("hidden");
                        $("div#alert_msg_box").find("a.close-window,button.btn-primary").click(function () {
                            $("div#alert_msg_box").addClass("hidden");
                        });
                        $(this).addClass("disabled");
                        if (resJsonObj.success) {
                            $("div#alert_msg_box").find("div.confirm-info p").text(resJsonObj.data);
                            divGetCode.find(">span:first").addClass("hidden");
                            divGetCode.find(">span:last").removeClass("hidden");
                            var countDown = 10;
                            var interval = setInterval(function () {
                                if (countDown === -1) {
                                    clearInterval(interval);
                                    divGetCode.find(">span:first").removeClass("hidden");
                                    divGetCode.find(">span:last").addClass("hidden");
                                    countDown = 10;
                                    divGetCode.find(">span:last").find("span:first").text(countDown);
                                }
                                divGetCode.find(">span:last").find("span:first").text(countDown--);
                            }, 1000);
                        } else {
                            $("div#alert_msg_box").find("div.confirm-info p").text(resJsonObj.errorMsg);
                        }
                    }, false);

            });
            inputCode.blur(function () {
                if ($(this).val() === "") {
                    $(this).val(holderTxtCode);
                }
            });
        });

    })($("ul.tab-title"), $("div.wrap"), $("div#dyn_btn_psw"));

    (function (imgSlider) {
        var liInfoList = imgSlider.find("ul>li");
        liInfoList.bind("mouseenter", function () {
            $(this).find(".ad-info").show().stop().animate({
                bottom: "0px"
            })
        });
        liInfoList.bind("mouseleave", function () {
            $(this).find(".ad-info").show().stop().animate({
                bottom: "-30px"
            })
        });
    })($("nav#ad-slider"));
});