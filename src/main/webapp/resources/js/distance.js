/**
 * @author Ruslan Lazin
 */

insertDistanceAndFlightTime();

function insertDistanceAndFlightTime() {

    const AVERAGE_SPEED = 800; //  km/hour
    var departLatitude = document.getElementById('depart_latitude').value;
    var departLongitude = document.getElementById('depart_longitude').value;
    var destLatitude = document.getElementById('dest_latitude').value;
    var destLongitude = document.getElementById('dest_longitude').value;

    //if coordinates incorrect do nothing
    if (!isCoordinatesCorrect(departLatitude, departLongitude)) return;
    if (!isCoordinatesCorrect(destLatitude, destLongitude))  return;

    // otherwise insert results in fields
    var distance = Math.ceil(getDistanceBetweenCoordinatesInKm(
        departLatitude, departLongitude,
        destLatitude, destLongitude));

    document.getElementById('distance').innerHTML = distance.toString();
    document.getElementById('flightTime').innerHTML = (Math.ceil(distance / AVERAGE_SPEED)).toString();
}
//checks is coordinates are correct
function isCoordinatesCorrect(latitude, longitude) {

    //Latitude can be from -90 to 90 degree. Longitude from -180 to 180.
    if (Math.abs(latitude) >= 90) return false;
    if (Math.abs(longitude) >= 180) return false;

    // There are no airports on the equator, or Greenwich meridian
    // so probably is't just an incorrect zero value
    if (Math.abs(latitude) < 0.1) return false;
    if (Math.abs(longitude) < 0.1) return false;
    return true;
}
//returns the approximated distance between departure and destinations in km.
function getDistanceBetweenCoordinatesInKm(lat1, lon1, lat2, lon2) {
    var R = 6371; // Radius of the earth in km
    var deltaLat = deg2rad(lat2 - lat1);
    var deltaLon = deg2rad(lon2 - lon1);
    var a =
            Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
            Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
            Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2)
        ;
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    var distance = R * c; // Distance in km
    return distance;
}
//converts degrees to radians
function deg2rad(deg) {
    return deg * (Math.PI / 180);
}



