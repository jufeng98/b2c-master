/**
 * Created by yudong on 2018/5/11 0011.
 */

var Javamaster = {};
Javamaster.Validation = {
    input: null,
    validateInput: function (inputValidate) {
        this.input = inputValidate;
        if (inputValidate.attr("data-pattern") === undefined) {
            return null;
        }
        var reg = new RegExp(inputValidate.attr("data-pattern"));
        if (inputValidate.val() === inputValidate.attr("data-placeholder")) {
            this.input.hasError = true;
            return this.input;
        }
        if (!reg.test(inputValidate.val())) {
            this.input.hasError = true;
        }
        return this.input;

    },
    inputList: $([]),
    validateForm: function (formValidate) {
        var form = formValidate[0];
        for (var i = 0, len = form.elements.length; i < len; i++) {
            var field = form.elements[i];
            switch (field.type) {
                case  "text":
                case "password":
                    var input = this.validateInput($(field));
                    if (input != null) {
                        this.inputList.push(input);
                    }
                    break;
                case "select-one":
                case "select-multiple":
                case undefined: //字段集
                case "file": //文件输入
                case "submit": //提交按钮
                case "reset": //重置按钮
                case "button": //自定义按钮
                case "radio": //单选按钮
                case "checkbox": //复选框
                /* 执行默认操作 */
                default:
            }
        }
        return this.inputList;
    }
};
Javamaster.formUtils = {
    getSubmitObjs: function (form) {
        var parts = [],
            jsonObj = {},
            xmlDom = $.parseXML("<page></page>"),
            field = null,
            i,
            len,
            j,
            optLen,
            option,
            optValue;
        for (i = 0, len = form[0].elements.length; i < len; i++) {
            field = form[0].elements[i];
            switch (field.type) {
                case "select-one":
                case "select-multiple":
                    if (field.name.length) {
                        for (j = 0, optLen = field.options.length; j < optLen; j++) {
                            option = field.options[j];
                            if (option.selected) {
                                optValue = "";
                                if (option.hasAttribute) {
                                    optValue = (option.hasAttribute("value") ?
                                        option.value : option.text);
                                } else {
                                    optValue = (option.attributes["value"].specified ?
                                        option.value : option.text);
                                }
                                parts.push(encodeURIComponent(field.name) + "="
                                    + encodeURIComponent(optValue));
                                jsonObj[field.name] = optValue;
                                var ele = xmlDom.createElement(field.name);
                                ele.textContent = optValue;
                                xmlDom.documentElement.appendChild(ele);

                            }
                        }
                    }
                    break;
                case undefined: //字段集
                case "file": //文件输入
                case "submit": //提交按钮
                case "reset": //重置按钮
                case "button": //自定义按钮
                    break;
                case "radio": //单选按钮
                case "checkbox": //复选框
                    if (!field.checked) {
                        break;
                    }
                /* 执行默认操作 */
                default:
                    //不包含没有名字的表单字段
                    if (field.name.length) {
                        parts.push(encodeURIComponent(field.name) + "="
                            + encodeURIComponent(field.value));
                        jsonObj[field.name] = field.value;
                        var ele = xmlDom.createElement(field.name);
                        ele.textContent = field.value;
                        xmlDom.documentElement.appendChild(ele);
                    }
            }
        }
        return {
            formUrlEncodeds: parts,
            jsonObj: jsonObj,
            xmlDom: xmlDom
        };
    }
};
Javamaster.SerializeUtils = {
    toJSONString: function (jsonObj) {
        return JSON.stringify(jsonObj);
    },
    toXMLString: function (xmlDom) {
        if (typeof XMLSerializer != "undefined") {
            return (new XMLSerializer()).serializeToString(xmlDom);
        } else if (typeof xmlDom.xml != "undefined") {
            return xmlDom.xml;
        } else {
            throw new Error("Could not serialize XML DOM.");
        }
    },
    toFormUrlEncodedStr: function (parts) {
        return parts.join("&");
    }
};
Javamaster.Constant = {
    HttpContentType: {
        JSON: "application/json",
        XML: "application/xml",
        FORM: "application/x-www-form-urlencoded; charset=UTF-8"
    }
};
Javamaster.AjaxUtils = {
    postJSON: function (url, jsonObj, completeFunc, async) {
        $.ajax({
            type: "POST",
            url: url,
            dataType: Javamaster.Constant.HttpContentType.JSON,
            contentType: Javamaster.Constant.HttpContentType.JSON,
            data: JSON.stringify(jsonObj),
            async: async || true,
            complete: completeFunc
        });
    }
};
