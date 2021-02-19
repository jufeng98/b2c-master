let uuid = Symbol("uuid")
console.log(uuid)
// Symbol值可以作为标识符，用于对象的属性名，保证不会出现同名的属性
let person = {[uuid]: "156441654646131", name: "Jack"}
console.log(person[uuid])

let log = {}
log.levels = {
    DEBUG: Symbol('debug'),
    INFO: Symbol('info'),
    WARN: Symbol('warn')
};
console.log(log.levels.DEBUG)