TYPE = {
    STRING: 'String',
    NUMBER: 'Number',
    BOOLEAN: 'Boolean',
    SYMBOL: 'Symbol',
    _map: null,
    contain(key) {
        if (this._map === null) {
            this._map = new Map();
            for (let arr of Object.entries(TYPE)) {
                this._map.set(arr[1], arr[1])
            }
        }
        return this._map.get(key) !== undefined
    }
}

class Person {
    constructor(name, age) {
        this._name = name
        this._age = age
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }

    get age() {
        return this._age;
    }

    set age(value) {
        this._age = value;
    }
}

let p = new Person('Rose', 22)
console.log(p.name)

let proxy = new Proxy(p, {
    get: function (target, property) {
        console.log(`${target.constructor.name}的${property}属性被读取,值为${target[property]}`)
        return target[property];
    },
    set: function (target, property, newValue) {
        console.log(`${target.constructor.name}的${property}属性被设置为${newValue},旧值为:${target[property]}`)
        target[property] = newValue
        return property
    },
})
proxy.name = 'Jack'
console.log(proxy.name)

let genericTypeArr = creatArray(String.name, 'Mice')
console.log(genericTypeArr[0])
genericTypeArr.push('Martin')
console.log(genericTypeArr)

// genericTypeArr.push(1)

let personTypeArr = creatArray(Person.name, p)
console.log(personTypeArr[0])
personTypeArr.push(p)
console.log(personTypeArr)

// personTypeArr.push(1)

function creatArray(type, ...values) {
    let pattern = /\d+/;
    let handler = {
        get(target, propKey, proxy) {
            if (typeof propKey != 'symbol' && pattern.test(propKey)) {
                if (parseInt(propKey) < 0 || parseInt(propKey) >= target.length) {
                    throw new RangeError(`数组越界错误,数组大小:${target.length},当前访问的下标:${parseInt(propKey)}`);
                }
            }
            return target[propKey]
        },
        set(target, propKey, newValue) {
            let tmp = newValue.constructor.name
            if (typeof propKey != 'symbol' && pattern.test(propKey)) {
                if (!TYPE.contain(type)) {
                    if (newValue.constructor.name !== type) {
                        throw new Error(`数组只能存放${type}类型对象`);
                    }
                } else if (tmp !== type) {
                    throw new Error(`数组只能存放${type}类型数据`);
                }

            }
            target[propKey] = newValue
            return propKey
        }
    }
    return new Proxy([...values], handler)
}

Object.defineProperty(p, '_company', {
    configurable: true,
    enumerable: true,
    value: 'bluemoon',
    writable: true
})
console.log(p)
Reflect.defineProperty(p, '_department', {
    configurable: true,
    enumerable: true,
    value: 'tech',
    writable: true
})
console.log(p)

console.log('_company' in p)
console.log(Reflect.has(p, '_department'))

console.log(p.name)
console.log(Reflect.get(p, '_name'))

delete p._department
Reflect.deleteProperty(p, '_department')
console.log(p)

p.name = 'Mario'
Reflect.set(p, '_name', 'Mayer')
console.log(p)

console.log(Function.prototype.apply.call(Math.floor, undefined, [1.75]))
console.log(Reflect.apply(Math.floor, undefined, [1.75]))

let person = Reflect.construct(Person, ['yu', '22'])
console.log(person)

console.log(Object.getPrototypeOf(person))
console.log(Reflect.getPrototypeOf(person))