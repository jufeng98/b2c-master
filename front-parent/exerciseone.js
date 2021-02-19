/**
 * Created by yudong on 2016/9/24 0024.
 */
function $(param){
    var firstChar=param.charAt(0);
    var ele=param.substring(1,param.length);
    if(firstChar==="."){
        return document.getElementsByClassName(ele);
    }else if(firstChar==="#"){
        return document.getElementById(ele);
    }else{
        document.getElementsByTagName(ele);
    }
}
$("#testPID").onmouseover=function(){
   this.style.border="2px solid green";
};
$("#testPID").onmouseout=function(){
   this.style.border="2px solid blue";
};
function Lecture(name,teacher){
    this.name=name;
    this.teacher=teacher;
}
Lecture.prototype.display=function(){
    return this.name+" "+this.teacher;
}

function Schedule(lectures){
    this.lectures=lectures;
}
Schedule.prototype.display=function(){
    var str="";
    for(var i=0;i<this.lectures.length;i++){
        str+=this.lectures[i].display()+" ";
    }
    return str;
}

var mySchedule=new Schedule([
    new Lecture("joke","smith"),
    new Lecture("rose","tom"),
    new Lecture("micke","gates")
]);
console.log(mySchedule.display());

function sendMessage(obj1,obj2){
    if(arguments.length===2){
       return  "the parameters are two";
    }else{
        return "the parameters are one";
    }
}
console.log(sendMessage(1,2));
console.log(sendMessage(1));

function makeArray(){
    var tmp=[];
    for(var i=0;i<arguments.length;i++){
        tmp.push(arguments[i]);
    }
    return tmp;
}

function displayError(msg){
    if(typeof msg=="undefined"){
        msg="an error occured";
        return msg;
    }else{
        return msg;
    }
}
console.log(displayError());
console.log(displayError("hello world"));

function checkType(para){
    console.log(typeof para);
    var cons=para.constructor.toString();
    var start=cons.indexOf(" ")+1;
    var end=cons.indexOf("(");
    console.log(cons.substring(start,end));
}
checkType("1");
checkType(1);
checkType(new Object());
checkType([]);

var foo="test";
var foo="new test";
console.log(foo);

(function hideElement(){
    var testPID=document.getElementById("testPID");
    testPID.style.border="2px solid red";
    setTimeout(function(){testPID.style.display="none";},60000);
})();

(function(){
var msg="闭包内的变量";
    window.onload=function(){
        console.log(msg);
    }
})();

var obj=new Object();
obj.val=19999;
obj.show=function(){
    return this.val;
}
console.log(obj.show());
var objEqual={
    val:19999,
    show:function(){
        return this.val;
    }
};
console.log(objEqual.show());

function User(name){
    this.name=name;
}
var me=new User("Jake");
console.log(me.name);
User("jake kakkaka");
console.log(window.name);
var you=new me.constructor("Tom");
console.log(you.name);

function Student(name,age){
    this.name=name;
    this.age=age;
    function disc(){
        console.log(name+"|||||"+age);
    }
    disc();
}
Student.prototype.toString=function(){
    return this.name+" "+this.age;
}
var student=new Student("Bills",23);
console.log(student.toString());

function Teacher(aName,aAge){
    var name=aName;
    var age=aAge;
    this.getName=function(){
        return name;
    }
    this.getAge=function(){
        return age;
    }
    this.setName=function(aName){
        name=aName;
    }
    this.setAge=function(aAge){
        age=aAge;
    }
    this.toString=function(){
        return name+" "+age;
    }
}
Teacher.clone=function(teacher){
    return new Teacher(
        teacher.getName(),teacher.getAge()
    );
}
var teacher=new Teacher("Bob",38);
console.log(teacher.toString());
teacher.setName("new Bob");
teacher.setAge(28);
console.log(teacher.toString());
var teacher1=Teacher.clone(teacher);
console.log(teacher1.toString());




function Person(){
    this.name=null;
}
Person.prototype.setName=function(name){
    this.name=name;
}
Person.prototype.toString=function(){
    return this.name;
}
function Manage(){
    this.sex=null;
}
Manage.prototype.setSex=function(sex){
    this.sex=sex;
}
Manage.prototype=new Person();
Manage.prototype.toString=function(){
    return this.name+" "+this.sex;
}
var manage=new Manage();
manage.setName("roseeeeeeeeeeeeee");
console.log(manage.toString());

function stopDefault(e){
    if(e&& e.preventDefault){
        e.preventDefault();
    }else{
        window.event.returnValue=false;
    }
    return false;
}
var iframe=document.getElementById("iframe");
var a=document.getElementsByTagName("a");
for(var i=0;i< a.length;i++){
    a[i].onclick=function(e){
        iframe.src=this.href;
        return stopDefault(e);
    };
}
var baidu = document.getElementsByClassName("baidu");
var type;
alert(type||"hello");