// javascript functions

//globals
var sections;
//init globals
function initPage(){
  sections = [
    document.getElementById("home_section"),
    document.getElementById("about_section"),
    document.getElementById("locations_section"),
    document.getElementById("media_section"),
    document.getElementById("contact_section"),
    ]

}

function showSection(name){
  sections.forEach(function (item, index, array) {
      item.style.display = "none";
      if(item.id.indexOf(name) == 0){
        item.style.display = "";
      }
  });
}
