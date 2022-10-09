navigator.geolocation.getCurrentPosition(function(position){
    console.log(position.coords.latitude, positions.coords.longitude);
}, function(error){
    console.log("Error code: " + error.code);
    console.log("Error message: " + error.message);
}, {
    enableHighAccuracy: true,
    timeout: 5000,
    maximumAge: 25000
});

var watchId = navigator.geolocation.watchPosition(function(position){
    console.log(position.coords.latitude, positions.coords.longitude);
}, function(error){
    console.log("Error code: " + error.code);
    console.log("Error message: " + error.message);
});
clearWatch(watchId);