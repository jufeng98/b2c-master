/**
 * Created by yudong on 2017/6/15 0015.
 *
 逻辑与操作可以应用于任何类型的操作数，而不仅仅是布尔值。在有一个操作数不是布尔值的情况下，逻辑与操作就不一定返回布尔值；此时，
 它遵循下列规则：
  如果第一个操作数是对象，则返回第二个操作数；
  如果第二个操作数是对象，则只有在第一个操作数的求值结果为 true 的情况下才会返回该对象；
  如果两个操作数都是对象，则返回第二个操作数；
  如果有一个操作数是 null，则返回 null；
  如果有一个操作数是 NaN，则返回 NaN；
  如果有一个操作数是 undefined，则返回 undefined。

 与逻辑与操作相似，如果有一个操作数不是布尔值，逻辑或也不一定返回布尔值；此时，它遵循下列规则：
  如果第一个操作数是对象，则返回第一个操作数；
  如果第一个操作数的求值结果为 false，则返回第二个操作数；
  如果两个操作数都是对象，则返回第一个操作数；
  如果两个操作数都是 null，则返回 null；
  如果两个操作数都是 NaN，则返回 NaN；
  如果两个操作数都是 undefined，则返回 undefined。
 */
console.log({} && false);
console.log({} && {a: 1});
console.log({} && null);
console.log(NaN && {});
console.log({} && undefined);

console.log({} || false);
console.log(true || {a: 1});
console.log({} || null);
console.log(NaN || 2);
console.log({} || undefined);