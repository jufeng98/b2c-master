const Counter = {
    data() {
        return {
            counter: 0,
            message: 'mouse hover to see hello world',
            text: '这是文本',
            name: 'hello vue'
        }
    },
    methods: {
        reverseText() {
            this.text = this.text
                .split('')
                .reverse()
                .join('')
        }
    },
    mounted() {
        setInterval(() => {
            this.counter++
        }, 1000)
    }
}

Vue.createApp(Counter).mount('#counter')