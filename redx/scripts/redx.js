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

function loadData(dataUrl, rootElement, target){
  $.ajax({
    url: dataUrl,
    type: 'GET',
    dataType: 'json'
  }).done(function(data) {
    $.each(data[rootElement], function (index) {
      target.append("<li>" + data[rootElement][index].address + "</li>");

      addMarker(
          data[rootElement][index].lat,
          data[rootElement][index].lng,
          data[rootElement][index].title
      );
    });
  }).fail(function (jqHXR, textStatus) {
    if (textStatus === 'parsererror') {
      console.log("Requested JSON parse failed.");
    } else if (textStatus === 'abort') {
      console.log("Ajax request was aborted");
    } else {
      console.log('Error status code:' + jxHXR.status);
    }
  });
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
      $("#locationMap").get(0),
      mapOptions);

  loadData('data/locations.json', 'locations', $("#office_list"));

}

function showSection(name){
  sections.each(function(index ){
    $(this).hide();
    if($(this).attr('id').indexOf(name) == 0){
      $(this).show();
    }
  });

  if (name == "locations" && !mapLoaded){
    initMap();
    google.maps.event.trigger(map, "resize");
    mapLoaded = true;
  }
}

function setLocation(position) {
  console.log("Geolocation Lat/Long: " + position.coords.latitude + ":" + position.coords.longitude);
  userPosition = new google.maps.LatLng(position.coords.latitude,
      position.coords.longitude);
  
}

function geoLoadFail() {
  console.log("Geolocation information not available or not authorized.");
  userPosition = null;
}

//init globals
function initPage(){
  sections =  $("section[id$='_section']");

  if(navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
        setLocation, geoLoadFail, {timeout:10000});
  } else {
    geoLoadFail();
  }
}

