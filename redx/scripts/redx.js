// javascript functions

//globals
var sections;
var userPosition;
var map;

//init globals
function initPage(){
  sections = [
    document.getElementById("home_section"),
    document.getElementById("about_section"),
    document.getElementById("locations_section"),
    document.getElementById("media_section"),
    document.getElementById("contact_section"),
    ];

    if(navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        setLocation, fail, {timeout:10000}); 
    } else {
      fail();
    }

    var mapOptions = {    
      center: new google.maps.LatLng(38.898013, -77.036539),
      zoom: 9 }; 
    map = new google.maps.Map(
      document.getElementById("locationMap"),
      mapOptions); 
    initMap();
}

function initMap(){
  var marker = new google.maps.Marker({
    position: new google.maps.LatLng(38.898013, -77.036539),
    map: map,
    title: "Our Locations"
    });
}

function showSection(name){
  sections.forEach(function (item, index, array) {
    item.style.display = "none";
    if(item.id.indexOf(name) == 0){
      item.style.display = "";
    }
  });
}

function setLocation(position) {
  console.log("Longitude: " + position.coords.longitude);
  console.log("Latitude: " + position.coords.latitude);
  userPosition = position;
}

function fail() {
  console.log("Geolocation information not available or not authorized.");
  userPosition = null;
} 
