// javascript functions

//globals
var sections;
var userPosition;
var map;
var mapLoaded = false;

function addMarker(lat, lng, title) {
  var mark = new google.maps.Marker({
    position: new google.maps.LatLng(lat, lng),
    map: map,
    title: title
  });
}

function showLocationInfo(locations){
  var index;
  var newNumberListItem;
  var numberListValue;
  //get data from parsed json data
  var locationCount = locations.length;
  console.log("Received " + locationCount + " locations from json");

  var officeList = document.getElementById("office_list");

  for(index = 0; index < locationCount; index++){
    //create new li element
    newNumberListItem = document.createElement("li");

    //create new text node
    numberListValue = document.createTextNode(locations[index].address);

    //add text node to li element
    newNumberListItem.appendChild(numberListValue);

    //add new list element built in previous steps to unordered list
    //called numberList
    officeList.appendChild(newNumberListItem);

    addMarker(
        locations[index].lat,
        locations[index].lng,
        locations[index].title
    );
  }

}

function loadData(dataUrl) {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', dataUrl);

  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4) {
      if ((xhr.status >= 200 && xhr.status < 300) ||
          xhr.status === 304) {
        var jsonData = xhr.responseText;

        //parse the campaign data
        var locationData = JSON.parse(jsonData).locations;
        showLocationInfo(locationData);
      } else {
        console.log(xhr.statusText);
      }
    }
  };
  xhr.send();
}

function initMap(){
  //default to center of DC area if geo-location not available
  var center = new google.maps.LatLng(38.898013, -77.036539);
  if(userPosition){
    center = userPosition;
  }

  console.log("Center position: " + center);

  var mapOptions = {
    center: center,
    zoom: 10,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  map = new google.maps.Map(
      document.getElementById("locationMap"),
      mapOptions);

  loadData('data/locations.json');

}

function showSection(name){
  sections.forEach(function (item, index, array) {
    item.style.display = "none";
    if(item.id.indexOf(name) == 0){
      item.style.display = "";
    }
  });

  if (name == "locations" && !mapLoaded){
    initMap();
    google.maps.event.trigger(map, "resize");
    mapLoaded = true;
  }
}

function setLocation(position) {
  console.log("Geolocation Latitude: " + position.coords.latitude);
  console.log("Geolocation Longitude: " + position.coords.longitude);
  userPosition = new google.maps.LatLng(position.coords.latitude,
      position.coords.longitude);
  
}

function geoLoadFail() {
  console.log("Geolocation information not available or not authorized.");
  userPosition = null;
}

//init globals
function initPage(){
  sections = [
    document.getElementById("home_section"),
    document.getElementById("about_section"),
    document.getElementById("locations_section"),
    document.getElementById("media_section"),
    document.getElementById("contact_section")
  ];

  if(navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
        setLocation, geoLoadFail, {timeout:10000});
  } else {
    geoLoadFail();
  }
}

