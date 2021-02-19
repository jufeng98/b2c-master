/**
 * Created by yudong on 2017/6/20 0020.
 *
 * 可以通过window.frames[0] 或者 window.frames["topFrame"] 来引用左方的框架。不过，恐怕你最好使用top 而非 window 来引用这些
 * 框架（例如，通过 top.frames[0] ）。与 top 相对的另一个 window 对象是 parent。顾名思义， parent（父）对象始终指向当前框架的
 直接上层框架。在某些情况下， parent 有可能等于 top；但在没有框架的情况下， parent 一定等于top（此时它们都等于 window）。
 与框架有关的最后一个对象是 self，它始终指向 window；实际上， self 和 window 对象可以互换使用。引入 self 对象的目的只是为了与
 top 和 parent 对象对应起来，因此它不格外包含其他值。
 所有这些对象都是 window 对象的属性，可以通过 window.parent、 window.top 等形式来访问。同时，这也意味着可以将不同层次的 window
 对象连缀起来， 例如 window.parent.parent.frames[0] 。
 */
console.log(window.frames[0].name);
console.log(window.frames["iaframe"].name);
console.log(top.frames[0].name);
console.log(top.frames["iaframe"]);
console.log(frames[0]);
console.log(frames["iaframe"]);