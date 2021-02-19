/**
 * Created by yu on 2018/3/20.
 */
var book = {
    "title": "Professional JavaScript",
    "authors": [
        "Nicholas C. Zakas"
    ],
    edition: 3,
    year: 2011
};
console.log(JSON.stringify(book, ["title", "edition"]));

var jsonText = JSON.stringify(book, function (key, value) {
    switch (key) {
        case "authors":
            return value.join(",")
        case "year":
            return 5000;
        case "edition":
            return undefined;
        default:
            return value;
    }
});
console.log(jsonText);
jsonText = JSON.stringify(book, null, 4);
console.log(jsonText);
jsonText = JSON.stringify(book, null, "--");
console.log(jsonText);

book = {
    "title": "Professional JavaScript",
    "authors": [
        "Nicholas C. Zakas"
    ],
    edition: 3,
    year: 2011,
    releaseDate: new Date(2011, 11, 1)
};
jsonText = JSON.stringify(book);
var bookCopy = JSON.parse(jsonText, function (key, value) {
    if (key == "releaseDate") {
        return new Date(value);
    } else {
        return value;
    }
});
console.log(bookCopy.releaseDate.getFullYear());