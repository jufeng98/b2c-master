const BigNumber = require('bignumber.js')
let x = new BigNumber(123.4567)
let y = BigNumber('123456.7e-3')
let z = new BigNumber(x)
x.isEqualTo(y) && y.isEqualTo(z) && x.isEqualTo(z)      // true

x = new BigNumber('1111222233334444555566')
x.toString()                       // "1.111222233334444555566e+21"
x.toFixed()                        // "1111222233334444555566"

// Precision loss from using numeric literals with more than 15 significant digits.
new BigNumber(1.0000000000000001)         // '1'
new BigNumber(88259496234518.57)          // '88259496234518.56'
new BigNumber(99999999999999999999)       // '100000000000000000000'

// Precision loss from using numeric literals outside the range of Number values.
new BigNumber(2e+308)                     // 'Infinity'
new BigNumber(1e-324)                     // '0'

// Precision loss from the unexpected result of arithmetic with Number values.
new BigNumber(0.7 + 0.1)                  // '0.7999999999999999'

let a = new BigNumber(1011, 2)          // "11"
let b = new BigNumber('zz.9', 36)       // "1295.25"
a.plus(b)                       // "1306.25"

0.3 - 0.1                           // 0.19999999999999998
x = new BigNumber(0.3)
x.minus(0.1)                        // "0.2"
x;                                   // "0.3"

x.dividedBy(y).plus(z).times(9)
x.times('1.23456780123456789e+9')
    .plus(9876.5432321)
    .dividedBy('4444562598.111772')
    .integerValue()

x = new BigNumber(255.5)
x.toExponential(5)                  // "2.55500e+2"
x.toFixed(5)                        // "255.50000"
x.toPrecision(5)                    // "255.50"
x.toNumber()                        //  255.5
x.toString(16)                     // "ff.8"

y = new BigNumber('1234567.898765')
y.toFormat(2)                       // "1,234,567.90"