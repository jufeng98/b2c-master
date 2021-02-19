console.log(Math.random());
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
});
var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于 ' + new Date().toLocaleString()
    }
});
var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: true,
        score: 90
    }
});
var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            {text: '学习 JavaScript'},
            {text: '学习 Vue'},
            {text: '整个牛项目'}
        ],
        person: {
            name: '梁煜东',
            nickName: 'jufeng98'
        }
    }
});
var app5 = new Vue({
    el: '#app-5',
    data: {
        message: 'Hello Vue.js!'
    },
    methods: {
        reverseMessage: function () {
            this.message = this.message.split('').reverse().join('')
        }
    }
});
var app6 = new Vue({
    el: '#app-6',
    data: {
        message: 'Hello Vue!'
    }
});

// 定义名为 need-item 的新组件
Vue.component('need-item', {
    template: '<li>这是个待办项</li>'
});
var appS = new Vue({
    el: '#app-S',
    data: {
        message: 'Hello Vue!'
    }
});

// 定义一个名为 button-counter 的新组件
Vue.component('button-counter', {
    //这里必须是一个函数,否则所有实例会共享这个data
    data: function () {
        return {
            count: 0
        }
    },
    template: '<button v-on:click="count++">You clicked me {{ count }} times.</button>'
});
var appK = new Vue({
    el: '#app-K'
});

Vue.component('blog-post', {
    props: ['title'],
    template: '<h3>{{ title }}<slot></slot></h3>'
});
var appG = new Vue({
    el: '#app-G',
    data: {
        posts: [
            {id: 1, title: 'My journey with Vue'},
            {id: 2, title: 'Blogging with Vue'},
            {id: 3, title: 'Why Vue is so fun'}
        ],
        postFontSize: 1
    }
});

Vue.component('blog-post-multi', {
    props: ['title'],
    template: '<div><header>{{ title }}<slot name="slot1"></slot></header>' +
        '<section>{{ title }}<slot></slot></section>' +
        '<footer>{{ title }}<slot name="slot2">default text</slot></footer></div>'
});
var appL = new Vue({
    el: '#app-L'
});

Vue.component('custom-input', {
    props: ['value'],
    template: `
    <input
      v-bind:value="value"
      v-on:input="$emit('input', $event.target.value)"
    >
  `
});
var appH = new Vue({
    el: '#app-H',
    data: {
        searchText: ''
    }
});

Vue.component('todo-item-in', {
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
});
var app7 = new Vue({
    el: '#app-7',
    data: {
        groceryList: [
            {id: 0, text: '蔬菜'},
            {id: 1, text: '奶酪'},
            {id: 2, text: '随便其它什么人吃的东西'}
        ]
    }
});

Vue.component('todo-item-slot', {
    props: ['todo'],
    template: '<li v-bind:key="todo.id">' +
        '        <slot v-bind:todo="todo">' +
        '          {{ todo.text }}' +
        '        </slot>' +
        '      </li>'
});
var app7a = new Vue({
    el: '#app-7a',
    data: {
        groceryList: [
            {id: 0, text: '蔬菜', isComplete: false},
            {id: 1, text: '奶酪', isComplete: false},
            {id: 2, text: '随便其它什么人吃的东西', isComplete: true}
        ]
    }
});

var app8 = new Vue({
    el: '#app-8',
    data: {
        rawHtml: '<span style="color:red;">this is red</span>'
    }
});

var app9 = new Vue({
    el: '#app-9',
    data: {
        liId: 'uniqueLi',
        liDisabled: 'disabled'
    }
});

var app10 = new Vue({
    el: '#app-10',
});

var app11 = new Vue({
    el: '#app-11',
    methods: {
        showCurrentText: function (word, event) {
            let clickText = event.target.innerHTML;
            let text = `text:${word} ${clickText}`;
            alert(text);
        }
    }
});

