class Db {
    constructor(vue) {
        this.vue = vue;
        this.indexedDB = null;
        this.database = null;

        this.storeName = null;
        this.indexKeyName = null;

        this.initIndexedDB();

        Db.IDBTransaction = {
            READ_WRITE: "readwrite"
        };
    }

    initIndexedDB() {
        this.indexedDB = window.indexedDB || window.msIndexedDB || window.mozIndexedDB || window.webkitIndexedDB;
    }

    initDbAndStore(dbName, storeName, keyPath, indexKeyName) {
        this.storeName = storeName;
        this.indexKeyName = indexKeyName;
        let request = this.indexedDB.open(dbName, "2");
        request.onerror = event => {
            this.vue.$notify.info({
                title: '提示', message: "Something bad happened while trying to open: " + event.target.errorCode
            });
        };
        request.onsuccess = event => {
            this.database = event.target.result;
            this.vue.$notify.info({
                title: '提示', message: "Database already initialized. Database name: " + this.database.name +
                    ", Version: " + this.database.version
            });
            this.database.onversionchange = () => this.database.close();
        };
        request.onupgradeneeded = event => {
            this.database = event.target.result;
            this.personsObjectStore = this.database.createObjectStore(storeName, {
                keyPath: keyPath
            });
            this.personsObjectStore.createIndex(indexKeyName, indexKeyName, {unique: false});
        }
    }

    saveObject(obj) {
        let transaction = this.database.transaction(this.storeName, Db.IDBTransaction.READ_WRITE);
        let store = transaction.objectStore(this.storeName);
        let request = store.put(obj);
        return new Promise((resolve, reject) => {
            request.onerror = error => {
                reject(error);
            };
            request.onsuccess = () => {
                resolve(obj);
            };
        })
    }

    getObject(keyPath) {
        let transaction = this.database.transaction(this.storeName, Db.IDBTransaction.READ_WRITE);
        let request = transaction.objectStore(this.storeName).get(keyPath);

        return new Promise((resolve, reject) => {
            request.onerror = event => {
                reject(event);
            };
            request.onsuccess = event => {
                let result = event.target.result;
                resolve(result);
            };
        });
    }

    getObjectsByIndex() {
        let transaction = this.database.transaction(this.storeName, Db.IDBTransaction.READ_WRITE);
        let request = transaction.objectStore(this.storeName).index(this.indexKeyName).openCursor();

        let list = [];
        return new Promise((resolve, reject) => {
            request.onerror = event => {
                reject(event);
            };
            request.onsuccess = event => {
                const cursor = event.target.result;
                //必须要检查
                if (cursor) {
                    list.push(cursor.value);
                    //移动到下一项
                    cursor.continue();
                } else {
                    resolve(list);
                }
            };
        });
    }

    delObject(keyPath) {
        let transaction = this.database.transaction(this.storeName, Db.IDBTransaction.READ_WRITE);
        let request = transaction.objectStore(this.storeName).delete(keyPath);

        return new Promise((resolve, reject) => {
            request.onerror = event => {
                reject(event);
            };
            request.onsuccess = event => {
                resolve(event.type);
            };
        });
    }

    delCursorObject(keyPath) {
        let transaction = this.database.transaction(this.storeName, Db.IDBTransaction.READ_WRITE);
        let request = transaction.objectStore(this.storeName).openCursor();

        return new Promise((resolve, reject) => {
            request.onerror = event => {
                reject(event);
            };
            request.onsuccess = event => {
                const cursor = event.target.result;
                //必须要检查
                if (cursor) {
                    if (cursor.key === keyPath) {
                        let delRequest = cursor.delete();
                        delRequest.onerror = event => {
                            reject(event);
                        };
                        delRequest.onsuccess = event => {
                            resolve(event.type);
                        }
                    }
                }
            };
        });
    }

    updateCursorObject(keyPath, key, newValue) {
        let transaction = this.database.transaction(this.storeName, Db.IDBTransaction.READ_WRITE);
        let request = transaction.objectStore(this.storeName).openCursor();

        return new Promise((resolve, reject) => {
            request.onerror = event => {
                reject(event);
            };
            request.onsuccess = event => {
                const cursor = event.target.result;
                //必须要检查
                if (cursor) {
                    if (cursor.key === keyPath) {
                        let value = cursor.value;
                        value[key] = newValue;
                        let delRequest = cursor.update(value);
                        delRequest.onerror = event => {
                            reject(event);
                        };
                        delRequest.onsuccess = event => {
                            resolve(event.type);
                        }
                    }
                }
            };
        });
    }

    getAllObjects() {
        let transaction = this.database.transaction(this.storeName, Db.IDBTransaction.READ_WRITE);
        let request = transaction.objectStore(this.storeName).openCursor();
        let list = [];
        return new Promise((resolve, reject) => {
            request.onerror = event => {
                reject(event);
            };
            request.onsuccess = event => {
                const cursor = event.target.result;
                //必须要检查
                if (cursor) {
                    list.push(cursor.value);
                    //移动到下一项
                    cursor.continue();
                } else {
                    resolve(list);
                }
            };
        });
    }

}