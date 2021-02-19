// Class不存在变量提升,必须声明之后才能使用
class Point {
    // 构造方法
    constructor(x, y) {
        this.x = x
        this.y = y
    }

    // 类的所有方法都定义在类的prototype属性上面,都是不可枚举的
    toString() {
        return `x:${this.x} y:${this.y}`
    }
}

Object.assign(Point.prototype, {
    toAbsolute() {
        return new Point(this.x + 10, this.y + 10)
    }
});

console.log(Point === Point.prototype.constructor)
let point = new Point(12, 23)
console.log(point.toString())
console.log(point.toAbsolute().toString())

class ColorPoint extends Point {
    constructor(x, y, color) {
        super(x, y);
        this.color = color;
    }

    // ES6 Class内部只有静态方法，没有静态属性。
    static getClassName() {
        return ColorPoint.name
    }

    toString() {
        return `${super.toString()} color:${this.color}`
    }
}

let colorPoint = new ColorPoint(12, 22, 'red')
console.log(colorPoint.toString())
console.log(ColorPoint.getClassName())