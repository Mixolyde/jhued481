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
      setLocation, geoLoadFail, {timeout:10000});
  } else {
    geoLoadFail();
  }

  initMap();
}

function initMap(){
  var center = new google.maps.LatLng(38.898013, -77.036539);
  if(userPosition){
    center = userPosition;
  }

  console.log("Center position: " + center);

  var mapOptions = {    
    center: center,
    zoom: 8 }; 
  map = new google.maps.Map(
    document.getElementById("locationMap"),
    mapOptions); 

  addMarker(38.885492, -77.097200, "Arlington, VA"); 
  addMarker(38.916833, -77.226124, "McLean, VA"); 
  addMarker(38.998082, -76.910128, "Greenbelt, MD"); 
}

function addMarker(lat, lng, title) {
  var mark = new google.maps.Marker({
    position: new google.maps.LatLng(lat, lng),
    map: map,
    title: title
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
  console.log("Latitude: " + position.coords.latitude);
  console.log("Longitude: " + position.coords.longitude);
  userPosition = position;
  
}

function geoLoadFail() {
  console.log("Geolocation information not available or not authorized.");
  userPosition = null;
} 
