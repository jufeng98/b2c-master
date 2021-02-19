// 定义一个混入对象
var myMixin = {
    created: function () {
        this.hello()
    },
    methods: {
        hello: function () {
            console.log('hello from mixin!')
        }
    }
};
// 定义一个使用混入对象的组件
var Component = Vue.extend({
    mixins: [myMixin]
});
var component = new Component() // => "hello from mixin!"

var mixin = {
    data: function () {
        return {
            message: 'hello',
            foo: 'abc'
        }
    }
};
new Vue({
    mixins: [mixin],
    data: function () {
        return {
            message: 'goodbye',
            bar: 'def'
        }
    },
    created: function () {
        console.log(this.$data) // => { message: "goodbye", foo: "abc", bar: "def" }
    }
});

mixin = {
    created: function () {
        console.log('混入对象的钩子被调用')
    }
};
new Vue({
    mixins: [mixin],
    created: function () {
        console.log('组件钩子被调用')
    }
});

mixin = {
    methods: {
        foo: function () {
            console.log('foo')
        },
        conflicting: function () {
            console.log('from mixin')
        }
    }
};
var vm = new Vue({
    mixins: [mixin],
    methods: {
        bar: function () {
            console.log('bar')
        },
        conflicting: function () {
            console.log('from self')
        }
    }
});
vm.foo(); // => "foo"
vm.bar(); // => "bar"
vm.conflicting();// => "from self"

// 注册一个全局自定义指令 `v-focus`
Vue.directive('focus', {
    // 当被绑定的元素插入到 DOM 中时……
    inserted: function (el) {
        // 聚焦元素
        el.focus()
    }
});
var app1 = new Vue({
    el: '#app-1',
    // 注册局部指令 v-focus1
    directives: {
        focus1: {
            // 指令的定义
            inserted: function (el) {
                el.focus()
            }
        }
    },
    data() {
        return {}
    },
    methods: {},
});

Vue.directive('demo', {
    bind: function (el, binding, vnode) {
        var s = JSON.stringify
        el.innerHTML =
            'name: ' + s(binding.name) + '<br>' +
            'value: ' + s(binding.value) + '<br>' +
            'expression: ' + s(binding.expression) + '<br>' +
            'argument: ' + s(binding.arg) + '<br>' +
            'modifiers: ' + s(binding.modifiers) + '<br>' +
            'vnode keys: ' + Object.keys(vnode).join(', ')
    }
});
var app2 = new Vue({
    el: '#app-2',
    data() {
        return {
            message: 'hello world'
        }
    },
    methods: {},
});

Vue.directive('pin', {
    bind: function (el, binding, vnode) {
        el.style.position = 'fixed'
        var s = (binding.arg === 'left' ? 'left' : 'top')
        el.style[s] = binding.value + 'px'
    }
});
var app3 = new Vue({
    el: '#app-3',
    data: function () {
        return {
            direction: 'left'
        }
    }
});

Vue.component('anchored-heading', {
    render: function (createElement) {
        return createElement(
            'h' + this.level,   // 标签名称
            this.$slots.default // 子节点数组
        )
    },
    props: {
        level: {
            type: Number,
            required: true
        }
    }
});
var app4 = new Vue({
    el: '#app-4',
    data: function () {
        return {}
    }
});

var getChildrenTextContent = function (children) {
    return children.map(function (node) {
        return node.children
            ? getChildrenTextContent(node.children)
            : node.text
    }).join('')
}
Vue.component('anchored-heading', {
    render: function (createElement) {
        // 创建 kebab-case 风格的 ID
        var headingId = getChildrenTextContent(this.$slots.default)
            .toLowerCase()
            .replace(/\W+/g, '-')
            .replace(/(^-|-$)/g, '')
        return createElement(
            'h' + this.level,
            [
                createElement('a', {
                    'class': {
                        'a-foo': true,
                    },
                    style: {
                        borderTopColor: 'red'
                    },
                    attrs: {
                        name: headingId,
                        href: '#' + headingId
                    },
                    key: 'myKey',
                    ref: 'myRef',
                }, this.$slots.default)
            ]
        )
    },
    props: {
        level: {
            type: Number,
            required: true
        }
    }
})
var app5 = new Vue({
    el: '#app-5',
    data: function () {
        return {}
    }
});