var app12 = new Vue({
    el: '#app-12',
    data: {
        firstName: "梁",
        lastName: "煜东",
        name: "梁煜东"
    },
    watch: {
        firstName: function (val) {
            this.name = val + " " + this.lastName;
        },
        lastName: function (val) {
            this.name = this.firstName + " " + val;
        }
    },
    computed: {
        fullName: {
            get() {
                return this.firstName + ' ' + this.lastName;
            },
            set(newValue) {
                this.firstName = newValue.split(" ")[0];
                this.lastName = newValue.split(" ")[1];
            }
        }
    }
});

var app13 = new Vue({
    el: '#app-13',
    data: {
        question: '',
        answer: 'I cannot give you an answer until you ask a question!'
    },
    watch: {
        question: function (newQuestion, oldQuestion) {
            this.answer = 'Waiting for you to stop typing...';
            //做点什么好呢
        }
    },
});

var app13a = new Vue({
    el: '#app-13-a',
    data: {
        message: '',
        multiMessage: '',
        checked: false,
        checkedNames: [],
        picked: 'One',
        selected: '',
        selectedd: 'A',
        options: [
            {text: 'One', value: 'A'},
            {text: 'Two', value: 'B'},
            {text: 'Three', value: 'C'}
        ],
        age: 12
    }
});

var app14 = new Vue({
    el: '#app-14',
    data: {
        activeRed: true,
        activeGreen: false,
        classObj: {
            red: false,
            green: true
        }
    },
    computed: {
        classObject() {
            return {
                activeRed: !this.activeGreen,
                newClass: 'newCla'
            }
        }
    }
});

var app15 = new Vue({
    el: '#app-15',
    data: {
        loginType: 'username'
    },
    methods: {
        change() {
            this.loginType = this.loginType === 'username' ? 'email' : 'username';
        }
    }
});

var app16 = new Vue({
    el: '#app-16',
    data: {
        ok: true
    },
    methods: {
        change() {
            this.ok = !this.ok;
        }
    }
});

var componentA = {
    props: ['title'],
    template: '<div>子组件A的title属性:{{title}}</div>'
};
var app17 = new Vue({
    el: '#app-17',
    components: {
        'component-a': componentA
    }
});

Vue.component('my-component', {
    props: {
        // 基础的类型检查 (`null` 匹配任何类型)
        propA: Number,
        // 多个可能的类型
        propB: [String, Number],
        // 必填的字符串
        propC: {
            type: String,
            required: true
        },
        // 带有默认值的数字
        propD: {
            type: Number,
            default: 100
        },
        // 带有默认值的对象
        propE: {
            type: Object,
            // 对象或数组默认值必须从一个工厂函数获取
            default: function () {
                return {message: 'hello'}
            }
        },
        // 自定义验证函数
        propF: {
            validator: function (value) {
                // 这个值必须匹配下列字符串中的一个
                return ['success', 'warning', 'danger'].indexOf(value) !== -1
            }
        },
        propG: Function
    },
    methods: {
        passParamToParent() {
            this.$emit('changemsg', '来自子组件的内容');
        },
    },
    mounted() {
        console.log(this.$attrs['prop-h']);
    },
    template: `
        <div>
          <p :class="propC">{{propF}}</p>
          <button @click="passParamToParent">传递参数给父组件</button>
          <button @click="propG">触发父组件函数</button>
        </div>
`
});
var app18 = new Vue({
    el: '#app-18',
    data() {
        return {
            msg: 'hello world'
        }
    },
    methods: {
        parentFunc() {
            alert("由子组件调用触发的父组件函数")
        }
    }
});

var app19 = new Vue({
    el: '#app-19',
    data: {
        date: 1542461415308
    },
    computed: {
        time() {
            return Date.now();
        }
    },
    filter: {
        formatDate() {
        }
    }
});

var app20 = new Vue({
    el: '#app-20',
    computed: {
        time() {
            return Date.now();
        }
    },
    template: '<div>当前时间:{{ time }}</div>'
});
var app21 = new Vue({
    el: '#app-21',
    computed: {
        time() {
            return Date.now();
        }
    },
    render(h) {
        return h('div', "当前时间:" + this.time);
    }
});

var app22 = new Vue({
    el: '#app-22',
    data() {
        return {
            msg: '动态参数',
            attr: 'id',
            attrValue: 'dynamic',
            msg1: '动态事件',
            attr1: 'click',
            name: '',
            showName: true,
        }
    },
    watch: {
        name() {
            console.log(this.name);
        },
    },
    beforeCreate() {
        console.log('beforeCreate')
    },
    created() {
        console.log('created')
    },
    beforeMount() {
        console.log('beforeMount')
    },
    mounted() {
        console.log('mounted')
    },
    beforeUpdate() {
        console.log('beforeUpdate')
    },
    updated() {
        console.log('updated')
    },
    beforeDestroy() {
        console.log('beforeDestroy')
    },
    destroyed() {
        console.log('destroyed')
    },
});

// 局部注册组件
var component1 = {
    template: '<h2>组件一</h2>',
    mounted() {
        console.log('组件一')
    }
};
var component2 = {
    template: '<h2>组件二</h2>',
    mounted() {
        console.log('组件二')
    }
};
var component3 = {
    template: '<h2>组件三</h2>',
    mounted() {
        console.log('组件三')
    }
};
var app23 = new Vue({
    el: '#app-23',
    components: {
        component1,
        component2,
        component3,
    },
    data() {
        return {
            componentName: 'component1'
        }
    },
    methods: {
        tabClick(event) {
            this.componentName = event.target.innerText;
        },
    },
});

Vue.component('base-checkbox', {
    model: {
        prop: 'checked',
        event: 'change'
    },
    props: {
        checked: Boolean
    },
    template: `
    <input
      type="checkbox"
      v-bind:checked="checked"
      v-on:change="$emit('change', $event.target.checked)"
    >
  `
});
var app24 = new Vue({
    el: '#app-24',
    data() {
        return {
            lovingVue: ''
        }
    },
    watch: {
        lovingVue() {
            console.log(this.lovingVue);
        }
    }
});


Vue.component('sync-component', {
    data() {
        return {
            index: 0
        }
    },
    methods: {
        getIndex() {
            return this.index;
        },
        click() {
            this.$emit('update:title', '点击次数sync:' + ++this.index);
            this.$emit('update:title1', '点击次数sync:' + this.index);
        }
    },
    template: ` <button @click="click">点击我.sync</button>`
});
var app25 = new Vue({
    el: '#app-25',
    data() {
        return {
            title: 'hello world',
            title1: 'hello world',
            count: '',
        }
    },
    methods: {
        touchSub() {
            this.count = this.$refs.subRef.getIndex();
        },
    }
});

Vue.component('sub-component1', {
    provide: function () {
        return {
            getSubComponent1Value: this.getSubComponent1Value
        }
    },
    data() {
        return {
            subComponent1Value: 'subComponent1Value'
        }
    },
    methods: {
        getSubComponent1Value() {
            return this.subComponent1Value;
        },
    },
    mounted() {
        console.log(this.$root.topParentValue);
        console.log(this.$parent.topParentValue);
    },
    template: ` 
         <div>
          <div>sub-component1</div>
          <slot></slot>
         </div>
    `
});
Vue.component('sub-component2', {
    // 依赖注入
    inject: ["getSubComponent1Value"],
    data() {
        return {
            parentValue: '',
            subComponent2Value: 'subComponent2Value'
        }
    },
    methods: {
        click() {
            this.parentValue = this.getSubComponent1Value();
        },
    },
    mounted() {
        console.log(this.$root.topParentValue);
        console.log(this.$parent.$parent.topParentValue);
    },
    template: ` 
          <div>
            sub-component2
            <button @click="click">点击调用依赖注入的方法:{{parentValue}}</button>
          </div>
    `
});
var app26 = new Vue({
    el: '#app-26',
    data() {
        return {
            topParentValue: 'top parent value',
        }
    },
    methods: {}
});